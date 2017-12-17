package com.mogsev.timeexample.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mogsev.timeexample.model.Task;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import io.reactivex.Observable;
import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public final class TasksHelper {

    private TasksHelper() {
        // empty
    }

    @Nullable
    public static List<Task> fetchTasksOfCurrentWeek(@NonNull List<Task> tasks) {
        Timber.i("fetchTasksOfCurrentWeek");
        DateTime now = DateTime.now();
        int week = now.getWeekOfWeekyear();
        Timber.i("Week number %s", week);
        final List<Task> list = new ArrayList<>();
        Observable.fromIterable(tasks)
                .filter(task -> {
                    DateTime startAt = new DateTime(task.getStartAt());
                    return startAt.getWeekOfWeekyear() == week;
                })
                .toList()
                .subscribe(
                        t -> list.addAll(t),
                        throwable -> Timber.e(throwable));

        return list;
    }

    public static boolean checkInnerList(List<List<Task>> calendar, int posX, int fromY, int toY) {
        Timber.i("checkInnerList: posX: %s; fromY: %s; toY: %s; %s", posX, fromY, toY, calendar);
        for (int i = fromY; i <= toY; i++) {
            List<Task> list = calendar.get(i);
            if (list.get(posX) != null) return true;
        }

        return false;
    }

    public static void addTask(List<List<Task>> calendar, Task task, int posX, int fromY, int toY) {
        for (int i = fromY; i <= toY; i++) {
            List<Task> list = calendar.get(i);
            list.set(posX, task);
        }
    }

    public static void addNewInnerList(List<List<Task>> calendar, Task task, int fromY, int toY) {
        int posX = calendar.get(0).size();
        for (List<Task> list : calendar) {
            list.add(null);
        }
        addTask(calendar, task, posX, fromY, toY);
    }

    public static List<List<Task>> fetchCalendarOfWeek(@NonNull List<Task> tasks) {
        Timber.i("fetchCalendarOfWeek");
        List<List<Task>> calendar = getEmptyCalendarOfWeek();

        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            Timber.i("Task: %s", task);
            DateTime startAt = new DateTime(task.getStartAt());
            int dayOfWeek = startAt.getDayOfWeek();
            DateTime finishAt = new DateTime(task.getFinishAt());
            Period period = new Period(startAt, finishAt);
            int days = period.getDays();

            int fromY = dayOfWeek - 1;
            int toY = fromY + days;
            if (toY > 6) toY = 6;
            for (int y = fromY; y <= toY; y++) {
                List<Task> list = calendar.get(y);
                boolean isAdded = false;
                for (int posX = 0; posX < list.size(); posX++) {
                    if (list.get(posX) == null) {
                        if (!checkInnerList(calendar, posX, fromY, toY)) {
                            addTask(calendar, task, posX, fromY, toY);
                            isAdded = true;
                            break;
                        }
                    }
                }

                if (!isAdded) {
                    addNewInnerList(calendar, task, fromY, toY);
                }

                break;
            }

            // remove task
            iterator.remove();
        }

        return calendar;
    }

    public static List<List<Task>> getEmptyCalendarOfWeek() {
        List<List<Task>> list = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            List<Task> ls = new ArrayList<>();
            ls.add(null);
            list.add(ls);
        }

        return list;
    }

    public static void showCalendar(List<List<Task>> calendar) {
        for (List<Task> list : calendar) {
            int x = list.size();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < x; i++) {
                Task task = list.get(i);
                if (task == null) {
                    builder.append("0");
                } else {
                    builder.append(task.getId());
                }
                builder.append("|");
            }

            Timber.i("DayOfWeek:  %s", builder.toString());
        }
    }

}
