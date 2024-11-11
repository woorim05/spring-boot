package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean07Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean07Application.class);
        PriceUnit priceUnit = ctxt.getBean(PriceUnit.class); // 같은 클래스 타입에 대한 의존성이 여러개 일 경우 NoUniqueBeanDefinitionException 발생함
        log.info("Locale in PriceUnit : {}", priceUnit.getLocale().toString());
        ctxt.close();
    }


    @Bean
    @Primary // 해당 애너테이션을 설정함으로써 primaryPriceUnit 스프링 빈이 주입됨
    public PriceUnit primaryPriceUnit() {
        return new PriceUnit(Locale.US);
    }

    @Bean
    public PriceUnit secondaryPriceUnit() {
        return new PriceUnit(Locale.KOREA);
    }
}
