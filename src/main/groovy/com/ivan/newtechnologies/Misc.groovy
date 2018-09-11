package com.ivan.newtechnologies

def fib
fib = { int n ->
    n < 2 ? n : fib(n - 1) + fib(n - 2)
}.memoize()
println fib(15)

def <T> LinkedList<T> newLinkedList(final T... values) {
    final LinkedList<T> list = new LinkedList<>()

    if (!values) {
        return list
    }

    values.each {
        list << it
    }

    return list
}

println newLinkedList("a", "c", "b")