package com.springtour.example.chapter05.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.springtour.example.chapter05.controller.serializer.ToDollarStringSerializer;
import com.springtour.example.chapter05.domain.HotelRoomType;
import com.springtour.example.chapter05.utils.IdGenerator;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class HotelRoomResponse {

    @JsonProperty("id") // json 내에서 다른 이름을 사용하고자 할 때
    @JsonSerialize(using = ToStringSerializer.class) // json 내에 String 으로 타입을 변경하려고 할 때
    private final Long hotelRoomId;

    private final String roomNumber;

    private final HotelRoomType hotelRoomType;

    @JsonSerialize(using = ToDollarStringSerializer.class) // BigDecimal 사용자 정의화
    private final BigDecimal originalPrice;

    private final List<Reservation> reservations;

    private HotelRoomResponse(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        this.hotelRoomId = hotelRoomId;
        this.roomNumber = roomNumber;
        this.hotelRoomType = hotelRoomType;
        this.originalPrice = originalPrice;
        reservations = new ArrayList<>();
    }

    public static HotelRoomResponse of(Long hotelRoomId, String roomNumber, HotelRoomType hotelRoomType, BigDecimal originalPrice) {
        return new HotelRoomResponse(hotelRoomId, roomNumber, hotelRoomType, originalPrice);
    }

    public void reservedAt(LocalDate reservedAt) {
        reservations.add(new Reservation(IdGenerator.create(), reservedAt));
    }

    @Getter
    private static class Reservation {

        @JsonProperty("id")
        @JsonSerialize(using = ToStringSerializer.class)
        private final Long reservationId;

        // date, calendar 객체를 원하는 포맷으로 변경하는 역할
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private final LocalDate reservedDate;

        public Reservation(Long reservationId, LocalDate reservedDate) {
            this.reservationId = reservationId;
            this.reservedDate = reservedDate;
        }
    }
}
