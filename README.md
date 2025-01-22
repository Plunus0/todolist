# TodoList API 서버

React Native로 생성한 프로젝트 **todolistrn**의 서버 역할을 하는 **TodoList API 서버**입니다.  
JAVA11 버전과 H2 서버를 이용해 DB설치 없이 실행할 수 있도록 만들었습니다.

---

## 빌드하기

```bash
.\mvnw clean install
```

---

## 실행하기

### 빌드된 JAR 파일 실행:

```bash
java -jar .\target\todolist-0.0.3.jar
```

### 소스코드로 실행:

```bash
./mvnw spring-boot:run
```
