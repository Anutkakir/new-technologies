package com.ivan.newtechnologies.threads;

public class ThreadLocalMain {

    public static void main(String[] args) throws Exception {
        // Simple Thread Local
        final ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("Welcome ThreadLocal");

        System.out.println("Main thread invokes thread local: " + tl.get());

        final Thread thread = new Thread(() -> {
            System.out.println("Child thread invokes thread local: " + tl.get());
        });
        thread.start();
        thread.join();

        // Inheritable Thread Local
        final InheritableThreadLocal<String> itl = new InheritableThreadLocal<>();
        itl.set("Welcome InheritableThreadLocal");

        System.out.println("Main thread invokes inheritable thread local: " + itl.get());

        final Thread thread2 = new Thread(() -> {
            System.out.println("Child thread invokes inheritable thread local: " + itl.get());
        });
        thread2.start();
        thread2.join();
    }


}
