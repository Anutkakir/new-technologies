package com.ivan.newtechnologies.xml.pin

import groovy.xml.FactorySupport

import javax.xml.parsers.SAXParser

class Extractor {

    final String xml = ''''''

    def extract(String xml) {
        final Ch ch = new Ch()

        final SAXParser parser = FactorySupport.createSaxParserFactory().newSAXParser()
        parser.parse(new ByteArrayInputStream(xml.bytes), ch)
    }

    public static void main(String[] args) {
        def extractor = new Extractor()
        extractor.extract(extractor.xml)
    }
}
