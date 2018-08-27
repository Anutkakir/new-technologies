package com.ivan.newtechnologies.json

import groovy.json.JsonSlurper

class JsonSlurperMain {

    static void main(String[] args) {
        def sl = new JsonSlurper()
        def jsn = sl.parse(new File("src/main/resources/json/todo-list.json"))

        println jsn

        println jsn.items.item.name // prints all names

        println jsn.items.item[1].description // prints the description of first item(counting from 0)
        println jsn.items.item[1].id // prints id 
    }
}
