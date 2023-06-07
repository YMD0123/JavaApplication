package com.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

//(演習問題4　日付計算)
public class TestMain {
    public static void main(String[] args) throws Exception {
//---------DateUtilの試し起動用------------------
        DateUtil dd;
        dd = new DateUtil();
        dd.setDateData("20200920");
        /*
        if (dd.isToday(dd.getDateData())) {
            System.out.printf("%s は 今日です\n", dd.getDateData());
        } else {
            System.out.printf("%s は 今日ではありません\n", dd.getDateData());
        }
        if (dd.isLeapYear(dd.getDateData())) {
            System.out.printf("%s はうるう年です\n", dd.getDateData());
        } else {
            System.out.printf("%s はうるう年ではありません\n", dd.getDateData());
        }
        System.out.printf("今日は%sです\n", dd.getToday());
*/
        //todo isweekdayの不具合がある治したい
        /*
        System.out.println(dd.isHoliDay("20230103"));
        System.out.println(dd.isHoliDay("20230201"));
        System.out.println(dd.isHoliDay("20230121"));
        System.out.println(dd.isHoliDay("20230531"));
        System.out.println("--------");
        */
        //System.out.println(dd.isHoliDay("20230513"));


        //System.out.println("差分" + dd.getDayBetweenDates("20230123","20230124"));
        //System.out.println("差分" + dd.getWeekdayCnt("20230304","20230305"));
        //dd.getNationalHoliday(2023);

        /*
        String a;
        a = dd.getNextWeekday("20230526");
        System.out.println(a);
        */
        //dd.getNationalHoliday(2023);
        //System.out.println( dd.isNationalHoliday("20230103") );
        //System.out.println( dd.isHoliDay("20230103") );
//--------------------------------------
        //boolean ans;
        /*
        Task T = new Task("task1","20230525");
        ans = T.isexpired();
        System.out.println(ans);
        //Task T = new Task("task2","20230524");
        //Task T = new Task("task3","20230523");

        Task T3 = new Task("task3","20230520");
        //System.out.println(T3.countToDeadLineDate());
        int[] score = {50,70,40,80,55};
        System.out.println(T3.sumarray(score));
        */

        Task task1 = new Task("タスク1","20230526");
        task1.actual_time = 20;
        task1.scheduled_time = 30;
        Task task2 = new Task("タスク2","20230528");
        task2.actual_time = 30;
        task2.scheduled_time = 50;
        Task task3 = new Task("タスク3","20230529");
        task3.actual_time = 40;
        task3.scheduled_time = 60;
        Task[] tasks = {task1,task2,task3};
        //Task.getActualtimeof(tasks);

        System.out.println(Task.sumScheduled_time(tasks));
    }
}
