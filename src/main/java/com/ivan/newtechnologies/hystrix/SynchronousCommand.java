package com.ivan.newtechnologies.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.time.Duration;
import java.time.LocalTime;
import java.util.stream.IntStream;

public class SynchronousCommand {

    private static final HystrixCommand.Setter cachedSetter = HystrixCommand.Setter.withGroupKey(
            HystrixCommandGroupKey.Factory.asKey("SimpleCommandGroupKey"))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withCircuitBreakerEnabled(true)
                    .withCircuitBreakerRequestVolumeThreshold(5)
                    .withExecutionTimeoutEnabled(true)
                    .withExecutionTimeoutInMilliseconds(200));

    private static int counter = 0;
    private static LocalTime firstFailure;

    public static void main(String[] args) throws Exception {

        IntStream.range(0, 5000000)
                .forEach(i -> {
                    try {
                        counter++;
                        String str = new SimpleCommand().execute();

                        if (firstFailure != null) {
                            System.out.println("Duration between failure and recover: " +
                                    Duration.between(LocalTime.now(), firstFailure).toString());
                            firstFailure = null;
                        }

                        System.out.println(String.format("Iteration %s: %s.", i, str));
                    } catch (Exception e) {
                        if (firstFailure == null) {
                            firstFailure = LocalTime.now();
                        }

                        System.out.println(String.format("Iteration %s: %s", counter, e.getMessage()));
                    }
                });
    }

    public static class SimpleCommand extends HystrixCommand<String> {

        public SimpleCommand() {
            super(cachedSetter);
        }

        @Override
        protected String run() throws Exception {
            Thread.sleep(80);
            if (counter > 3 && counter < 25) {
                System.out.println("I am failing");
                throw new RuntimeException("Smth went wrong");
            }
            return "Hello world: " + Thread.currentThread().getId();
        }
    }

}
