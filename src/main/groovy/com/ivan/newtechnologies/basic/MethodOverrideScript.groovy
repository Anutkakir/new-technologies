package com.ivan.newtechnologies.basic

def person = new Person(name: "John")

// add a method for specified object only
person.metaClass.sayHi = { name ->
    println "Hello, $name! I am $delegate.name"
}
person.sayHi("Jane")
// new Person(name: "Josh").sayHi("Paola") - throws error because it does not have sayHi() method

// add a method for a class
Person.metaClass.sing = {
    println "La la la la la"
}
new Person().sing()