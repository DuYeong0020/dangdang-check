# dangdang-check
## 아키텍처 개요

1. CI/CD (GitHub Actions)
- main 브랜치 푸시 또는 PR 발생 시 GitHub Actions 실행
- 테스트 및 빌드 후 Docker 이미지 생성 & 컨테이너 레지스트리에 푸시
- 배포 서버에 SSH 접속하여 Green-Blue 배포 수행
- 애플리케이션 (Spring Boot + JWT + Spring Security)

2. JWT를 사용한 인증/인가
- Spring Security를 통한 보안 설정
- Refresh Token, Access Token을 사용하여 인증/인가 처리
- 데이터베이스 (MySQL)

3. Docker 컨테이너 또는 클라우드 RDS에서 실행
- 주요 테이블: User, PrepaidTicket, Payment, Reservation 등
- 배포 (Green-Blue Deployment)

4. 두 개의 환경(Green & Blue) 운영
- 새 버전 배포 시 기존 버전과 병렬로 실행
- 정상 동작 확인 후 트래픽 전환  


## ERD
![Image](https://github.com/user-attachments/assets/51a7dbd1-9953-4e25-9ded-a2c70c327fc8)