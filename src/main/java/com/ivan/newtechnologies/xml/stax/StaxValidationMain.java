package com.ivan.newtechnologies.xml.stax;

import com.ivan.newtechnologies.xml.InputStreamFactory;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.EventReaderDelegate;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class StaxValidationMain {

    public static void main(String[] args) throws Exception {
        final XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader eventReader = factory.createXMLEventReader(InputStreamFactory.getTodoListInvalid());

        eventReader = new EventReaderDelegate(eventReader) {
            @Override
            public XMLEvent nextEvent() throws XMLStreamException {
                final XMLEvent event = super.nextEvent();

                if (event.isStartElement()) {
                    final StartElement startElement = event.asStartElement();
                    System.out.println("ELEMENT - " + startElement.getName().getLocalPart());
                }

                return event;
            }
        };

        final SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        final Schema schema = schemaFactory.newSchema(new StreamSource(InputStreamFactory.getSchema()));
        final Validator validator = schema.newValidator();
        validator.validate(new StAXSource(eventReader));
    }

}
