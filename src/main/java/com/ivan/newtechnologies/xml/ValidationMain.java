package com.ivan.newtechnologies.xml;

import org.xml.sax.SAXException;

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
            validator.validate(new StreamSource(InputStreamFactory.getTodoList()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
