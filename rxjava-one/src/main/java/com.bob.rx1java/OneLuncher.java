package com.bob.rx1java;

import com.bob.utils.Range;
import rx.Observable;
import rx.Observer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxiang on 18/1/5.
 */
public class OneLuncher {

    public static void main(String[] args) throws IOException {

        Observer observer = new Observer() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }
        };

        // using create to generate an observable
        Observable.unsafeCreate(subscriber -> {
            {
                new Range(0, 10, 1).forEach(i -> subscriber.onNext(new Integer(i.toString())));
                subscriber.onCompleted();
            }
        }).subscribe(observer);

        // using list to generate an observable
        List<Long> list = new Range(11, 10, 2).toList();
        Observable.from(list).subscribe(observer);

        Observable.range(20, 30).subscribe(observer);

        // 每隔三秒钟进行打印
        Observable.interval(3, TimeUnit.SECONDS).subscribe(aLong ->
                System.out.println("now time is " + LocalDateTime.now() + " and aLong value is " + aLong));

        // the same data will send two times using repeat method
        Observable.just("hello, fuck u", "come on", "again and again").repeat(10).subscribe(s ->
                System.out.println(s)
        );

        System.in.read();
    }
}