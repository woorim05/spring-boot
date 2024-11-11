package com.springtour.example.chapter03;

import com.springtour.example.chapter03.domain.PriceUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.Locale;

@Slf4j
@SpringBootApplication
public class SpringBean09Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctxt = SpringApplication.run(SpringBean09Application.class);
        log.info("------- Done to initialize spring beans");
        // @Lazy를 사용하였기 때문에 위에 context가 로드되었지만 "initialize lazyPriceUnit" 로그가 출력되지 않았음
        PriceUnit priceUnit = ctxt.getBean("lazyPriceUnit",PriceUnit.class);
        // getBean 메서드를 사용함으로 써 lazyPriceUnit 스프링 빈을 생성하여 "initialize lazyPriceUnit" 로그가 출력됨
        log.info("Locale in PriceUnit : {}", priceUnit.getLocale().toString());
        ctxt.close();
    }

    @Bean
    @Lazy
    public PriceUnit lazyPriceUnit() {
        log.info("initialize lazyPriceUnit");
        return new PriceUnit(Locale.US);
    }
}
