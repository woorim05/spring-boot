package com.springtour.example.chapter05.controller;

import com.springtour.example.chapter05.domain.HotelRoomType;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@ToString
public class HotelRoomUpdateRequest {

    // 컨트롤러에서 @Valid 어노테이션과 함께 사용해야 작동함
    @NotNull(message = "roomType can't be null")
    private HotelRoomType roomType;

    @NotNull(message = "originalPrice can't be null")
    @Min(value = 0, message = "originalPrice must be larger than 0")
    private BigDecimal originalPrice;

}
