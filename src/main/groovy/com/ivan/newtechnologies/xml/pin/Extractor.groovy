package com.ivan.newtechnologies.xml.pin

import groovy.xml.FactorySupport

import javax.xml.parsers.SAXParser

class Extractor {

    final String xml = ''''''

    def extract() {
        final Ch ch = new Ch()

        final SAXParser parser = FactorySupport.createSaxParserFactory().newSAXParser()
        parser.parse(new ByteArrayInputStream(this.xml.bytes), ch)
    }

}
