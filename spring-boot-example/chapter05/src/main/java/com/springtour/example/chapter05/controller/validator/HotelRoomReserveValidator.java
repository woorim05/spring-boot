package com.springtour.example.chapter05.controller.validator;


import com.springtour.example.chapter05.controller.HotelRoomReserveRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * 검증 조건이 복잡해져 애너테이션의 조합만으로 검증하기 어려울 때
 * validator 인터페이스를 사용하여 검증 코드 작성
 */
public class HotelRoomReserveValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return HotelRoomReserveRequest.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        HotelRoomReserveRequest request = HotelRoomReserveRequest.class.cast(target);

        if (Objects.isNull(request.getCheckInDate())) {
            errors.rejectValue("checkInDate", "NotNull", "checkInDate is null");
            return;
        }
        if (Objects.isNull(request.getCheckOutDate())) {
            errors.rejectValue("checkOutDate", "NotNull", "checkOutDate is null");
            return;
        }
        // 체크인날짜가 체크아웃날짜보다 크면 Errors 객체에 검증 실패 메시지를 입력
        if (request.getCheckInDate().compareTo(request.getCheckOutDate()) >= 0) {
            errors.rejectValue("checkOutDate", "Constraint Error", "checkOutDate is earlier than checkInDate ");
            return;
        }
    }
}
