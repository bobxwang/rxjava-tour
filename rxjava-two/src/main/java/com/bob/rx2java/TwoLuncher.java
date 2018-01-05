package com.bob.rx2java;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangxiang on 18/1/5.
 */
public class TwoLuncher {

    public static void main(String[] args) {

        Flowable.just("Hello world").subscribe(System.out::println);

        Flowable.range(1, 10)
                .observeOn(Schedulers.computation())
                .map(v -> {
                    // u can see here the thread name is same
                    System.out.println(Thread.currentThread().getName());
                    return v * v;
                })
                .blockingSubscribe(v -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(v);
                });

        Flowable.range(1, 10)
                .flatMap(v ->
                        Flowable.just(v)
                                .subscribeOn(Schedulers.computation())
                                .map(w -> {
                                    // u can see here the thread name is different
                                    System.out.println(Thread.currentThread().getName());
                                    return w * w;
                                })
                )
                .blockingSubscribe(v -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(v);
                });

        // start 2.0.5 version, we can using parallel to achieve the above function in different thread
        Flowable.range(1, 10)
                .parallel()
                .runOn(Schedulers.computation())
                .map(w -> {
                    System.out.println(Thread.currentThread().getName());
                    return w * w;
                })
                .sequential()
                .blockingSubscribe(v -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(v);
                });
    }


}