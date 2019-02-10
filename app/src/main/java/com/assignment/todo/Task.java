package com.assignment.todo;

public class Task {

    private String task;
    private String date;

    public Task(String task, String date) {
        this.task = task;
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public String getDate() {
        return date;
    }
}
