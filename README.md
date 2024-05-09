API 서버

### docker-compose 명령어

```vi
# 이름이 docker-compose가 아닐때 실행명령어
docker-compose -f docker-compose-local.yml up

# 물론 로컬에 docker 가 설치되 있어야 한다.
```

docker에 env가 적용되어 있다. 새로 받는다면 `.env` 파일을 만들어서 실행하자

```vi
# Google keep 참조
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
REDIS_PASSWORD=
```