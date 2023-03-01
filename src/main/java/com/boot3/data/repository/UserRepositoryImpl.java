package com.boot3.data.repository;

import com.boot3.data.dto.request.UserRecordReqDTO;
import com.boot3.data.dto.response.UserDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.boot3.data.entity.QUserEntity.userEntity;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public UserDTO userInfoByQuerydsl(UserRecordReqDTO dto) {

        return jpaQueryFactory.select(Projections.fields(UserDTO.class
                                , userEntity.userId.as("userId")
                                , userEntity.email.as("email")
                                , userEntity.encryptedPwd.as("encryptedPwd")
                                , userEntity.createdDate.as("createdDate")
                                , userEntity.name.as("name")
                        )
                )
                .from(userEntity)
                .where(userEntity.userId.eq(dto.userId()))
                .fetchOne()
                ;
    }
}
