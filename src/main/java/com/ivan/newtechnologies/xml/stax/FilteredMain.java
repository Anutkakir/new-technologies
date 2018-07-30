package com.ivan.newtechnologies.xml.stax;

import com.ivan.newtechnologies.xml.InputStreamFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.EventFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class FilteredMain {

    public static void main(String[] args) throws Exception {
        final XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader eventReader = factory.createXMLEventReader(InputStreamFactory.getTodoList());
        eventReader = factory.createFilteredReader(eventReader, new Filter());

        while (eventReader.hasNext()) {
            final XMLEvent event = eventReader.nextEvent();

            if (event.isStartElement()) {
                final StartElement startElement = event.asStartElement();
                final QName name = startElement.getName();
                System.out.printf("Start element: uri - %s; localName - %s; prefix - %s;\n", name.getNamespaceURI(), name.getLocalPart(), name.getPrefix());
            }

            if (event.isEndElement()) {
                System.out.println("Should not be printed because of filter");
            }
        }

        eventReader.close();
    }

    private static class Filter implements EventFilter {
        @Override
        public boolean accept(final XMLEvent event) {
            return event.isStartElement();
        }
    }

}
