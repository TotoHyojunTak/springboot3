# Springboot 3 Backend API

- Springboot 3.0.4
  - Java JDK 17
- Spring Data JPA
- QueryDSL 5.0.0
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


# Swagger
http://localhost:8888/swagger-ui/index.html

# Openapi
http://localhost:8888/docs/index.html

/src/main/resources/static/docs/index.adoc