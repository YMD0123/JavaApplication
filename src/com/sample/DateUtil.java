package com.sample;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.net.URL;
import java.util.concurrent.TimeUnit;


/**
 * 日付データクラス
 */
public class DateUtil {
    private String dateData = null;
    /**dateData setter*/
    public void setDateData(String yyyymmdd) {
        dateData = yyyymmdd;
    }
    /**dateData getter*/
    public String getDateData() {
        return dateData;
    }
    /**今日の日付取得**/
    public String getToday() {
        Calendar cal = Calendar.getInstance();
        return String.format("%02d", cal.get(Calendar.YEAR)) +
                String.format("%02d", cal.get(Calendar.MONTH) + 1) +
                String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
        //カレンダークラスの月は0-11の間で返されるから+1しなければならない
    }
    /**今日か否かの判定*/
    public boolean isToday(String yyyymmdd) {

        return yyyymmdd.equals(getToday());
    }
    /**うるう年判定 String*/
    public boolean isLeapYear(String yyyymmdd) {
        return isLeapYear(Integer.parseInt(yyyymmdd.substring(0,4)));
    }
    /**うるう年判定 int*/
    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else return year % 4 == 0;
    }

    public int getDayBetweenDates (String date1,String date2) throws ParseException{
        int ans;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date11 = new Date(sdf.parse(date1).getTime());
        Date date22 = new Date(sdf.parse(date2).getTime());

        ans = (int)TimeUnit.DAYS.convert(date22.getTime() - date11.getTime(), TimeUnit.MILLISECONDS);

        return ans;
    }

    public boolean isWeekDay(String date1) throws IOException {
        int weeknum;
        int year,month,day;
        boolean ans = true;

        Calendar cal = Calendar.getInstance();
        year  = Integer.parseInt(date1.substring(0,4));
        month = Integer.parseInt(date1.substring(4,6));
        day   = Integer.parseInt(date1.substring(6,8));

        //System.out.println(year + " "+ month + " " + day);

        cal.set(year,month+1,day);
        weeknum = cal.get(Calendar.DAY_OF_WEEK);
        //System.out.println(cal.getTime() + ","+weeknum);

        weeknum = weeknum + 2;

        if(weeknum > 7){
            weeknum = weeknum - 7;
        }
        //System.out.println(weeknum);
        if((weeknum == Calendar.SUNDAY || weeknum == Calendar.SATURDAY ) || isNationalHoliday(date1)){
            ans = false;
        }

        return ans;
    }

    public boolean isHoliDay(String date1) throws IOException {
        return !isWeekDay(date1);
    }

    public int getWeekdayCnt(String day1,String day2) throws ParseException, IOException {
        int ans=0;
        int getcnt;
        int year,month,day;
        String sday;
        boolean getyn;
        getcnt = getDayBetweenDates(day1,day2);

        year  = Integer.parseInt(day1.substring(0,4));
        month = Integer.parseInt(day1.substring(4,6));
        day   = Integer.parseInt(day1.substring(6,8));

        for(int i = 0; i < getcnt;i++){
            sday = String.format("%04d", year) +String.format("%02d", month) +String.format("%02d", day+i);
            //System.out.println(sday);
            getyn = isWeekDay(sday);
            if(getyn){
                ans++;
            }
        }

        return ans;
    }

    public String getNextWeekday(String date1) throws IOException {
        String ndate = "";

        int year, month, day;
        int nyear, nmonth, nday;
        int gyear, gmonth, gday;
        boolean flg = false;

        //System.out.println("a");
        year = Integer.parseInt(date1.substring(0, 4));
        month = Integer.parseInt(date1.substring(4, 6));
        day = Integer.parseInt(date1.substring(6, 8));

        //System.out.println(year +""+month+""+day);
        int i  = 0;
        while(!flg) {
            i++;
            ndate = String.format("%04d", year) + String.format("%02d", month) + String.format("%02d", day + i);
            flg = isWeekDay(ndate);
            //System.out.println(ndate);
        }
//-------次の日を計算+セット-------
        nyear = Integer.parseInt(ndate.substring(0, 4));
        nmonth = Integer.parseInt(ndate.substring(4, 6));
        nday = Integer.parseInt(ndate.substring(6, 8));

        Calendar cal = Calendar.getInstance();
        cal.set(nyear,nmonth+1,nday);
//--------------
        gyear = cal.get(Calendar.YEAR);
        gmonth = cal.get(Calendar.MONTH);
        gday = cal.get(Calendar.DATE);

        ndate = String.format("%04d", gyear) +String.format("%02d", gmonth-1) +String.format("%02d", gday);

        return ndate;
    }

    public static  String[] getNationalHoliday(int yyyy) throws IOException {
        String[] strlist;
        String jtext = "";
        String atext = "";
        boolean flg = true;

        URL url = new URL("https://holidays-jp.github.io/api/v1/" + yyyy + "/date.json");
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is);
        int i = isr.read();

        while(i != -1) {
            //System.out.print((char) i);
            //System.out.println(i + "→" + (char)i);
            jtext = jtext + (char)i;

            if(i == 58){
                flg = false;
            }
            else if(i == 44){
                flg = true;
            }
            
            if(flg && i != 32 && i != 10 && i != 123 && i != 45){
                atext = atext + (char)i;
            }

            i = isr.read();
        }

        //System.out.println(atext);
        strlist = atext.split(",");
        //System.out.println(Arrays.toString(strlist));

        //ObjectMapper objmap = new ObjectMapper();
        //JsonNode jsnode = objmap.readTree(jtext);
        //System.out.println(jsnode);

        return strlist;
    }

    public static boolean isNationalHoliday(String day) throws IOException {
        boolean ans = false;
        String[] holidaybox = getNationalHoliday(2023);

        for(String holiday : holidaybox){
            //System.out.println(holiday + " " + day);
            if(holiday.equals("\"" + day + "\"")){
                ans = true;
                break;
            }
        }

        return ans;
    }


}

