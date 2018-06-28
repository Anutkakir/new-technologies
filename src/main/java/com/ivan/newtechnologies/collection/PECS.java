package com.ivan.newtechnologies.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PECS {

    public static void main(String[] args) {
        // can invoke with any type
        producer(new ArrayList<String>());
        producer(new ArrayList<Integer>());

        // can invoke with type itself and child types
        producer2(new ArrayList<A>());
        producer2(new ArrayList<B>());
        producer2(new ArrayList<C>());

        // can invoke with type itself and super types
        consumer(new ArrayList<A>());
        consumer(new ArrayList<Object>());
    }

    private static void producer(final Collection<?> collection) {
        // can not add anything to the collection except null
        // collection.add(1);
        collection.add(null);
    }

    private static void producer2(final Collection<? extends A> collection) {
        // can not add anything except null
//        collection.add(new B());
        collection.add(null);

        // can iterate with type A
        final List<Integer> collect = collection.stream()
                .map(A::getNum)
                .collect(Collectors.toList());
    }

    private static void consumer(final Collection<? super A> collection) {
        collection.add(new A());
        collection.add(new B());
        collection.add(new C());

        // can not iterate with type A
//        final List<Integer> collect = collection.stream()
//                .map(A::getNum)
//                .collect(Collectors.toList());
    }

    static class A {
        int getNum() {
            return 1;
        }
    }

    static class B extends A {

    }

    static class C extends A {

    }
}
