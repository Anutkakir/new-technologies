package com.ivan.newtechnologies.operatoroverride

import groovy.transform.Canonical
import groovy.transform.Immutable

@Canonical
@Immutable
class Money {

    BigDecimal amount

    // overridden '+' operator for Money class
    Money plus(Money money) {
        new Money(this.amount + money.amount)
    }

    static void main(String[] args) {
        def m1 = new Money(12)
        def m2 = new Money(55)
        println m1 + m2
    }
}
