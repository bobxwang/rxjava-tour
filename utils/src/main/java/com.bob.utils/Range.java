package com.bob.utils;

import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangxiang on 18/1/5.
 */
public class Range implements Iterable<Long> {

    private Stream<Long> range;

    public Range(final long start, final long length, final int step) {
        Supplier<Long> seed = new Supplier<Long>() {

            private long next = start;

            @Override
            public Long get() {
                long _next = next;
                next += step;
                return _next;
            }
        };
        range = Stream.generate(seed).limit(length);
    }

    public List<Long> toList() {
        return range.collect(Collectors.toList());
    }

    @Override
    public Iterator<Long> iterator() {
        return range.iterator();
    }
}