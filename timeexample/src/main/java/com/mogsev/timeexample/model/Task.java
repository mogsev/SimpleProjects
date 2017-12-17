package com.mogsev.timeexample.model;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Task {

    private int id;
    private String startAt;
    private String finishAt;

    public Task() {

    }

    public Task(int id, String startAt, String finishAt) {
        this.id = id;
        this.startAt = startAt;
        this.finishAt = finishAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(String finishAt) {
        this.finishAt = finishAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", startAt='" + startAt + '\'' +
                ", finishAt='" + finishAt + '\'' +
                '}';
    }

}
