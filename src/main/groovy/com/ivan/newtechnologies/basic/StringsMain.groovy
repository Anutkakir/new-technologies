package com.ivan.newtechnologies.basic

class StringsMain {

    static void main(String[] args) {
        int num = 15

        String s1 = "string with double quotes $num or ${num}"
        println s1

        String s2 = 'string with single quotes $num'
        println s2

        String s3 = """
Multiline string with double quotes $num
Second line
"""
        println s3

        String s4 = '''
Multiline string with single quotes $num
Second line
'''
        println s4

        String s5 = /string with slashes $num/
        println s5

        println "abc" - "a" // bc
        println "14".isNumber() // true

        println "text_file.txt"[0..-5]
    }

}
