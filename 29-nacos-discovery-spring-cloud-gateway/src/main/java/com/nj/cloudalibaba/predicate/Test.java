package com.nj.cloudalibaba.predicate;

import java.time.LocalTime;
import java.time.ZonedDateTime;

/**
 * 时间日期查看
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(ZonedDateTime.now());

//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
//        String nowTime = dateTimeFormatter.format(ZonedDateTime.now());
//        System.out.println(nowTime);


        LocalTime now = LocalTime.now();
        //System.out.println(now);
        //System.out.println(now.plusHours(12));
    }
}