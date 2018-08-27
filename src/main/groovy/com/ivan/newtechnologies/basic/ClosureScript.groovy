package com.ivan.newtechnologies.basic

def sayHello = { println 'Hello!' }
sayHello.call()
sayHello()

def c2 = { name -> println "Hello, $name!" }
c2.call("World")
c2("World")

class P {
    String name
    def sayHi() {
        println "Hello, world!"
    }
}

def c3 = { // fields and methods accessed without an object, will be accessed on the delegate
    println name
    sayHi()
}
c3.delegate = new P(name: 'Helga')
c3()

def sayHi(String name, Closure<String> convertName) {
    println convertName(name)
}
sayHi('John') {
    it.toUpperCase()
}

