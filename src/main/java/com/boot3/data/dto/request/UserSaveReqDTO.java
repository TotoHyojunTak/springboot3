package com.boot3.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSaveReqDTO {

    private String userId;


    @NotNull()
    @Size(min = 2)
    @Email
    private String email;

    @NotNull()
    @Size(min = 2)
    private String name;

    @NotNull()
    @Size(min = 8)
    private String pwd;
}