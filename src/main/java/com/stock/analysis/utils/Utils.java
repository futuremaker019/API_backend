package com.stock.analysis.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class Utils {

    /**
     * TODO: dataTime 형식으로 변환하여 화면에 보내줘야 함
     * @Bean 으로 할지 싱글턴으로 할지 고민중
     */

    public static String ConvertDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("YYYY.MM.dd"));
    }
}
