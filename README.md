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

# 개요
이 프로젝트에서는 **Spring Boot**와 **Redis**를 활용한 **OTP(One-Time Password)** 시스템을 구현했습니다.
Redis의 다양한 기능을 활용하여 빠르고 효율적인 데이터 관리 시스템을 구현하는 방법을 학습할 수 있습니다. 특히, **DTO 패턴**, **UseCase 인터페이스**, **@Indexed** 사용 등 실무에서 유용한 기술들을 적극 도입하였습니다.

# 학습 목표
* **Spring Boot**와 **Redis**를 기반으로 한 애플리케이션 개발 경험
* **OTP** 시스템의 기본 구조와 구현 방식 습득
* **DTO** 패턴을 통한 데이터 전송 최적화 및 엔티티 노출 방지
* **UseCase** 인터페이스를 도입하여 비즈니스 로직의 가독성과 유지보수성 향상
* **@Indexed**를 통해 Redis 데이터의 효율적인 조회 구현
---

## 1. 프로젝트 구조
* **계층형 아키텍처**: Controller, Service, Repository, Entity로 구성
* 각 계층 간의 역할 분리 및 유연한 데이터 처리 지원
* **DTO(Data Transfer Object)** 패턴을 도입하여 계층 간 데이터 전송을 개선
* **UseCase 인터페이스**를 사용하여 비즈니스 로직을 모듈화하고 가독성을 높임
<br><br>
## 2. 주요 컴포넌트와 코드
* **EmailOtp 엔티티**: Redis에 OTP 데이터를 저장하며, @Indexed를 통해 이메일 필드에 대한 효율적인 조회를 지원

```java

@RedisHash("otp")
public class EmailOtp {
    @Id
    private String id;
    
    @Indexed
    public String email;
    
    private String otp;
    private String refreshToken;
    
    @TimeToLive
    private Integer ttl;
}
```

<br>

* @Indexed는 email 필드에 대한 인덱스를 생성하여 Redis에서 빠른 조회를 가능하게 함.

<br>

* EmailOtpRepository: Redis와의 CRUD 연산을 담당하며, findByRefreshToken 메서드를 통해 리프레시 토큰 기반 조회 기능 제공

```java
public interface EmailOtpRepository extends CrudRepository<EmailOtp, String> {
    EmailOtp findByRefreshToken(String refreshToken);
}
```

<br>

* **DTO의 공개 필드**: 이 프로젝트에서는 **DTO(Data Transfer Object)**를 public 필드로 설정하여 유연하고 간결한 데이터 전송을 구현

```java
@Builder
public record EmailOtpDto(String email, String otp, Integer ttl, String refreshToken) {}
```

<br>

* public DTO 필드를 통해 코드의 간결함을 유지하고, 불필요한 getter/setter 생성을 줄임.

<br>

* EmailOtpService: OTP 생성, 조회, 삭제, 리프레시 토큰 갱신 기능을 담당하며, UseCase 인터페이스를 통해 모듈화된 비즈니스 로직을 구현

```java
public class EmailOtpService implements EmailOtpCreateUseCase, EmailOtpReadUseCase, EmailOtpDeleteUseCase {
    // Service logic
}
```

<br>

* UseCase 인터페이스 도입을 통해 기능별로 책임을 분리하고, 코드의 유지보수성을 높임

<br>

## 3. Redis 활용
* "@RedisHash", "@TimeToLive"를 사용하여 Redis에 OTP 데이터를 저장하고 만료 시간을 설정
* 인메모리 캐시를 통해 빠르고 효율적인 OTP 관리
* @Indexed를 통해 Redis에서 특정 필드의 빠른 조회 가능
 
  <br>
  
## 4. RESTful API 설계
* POST /otp: OTP 생성
* GET /otp/{id}: OTP 조회
* DELETE /otp/{id}: OTP 삭제
* OST /otp/refresh: 리프레시 토큰으로 새 OTP 발급

   <br>

## 5. 보안 고려사항
* SecureRandom을 사용하여 안전한 랜덤 토큰 생성
* 리프레시 토큰을 사용한 OTP 갱신 메커니즘 구현

   <br>

## 6. 사용된 Spring Boot 기능
*  의존성 주입 (DI)
* Spring Data Redis
* RESTful API 구현
* 어노테이션 기반 설정
* DTO와 UseCase 인터페이스 도입


