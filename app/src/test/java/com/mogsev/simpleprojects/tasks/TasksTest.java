package com.mogsev.simpleprojects.tasks;

import com.mogsev.simpleprojects.data.model.Task;
import com.mogsev.simpleprojects.mock.MockTask;



import org.junit.Test;


import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class TasksTest {

    @Test
    public void onFillTaskTableTest() {
        int days[] = {1, 2, 3, 4, 5, 6, 7};
        List<Task> listTasks = MockTask.getFakeTasks();

        Task tasks[] = new Task[7];


        Task task = listTasks.get(0);

        LocalDate from = LocalDate.of(2017, 11, 2);
        LocalDate to = LocalDate.of(2017, 11, 4);

        long p2 = ChronoUnit.DAYS.between(from, to) + 1;
        System.out.println("Count of days: " + p2);

        int d = from.getDayOfWeek().getValue();

        for (int i = 1; i <= tasks.length; i++) {
            if (i == d) {
                tasks[i] = task;
            }

        }

    }
}
