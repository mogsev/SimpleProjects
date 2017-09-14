package com.mogsev.simpleprojects.rxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Eugene Sikaylo on 08.09.2017.
 */

public class RxJavaExampleTest {

    @Test
    public void onFirstTest() {
        System.out.println("onFirstTest start");
        List<String> list = Arrays.asList("One", "Two", "Three", "Four", "Five");

        list.forEach(str -> {
            System.out.println(str);
        });

        Observable.fromIterable(list)
                .subscribeOn(Schedulers.io())
                .map(new Function<String, Object>() {
                    @Override
                    public Object apply(@NonNull String s) throws Exception {
                        if (s.equals("One")) return "ONE";
                        return s;
                    }
                })
//                .filter(s -> {
//                    if (s.equals("Two")) return true;
//                    if (s.length() > 4) return true;
//                    return false;
//                })
//                .filter(new Predicate<String>() {
//                    @Override
//                    public boolean test(@NonNull String s) throws Exception {
//                        return s.equals("One");
//                    }
//                })
                .subscribe(str -> {
                    System.out.println(str);
                });

        PublishSubject<Long> subject = PublishSubject.create();
        subject.timeInterval();

        System.out.println("onFirstTest end");
    }

}
