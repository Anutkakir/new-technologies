package com.ivan.newtechnologies.guava.cache;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class LoadingCacheMain {

    public static void main(String[] args) throws Exception {

        final TriggeredTicker ticker = new TriggeredTicker();

        final LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.HOURS)
                .recordStats()
                .ticker(ticker)
                .removalListener(rn -> {
                    System.out.println("==== Removal Listener: " + rn.getCause().name());
                })
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(final Integer i) throws Exception {
                        if (i == 1) {
                            System.out.println("Loading value 1");
                            return "one";
                        }

                        if (i == 2) {
                            System.out.println("Loading value 2");
                            return "two";
                        }

                        System.out.println("Loading value " + i);
                        return null;
                    }
                });

        cache.put(3, "three");

        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println("Invoking before cache gets expired");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));

        ticker.trigger();

        System.out.println("Invoking after cache expired");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
//        System.out.println(cache.get(3)); // This causes exception because Loader for value 3 returns null

        System.out.println(cache.stats());
    }

    public static class TriggeredTicker extends Ticker {
        private boolean trigger;

        public void trigger() {
            this.trigger = true;
        }

        @Override
        public long read() {
            if (this.trigger) {
                return TimeUnit.HOURS.toNanos(2);
            }

            return 0;
        }
    }
}
