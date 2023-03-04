package com.boot3.data.dto.response;

import java.time.LocalDateTime;

public record FruitRecordDTO(Integer seq, String name, String season, Integer price, String region, LocalDateTime createdDate){
}