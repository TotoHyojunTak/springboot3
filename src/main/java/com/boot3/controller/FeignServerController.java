package com.boot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/feign/server")
@RestController
public class FeignServerController {

    @PostMapping("/file")
    @Operation(description = "Feign File Transfer")
    public ResponseEntity<Map<String, String>>  getFileByFeign(@RequestPart(required = false) LinkedMultiValueMap<String, Object> fileInfo){
        log.debug(String.valueOf(fileInfo));

//        fileInfo.forEach(file -> {
////            System.out.println(file.getContentType());
////            System.out.println(file.getOriginalFilename());
//            System.out.println();
//            System.out.println("files : " + file.toString());
//        });
        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return ResponseEntity.ok(resultMap);
    }

    @PostMapping("/msg")
    @Operation(description = "Feign - Message")
    public ResponseEntity<Map<String, String>>  getMessageByFeign(@RequestBody HashMap map){
        log.debug(map.toString());

        HashMap<String, String> resultMap = new HashMap<>();
        resultMap.put("result", "success");
        return ResponseEntity.ok(resultMap);
    }
}
