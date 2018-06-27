package com.ivan.newtechnologies.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

public class EventBusMain {

    public static void main(String[] args) {

        final EventBus eventBus = createEventBus(true);

        eventBus.register(new Listener());
        System.out.println("'Hello listener' has been registered");

        eventBus.register(new DeadListener());
        System.out.println("'Dead listener' has been registered");

        System.out.println("Firing first event");
        eventBus.post(new HelloEvent("Dumbledore"));

        System.out.println("Firing second event");
        eventBus.post(new HelloEvent("Voldemort"));

        System.out.println("Firing unknown event");
        eventBus.post(new UnknownEvent("Hagrid"));

    }

    public static class Listener {

        @Subscribe
        public void listen(final HelloEvent helloEvent) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello, " + helloEvent.name);
        }

    }

    public static class DeadListener {
        @Subscribe
        public void listen(final DeadEvent deadEvent) {
            System.out.println(deadEvent.getEvent());
        }
    }

    public static class HelloEvent {
        private String name;

        public HelloEvent(final String name) {
            this.name = name;
        }
    }

    public static class UnknownEvent {
        private String value;

        public UnknownEvent(final String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "UnknownEvent{" +
                    "value='" + this.value + '\'' +
                    '}';
        }
    }

    public static EventBus createEventBus(final boolean async) {
        if (async) {
            return new AsyncEventBus(Executors.newFixedThreadPool(3));
        }

        return new EventBus();
    }
}
