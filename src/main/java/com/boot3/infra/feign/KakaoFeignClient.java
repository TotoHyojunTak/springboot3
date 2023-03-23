package com.boot3.infra.feign;

import com.boot3.data.dto.response.BlogKakaoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "KakaoBlogFeign", url="${api.kakao.url}")
public interface KakaoFeignClient {
    @GetMapping(value = "${api.kakao.path}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, headers="Authorization=${api.kakao.aukey}")
    BlogKakaoDTO.KakaoResponse search(@RequestParam(value = "query") String query, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size);
}
