package com.boot3.infra.feign;

import com.boot3.data.dto.response.BlogNaverDTO;
import com.boot3.infra.feign.config.NaverFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "NaverBlogFeign", url="${api.naver.url}", configuration = NaverFeignConfig.class)
public interface NaverFeignClient {
    @GetMapping(value = "${api.naver.path}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    BlogNaverDTO.NaverResponse search(@RequestParam(value = "query") String query, @RequestParam(value = "sort", required = false) String sort, @RequestParam(value = "start", required = false) Integer start, @RequestParam(value = "display", required = false) Integer display);
}
