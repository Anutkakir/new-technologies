package com.ivan.newtechnologies

def fib
fib = { int n ->
    n < 2 ? n : fib(n - 1) + fib(n - 2)
}.memoize()
println fib(15)