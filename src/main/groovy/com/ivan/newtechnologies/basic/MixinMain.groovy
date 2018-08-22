package com.ivan.newtechnologies.basic

class MixinMain {

    static void main(String[] args) {
        def a = new ClassA()
        a.metaClass.mixin ClassB

        a.methodA()
        a.methodB() // it has this method because of the mixin

        // new ClassA().methodB() - no method, because mixin was applied to "a" object only

        ClassA.metaClass.mixin ClassB

        new ClassA().methodB() // it has this method, because mixin was applied to the class instance
    }

    static class ClassA {
        def methodA() {
            println "ClassA - methodA"
        }
    }

    static class ClassB {
        def methodB() {
            println "ClassB - methodB"
        }
    }

}
