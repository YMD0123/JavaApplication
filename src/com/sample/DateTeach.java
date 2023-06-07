package com.sample;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DateTeach {
    /**
     * 指定日が月曜日から金曜日かどうかを返す
     * @param yyyymmdd - format"YYYYMMDD"
     * @return true:引数の日が月曜日から金曜日ならば、false:それ以外
     * @throws ParseException 日付がYYYYMMDDの形でない時
     */
    public static boolean isWeekDay(String yyyymmdd) throws ParseException {
        final Calendar calendar = getCalendarFor(yyyymmdd);
        final int TUESDAY_CODE = 2;
        final int FRIDAY_CODE = 6;
        final int dayOfWeekCode = calendar.get(Calendar.DAY_OF_WEEK);
        if(TUESDAY_CODE <= dayOfWeekCode && dayOfWeekCode <= FRIDAY_CODE ) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 指定日が土曜日か日曜日かどうかを返す
     * @param yyyymmdd - format"YYYYMMDD"
     * @return true:引数の月が土曜日、日曜日ならば、false:それ以外
     * @throws Exception 日付がYYYYMMDDの形でない時
     */
    public static boolean isSaturdayOrSunday(String yyyymmdd) throws Exception{
        return !isWeekDay(yyyymmdd);
    }
    /**
     * URLからその年の祝日のリストを取得する
     * @param yyyy　その年の年
     * @return その年の祝日のリスト
     * @throws Exception 日付がYYYYの形でない時
     */
    public static String[] getNationalHoliday(int yyyy) throws Exception {
        final String responseBody = getResponseBodyTo("https://holidays-jp.github.io/api/v1/" + yyyy + "/date.json");
        final ObjectMapper objectMapper = new ObjectMapper();
        final JsonNode jsonNode = objectMapper.readTree(responseBody);
        return getKeyStringArr(jsonNode);
    }
    /**
     * jsonのキー値のリストを取得する
     * @param jsonNode　キー値を取得したいjson
     * @return jsonのキー値
     */
    private static String[] getKeyStringArr(JsonNode jsonNode) {
        final int size = jsonNode.size();
        final String[] arr = new String[size];
        final Iterator<String> iterator = jsonNode.fieldNames();
        for (int i = 0; iterator.hasNext(); i++) {
            arr[i] = iterator.next().replace("-", "/");
        }
        return arr;
    }

    public static String getResponseBodyTo(String url) throws Exception {
        final HttpClient client = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .build();
        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
    /**
     * 指定日が祝日かどうかを返す
     * @param yyyymmdd - format"YYYYMMDD"
     * @return true:引数の日が祝日、false:それ以外
     * @throws Exception 日付がYYYYMMDDの形でない時
     */
    public static boolean isNationalHoliday(String yyyymmdd) throws Exception {
        final int year = getYearOf(yyyymmdd);
        final String[] nationalHolidays = getNationalHoliday(year);
        final ArrayList<String> arrDates = new ArrayList<>(Arrays.asList(nationalHolidays));
        return arrDates.contains(yyyymmdd);
    }
    /**
     * 指定日から年だけを取得する
     * @param yyyymmdd - format"YYYYMMDD"
     * @return 渡した日の年を返す
     */
    private static int getYearOf(String yyyymmdd) {
        return Integer.parseInt(yyyymmdd.substring(0, 4));
    }
    /**
     * calendarクラスの値をセットする
     * @param yyyymmdd - format"YYYYMMDD"
     * @return 渡した日付のcalendar
     * @throws ParseException 日付がYYYYの形でない時
     */
    private static Calendar getCalendarFor(String yyyymmdd) throws ParseException {
        final Date date = DateTeach.validateAndParseDate(yyyymmdd);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    /**
     * 指定日が休日(土日 or 祝日)かどうかを返す
     * @paramete yyyymmdd - format"YYYYMMDD"
     * @return true:引数の月が休日ならば、false:それ以外
     * @throw ParseException 日付がYYYYMMDDの形でない時
     */
    public static boolean isHoliday(String yyyymmdd) throws Exception {
        return isNationalHoliday(yyyymmdd) || isSaturdayOrSunday(yyyymmdd);
    }


    public static Date validateAndParseDate(String inputDate) throws ParseException {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        sdf.setLenient(false);
        try {
            return sdf.parse(inputDate);
        } catch (ParseException e) {
            throw new ParseException("Invalid date format", 0);
        }
    }

    public static Date addDaysToDate(Date date, int days) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}