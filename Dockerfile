# 1. Java 17 JDK 이미지 사용
FROM openjdk:17-jdk-slim

# 2. 컨테이너 내부 작업 디렉터리 설정
WORKDIR /app

# 3. 실행 가능한 JAR 파일 복사 (plain 제외)
COPY build/libs/*-SNAPSHOT.jar app.jar

# 4. 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]