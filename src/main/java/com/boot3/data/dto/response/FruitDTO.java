package com.boot3.data.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FruitDTO {
    private Integer seq;

    private String name;

    private String season;

    private Integer price;

    private String region;

    private LocalDateTime createdDate;
}