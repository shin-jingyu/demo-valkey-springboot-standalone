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


