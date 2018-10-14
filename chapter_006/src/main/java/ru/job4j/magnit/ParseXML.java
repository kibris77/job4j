package ru.job4j.magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализует парсинг XML файла.
 */
public class ParseXML {
    private String filename;
    private final List<Integer> list = new ArrayList<>();

    public ParseXML(String filename) {
        this.filename = filename;
    }

    /**
     * Метод парсит XML файл и возращает сумму элементов.
     * @return
     */
    public int parse() {
        int result = 0;
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(new File(filename), new DefaultHandler() {
                    @Override
                        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                            if (qName.equals("entry")) {
                                String num = attributes.getValue("href");
                                list.add(Integer.parseInt(num));
                            }
                    }
            });
            for (int i = 0; i < list.size(); i++) {
                result += list.get(i);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ParseXML parseXML = new ParseXML("/home/alexander/database/newstore.xml");
        System.out.println(parseXML.parse());
    }
}
