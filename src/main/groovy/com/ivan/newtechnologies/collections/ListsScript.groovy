package com.ivan.newtechnologies.collections

def list1 = [54, 70, 32]
println list1

List list2 = (0..15).toList()
println list2
println "Last element: ${list2[-1]}"

println list1 + list2
println list2 + list1
println list1 + list1 // duplicates elements

println list1[2, 0]

list2[3..8] = []
println list2

list2 += 99
println list2

list2 << 100
println list2

list2 -= 9
println list2

println list2.isCase(14) // contains
println list2.isCase(500)
println 14 in list2 // contains
println 500 in list2

def list3 = list2.collect { it * 2 } // same as { elem -> elem * 2 }
println list3

println list2.findAll { it % 2 == 0 }