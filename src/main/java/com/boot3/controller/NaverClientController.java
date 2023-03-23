package com.boot3.controller;

import com.boot3.common.data.dto.CommonResponseDTO;
import com.boot3.data.dto.request.BlogRecordReqDTO;
import com.boot3.data.dto.request.BlogReqDTO;
import com.boot3.data.dto.response.BlogNaverDTO;
import com.boot3.infra.feign.NaverFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name="Naver API Controller", description="Naver API Controller 구현하기")
public class NaverClientController {
    private final RestTemplate restTemplate;

    private final NaverFeignClient naverFeignClient;

    @Value("${api.naver.url}")
    private String url;

    @Value("${api.naver.client-id}")
    private String clientId;

    @Value("${api.naver.client-secret}")
    private String clientSecret;

    @Value("${api.naver.path}")
    private String path;

    // Java Record Type
    @GetMapping("/blog/naver/search/resttemplate/record")
    @Operation(description = "자료형(Record) - 네이버 블로그 조회 (RestTemplate)")
    public CommonResponseDTO getNaverBlogListByResttemplateForRecord(@NotNull @Valid BlogRecordReqDTO request){

        // CASE1. RestTemplate 버전 (주석 처리)
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path(path)
                .queryParam("query", request.query())
                .queryParam("sort", request.sort() == "accuracy"? "sim": "date")
                .queryParam("start", request.page())
                .queryParam("display", request.size())
                .encode()
                .build().toUri();

        RequestEntity<Void> res = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build()
                ;

        final ResponseEntity<BlogNaverDTO.NaverResponse> resEntity = restTemplate.exchange(
                res, BlogNaverDTO.NaverResponse.class);
        log.debug("uri={}, res={}, statusCode={}, responseBody={}", uri, res, resEntity.getStatusCode(), resEntity.getBody());

        if (!resEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("[실패] 네이버 API 통신 실패");
        }
        ///return BlogDTO.naverOf(resEntity.getBody().getStart(), resEntity.getBody());

        return CommonResponseDTO.of(resEntity.getBody()); // webclient
    }

    // Java 기본 자료형
    @GetMapping("/blog/naver/search/resttemplate")
    @Operation(description = "네이버 블로그 조회 (RestTemplate)")
    public CommonResponseDTO getNaverBlogListByResttemplate(@NotNull @Valid BlogReqDTO request){

        // CASE1. RestTemplate 버전 (주석 처리)
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path(path)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort() == "accuracy"? "sim": "date")
                .queryParam("start", request.getPage())
                .queryParam("display", request.getSize())
                .encode()
                .build().toUri();

        RequestEntity<Void> res = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", clientId)
                .header("X-Naver-Client-Secret", clientSecret)
                .build()
                ;

        final ResponseEntity<BlogNaverDTO.NaverResponse> resEntity = restTemplate.exchange(res, BlogNaverDTO.NaverResponse.class);
        log.debug("uri={}, res={}, statusCode={}, responseBody={}", uri, res, resEntity.getStatusCode(), resEntity.getBody());

        if (!resEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("[실패] 네이버 API 통신 실패");
        }
        ///return BlogDTO.naverOf(resEntity.getBody().getStart(), resEntity.getBody());

        return CommonResponseDTO.of(resEntity.getBody()); // webclient
    }

    @GetMapping("/blog/naver/search/webclient")
    @Operation(description = "네이버 블로그 조회 (WebClient)")
    public CommonResponseDTO getNaverBlogListByWebclient(@NotNull @Valid BlogReqDTO request){

        // CASE. WebClient
        WebClient webClient = WebClient
                .builder()
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                        .responseTimeout(Duration.ofMillis(20000))
                        .proxyWithSystemProperties())) // 외부 API 서버 호출
                .baseUrl(url)
                .defaultHeaders(headers -> {
                    headers.set("X-Naver-Client-Id", clientId);
                    headers.set("X-Naver-Client-Secret", clientSecret);
                })
                .build();

        BlogNaverDTO.NaverResponse result = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("query", request.getQuery())
                        .queryParam("sort", request.getSort() == "accuracy"? "sim": "date")
                        .queryParam("start", request.getPage())
                        .queryParam("display", request.getSize())
                        .build())
                .retrieve()
                .bodyToMono(BlogNaverDTO.NaverResponse.class)
                .block()
                ;

        return CommonResponseDTO.of(result); // webclient
    }

    @GetMapping("/blog/naver/search/feign")
    @Operation(description = "네이버 블로그 조회 (feignclient)")
    public CommonResponseDTO getNaverBlogListByFeign(@NotNull @Valid BlogReqDTO request){

        // CASE. WebClient
        BlogNaverDTO.NaverResponse result = naverFeignClient.search(request.getQuery(), request.getSort() == "accuracy"? "sim": "date", request.getPage(), request.getSize());

        return CommonResponseDTO.of(result); // webclient
    }

}
