package com.ivan.newtechnologies.xml.pin

import groovy.transform.PackageScope
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

@PackageScope
class Ch extends DefaultHandler {

    private static final String PINPOINT_ID_PREFIX = "a"

    private Linkable root

    private List<String> pinpointHierarchy
    private Deque<String> tagHierarchy
    private StringBuilder text

    PinpointContentHandler(final Linkable root) {
        this.root = root

        this.pinpointHierarchy = []
        this.reset()
    }

    @Override
    void startElement(final String uri, final String localName, final String qName, final Attributes attributes) throws SAXException {

        final String id = attributes.getValue("id")
        if (this.hasId(id)) {
            this.pinpointHierarchy << id
            this.tagHierarchy << localName
            return
        }

        if (this.isLabelDepthReached() || !this.tagHierarchy) {
            return
        }

        this.tagHierarchy << localName
    }

    @Override
    void characters(final char[] ch, final int start, final int length) throws SAXException {
        if (!this.isLabelDepthReached()) {
            return
        }

        this.text << new String(ch, start, length)
    }

    @Override
    void endElement(final String uri, final String localName, final String qName) throws SAXException {
        if (!this.tagHierarchy) {
            return
        }

        this.tagHierarchy.remove(localName)

        if (!this.tagHierarchy) {
            this.reset()
        }
    }

    private void reset() {

        this.tagHierarchy = [] as LinkedList
        this.text = new StringBuilder()
    }

    private boolean hasId(final String id) {
        return id && id.startsWith(PINPOINT_ID_PREFIX)
    }

    private boolean isLabelDepthReached() {
        return this.tagHierarchy.size() == 3
    }
}
