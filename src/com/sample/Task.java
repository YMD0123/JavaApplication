package com.sample;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Task {
    //値が不変ならfinalを使うと変わらなくなる
    int actual_time;
    int scheduled_time;
    String deadLineDate;
    String taskName;
    Status status;
    DateUtil dd = new DateUtil();

    public Task(String name,String taskDeadline){
        this.taskName       = name;
        this.deadLineDate   = taskDeadline;
        this.actual_time    = 0;
        this.scheduled_time = 0;
        this.status         = status.UNHANDLED;
    }


    public int getScheduled_time(){
        return this.scheduled_time;
    }



    public boolean isexpired() {
        //今日で期限切れならtrue
        boolean ans;
        int year,month,day;

        //今日の取得
        Calendar cl = Calendar.getInstance();
        Date date1 = cl.getTime();

        //期日の取得
        year  = Integer.parseInt(this.deadLineDate.substring(0,4));
        month = Integer.parseInt(this.deadLineDate.substring(4,6));
        day   = Integer.parseInt(this.deadLineDate.substring(6,8));
        cl.set(year,month-1,day);
        Date date2 = cl.getTime();
        //System.out.println(this.taskName);
/*
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.after(date2));
*/
        ans = date1.after(date2);
        return ans;
    }

    public boolean isDeadLineDate(){
        //今日ならtrue
        boolean ans = false;
        int year,month,day;

        //今日の取得
        Calendar cl = Calendar.getInstance();
        Date date1 = cl.getTime();

        //期日の取得
        year  = Integer.parseInt(this.deadLineDate.substring(0,4));
        month = Integer.parseInt(this.deadLineDate.substring(4,6));
        day   = Integer.parseInt(this.deadLineDate.substring(6,8));
        cl.set(year,month-1,day);
        Date date2 = cl.getTime();
/*
        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date1.after(date2));
*/
        if(date1.compareTo(date2) == 0){
            ans = true;
        }

        return ans;
    }

    public int countToDeadLineDate() throws ParseException {
        return dd.getDayBetweenDates(dd.getToday(),this.deadLineDate);
    }

    public int sumarray(int[]  box){
        int sum = 0;
        for (int j : box) {
            sum = sum + j;
        }
        return sum;
    }

    public static int getActualtimeof(Task[] tasks)
    {
        int i = 0;
        for(Task taskbox:tasks){
            i += taskbox.actual_time;
        }
        return i;
    }

    public static int sumScheduled_time(Task[] tasks) {
        /*
        List<Task> taskList = List.of(tasks);
        // streamの取得
        Stream<Task> taskStream = taskList.stream();
        IntStream stream2 = taskStream.mapToInt(Task::getScheduled_time);// 中間操作
        int a = stream2.sum(); // 終端操作
        */

        return List.of(tasks).stream().mapToInt(Task::getScheduled_time).sum();
    }


}
