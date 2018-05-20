package com.springboot.racemanage.util;


import org.junit.jupiter.api.Test;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParserFactory;

import java.util.Map;

public class ToolsTest {

    String json = "{\n" +
            "  \"result\": {\n" +
            "    \"id\": 2,\n" +
            "    \"stuUuid\": \"951753\",\n" +
            "    \"stuEmail\": \"1031145353111@qq.commm\",\n" +
            "    \"stuNumber\": \"201470024136\",\n" +
            "    \"stuName\": \"李自豪\",\n" +
            "    \"stuPhone\": \"123456789\",\n" +
            "    \"stuPassword\": \"123456asd\",\n" +
            "    \"stuDescription\": \"无法描述\",\n" +
            "    \"stuDuty\": \"不知道是一个好的worker还是开发者\",\n" +
            "    \"stuStatus\": 1,\n" +
            "    \"stuGender\": \"男\",\n" +
            "    \"photo\": \"\"\n" +
            "  },\n" +
            "  \"code\": 1,\n" +
            "  \"msg\": null\n" +
            "}";

    @Test
    public void transJson() {
        Map map = JsonParserFactory.getJsonParser().parseMap(json);
        GsonJsonParser gsonJsonParser = new GsonJsonParser();

//        System.out.println(map);
//
//        Map<String,String> re = (Map<String, String>) map.get("result");
////        System.out.println(re);
//        String ss = re.get("stuUuid");
//        System.out.println("stuUuid : " + ss);


    }

}
