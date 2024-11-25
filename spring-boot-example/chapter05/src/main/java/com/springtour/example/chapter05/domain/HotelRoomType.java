package com.springtour.example.chapter05.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum HotelRoomType {
    SINGLE("single"),
    DOUBLE("double"),
    TRIPLE("triple"),
    QUAD("quad");

    private static final Map<String, HotelRoomType> paramMap = Arrays.stream(HotelRoomType.values())
            .collect(Collectors.toMap(
                    HotelRoomType::getParam,
                    Function.identity()
            ));
    private final String param;

    HotelRoomType(String param) {
        this.param = param;
    }

    @JsonCreator // 언마셜링 과정에서 값 변환에 사용되는 메서드를 지정하는 애너테이션
    public static HotelRoomType fromParam(String param) {
        return Optional.ofNullable(param)
                .map(paramMap::get)
                .orElseThrow(() -> new IllegalArgumentException("param is not valid"));
    }

    @JsonValue // 마셜링 과정에서 값 변환에 사용되는 메서드를 지정하는 애너테이션
    public String getParam() {
        return this.param;
    }
}
