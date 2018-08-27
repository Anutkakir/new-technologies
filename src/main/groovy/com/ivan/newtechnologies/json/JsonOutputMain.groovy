package com.ivan.newtechnologies.json

import com.ivan.newtechnologies.basic.Person
import groovy.json.JsonOutput

class JsonOutputMain {

    static void main(String[] args) {
        def person = new Person(name: "John", age: 43)
        final String json = JsonOutput.toJson(person)
        final String json2 = JsonOutput.toJson([id: 1, value: 'Project'])

        println json
        println json2
    }
}
