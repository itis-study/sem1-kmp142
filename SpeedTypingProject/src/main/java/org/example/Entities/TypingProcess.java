package org.example.Entities;
import java.time.LocalTime;
import java.util.UUID;

public class TypingProcess {
    String id = UUID.randomUUID().toString();
    private LocalTime startTime;
    private LocalTime endTime;
    private Double accuracy;
    private Task task;
    private User performer;

    public User getPerformer() {
        return performer;
    }

    public String getId() {
        return id;
    }

    public TypingProcess(){}

    public TypingProcess(String id, LocalTime startTime, LocalTime endTime, Double accuracy, Task task, User performer) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accuracy = accuracy;
        this.task = task;
        this.performer = performer;
    }

    public TypingProcess(String id, LocalTime startTime, LocalTime endTime, Double accuracy, Task task) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accuracy = accuracy;
        this.task = task;

    }

    public void setPerformer(User performer) {
        this.performer = performer;
    }

    public Task getTask() {
        return task;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }

    public void setTask(Task task) {
        this.task = task;
    }


}
