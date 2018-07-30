package com.ivan.newtechnologies.xml.stax;

import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class StreamWriterMain {

    public static void main(String[] args) throws Exception {
        final StringWriter stringWriter = new StringWriter();

        final XMLOutputFactory factory = XMLOutputFactory.newFactory();
        final XMLStreamWriter writer = factory.createXMLStreamWriter(stringWriter);

        writer.writeStartDocument();
        writer.writeStartElement("money");

        writer.writeStartElement("amount");
        writer.writeCharacters("300.2");
        writer.writeEndElement();

        writer.writeStartElement("currency");
        writer.writeCharacters("USD");
        writer.writeEndElement();

        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();

        System.out.println(stringWriter);
    }

}
