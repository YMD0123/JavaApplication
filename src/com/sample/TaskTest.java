package com.sample;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void isexpired() {
        //今日は24日
        Task T1 = new Task("task1","20230523");
        assertTrue(T1.isexpired());
        Task T2 = new Task("task2","20230524");
        assertFalse(T2.isexpired());
        Task T3 = new Task("task3","20230525");
        assertFalse(T3.isexpired());
    }

    @Test
    void isDeadLineDate() {
        //24日にやった
        Task T1 = new Task("task1","20230523");
        assertFalse(T1.isDeadLineDate());
        Task T2 = new Task("task2","20230524");
        assertTrue(T2.isDeadLineDate());
        Task T3 = new Task("task3","20230525");
        assertFalse(T3.isDeadLineDate());
    }

    @Test
    void countToDeadLineDate() throws ParseException {
        //24日にやった
        Task T1 = new Task("task1","20230524");
        assertEquals(0,T1.countToDeadLineDate());
        Task T2 = new Task("task2","20230525");
        assertEquals(1,T2.countToDeadLineDate());
        Task T3 = new Task("task3","20230527");
        assertEquals(3,T3.countToDeadLineDate());
    }

    @Test
    void sumarray() {
        Task T1 = new Task("task1","20230524");
        int[] score1 = {50,70,40,80,55};
        assertEquals(295,T1.sumarray(score1));
        int[] score2 = {50,70,40,80,55,15};
        assertEquals(310,T1.sumarray(score2));
        int[] score3 = {50,70};
        assertEquals(120,T1.sumarray(score3));
    }

    @Test
    void getActualtimeof() {

        Task task1 = new Task("タスク1","20230526");
        task1.actual_time = 20;
        Task task2 = new Task("タスク2","20230528");
        task2.actual_time = 30;
        Task task3 = new Task("タスク3","20230529");
        task3.actual_time = 40;
        Task[] tasks = {task1,task2,task3};
        assertEquals(90,Task.getActualtimeof(tasks));

    }
}