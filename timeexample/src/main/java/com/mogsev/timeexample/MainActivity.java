package com.mogsev.timeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.i("onCreate");

        Timber.i("%s", getPassedTime("2017-10-20T00:35:44.362+03:00"));
        Timber.i("%s", getPassedTime("2017-9-20T00:35:44.362+03:00"));
        Timber.i("%s", getPassedTime("2017-10-20T16:07:57.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-11T16:05:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T08:35:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T10:35:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T11:35:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T12:35:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T13:45:46.717+03:00"));
        Timber.i("%s", getPassedTime("2017-11-12T14:23:46.717+03:00"));

        // get day
        fetchOneWeak();
    }

    private void fetchOneWeak() {
        Timber.i("fetchOneWeak");
        int day = Calendar.getInstance().getFirstDayOfWeek();
        Timber.i("FirstDayOfWeek: %s", day);
        int days = day + 5;
        Timber.i("Days: %s", days);

    }

    private static final int getFirstDayOfWeek() {
        return 0;
        //return ((Calendar.getInstance().getFirstDayOfWeek() + 5) % 7) + 1;
    }

    private String getPassedTime(String time) {
        Timber.i("getPassedTime");
        DateTime currentTime = DateTime.now();
        DateTime dateTime = new DateTime(time);

        Period period = new Period(dateTime, currentTime);

        int days = period.getDays();
        if (days <= 0) {
            int hours = period.getHours();
            if (hours <= 1) {
                int minutes = period.getMinutes();
                return String.format("Updated at %s minutes", minutes);
            } else {
                return String.format("Updated at %s hours", hours);
            }
        } else {
            return String.format("Updated at %s days", days);
        }
    }

    private List<String> getFakeDates() {
        Timber.i("getFakeDates");

        return Arrays.asList("2017-10-20T00:35:44.362+03:00",
                "2017-9-20T00:35:44.362+03:00",
                "2017-10-20T16:07:57.717+03:00",
                "2017-06-11T16:05:46.717+03:00",
                "2017-11-11T16:05:46.717+03:00",
                "2017-07-11T16:05:46.717+03:00",
                "2017-08-11T16:05:46.717+03:00",
                "2017-01-11T16:05:46.717+03:00");
    }

}
