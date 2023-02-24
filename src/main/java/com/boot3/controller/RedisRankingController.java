package com.boot3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
@Tag(name="[Redis] LeaderBoard Controller", description="Redis로 리더보드 (랭킹) 시스템 구현하기")
public class RedisRankingController {

    private static final String LEADERBOARD_KEY = "leaderBoard";

    private final StringRedisTemplate redisTemplate;

    // 특정 USER 순위 가져오기
    @GetMapping("/rank/{userId}")
    @Operation(description = "특정 USER 순위 가져오기")
    public Long getUserRanking(@PathVariable String userId) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.rank(LEADERBOARD_KEY, userId);
        return rank;
    }

    // 데이터 저장하기
    @GetMapping("/ranks")
    @Operation(description = "데이터 저장하기")
    public boolean setUserScore(@RequestParam String userId, @RequestParam int score) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

    // 특정 순위까지의 범위를 가져오기
    @GetMapping("/rank")
    @Operation(description = "특정 순위까지의 범위를 가져오기")
    public List<String> getTopRank(@RequestParam int limit) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit - 1);

        return new ArrayList<>(rangeSet);
    }

    // 임시 데이터 생성
    @GetMapping("/temp")
    @Operation(description = "임시 데이터 생성")
    public boolean generateData(){
        for(int i = 0; i < 10000; i++){
            int score = (int)(Math.random() * 10000);
            String userId = "user_"+i;

            this.setUserScore(userId, score);
        }

        return true;
    }
}