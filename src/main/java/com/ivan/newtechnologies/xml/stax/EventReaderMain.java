package com.ivan.newtechnologies.xml.stax;

import com.ivan.newtechnologies.xml.InputStreamFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class EventReaderMain {

    public static void main(String[] args) throws Exception {
        final XMLInputFactory factory = XMLInputFactory.newFactory();
        final XMLEventReader eventReader = factory.createXMLEventReader(InputStreamFactory.getTodoList());

        while (eventReader.hasNext()) {
            final XMLEvent event = eventReader.nextEvent();

            if (event.isStartDocument()) {
                System.out.println("Document processing started");
            }

            if (event.isEndDocument()) {
                System.out.println("Document processing completed");
            }

            if (event.isStartElement()) {
                final StartElement startElement = event.asStartElement();
                final QName name = startElement.getName();
                System.out.printf("Start element: uri - %s; localName - %s; prefix - %s;\n", name.getNamespaceURI(), name.getLocalPart(), name.getPrefix());
            }

            if (event.isEndElement()) {
                final EndElement endElement = event.asEndElement();
                final QName name = endElement.getName();
                System.out.printf("End element: uri - %s; localName - %s; prefix - %s;\n", name.getNamespaceURI(), name.getLocalPart(), name.getPrefix());
            }

            if (event.isCharacters()) {
                final Characters characters = event.asCharacters();
                System.out.printf("Characters: data - %s;\n", characters.getData());
            }
        }

        eventReader.close();
    }

}
