package com.ivan.newtechnologies.xml.sax;

import com.ivan.newtechnologies.xml.InputStreamFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxMain {

    public static void main(String[] args) throws Exception {
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(InputStreamFactory.getTodoList(), new TodoListHandler());
    }

    private static class TodoListHandler extends DefaultHandler {
        private boolean isElem;

        public TodoListHandler() {
            this.isElem = false;
        }

        @Override
        public void startDocument() throws SAXException {
            System.out.println("Document processing started");
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println("Document processing completed");
        }

        @Override
        public void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {
            this.isElem = true;
            System.out.printf("Start element: uri - %s; localName - %s; qName - %s;\n", uri, localName, qName);
        }

        @Override
        public void endElement(final String uri, final String localName, final String qName) throws SAXException {
            this.isElem = false;
            System.out.printf("End element: uri - %s; localName - %s; qName - %s;\n", uri, localName, qName);
        }

        @Override
        public void characters(final char[] ch, final int start, final int length) throws SAXException {
            if (this.isElem) {
                System.out.printf("Characters: ch - %s; start - %s; length - %s;\n", new String(ch, start, length), start, length);
            }
        }

    }
}
