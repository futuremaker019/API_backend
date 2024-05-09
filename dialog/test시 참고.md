### Test 시 확인사항

test 시 에러가 나면 `application.yml` 설정을 확인하자

로컬에서 직접 h2 데이터베이스 서버를 켜고 확인하고 싶다면 `@AutoConfigureTestDatabase`를 활성화하여 H2를 키고 테스트 하자 (물론 서버 정보는 H2 데이터베이스 정보는 `yml`에 작성해야한다.).<br>
`h2:mem`으로 테스트하고 싶다면 위의 설정은 무시해도 된다. 

```java
@DisplayName("JPA 연결 테스트")
@Import({JpaRepositoryTest.TestJpaConfig.class})
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JpaRepositoryTest {
    
    //...
    
}
```
또한 더미 테스트를 넣고 테스트를 하고 싶다면 아래 `defer-datasource-initialization`를 `true`로 `sql.init.mode`를 `always`로 변경하여 테스트 하자 

```yaml
spring:
  datasource:
    url:
    username: 
    password: 
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    open-in-view: false
    defer-datasource-initialization: true
    hibernate.ddl-auto: update
    show-sql: true
    properties:
      hibernate.format_sql: true
      hibernate.default_batch_fetch_size: 100
#    database-platform: org.hibernate.dialect.MySQL8Dialect
  sql.init.mode: always
```
참고 블로그 : [https://velog.io/@jwkim/spring-boot-datajpatest-error](https://velog.io/@jwkim/spring-boot-datajpatest-error)