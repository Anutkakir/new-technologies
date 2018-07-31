package com.ivan.newtechnologies.xml.dom;

import com.ivan.newtechnologies.xml.InputStreamFactory;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class DomValidationMain {

    public static void main(String[] args) throws Exception {
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema = schemaFactory.newSchema(new StreamSource(InputStreamFactory.getSchema()));

        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setSchema(schema);

        final DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        documentBuilder.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException e) throws SAXException {
                System.out.println("WARN - " + e.getMessage());
            }

            @Override
            public void error(SAXParseException e) throws SAXException {
                System.out.println("ERROR - " + e.getMessage());
            }

            @Override
            public void fatalError(SAXParseException e) throws SAXException {
                System.out.println("FATAL ERROR - " + e.getMessage());
            }
        });
        final Document document = documentBuilder.parse(InputStreamFactory.getTodoListInvalid());
    }
}
