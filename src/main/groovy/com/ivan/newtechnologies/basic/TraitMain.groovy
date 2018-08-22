package com.ivan.newtechnologies.basic

class TraitMain {

    static void main(String[] args) {
        new ClassA().sayHi() // Hello from ClassA
        new ClassB().sayHi() // Hello from ClassB
        new ClassC().sayHi() // Hello from ClassC
        new ClassD().sayHi() // Hello from ClassC
        new ClassE().sayHi() // Hello from Trait

        TraitA traitA = new ClassA()
        traitA.sayHi()
    }

    static trait TraitA {
        void sayHi() {
            println "Hello from Trait"
        }
    }

    static class ClassA implements TraitA {
        void sayHi() {
            println "Hello from ClassA"
        }
    }

    static class ClassB extends ClassA {
        void sayHi() {
            println "Hello from ClassB"
        }
    }

    static class ClassC {
        void sayHi() {
            println "Hello from ClassC"
        }
    }

    static class ClassD extends ClassC {
    }

    static class ClassE extends ClassC implements TraitA {
    }

}
