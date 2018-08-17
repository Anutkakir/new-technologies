package com.ivan.newtechnologies.basic

class StringsMain {

    static void main(String[] args) {
        int num = 15

        String s1 = "string with double quotes $num or ${num}"
        println s1 // string with double quotes 15 or 15

        String s2 = 'string with single quotes $num'
        println s2 // string with single quotes $num

        String s3 = """
Multiline string with double quotes $num
Second line"""
        println s3 // $num gets interpolated

        String s4 = '''
Multiline string with single quotes $num
Second line'''
        println s4 // $num does not get interpolated

        String s5 = /string with \/ slashes $num/ // slash is escaped with backslash
        println s5 // string with / slashes 15

        println "abc" - "a" // bc
        println "14".isNumber() // true

        println "text_file.txt"[0..-5] // prints substring from 0 to fifth element inclusive from the end (counting from 1)

        String s6 = $/dollar slashy string $ $num //$
        println s6 // dollar slashy string $ 15 /
    }

}
