package com.example.demo.layered.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("otp")
public class MobileOtp {

    @Id
    @Getter
    private String id;
    @Indexed
    public String tel; // (cluster 환경) 조회조건(id) = tel로 id르 조회 한다
    public String otp;
    @TimeToLive // 데이터의 수명 (TTL) unit: [sec]
    public Integer ttl;

}
