### 일정 관리 앱 만들기 Develop

## 1. 프로젝트 소개
JPA를 활용한 연관관계 CRUD 작성과 인증/인가를 통한 일정 관리 앱 설계

---
## 2. 프로젝트 구조 설명
- API 명세서
<img width="514" height="696" alt="image" src="https://github.com/user-attachments/assets/f6b47738-fb98-41b0-8c21-b5ea086ce2b8" />

- ERD
<img width="547" height="325" alt="image" src="https://github.com/user-attachments/assets/1f647439-3eda-4f7a-9af1-5c3123e4858f" />


- 메인 프로젝트 구조             

        📁 src/  
            └── 📁main/
                └── 📁java/
                    └── 📁scheduleDevelop/
                        └── 📁confige/        #전체 예외 처리 및 비밀번호 보안
                        └── 📁controller/     #요청 전달
                        └── 📁dto/            #requeset, response 클래스 보관
                        └── 📁entity/         #DB 정보
                        └── 📁repository/     #레퍼지토리 
                        └── 📁service/        #비즈니스 로직 관리
                    └── 📁userDevelop/
                        └── 📁controller/    #요청 전달
                        └── 📁dto/           #requeset, response 클래스 보관
                        └── 📁entity/        #DB 정보
                        └── 📁repository/    #레퍼지토리 
                        └── 📁service/       #비즈니스 로직 관리

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

### ◆ Lv 5. 다양한 예외처리 (도전)
- Validation을 활용해 다양한 예외처리 적용
    - @RestControllerAdvice 활용
- 정해진 예외처리 항목 이외 커스텀 예외상황 지정

### ◆ Lv 6. 비밀번호 암호화 (도전)
- Lv.3에서 추가한 비밀번호 필드에 들어가는 암호화
    - 암호화를 위한 PasswordEncoder 생성
