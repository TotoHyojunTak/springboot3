package com.boot3.controller;

import com.boot3.infra.feign.TestFeignClient;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/feign/client")
@RestController
public class FeignClientController {
    private final TestFeignClient testFeignClient;


    @PostMapping("/file")
    @Operation(description = "Feign File Transfer")
    public void postFileByFeign(MultipartFile multipartFile) throws IOException {
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        Resource file1 = new FileSystemResource("/Volumes/DATA/git/boot3/src/main/resources/feign/testdata.txt");
        Resource file2 = new FileSystemResource("/Volumes/DATA/git/boot3/src/main/resources/feign/testdata.txt");
        body.add("files", file1);
        body.add("files", file2);



        testFeignClient.sendFileByFeign(body);
    }

    @PostMapping("/msg")
    @Operation(description = "Feign - Message")
    public void postMessageByFeign(){
        HashMap data = new HashMap();
        data.put("test", "testdata");

        testFeignClient.sendMessageByFeign(data);
    }
}
