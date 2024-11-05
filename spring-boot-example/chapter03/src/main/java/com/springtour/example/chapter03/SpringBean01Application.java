package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Locale;

@Slf4j
@SpringBootApplication // 이 어노테이션 안에 @Configuration, @ComponentScan 이 존재하여 자바설정이 됨
public class SpringBean01Application {

    public static void main(String[] args) {
        // 어플리케이션을 실행하여 ctxt에 대입
        ConfigurableApplicationContext ctxt =
                SpringApplication.run(SpringBean01Application.class, args);

        // context에 주입된 bean을 읽어서 저장
        PriceUnit defaultPriceUnit = ctxt.getBean("priceUnit", PriceUnit.class);
        log.info("Price #1 : {}", defaultPriceUnit.format(BigDecimal.valueOf(10.2)));

        PriceUnit wonPriceUnit = ctxt.getBean("wonPriceUnit", PriceUnit.class);
        log.info("Price #2 : {}", wonPriceUnit.format(BigDecimal.valueOf(1000)));

        // 어플리케이션 종료
        ctxt.close();
    }

    @Bean(name = "priceUnit")
    public PriceUnit dollarPriceUnit() {
        return new PriceUnit(Locale.US); // 어플리케이션 실행 될 때 스프링 빈 객체로 설정됨
    }

    @Bean
    public PriceUnit wonPriceUnit() {
        return new PriceUnit(Locale.KOREA);
    }
}
