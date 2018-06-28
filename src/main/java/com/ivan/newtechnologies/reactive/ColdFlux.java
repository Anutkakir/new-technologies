package com.ivan.newtechnologies.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

public class ColdFlux {

    public static void main(String[] args) {
        final StringBuilder stringBuilder = new StringBuilder();

        final Flux<Integer> flux = Flux.just(1, 2, 3);
        flux.log()
                .doOnComplete(() -> System.out.println("Completed"))
                .subscribe(stringBuilder::append);

        System.out.println(stringBuilder);

        // Backpressure example
        flux.subscribe(new Subscriber<Integer>() {

            private Subscription s;
            private int counter;

            @Override
            public void onSubscribe(Subscription s) {
                this.s = s;
                s.request(2);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("On next: " + integer);
                if (++counter % 2 == 0) {
                    s.request(2);
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });
    }

}
