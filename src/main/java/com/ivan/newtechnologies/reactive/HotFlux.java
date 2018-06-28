package com.ivan.newtechnologies.reactive;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.util.Random;

public class HotFlux {

    public static void main(String[] args) throws Exception {

        final ConnectableFlux<Integer> flux = createFlux();
        flux.subscribe(i -> {
            System.out.println("OnNext: " + i);
        });
        flux.connect();
    }

    private static ConnectableFlux<Integer> createFlux() {
        return Flux.<Integer>create(fluxSink -> {
            Integer counter = fluxSink.currentContext().getOrDefault("counter", 0);
            while (true) {
                if (counter == 10) {
                    fluxSink.complete();
                    return;
                }

                fluxSink.next(new Random().nextInt(100));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                fluxSink.currentContext().put("counter", ++counter);
            }
        }).publish();
    }

}
