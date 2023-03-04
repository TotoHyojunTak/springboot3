package com.boot3.data.repository;

import com.boot3.data.entity.FruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<FruitEntity, Long> {


}