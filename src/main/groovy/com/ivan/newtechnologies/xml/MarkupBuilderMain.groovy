package com.ivan.newtechnologies.xml

import groovy.xml.MarkupBuilder

class MarkupBuilderMain {

    static void main(String... args) {
        def writer = new StringWriter()
        final MarkupBuilder mb = new MarkupBuilder(new IndentPrinter(writer))

        mb.rootNode(someAttr: "Hello, world!") {
            childNode(123)
            secondNode()
            thirdNode("This is a text")
            fourthNode {
                nestedNode("nested value")
            }
        }

        println writer
    }

}
