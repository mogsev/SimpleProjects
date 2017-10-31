package com.mogsev.simpleprojects.mock;

import com.mogsev.simpleprojects.data.model.Task;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class MockTask {

    public static List<Task> getFakeTasks() {
        Timber.i("getFakeTasks");
        List<Task> tasks = new ArrayList<>();

        // fill list of tasks
        Task task = null;
        task = new Task();
        task.setId(1);
        task.setTitle("First task");
        task.setDateFrom(1509567547);
        task.setDateTo(1509653947);
        tasks.add(task);
        task = new Task();
        task.setId(2);
        task.setTitle("Second task");
        task.setDateFrom(1509653947);
        task.setDateTo(1509740347);
        tasks.add(task);
        task = new Task();
        task.setId(3);
        task.setTitle("Third task");
        task.setDateFrom(1509740347);
        task.setDateTo(1509826747);
        tasks.add(task);
        task = new Task();
        task.setId(4);
        task.setTitle("Fourth task");
        task.setDateFrom(1509826747);
        task.setDateTo(1509913147);
        tasks.add(task);

        return tasks;
    }

}
