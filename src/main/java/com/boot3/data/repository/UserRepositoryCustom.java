package com.boot3.data.repository;

import com.boot3.data.dto.request.UserRecordReqDTO;
import com.boot3.data.dto.response.UserDTO;
import com.boot3.data.dto.response.UserRecordDTO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryCustom{


    UserDTO userInfoByQuerydsl(UserRecordReqDTO dto);
}