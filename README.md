# 🚀 Valkey + Spring Boot 기반 OTP 인증 시스템

이 프로젝트는 **Spring Boot** 기반으로 **Valkey(Redis 포크)** 를 활용하여 **OTP 인증 시스템** 을 구축하는 예제입니다.  
**RESTful API**, **레이어드 아키텍처**, **Redis 기반의 데이터 저장소**, **Docker Compose를 활용한 컨테이너화** 등의 개념을 적용하였습니다.

---

## 📖 **프로젝트 개요**
이 프로젝트는 **Spring Boot**와 **Valkey(Redis 포크)**를 사용하여 **OTP(One-Time Password) 인증 시스템**을 구현하는 애플리케이션입니다.  
사용자의 전화번호를 입력받아 OTP를 생성하고, 유효성을 검증하며, 일정 시간이 지나면 자동으로 만료되도록 설계되었습니다.  

또한 **Docker Compose를 사용하여 Valkey(Redis)를 컨테이너 환경에서 실행**할 수 있도록 설정되어 있습니다.

---

## 🌟 **주요 기능**
- **RESTful API 기반의 데이터 처리**: HTTP 요청을 통해 OTP 생성, 조회, 검증, 삭제 API 제공  
- **레이어드 아키텍처 적용**: Controller, Service, Repository 계층을 분리하여 유지보수성을 향상  
- **OTP 생성**: 사용자의 전화번호를 입력받아 **OTP를 생성하고 Valkey(Redis)에 저장**  
- **OTP 조회**: 특정 전화번호를 통해 저장된 OTP 값을 **Valkey(Redis)에서 조회**  
- **OTP 검증**: 입력된 OTP가 유효한지 확인 후 인증 처리  
- **OTP 삭제**: 특정 전화번호와 관련된 OTP 데이터를 **Valkey(Redis)에서 삭제**  
- **만료 시간 설정**: OTP는 일정 시간이 지나면 자동으로 만료되도록 설정 가능  
- **컨테이너화**: **Valkey(Redis)를 Docker Compose로 실행하여 환경 구성 자동화**  

---

## 🛠️ **기술 스택**
- **프레임워크:** Spring Boot  
- **언어:** Java (메인 코드)  
- **빌드 도구:** Gradle  
- **데이터베이스:** Valkey(Redis 포크) (Docker Compose 사용)  
- **DTO 변환:** MapStruct (Mapper)  
- **컨테이너화:** Valkey(Redis)를 Docker Compose로 실행  

---

## 📂 **프로젝트 구조**
```bash
src/
├── java/com/example/demo/
│   ├── common/                   # 공통 모듈
│   │   ├── exception/            # 예외 처리
│   │   │   ├── response/         # API 응답 처리
│   │   │   └── support/          # 예외 관련 서포트 클래스
│   │   ├── redis/                # Redis (Valkey) 설정 및 연동
│   │   │   ├── configuration/    # 애플리케이션 설정 관련 클래스
│   │   │   └── support/          # Redis 서포트 클래스
│   │   ├── util/                 # 유틸리티 모음
│   │   │   ├── random/           # 랜덤 데이터 관련 유틸
│   │   │   └── text/             # 문자열 처리 유틸
│   ├── layered/                  # 주요 서비스 레이어
│   │   ├── controller/           # REST API 컨트롤러
│   │   │   └── dto/              # 데이터 전송 객체 (DTO)
│   │   ├── exception/            # 레이어드 아키텍처에서의 예외 처리
│   │   ├── mapper/               # 엔티티 ↔ DTO 변환 매퍼
│   │   ├── repository/           # 데이터 저장소 (Valkey 사용)
│   │   ├── service/              # 비즈니스 로직을 처리하는 서비스 계층
│   │   └── usecase/              # 특정 유스케이스(기능) 인터페이스 정의
│   ├── simple/                   # 추가적인 단순 비즈니스 로직
│   │   ├── controller/           # 단순한 API 컨트롤러
│   │   ├── entity/               # 단순 엔티티
│   │   ├── repository/           # 단순 데이터 저장소

```

---
# 프로젝트 받기

```bash
git clone https://github.com/shin-jingyu/demo-valkey-springboot-standalone.git
```


# Valkey 설치 (local)


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








