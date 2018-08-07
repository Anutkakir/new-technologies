package com.ivan.newtechnologies.basic

def sayHello = { println 'Hello!' }
sayHello.call()
sayHello()

def c2 = { name -> println "Hello, $name!" }
c2.call("World")
c2("World")



