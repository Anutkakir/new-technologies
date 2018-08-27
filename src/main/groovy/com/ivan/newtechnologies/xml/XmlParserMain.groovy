package com.ivan.newtechnologies.xml

class XmlParserMain {

    static void main(String[] args) {
        final def parser = new XmlParser()
        def doc = parser.parse("src/main/resources/xml/todo-list.xml")

        println doc

        println doc.items.item.name // prints all names as nodes

        println doc.items.item[1].description // prints the description of first item as a node (counting from 0)
        println doc.items.item[1].description.text() // prints the description of first item as a text (counting from 0)
        println doc.items.item[1].@id // prints id attribute
    }
}
