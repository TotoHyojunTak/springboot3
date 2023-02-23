package com.boot3.data.repository;

import com.boot3.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, UserRepositoryCustom {
    UserEntity findByEmail(String username);

    @Query(value = "select email, encrypted_pwd, name, user_id, created_date from tb_users where user_id = :userId", nativeQuery = true)
    UserEntity findByUserId(String userId);

}