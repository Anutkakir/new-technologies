package com.ivan.newtechnologies.hystrix;

import com.ivan.newtechnologies.util.Utils;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ObservableCommand {

    public static void main(String[] args) {
        Observable<String> observable = new SimpleObservableCommand().construct();

        System.out.println("Subscribing to the observable...");
        observable.subscribe(System.out::println);
        System.out.println("Subscribed!");

        Utils.sleepQuietly(5000);
    }

    static class SimpleObservableCommand extends HystrixObservableCommand<String> {
        protected SimpleObservableCommand() {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("SimpleObservableCommandGroup"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("simpleObservableCommand")));
        }

        @Override
        protected Observable<String> construct() {
            return Observable.create((Subscriber<? super String> observer) -> {
                Utils.sleepQuietly(3000);

                if (!observer.isUnsubscribed()) {
                    observer.onNext("Hello, world");
                    observer.onCompleted();
                }
            }).subscribeOn(Schedulers.computation());
        }

    }

}
