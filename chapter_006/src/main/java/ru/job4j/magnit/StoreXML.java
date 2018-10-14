package ru.job4j.magnit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс формирует XML файл на основе данных в базе данных.
 */
public class StoreXML implements AutoCloseable {
    private String target;
    private Connection connection;


    public StoreXML(String target) {
        this.target = target;
        Config config = new Config();
        connection = config.connectToDB();
    }

    /**
     * Метод возвращющий список элементов типа Entry из базы.
     * @return - список элементов.
     */
    private Entries getEntries() {
        Entries entries = new Entries();
        try (Statement statement = connection.createStatement()) {
            ResultSet set = statement.executeQuery("SELECT * FROM entry");
            while (set.next()) {
                Entry entry = new Entry();
                entry.setField(set.getInt(1));
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    /**
     * Метод формирует из списка элементов Entries XML файл.
     */
    public void safeToXML() {
        Entries entries = getEntries();
        try {
            System.out.println(entries);
            JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, new File(target));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        StoreXML storeXML = new StoreXML("/home/alexander/database/store.xml");
        storeXML.safeToXML();
    }

    /**
     * Внутренний клас обертка для списка элементов типа Entry.
     */
    @XmlRootElement
    public static class Entries {
        private List<Entry> values = new ArrayList<>();

        public Entries() {
        }

        /**
         * Добавляет элемент в список.
         * @param entry - элемент.
         */
        public void add(Entry entry) {
            values.add(entry);
        }

        @XmlElement
        public List<Entry> getValues() {
            return values;
        }

        @Override
        public String toString() {
            return "Entries{list=" + values + '}';
        }
    }

    /**
     * Внктренний класс описывающий элемент для формирования XML.
     */
    @XmlRootElement
    public static class Entry {
        private int field;

        public Entry() {
        }

        @XmlElement
        public int getField() {
            return field;
        }

        public void setField(int field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return "Entry{field=" + field + "}";
        }
    }
}
