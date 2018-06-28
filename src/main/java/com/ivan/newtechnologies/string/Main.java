package com.ivan.newtechnologies.string;

public class Main {

    public static void main(String[] args) {
//        var s1 = "123";
//        var s2 = "12" + "3";
//        System.out.println(s1 == s2); // true
//
//        var s3 = new String("123");
//        System.out.println(s1 == s3); // false
//
//        var s4 = new String("12") + new String("3");
//        System.out.println(s1 == s4); // false
//
//        var s5 = "12" + new String("3");
//        System.out.println(s1 == s5); // false
//
//        var s6 = s1 + new A();
//        System.out.println(s6); // 123A
//
//        var s7 = new String("123").intern();
//        System.out.println(s1== s7); // true, because of .intern()
    }

    static class A {
        @Override
        public String toString() {
            return "A";
        }
    }
}
