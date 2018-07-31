package com.ivan.newtechnologies.xml.sax;

import com.ivan.newtechnologies.xml.InputStreamFactory;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

public class SaxValidationMain {

    public static void main(String[] args) throws Exception {
        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema = schemaFactory.newSchema(new StreamSource(InputStreamFactory.getSchema()));

        final SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        factory.setSchema(schema);

        final SAXParser saxParser = factory.newSAXParser();

        System.out.println("Parsing valid");
        saxParser.parse(InputStreamFactory.getTodoList(), new ErrorHandler());

        System.out.println("Parsing invalid");
        saxParser.parse(InputStreamFactory.getTodoListInvalid(), new ErrorHandler());
    }

    private static class ErrorHandler extends DefaultHandler {
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
    }

}
