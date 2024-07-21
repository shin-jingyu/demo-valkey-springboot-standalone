package com.example.demo.layered.entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("otp")
public class MobileOtp {
    // id는 임의로 바꾸면 안되기 떄문에 private
    // id를 제외한 다른 필드를 public으로 한 이유는
    // IDE의 발전으로, 불필요한 경우까지 getter/setter로 통일할 필요가 없기 때문이다.

    @Id
    @Getter
    private String id;
    @Indexed
    public String tel; // (cluster 환경) 조회조건(id) = tel로 id르 조회 한다
    public String otp;
    @TimeToLive // 데이터의 수명 (TTL) unit: [sec]
    public Integer ttl;

}
