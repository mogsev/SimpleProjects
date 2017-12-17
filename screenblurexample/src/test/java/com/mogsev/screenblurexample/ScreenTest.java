package com.mogsev.screenblurexample;

import org.junit.Test;

import java.util.Arrays;

import io.reactivex.Observable;

import static org.junit.Assert.assertEquals;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ScreenTest {

    @Test
    public void screen_Successful() throws Exception {
        Observable.fromIterable(Arrays.asList("First", "Second", "Third"));
    }

}
