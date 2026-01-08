### 일정 관리 앱 만들기 Develop

## 1. 프로젝트 소개

---
## 2. 프로젝트 구조 설명
- API 명세서
    - 일정 API

     | 기능    | Method | URL | request   | response     |
     |:------|:-------|:----|:------------|:-------------|
     | 일정 등록 | POST | /schedules |{<p>"username" : "김씨",<p>"title" : "안녕",<p>"text" : "안녕하세요 "<p>}|{ <p> "id" : 1, <p> "username" : "김씨", <p> "title" : "안녕", <p> "text" : "안녕하세요 "<p> "createDate" : "2026-01-08 16:34" <p> "updateDate" : "2026-01-08 16:35" <p>}|
    |일정 조회| GET | /schedules |{ <p> "id" : 1, <p> "username" : "김씨", <p> "title" : "안녕", <p> "text" : "안녕하세요 "<p> "createDate" : "2026-01-08 16:34" <p> "updateDate" : "2026-01-08 16:35" <p>}|{ <p> "id" : 1, <p> "username" : "김씨", <p> "title" : "안녕", <p> "text" : "안녕하세요 "<p> "createDate" : "2026-01-08 16:34" <p> "updateDate" : "2026-01-08 16:35" <p>}|
    | 일정 단건 | GET | /schedules/{scheduleId} |                                                                 | { <p> "id" : 1, <p> "username" : "김씨", <p> "title" : "안녕", <p> "text" : "안녕하세요 "<p> "createDate" : "2026-01-08 16:34" <p> "updateDate" : "2026-01-08 16:35" <p>} |
    | 일정 수정 | PUT | /schedules/{scheduleId} | {<p>"username" : "서씨",<p>"title" : "새해 복 많이 받으세요!!",<p>"text" : "안녕하세요 "<p>} | { <p> "id" : 1, <p> "username" : "서씨", <p> "title" : "새해 복 많이 받으세요!!", <p> "text" : "안녕하세요 "<p> "createDate" : "2026-01-08 16:34" <p> "updateDate" : "2026-01-08 16:50" <p>} |
     | 일정 삭제 | DELETE | /schedules/{scheduleId}    |                                                                 | 200 OK |
 
    - 유저 API
  
    | 기능    | Method |URL|request| response |
    |:------|:-------|:---|:---|:---------|
    | 일정 등록 | POST   |||          |
    | 일정 조회 | GET    |||          |
    | 일정 단건 | GET    |||          |
    | 일정 수정 | PUT    |||          |
    | 일정 삭제 | DELETE |||          |

- ERD


- 메인 프로젝트 구조             

        📁 src/  
            └── 📁main/
                └── 📁java/
                    └── 📁scheduleDevelop/
                        └── 📁controller/
                        └── 📁dto/
                        └── 📁entity/
                        └── 📁repository/ 
                        └── 📁service/
                    └── 📁userDevelop/
                        └── 📁controller/
                        └── 📁dto/
                        └── 📁entity/
                        └── 📁repository/ 
                        └── 📁service/

- 사용언어
  - Java (IntelliJ)
  - Spring
  - MySQL
  - Lombok

---
## 3. 프로젝트 목표
### ◆ Lv 0. API 명세서 및 ERD 작성
- ```README.md```에 API 명세서를 작성해 서비스의 큰 흐름과 기능을 파악해 협업 소통을 원활하게 할 수 있다.
- ```README.md```에 ERD를 통해 구현해야할 서비스의 영역별로 필요한 데이터를 설계 & 관계를 표현할 수 있다.

### ◆ Lv 1. 일정 CRUD
- 일정을 생성, 전체 조회, 단건 조회, 수정, 삭제할 수 있다.
- 필드 조건
  - 작성 유저명 (username), 할일 제목 (titel), 할일 내용 (text), 작성일(creatDate), 수정일(updateDate)
  - 작성일, 수정일 필드는 ```JPA Auditing```을 활용한다.

### ◆ Lv 2. 유저 CRUD
- 유저를 생성, 전체 조회, 단건 조회, 수정, 삭제 할 수 있다.
- 필드 조건
  - 유저명 (user), 이메일(email), 작성일(createDate), 수정일(updateDate)
  - 작성일, 수정일 필드는 ```JPA Auditing```을 활용한다.
  
### ◆ Lv 3. 회원가입
- 유저에 ```비밀번호 (passworld)``` 필드 추가
  - 비밀번호는 **8글자 이상** 이어야한다
  - 비밀번호 암호화는 **도전 기능**에서 수행

### ◆ Lv 4. 로그인(인증)
- Coolie / Session을 활용해 로그인 기능을 구현
- 조건
  - 이메일(email) 과 비밀번호(password)를 활용해 로그인 기능을 구현
  - 필요한 API들에게 세션을 활용
