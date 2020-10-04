
![logo](assets/logo.png)

<center><h2>A106 - Tikitacar</h2></center>

<center>
    <img src="https://img.shields.io/badge/platform-web-green">
    <img src="https://img.shields.io/static/v1.svg?label=&message=Vue&style=flat-square&logo=Vue.js&logoColor=white&color=42b883">
    <img src="https://img.shields.io/badge/framework-spring boot-blue">
    <img src="https://img.shields.io/badge/database-MySQL-9cf">
    <img src="https://img.shields.io/badge/server-AWS-yellow">
    <img src="https://img.shields.io/badge/language-java%2C javascript-yellowgreen">
    <img src="https://img.shields.io/badge/swagger-valid-brightgreen">
</center>


<center>
  <a href="http://j3a106.p.ssafy.io/">
    <h3>
      TikitaCar 바로가기
    </h3>
  </a>
</center>
<br>
<center>
    중고차 설명설명<br>
    중고차 설명설명<br>
    중고차 설명설명
</center>





## :point_right: 프로젝트 기술 특이점

* JWT 토큰을 활용한 로그인 기능
* 
* 
* 



## :package: 프로젝트 구성

### frontend

|          Package          |    Version     |                      Comment                      |
| :-----------------------: | :------------: | :-----------------------------------------------: |
|        vue-router         |     3.2.0      |      요청 URL에 따라 브라우저에서 돔을 변경       |
|           vuex            |     3.5.1      |            상태 관리 패턴 + 라이브러리            |
|           axios           |     0.19.2     |  REST API 서버로부터 데이터를 받아오기 위해 사용  |
| bootstrap / bootstrap-vue | 4.5.0 / 2.15.0 | 프론트엔드 디자인을 효율적으로 진행하기 위해 사용 |
|          vuetify          |     2.3.8      |                Calendar 요소 사용                 |
|  chart.js / vue-chartjs   | 2.9.3 / 3.5.0  |      데이터를 시각화하여 보여주기 위해 사용       |
|       scrollmonitor       |     1.2.4      |          무한스크롤을 구현하기 위해 사용          |
|          lodash           |    4.17.19     |     요리대전의 순서를 랜덤으로 섞기위해 사용      |

### backend

| Package | Version | Comment |
| :--: | :--: | :--- |
| OpenJDK - zulu |  8.33   | 프로젝트의 Java vendor로써 사용 |
|   SpringBoot   |  2.3.1  | Java의 서버 개발 환경을 구성하기 위해 사용 |
| JWT | 0.9.1 | 클라이언트와 서버의 stateless 환경 구성을 위해 사용 |
|    Docker     |  19.03.12  | 서버 환경 구축을 위해 사용 |
|    MySQL     |  8.0.21  | 서버와 DB의 연동을 위해 사용 |
|     Lombok     | 1.18.10 | Annotation을 이용한 코드 생성으로 가독성 & 유지 보수를 위해 사용 |
|    Swagger     |  2.9.2  | API 스펙 문서의 자동화를 위해 사용 |
|     Nginx     | 1.14.0  | 서버 배포를 용이하게 하기 위해 사용 |
| pm2 | 4.4.0 | 서버의 무중단 배포를 위해 사용 |
| git | 2.17.1 | 코드 관리 목적을 위해 사용 |






## :globe_with_meridians: 지원 브라우저

| <img width="50" src="https://simpleicons.org/icons/googlechrome.svg" alt="Chrome" /> |
| ------------------------------------------------------------ |
| Latest                                                       |



## :two_men_holding_hands: 요리사들

* **부정수** - *PM, frontend* - [wjdtn7823](https://lab.ssafy.com/wjdtn7823)
* **권민** - *backend* - [minkwon4](https://lab.ssafy.com/minkwon4)
* **맹승규** - *frontend* - [Maengsk](https://lab.ssafy.com/Maengsk)
* **장윤정** - *backend* - [wkddbswjd325](https://lab.ssafy.com/wkddbswjd325)
* **최원대** - *backend* - [ryunhoo123](https://lab.ssafy.com/ryunhoo123)



### :star: 브랜치 전략

### branch

```bash
master -> release -> developer -> frontend/backend -> frontend-feature/backend-feature
```
