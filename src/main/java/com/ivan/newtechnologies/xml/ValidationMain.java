package com.ivan.newtechnologies.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class ValidationMain {

    public static void main(String[] args) {

        try {
            final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            final Schema schema = factory.newSchema(new StreamSource(InputStreamFactory.getSchema()));

            final Validator validator = schema.newValidator();
            validator.setErrorHandler(new ErrorHandler() {
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

            System.out.println("Validating valid");
            validator.validate(new StreamSource(InputStreamFactory.getTodoList()));

            System.out.println("Validating invalid");
            validator.validate(new StreamSource(InputStreamFactory.getTodoListInvalid()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
