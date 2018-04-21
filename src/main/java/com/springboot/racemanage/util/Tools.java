package com.springboot.racemanage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Tools {

    /**
     * begainDate: 开始日期  endDate:结束日期
     * @param dateString 日期
     * @return  开始日期和结束日期的map
     * @throws ParseException 先不管，往外抛
     */
    public static Map<String , Date> dateRangeTransform(String dateString) throws ParseException {
        String[] st = dateString.split("-");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date beginDate = simpleDateFormat.parse(st[0]);
        Date endDate = simpleDateFormat.parse(st[1]);
        Map<String, Date> dateMap = new HashMap<>();
        dateMap.put("begainDate", beginDate);
        dateMap.put("endDate", endDate);
        return dateMap;
    }
}
