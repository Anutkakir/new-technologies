package com.ivan.newtechnologies.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.StringReader;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;

public class XmlReaderMain {

    public static void main(String[] args) throws Exception {
        final String xml = "<users><user><name>user1</name></user></users>";

        XMLReader xr = new XMLFilterImpl(XMLReaderFactory.createXMLReader()) {
            private String tagName = "";
            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts)
                    throws SAXException {
                tagName = qName;
                super.startElement(uri, localName, qName, atts);
            }
            public void endElement(String uri, String localName, String qName) throws SAXException {
                tagName = "";
                super.endElement(uri, localName, qName);
            }
            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (tagName.equals("name")) {
                    ch = "user2".toCharArray();
                    start = 0;
                    length = ch.length;
                }
                super.characters(ch, start, length);
            }
        };

        Source src = new SAXSource(xr, new InputSource(new StringReader(xml)));
        Result res = new StreamResult(System.out);
        TransformerFactory.newInstance().newTransformer().transform(src, res);
    }
}
