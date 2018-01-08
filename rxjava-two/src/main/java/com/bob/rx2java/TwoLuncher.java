package com.bob.rx2java;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wangxiang on 18/1/5.
 */
public class TwoLuncher {

    public static void main(String[] args) {

        Flowable.just(1, 2, 3, 4).subscribeOn(Schedulers.io()).observeOn(Schedulers.newThread())
                .map(s -> {
                    System.out.println(Thread.currentThread().getName() + " - " + s);
                    return s + "u";
                }).subscribe(s -> System.out.println(Thread.currentThread().getName() + " -- " + s));

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
                    System.out.println(Thread.currentThread().getName() + " - " + w);
                    return w * w;
                })
                .sequential()
                .blockingSubscribe(v -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(v);
                });

        Maybe.just(1).map(x -> x + 1).filter(v -> v == 2).defaultIfEmpty(4).test().assertResult(2);
    }

}