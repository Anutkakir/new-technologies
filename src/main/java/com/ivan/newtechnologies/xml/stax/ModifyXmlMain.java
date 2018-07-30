package com.ivan.newtechnologies.xml.stax;

import com.ivan.newtechnologies.xml.InputStreamFactory;

import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ModifyXmlMain {

    public static void main(String[] args) throws Exception {
        final StringWriter stringWriter = new StringWriter();

        final XMLEventReader reader = XMLInputFactory.newFactory().createXMLEventReader(InputStreamFactory.getTodoList());
        final XMLEventWriter writer = XMLOutputFactory.newFactory().createXMLEventWriter(stringWriter);
        final XMLEventFactory eventFactory = XMLEventFactory.newFactory();

        boolean isAge = false;

        while (reader.hasNext()) {
            final XMLEvent event = reader.nextEvent();

            if (event.isStartElement()) {
                final StartElement element = event.asStartElement();
                if (isAgeElement(element.getName())) {
                    writer.add(eventFactory.createStartElement("", "", "year-of-birth"));
                    isAge = true;
                } else {
                    writer.add(event);
                }
            } else if (event.isEndElement()) {
                final EndElement element = event.asEndElement();
                if (isAgeElement(element.getName())) {
                    writer.add(eventFactory.createEndElement("", "", "year-of-birth"));
                    isAge = false;
                } else {
                    writer.add(event);
                }
            } else if (event.isCharacters() && isAge) {
                final Integer age = Integer.valueOf(event.asCharacters().getData());
                writer.add(eventFactory.createCharacters(LocalDate.now().minusYears(age).format(DateTimeFormatter.ISO_DATE)));
            } else {
                writer.add(event);
            }
        }

        writer.flush();
        writer.close();

        System.out.println(stringWriter);
    }

    private static boolean isAgeElement(final QName name) {
        return "age".equals(name.getLocalPart());
    }
}
