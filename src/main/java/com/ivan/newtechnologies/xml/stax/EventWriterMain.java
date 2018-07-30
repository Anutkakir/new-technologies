package com.ivan.newtechnologies.xml.stax;

import java.io.StringWriter;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;

public class EventWriterMain {

    public static void main(String[] args) throws Exception {
        final StringWriter stringWriter = new StringWriter();

        final XMLOutputFactory factory = XMLOutputFactory.newFactory();
        final XMLEventWriter writer = factory.createXMLEventWriter(stringWriter);

        final XMLEventFactory eventFactory = XMLEventFactory.newFactory();

        writer.add(eventFactory.createStartDocument());

        writer.add(eventFactory.createStartElement("", "", "money"));

        writer.add(eventFactory.createStartElement("", "", "amount"));
        writer.add(eventFactory.createCharacters("200.2"));
        writer.add(eventFactory.createEndElement("","", "amount"));

        writer.add(eventFactory.createStartElement("", "", "currency"));
        writer.add(eventFactory.createCharacters("USD"));
        writer.add(eventFactory.createEndElement("","", "currency"));

        writer.add(eventFactory.createEndElement("","", "money"));

        writer.add(eventFactory.createEndDocument());
        writer.flush();
        writer.close();

        System.out.println(stringWriter);
    }

}
