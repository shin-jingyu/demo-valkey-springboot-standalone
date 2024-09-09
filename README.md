# 프로젝트 받기

```bash
git clone https://github.com/shin-jingyu/demo-valkey-springboot-standalone.git
```

IDE에서 열면 됩니다. 

# Valkey 설치 (local)

prerequisite 

- docker compose 설치가 되어있어야 합니다.
  ([docker desktop 설치](https://www.docker.com/products/docker-desktop/))

- docker가 실행되고 있어야 합니다.

프로젝트 루트경로에서 다음 명령어를 실행합니다.

```bash
docker compose up -d
```
<details> 
 <summary> 컨테이너 내리기(데이터는 보존) </summary> 
 <div markdown="1"> 

```bash
docker compose down 
```

 </div>
 </details>

<details> 
 <summary> 컨테이너 내리기(데이터도 삭제) </summary> 
 <div markdown="1"> 

```bash
docker compose down -v 
```

 </div>
 </details>

---

# Valkey는 redis를 대신합니다.

spring boot에서도 redis를 다루기 위한 주요 라이브러리를 valkey에 그대로 적용할수 있습니다.

이 프로젝트는 그것이 가능한지 실제로 검토해 본 프로젝트입니다.








---

## Redis 사용
Redis는 인메모리 데이터 저장소로서 빠른 접근 속도와 다양한 기능을 제공하며, 이 프로젝트에서는 주로 Valkey와 유사한 방식으로 활용되었습니다.

1. 인메모리 Key-Value 저장소
* EmailOtp 엔티티를 Redis에 저장합니다.
* @RedisHash("otp") 어노테이션은 Redis의 key-value 구조를 활용함을 나타냅니다.
2. 분산 캐시
* Redis는 OTP 정보를 빠르게 저장하고 조회하는 캐시로 사용됩니다.
* 이는 Valkey의 분산 캐시 기능과 유사합니다.
3. 옵션 지속성
* Redis는 인메모리 저장소이지만, 필요에 따라 데이터를 디스크에 저장할 수 있습니다.
* RedisConfiguration에서 이러한 옵션을 설정할 수 있습니다.
4. 메시지 브로커 기능
* 현재 코드에서는 직접적으로 사용되지 않지만, Redis의 pub/sub 기능을 통해 메시지 브로커로 사용 가능합니다.
5. TTL (Time To Live) 지원
* @TimeToLive 어노테이션을 통해 OTP의 만료 시간을 설정합니다.
```java
 @TimeToLive
public Integer ttl;
 ```
  
6. 분산 환경 지원
* @Indexed 어노테이션을 사용하여 클러스터 환경에서의 효율적인 조회를 지원합니다.
7. 설정
* RedisConfiguration 클래스와 redis.yml 파일을 통해 Redis 연결을 구성합니다.
``` java
@Configuration
public class RedisConfiguration {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        // Redis 연결 설정
    }
}
```
## @Indexed 어노테이션 설명
---
@Indexed 어노테이션은 Spring Data에서 제공하는 기능으로, 특정 필드에 대한 인덱스를 생성하도록 지시합니다. Redis 환경에서 이 어노테이션은 특별한 의미를 가집니다.

### EmailOtp 엔티티에서의 사용 예
``` java
@RedisHash("otp")
public class EmailOtp {
    @Id
    private String id;
    
    @Indexed
    public String email;
    
    // ... 다른 필드들
}
```
### @Indexed의 목적과 효과
* 빠른 조회: @Indexed가 적용된 필드는 Redis에서 별도의 인덱스로 관리되어, 해당 필드를 기준으로 한 조회 연산의 성능이 크게 향상됩니다.

* 보조 인덱스: Redis는 기본적으로 키-값 저장소이지만, @Indexed를 사용하면 보조 인덱스를 생성하여 다양한 필드로 빠른 검색이 가능합니다.

* 클러스터 환경 지원: Redis 클러스터에서 데이터는 여러 노드에 분산되는데, @Indexed를 사용하면 특정 필드로 전체 클러스터에서 효율적인 검색이 가능합니다.

* 유연한 쿼리: id 외의 필드로도 엔티티를 쉽게 조회할 수 있게 해줍니다. 예를 들어, EmailOtp를 email로 조회하는 메서드를 쉽게 구현할 수 있습니다.

### 사용 예시
```java
public interface EmailOtpRepository extends CrudRepository<EmailOtp, String> {
    EmailOtp findByEmail(String email);
}
```
위 레포지토리 메서드는 @Indexed로 인해 효율적으로 동작합니다. email 필드에 대한 인덱스가 있어 빠른 조회가 가능합니다.

### 주의사항
* 인덱스 오버헤드: 인덱스는 추가 저장 공간을 사용하며, 쓰기 작업 시 약간의 성능 저하가 있을 수 있습니다.
* 선별적 사용: 모든 필드에 @Indexed를 사용하는 것은 권장되지 않습니다. 자주 조회되는 필드에만 선별적으로 사용하세요.
@Indexed 어노테이션을 적절히 사용하면 Redis 기반의 애플리케이션에서 데이터 조회 성능을 크게 향상시킬 수 있습니다.

이 프로젝트는 Redis의 다양한 기능을 활용하여 OTP 관리 시스템을 구현한 예제입니다. Redis의 인메모리 저장소, 분산 캐시, TTL 지원 등 다양한 특징들을 통해 빠르고 효율적인 데이터 관리를 실현합니다.

