package com.mogsev.timeexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mogsev.timeexample.fakes.FakeHelper;
import com.mogsev.timeexample.model.Task;
import com.mogsev.timeexample.utils.TasksHelper;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Timber.i("onCreate");

        List<Task> tasks = TasksHelper.fetchTasksOfCurrentWeek(FakeHelper.getFakeTasks());
        Timber.i("Tasks: %s", tasks);

        List<List<Task>> calendar = TasksHelper.fetchCalendarOfWeek(FakeHelper.getFakeTasks());
        // show calendar
        TasksHelper.showCalendar(calendar);

        // get day
        fetchOneWeak();
    }

    private void fetchOneWeak() {
        Timber.i("fetchOneWeak");
        DateTime now = DateTime.now();

        DateTime dateTime = new DateTime("2017-12-11T14:10:46.717+03:00");
        int week = dateTime.getWeekOfWeekyear();
        Timber.i("Week: %s", week);
        DateTime min = dateTime.dayOfWeek().withMinimumValue();
        DateTime max = dateTime.dayOfWeek().withMaximumValue();
        Timber.i("DayOfWeek: %s, %s", min, max);


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


}
