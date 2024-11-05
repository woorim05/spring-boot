# chapter03

---

### 1. @Bean
어플리케이션에 의존성을 주입할 때 사용함.
```java
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})  // 메서드와 애너테이션에 Bean 주입
@Retention(RetentionPolicy.RUNTIME) // 런타임 시점까지 @Bean 코드 존재
@Documented
public @interface Bean {
    // 의존성 이름 설정(name과 value 서로 참조하므로 둘 중 하나만 설정해도 됨)
    @AliasFor("name") // 변수를 참조 하는 이름
    String[] value() default {};

    @AliasFor("value")
    String[] name() default {};
   
    @Deprecated
    Autowire autowire() default Autowire.NO;

    boolean autowireCandidate() default true;

    String initMethod() default "";

    String destroyMethod() default "(inferred)";
}
```

### 2. @Configuration
* 자바 설정을 포함하고 있는 자바 설정 클래스를 정의하는 데 사용함
* @SpringBootApplication 애너테이션 안에 @Configuration 이 존재하여 어플리케이션을 실행 시킬 때 따로 정의하지 않아도 됨

### 3. @ComponentScan
* 설정된 패키지 경로에 포함된 자바 설정 클래스들과 스테레오 타입 애너테이션들이 선언된 클래스들을 스캔하여 해당 클래스에 빈 설정이 있으면 빈으로 생성함
* @Configuration 과 함께 사용해야 정상 동작함
* @SpringBootApplication 애너테이션 안에 @Configuration 이 존재하여 어플리케이션을 실행 시킬 때 따로 정의하지 않아도 됨

### 4. stereo type 애너테이션
* @Component: 클래스를 스프링 빈으로 정의하는 데 사용하느 가장 일반적인 애너테이션(아래 애너테이션은 @Component 에서 파생 됨)
* @Controller: 사용자 요청 처리하고, 비즈니스 로직을 포함한 다른 컴포넌트에 전달하는 역할. MVC 프레임워크에서 사용하며, HTTP 프로토콜 처리, View를 처리하는 클래스에 정의
* @Service: 사용자 행위에 해당하는 기능을 추상화하고, 여러 객체에서 일어나는 행위를 하나의 기능으로 모아서 처리하는 클래스에 정의
* @Repository: 객체를 저장/조회 하는 행위를 담당하는 클래스에 정의