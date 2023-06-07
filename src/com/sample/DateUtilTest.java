package com.sample;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {
    DateUtil dd;
    DateUtilTest(){
        dd = new DateUtil();
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void setDateData() {
        dd.setDateData(null);
        assertNull(dd.getDateData());
    }

    @org.junit.jupiter.api.Test
    void getDateData() {
        dd.setDateData("20200916");
        assertEquals("20200916", dd.getDateData());
    }

    @org.junit.jupiter.api.Test
    void getToday() {
        Calendar cal = Calendar.getInstance();
        String toDay = String.format("%02d", cal.get(Calendar.YEAR)) +
                String.format("%02d", cal.get(Calendar.MONTH) + 1) +
                String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(toDay, dd.getToday());
    }

    @org.junit.jupiter.api.Test
    void isToday() {
        assertTrue(dd.isToday(dd.getToday()));
    }

    @org.junit.jupiter.api.Test
    void isLeapYear() {
        assertTrue(dd.isLeapYear("20200916"));
        assertFalse(dd.isLeapYear("20210916"));
        assertTrue(dd.isLeapYear("20000916"));
        assertTrue(dd.isLeapYear(2020));
        assertFalse(dd.isLeapYear(2021));
        assertTrue(dd.isLeapYear(20000));
    }

    @org.junit.jupiter.api.Test
    void testIsLeapYear() {
    }

    @Test
    void getDayBetweenDates() throws ParseException {
        assertEquals(1,dd.getDayBetweenDates("20230304","20230305"));
        assertEquals(5,dd.getDayBetweenDates("20230305","20230310"));
        assertEquals(14,dd.getDayBetweenDates("20230301","202303015"));
        assertEquals(30,dd.getDayBetweenDates("20230101","20230131"));
    }

    @Test
    void isWeekDay() throws ParseException, IOException {

        assertTrue(dd.isWeekDay("20230801"));
        assertTrue(dd.isWeekDay("20230901"));
        assertTrue(dd.isWeekDay("20230511"));
        assertTrue(dd.isWeekDay("20230601"));
        assertTrue(dd.isWeekDay("20230501"));
        //assertTrue(dd.isWeekDay("20230103"));

        assertFalse(dd.isWeekDay("20230408"));
        assertFalse(dd.isWeekDay("20230513"));
        assertFalse(dd.isWeekDay("20230514"));
        assertFalse(dd.isWeekDay("20230701"));
        assertFalse(dd.isWeekDay("20231001"));

    }

    @Test
    void isHoliDay() throws IOException {
        assertTrue(dd.isHoliDay("20230513"));
        assertTrue(dd.isHoliDay("20230514"));
        assertTrue(dd.isHoliDay("20230408"));
        assertTrue(dd.isHoliDay("20230701"));
        assertTrue(dd.isHoliDay("20231001"));

        assertTrue(dd.isHoliDay("20230101"));
        assertTrue(dd.isHoliDay("20230223"));
        assertTrue(dd.isHoliDay("20230321"));
        assertTrue(dd.isHoliDay("20230918"));
        assertTrue(dd.isHoliDay("20231123"));

        //-----
        assertFalse(dd.isHoliDay("20230511"));
        assertFalse(dd.isHoliDay("20230501"));
        assertFalse(dd.isHoliDay("20230601"));
        assertFalse(dd.isHoliDay("20230801"));
        assertFalse(dd.isHoliDay("20230901"));

        assertFalse(dd.isHoliDay("20230105"));
        assertFalse(dd.isHoliDay("20230111"));
        assertFalse(dd.isHoliDay("20230508"));
        assertFalse(dd.isHoliDay("20231224"));
        //assertFalse(dd.isHoliDay("20230103"));
    }

    @Test
    void getWeekdayCnt() throws ParseException, IOException {
        //修正の可能性アリ
        assertEquals(0,dd.getWeekdayCnt("20230304","20230305"));
        assertEquals(4,dd.getWeekdayCnt("20230305","20230310"));
        assertEquals(10,dd.getWeekdayCnt("20230301","202303015"));
        assertEquals(20,dd.getWeekdayCnt("20230101","20230131"));
    }

    @Test
    void getNextWeekday() throws IOException {
        assertEquals("20230523",dd.getNextWeekday("20230522"));
        assertEquals("20230529",dd.getNextWeekday("20230526"));
        assertEquals("20230601",dd.getNextWeekday("20230531"));
        assertEquals("20230529",dd.getNextWeekday("20230526"));
        assertEquals("20230529",dd.getNextWeekday("20230526"));
        assertEquals("20230529",dd.getNextWeekday("20230526"));
    }

    @Test
    void getNationalHoliday() throws Exception {
        String[] expected = {
                "20230101",
                "20230102",
                "20230109",
                "20230211",
                "20230223",
                "20230321",
                "20230429",
                "20230503",
                "20230504",
                "20230505",
                "20230717",
                "20230811",
                "20230918",
                "20230923",
                "20231009",
                "20231103",
                "20231123"
        };
        assertArrayEquals(expected, DateTeach.getNationalHoliday(2023));

    }

    @Test
    void isNationalHoliday() throws IOException {
        assertTrue(dd.isNationalHoliday("20230101"));
        assertTrue(dd.isNationalHoliday("20230223"));
        assertTrue(dd.isNationalHoliday("20230321"));
        assertTrue(dd.isNationalHoliday("20230918"));
        assertTrue(dd.isNationalHoliday("20231123"));

        assertFalse(dd.isNationalHoliday("20230103"));
        assertFalse(dd.isNationalHoliday("20230110"));
        assertFalse(dd.isNationalHoliday("20230506"));

    }
}