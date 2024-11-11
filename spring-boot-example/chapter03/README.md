# chapter03

---

### 1. @Bean
어플리케이션에 의존성을 주입하기 위해 Bean 으로 등록함
```java
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})  // 메서드와 애너테이션을 Bean 으로 등록 가능
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

### 5. @Autowired, @Qualifier
* 정의된 스프링 빈에 의존성을 주입받기 위해 사용
* 의존성을 주입하기 위해서는 주입받을 객체와 대상 객체 모두 스프링 빈 객체이어야함
* field injection, setter injection, constructor injection -> constructor injection 을 지향

### * 스프링 빈 컨테이너의 의존성 분석 과정
1. ApplicationContext 실행될 때 설정 파일 로딩
2. 컴포넌트 스캔하여 스프링 빈 설정 로딩
3. 스프링 빈 사이의 의존성 파악
4. 하위모듈에서 상위모듈 순으로 스프링 빈을 생성하고 순차적으로 의존성 주입

### 6. 스프링 빈 스코프 @Scope
* 스프링 빈 객체를 생성하는 시간부터 만든 객체가 소멸되기까지의 기간
* singleton: 기본값. ApplicationContext 가 생성되는 시점에 객체가 생성되고 종료될 때 같이 소멸됨.
  하나의 객체가 여러곳에서 공유됨
* prototype: 의존성 주입할 때마다 새로운 객체를 생성하여 주입.
  여러 객체가 빈 컨테이너에 존재함
* request: 웹 기능 한정 스코프. HTTP 요청을 처리할 때마다 새로운 객체를 생성
* session: 웹 기능 한정 스코프. HTTP Session 과 대응하는 새로운 객체 생성
* application: 웹 기능 한정 스코프. Servlet 컨텍스트와 대응하는 새로운 객체 생성
* websocket: 웹 기능 한정 스코프. Web Socket Session 과 대응하는 새로운 객체 생성

### 7. 스프링 빈 생명주기
* @Bean의 initMethod 속성에 설정된 스프링 빈을 생성한 후 실행하고, 
destroyMethod 속성에 설정된 메서드를 애플리케이션이 종료되기 전 실행함

### 8. 스프링 빈 고급 정의
* @Primary: 같은 클래스 타입인 여러 스프링 빈 중 의존성을 주입할 클래스 타입에 선언
  * spring.main.allow-bean-definition-overriding 속성은 동일한 이름의 스프링 빈에 대해 덮어쓰기 여부를 설정함
* @Lazy: 스프링 빈 컨테이너가 설정만 로딩하고 의존성 주입하는 시점에 스프링 빈 객체를 생성함(스프링 빈 객체를 지연 생성함을 의미)

---
* 스프링 빈: 객체와 이름, 클래스 타입 정보가 스프링 컨테이너로 관리되는 객체
* 자바 빈: 기본 생성자가 선언되어 있고, getter/setter 패턴으로 클래스 내부 속성에 접근할 수 있어야 함
* DTO: 소프트웨어 사이에 데이터를 전달하는 객체
* VO: 특정 데이터를 추상화하여 데이터를 표현하는 객체(DDD에서 VO는 불변 속성을 가지고 있어야 함)