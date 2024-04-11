package com.stock.analysis.utils;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String ConvertDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public static <T> List<OrderSpecifier> getOrderList(Sort sort, Class<T> clazz) {
        List<OrderSpecifier> orders = new ArrayList<>();
        sort.stream().forEach(order -> {
            Order direction = order.isAscending() ? Order.ASC : Order.DESC;
            PathBuilder pathBuilder = new PathBuilder(clazz, convertLowercase(clazz.getSimpleName()));
            orders.add(new OrderSpecifier(direction, pathBuilder.get(order.getProperty())));
        });
        return orders;
    }

    public static String convertLowercase(String className) {
        return className.chars()
                .mapToObj(c -> c == className.charAt(0) ? Character.toLowerCase((char) c) : (char) c)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
