package com.boot3.data.dto.response;

import java.time.LocalDateTime;

public record UserRecordDTO (String email, String name, String pwd, String userId, LocalDateTime createdDate){
}