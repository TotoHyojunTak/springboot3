package com.boot3.data.repository;

import com.boot3.data.entity.FruitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<FruitEntity, Long> {

    @Query(value = "select seq, name, season, price, region, created_date from TB_FRUIT where name = :name", nativeQuery = true)
    FruitEntity findByName(String name);
}