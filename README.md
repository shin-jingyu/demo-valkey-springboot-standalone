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

# Demo Valkey Spring Boot Standalone

이 프로젝트는 Spring Boot와 Redis를 사용하여 구현된 독립 실행형 OTP 관리 애플리케이션입니다. 효율적이고 확장 가능한 아키텍처를 통해 OTP 생성, 조회, 삭제 기능을 제공합니다.

## 📌 프로젝트 개요
- **기술 스택**: Java, Spring Boot, Redis, Docker, Lombok
- **주요 기능**: OTP 생성, 조회, 삭제
- **구조**: 계층형 아키텍처로 구성 (Controller, Service, Repository, Entity)

## 🚀 프로젝트 특징
- **커스텀 에러 코드 인터페이스**: `ErrorCode` 인터페이스를 통해 다양한 예외 상황에 대해 일관성 있는 에러 코드를 정의하고 확장성 있는 예외 처리를 구현했습니다.
- **RESTful API 구현**: OTP 생성, 조회, 삭제 기능을 제공하는 RESTful API 엔드포인트 구현.
- **Redis 활용**: OTP 데이터를 Redis에 저장하여 높은 처리 속도와 확장성을 보장.
- **계층형 아키텍처**: 역할별 코드 분리로 유지보수성 및 확장성 향상.
- **보안성 강화**: OTP 생성에 `SecureRandom`을 사용하여 안전한 난수 생성.
- **Docker 기반 배포**: Redis와 애플리케이션을 컨테이너화하여 손쉬운 배포와 환경 설정 가능.

## 🌟 주요 기능
- **OTP 생성**: 전화번호를 입력받아 OTP를 생성하고 Redis에 저장합니다.
- **OTP 조회**: 전화번호를 통해 저장된 OTP를 조회합니다.
- **OTP 삭제**: 특정 전화번호와 관련된 OTP를 삭제합니다.

## 📋 사용된 기술
- **Spring Boot**: 빠른 개발과 프로덕션 수준의 애플리케이션 제공.
- **Redis**: 데이터 저장 및 조회를 빠르게 처리하기 위한 인메모리 데이터베이스.
- **Docker**: 컨테이너화를 통한 일관된 환경 제공.
- **Lombok**: 반복 코드를 줄여주는 편리한 애노테이션 사용.
- **MapStruct**: DTO와 엔티티 간의 매핑을 자동화하여 코드의 가독성과 유지보수성 향상.
- **Spring Boot**: 빠른 개발과 프로덕션 수준의 애플리케이션 제공.
- **Redis**: 데이터 저장 및 조회를 빠르게 처리하기 위한 인메모리 데이터베이스.
- **Docker**: 컨테이너화를 통한 일관된 환경 제공.
- **Lombok**: 반복 코드를 줄여주는 편리한 애노테이션 사용.

## 🛠️ 설치 및 실행 방법
1. **환경 구성**: Redis 설정을 아래와 같이 `application.yml` 파일에 추가합니다.
   ```yaml
   spring.data.redis:
     host: ${REDIS_MASTER_HOST:localhost}
     port: ${REDIS_MASTER_PORT:6378}
     password: ${REDIS_MASTER_PASSWORD:root}
   ```
2. **Docker Compose**를 사용하여 Redis 마스터-슬레이브 환경을 설정합니다. 아래는 `docker-compose.yml` 파일의 내용입니다.
   ```yaml
   version: "3"

   services:
     demo-valkey-primary:
       image: docker.io/bitnami/valkey:7.2
       ports:
         - '6378:6379'
       environment:
         - VALKEY_REPLICATION_MODE=master
         - VALKEY_PASSWORD=root
         - VALKEY_DISABLE_COMMANDS=FLUSHDB,FLUSHALL
       volumes:
         - 'sticky_volume_demo-valkey-primary:/bitnami/valkey/data'

     demo-valkey-secondary:
       image: docker.io/bitnami/valkey:7.2
       ports:
         - '6379:6379'
       depends_on:
         - demo-valkey-primary
       environment:
         - VALKEY_REPLICATION_MODE=slave
         - VALKEY_MASTER_HOST=demo-valkey-primary
         - VALKEY_MASTER_PORT_NUMBER=6379
         - VALKEY_MASTER_PASSWORD=root
         - VALKEY_PASSWORD=root
         - VALKEY_DISABLE_COMMANDS=FLUSHDB,FLUSHALL

   volumes:
     sticky_volume_demo-valkey-primary:
       driver: local
   ```
3. **애플리케이션 빌드 및 실행**:
   ```sh
   ./gradlew build
   docker-compose up
   ```

## 📂 프로젝트 구조
- **common.exception**: 공통 예외 처리 및 오류 응답 정의.
- **controller**: API 엔드포인트 정의 및 요청 처리.
- **service**: 비즈니스 로직 구현.
- **repository**: Redis 데이터 접근 계층.
- **entity**: 데이터 모델 정의.
- **mapper**: DTO와 엔티티 간의 변환.

## 💡 학습 포인트
- **커스텀 에러 코드 인터페이스 사용**: `ErrorCode` 인터페이스를 사용하여 커스텀 에러 코드를 정의하고 일관된 예외 처리를 구현하는 방법을 학습했습니다.
- **OTP 보안**: `SecureRandom`을 사용하여 보안성이 높은 OTP를 생성하는 방법을 학습했습니다.
- **Redis와 Spring 통합**: Redis를 사용하여 빠르고 효율적인 OTP 저장소를 구성하는 방법을 배웠습니다.
- **Docker Compose**: Redis 마스터-슬레이브 구조를 손쉽게 설정하고 관리하는 방법을 습득했습니다.
- **MapStruct 사용**: DTO와 엔티티 간의 변환을 자동화하여 코드의 유지보수성을 높이는 방법을 배웠습니다.
- **OTP 보안**: `SecureRandom`을 사용하여 보안성이 높은 OTP를 생성하는 방법을 학습했습니다.
- **Redis와 Spring 통합**: Redis를 사용하여 빠르고 효율적인 OTP 저장소를 구성하는 방법을 배웠습니다.
- **Docker Compose**: Redis 마스터-슬레이브 구조를 손쉽게 설정하고 관리하는 방법을 습득했습니다.





