# Springboot 3 Backend API

## Tech Spec
- Springboot 3.0.4
  - Java JDK 17
- Spring Data JPA
- QueryDSL 5.0.0 (Spring 6, Springboot 3 이상 대응)
- MapStruct 1.4.2.Final
- OpenAPI
  - SpringDoc OpenAPI
- Spring Restdocs
  - mockmvc
  - asciidoctor
- Prometheus
- Spring Validation
- Spring Security (구현 예정)
  - OAuth
  - OAuth2
- Spring Kafka (구현 예정)
- Spring Redis
  - 단순 샘플 소스 및 데이터 추가
- Spring Batch (구현 예정)
- Spring Cloud 
- kubernetes (구현 예정)
- multi database connection (구현 예정)
- 외부와의 통신
  - RestTemplate (Deprectated)
    ```
    GET http://localhost:8888/blog/naver/search/resttemplate?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10

    GET http://localhost:8888/blog/kakao/search/resttemplate?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10
    ```
  - WebClient
      ```
    GET http://localhost:8888/blog/naver/search/webclient?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10
    
    GET http://localhost:8888/blog/kakao/search/webclient?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10
    ```
  - Feign Client
    ```
    GET http://localhost:8888/blog/naver/search/feign?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10
    
    GET http://localhost:8888/blog/kakao/search/feign?query=%EC%9C%A0%EB%9F%BD&sort=accuracy&page=1&size=10
    ```

## TODO LIST
- Kotlin
- JPA/QueryDSL/JPQL, MyBatis을 이용하여 DB Handling
- Spring Security에서 OAuth2, JWT을 활용하여, Session 인증, 인가 처리
- Spring Security에서 Session, JWT을 활용하여 Session 인증, 인가 처리
- JUnit5 -> Controller, Service, Repository 레이어 마다 구현 하기
- Elastic Search를 이용하여 검색 기능 구현
- File Up/Download 기능 구현
- SSL/TLS 기반 https API Service 구현
- Text Editor 기술 구현을 위한 API 구현
- Web Sockect을 이용한 실시간 채팅 API 구현
- Jenkins(Pipeline), SonarQube, Docker, Nginx, K8s를 이용한 CI/CD 구현

# Swagger
http://localhost:8888/swagger-ui/index.html

# Openapi
http://localhost:8888/docs/index.html

/src/main/resources/static/docs/index.adoc