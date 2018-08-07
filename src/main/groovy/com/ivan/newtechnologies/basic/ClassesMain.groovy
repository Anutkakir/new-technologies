package com.ivan.newtechnologies.basic

import org.codehaus.groovy.runtime.typehandling.GroovyCastException

class ClassesMain {

    static void main(String[] args) {
        def nameAge = [name: "John Doe", age: 13]
        def nameAgeEmail = [name: "John Doe", age: 13, email: "john@doe.com"]

        Person p = nameAge
        println p

        try {
            Person p1 = nameAgeEmail
        } catch (GroovyCastException e) {
            // exception because there is no 'email' field in Person class
        }

        // because of casting (as Person) there will be no exception, but p2 object will be empty
        Person p2 = nameAgeEmail as Person
        println p2
    }

}
