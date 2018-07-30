package com.ivan.newtechnologies.xml.stax;

import com.ivan.newtechnologies.xml.InputStreamFactory;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class StreamReaderMain {

    public static void main(String[] args) throws Exception {
        final XMLInputFactory factory = XMLInputFactory.newFactory();
        final XMLStreamReader streamReader = factory.createXMLStreamReader(InputStreamFactory.getTodoList());

        while (streamReader.hasNext()) {
            final int eventType = streamReader.next();

            if (XMLStreamReader.START_DOCUMENT == eventType) {
                System.out.println("Document processing started");
            }

            if (XMLStreamReader.END_DOCUMENT == eventType) {
                System.out.println("Document processing completed");
            }

            if (streamReader.isStartElement()) {
                final QName name = streamReader.getName();
                System.out.printf("Start element: uri - %s; localName - %s; prefix - %s;\n", name.getNamespaceURI(), name.getLocalPart(), name.getPrefix());
            }

            if (streamReader.isEndElement()) {
                System.out.printf("End element: uri - %s; localName - %s; prefix - %s;\n", streamReader.getNamespaceURI(), streamReader.getLocalName(), streamReader.getPrefix());
            }

            if (streamReader.isCharacters()) {
                System.out.printf("Characters: data - %s;\n", streamReader.getText());
            }
        }

        streamReader.close();
    }
}
