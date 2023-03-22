package com.boot3.controller;

import com.boot3.common.data.dto.CommonResponseDTO;
import com.boot3.data.dto.request.BlogRecordReqDTO;
import com.boot3.data.dto.request.BlogReqDTO;
import com.boot3.data.dto.response.BlogKakaoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name="Kakao API Controller", description="Kakao API Controller 구현하기")
public class KakaoClientController {

    private final RestTemplate restTemplate;

    @Value("${api.kakao.url}")
    private String url;

    @Value("${api.kakao.authorization}")
    private String auth;

    @Value("${api.kakao.key}")
    private String key;

    @Value("${api.kakao.path}")
    private String path;


    // Java Record Type
    @GetMapping("/blog/kakao/search/resttemplate/record")
    @Operation(description = "자료형(Record) - 카카오 블로그 조회 (RestTemplate)")
    public CommonResponseDTO getKakaoBlogListByResttemplateForRecord(@NotNull @Valid BlogRecordReqDTO request){

        // CASE1. RestTemplate 버전 (주석 처리)
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path(path)
                .queryParam("query", request.query())
                .queryParam("sort", request.sort())
                .queryParam("page", request.page())
                .queryParam("size", request.size())
                .encode()
                .build().toUri();

        RequestEntity<Void> res = RequestEntity
                .get(uri)
                .header("Authorization", auth + " " + key)
                .build()
                ;

        final ResponseEntity<BlogKakaoDTO.KakaoResponse> resEntity = restTemplate.exchange(res, BlogKakaoDTO.KakaoResponse.class);
        log.debug("uri={}, res={}, statusCode={}, responseBody={}", uri, res, resEntity.getStatusCode(), resEntity.getBody());
        if (!resEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("[실패] 카카오 API 통신 실패");
        }

        return CommonResponseDTO.of(resEntity.getBody());
    }

    // Java 기본 자료형
    @GetMapping("/blog/kakao/search/resttemplate")
    @Operation(description = "카카오 블로그 조회 (RestTemplate)")
    public CommonResponseDTO getKakaoBlogListByResttemplate(@NotNull @Valid BlogReqDTO request){

        // CASE1. RestTemplate 버전 (주석 처리)
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path(path)
                .queryParam("query", request.getQuery())
                .queryParam("sort", request.getSort())
                .queryParam("page", request.getPage())
                .queryParam("size", request.getSize())
                .encode()
                .build().toUri();

        RequestEntity<Void> res = RequestEntity
                .get(uri)
                .header("Authorization", auth + " " + key)
                .build()
                ;

        final ResponseEntity<BlogKakaoDTO.KakaoResponse> resEntity = restTemplate.exchange(res, BlogKakaoDTO.KakaoResponse.class);
        log.debug("uri={}, res={}, statusCode={}, responseBody={}", uri, res, resEntity.getStatusCode(), resEntity.getBody());
        if (!resEntity.getStatusCode().is2xxSuccessful()) {
            throw new RestClientException("[실패] 카카오 API 통신 실패");
        }

        return CommonResponseDTO.of(resEntity.getBody());
    }

    @GetMapping("/blog/kakao/search/webclient")
    @Operation(description = "카카오 블로그 조회 (WebClient)")
    public CommonResponseDTO getKakaoBlogListByWebclient(@NotNull @Valid BlogReqDTO request){

        // Case. WebClient
        WebClient webClient = WebClient
                .builder()
                .baseUrl(url)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", auth + " " + key)
                .build();

        BlogKakaoDTO.KakaoResponse result = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("query", request.getQuery())
                        .queryParam("sort", request.getSort())
                        .queryParam("page", request.getPage())
                        .queryParam("size", request.getSize())
                        .build())
                .retrieve()
                .bodyToMono(BlogKakaoDTO.KakaoResponse.class)
                .block();

        return CommonResponseDTO.of(result);
    }
}
