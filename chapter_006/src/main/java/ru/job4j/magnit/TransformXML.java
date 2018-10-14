package ru.job4j.magnit;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Класс трансофрмирующий XML файл через XSLS.
 */
public class TransformXML {
    private String source;
    private String dest;
    private String schema;

    public TransformXML(String source, String dest, String schema) {
        this.source = source;
        this.dest = dest;
        this.schema = schema;
    }

    /**
     * Метод трансформирует XML файл из одного формата в другой.
     */
    public void transform() {
        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(new File(schema)));
            transformer.transform(new StreamSource(new File(source)), new StreamResult(new File(dest)));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "/home/alexander/database/store.xml";
        String dest = "/home/alexander/database/newstore.xml";
        String shema = "/home/alexander/database/storeshema.xsl";
        TransformXML transformXML = new TransformXML(source, dest, shema);
        transformXML.transform();
    }
}
