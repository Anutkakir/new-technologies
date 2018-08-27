package com.ivan.newtechnologies.xml

class XmlSlurperMain {

    static void main(String[] args) {
        def sl = new XmlSlurper()
        def doc = sl.parse("src/main/resources/xml/todo-list.xml")

        println doc

        println doc.items.item.name // prints all names

        println doc.items.item[1].description // prints the description of first item as a text (counting from 0)
        println doc.items.item[1].description.text() // prints the description of first item as a text (counting from 0)
        println doc.items.item[1].@id // prints id attribute
    }

}
