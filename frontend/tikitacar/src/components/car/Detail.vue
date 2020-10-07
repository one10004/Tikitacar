<template>
  <v-app id="app">
    <v-main style="padding-left: 10%; margin-top: 40px; font-family: 'Do Hyeon', sans-serif;">
      <h2>{{this.info.data.title}}</h2>
      <hr>
      <div class="carInfo">
        <div>
          <v-carousel style="width: 500px; height: 450px;">
            <v-carousel-item
              v-for="(item,i) in info.data.src"
              :key="i"
              :src="getImagesUrl(i)"
              reverse-transition="fade-transition"
              transition="fade-transition"
              contain
            ></v-carousel-item>
          </v-carousel>
        </div>
        
        <div>
          <div>
            <div class="spec">
              <h4>모델: </h4>
              <v-text-field 
                readonly
                v-model="this.info.data.name"
              ></v-text-field>
            </div>
            <div class="spec">
              <h4>연식: </h4>
              <v-text-field 
                readonly
                v-model="this.info.data.year"
              ></v-text-field>
            </div>
            <div class="spec">
              <h4>주행거리: </h4>
              <v-text-field 
                readonly
                v-model="this.info.data.distance"
                suffix="km"
              ></v-text-field>
            </div>
            <div class="spec">
              <h4>사고유무: </h4>
              <v-checkbox
                v-model="this.info.data.flooding"
                readonly
                :label="'사고 경험 ' + this.info.data.flooding + '회'"
              ></v-checkbox>
            </div>
            <div class="price">
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 20px;">
                <b>판매가격</b>
              </div>
              <div style="width: 80px; height: 60px; position: absolute; bottom: 0; margin-left: 170px;">
                <p style="font-size: 50px; color: blue">{{this.info.data.price}}</p>
              </div>
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 300px;">
                <b>만원</b>
              </div>
              <v-btn
                large
                color="primary"
                style="position: absolute; bottom: 0; margin-left: 430px;"
                @click="buyCar(this.info.data.name)"
              >구매</v-btn>
            </div>
          </div>
        </div>
      </div>
      <div class="detailInfo">
        <hr>
        <h3>차량 정보</h3>
        <v-row>
          <v-col cols="12" sm="4">
            <p>차량정보: {{this.info.data.name}}</p>
            <p>주행거리: {{this.info.data.distance}}km</p>
            <p>변속기: {{this.info.data.gear}}</p>
            <p>출시가격: {{this.info.data.releasePrice}}</p>
          </v-col>
          <v-col cols="12" sm="4">
            <p>색상: {{this.info.data.color}}</p>
            <p>연식: {{this.info.data.year}}</p>
            <p>인승: {{this.info.data.seat}} 인승</p>
          </v-col>
          <v-col cols="12" sm="4">
            <p>침수이력: {{this.info.data.flooding}} 회</p>
            <p>보험: {{this.info.data.insurance}} 개</p>
            <p>연료: {{this.info.data.fuel}}</p>
          </v-col>
        </v-row>
      </div>
      <div>
        <hr>
        <h3>주행거리 분석</h3>
        <p>주행 거리는 총 <span style="font-size: 30px; color: blue; padding-left: 8px;">{{this.info.data.distance}} km</span> 로 일반적인 연평균 주행거리 대비 [<span style="color: green; font-weight: bold;">{{this.status}}</span>] 입니다.</p>
      </div>
      <div>
        <hr>
        <h3>주요옵션</h3>
        <div style="display: flex;">
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-map-marker</v-icon>
            <p>네비게이션</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-key</v-icon>
            <p>스마트키</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-light-high</v-icon>
            <p>AFLS</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-electric</v-icon>
            <p>고속충전</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-seat-heater</v-icon>
            <p>열선시트</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-cruise-control</v-icon>
            <p>크루즈 컨트롤</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-bluetooth</v-icon>
            <p>블루투스</p>
          </div>
          <div class="optionList">
            <v-icon size="60px" style="margin-bottom: 5px;">mdi-car-connected</v-icon>
            <p>주차 감지 센서</p>
          </div>
        </div>
      </div>
      <div>
        <hr>
        <h3>판매자 정보</h3>
        <p>판매자: {{this.info.data.nickname}}</p>
        <p>전화번호: {{this.info.data.phone}}</p>
        <p>지역: {{this.info.data.address}}</p>
      </div>
      <div>
        <h3>비슷한 차량 추천</h3>
        <!-- <v-container> -->
          <v-row class="recommend">
            <v-col cols="12" sm="3" v-for="car in cars" :key="car.id">
              <router-link :to="{ name: 'Detail', params: { id: car.id } }" style="text-decoration: none;">
                <v-card style="height: 500px;">
                  <v-card-title><div class="headerClass">{{car.name}}</div></v-card-title>
                    <img class="thumbnail" :src="getImageUrl(car.src)" />
                    <p>연식: {{car.year}}</p>
                    <p>주행거리: {{car.distance}} km</p>
                    <p>출시가격: {{car.releasePrice}}</p>
                    <p>색상: {{car.color}}</p>
                    <p>판매자: {{car.nickname}}</p>
                    <p><span style="font-size: 30px; color: blue;">{{car.price}}</span>만원</p>
                </v-card>
              </router-link>
            </v-col>
          </v-row>
        <!-- </v-container> -->
      </div>
    </v-main>
  </v-app>
</template>

<script>
import {mapActions} from "vuex";
import api from "@/api/api.js";

  export default {
    props: {
      source: String,
    },
    data: () => ({
      info: {},
      status: "",
      cars: [],
      searchInfo: {
        cc: -1,
        color: "없음",
        distance: -1,
        flooding: -1,
        fuel: "없음",
        gear: "없음",
        insurance: -1,
        name: "없음",
        price: -1,
        seat: -1,
        status: "없음",
        year1: -1,
        year2: -1
      },
    }),
    created() {
      this.getInfo(this.$route.params.id)
        .then((res) => {
          this.info = res.data;
          if(this.info.data.distance > 50000) this.status = "많음";
          else if(this.info.data.distance > 20000) this.status = "보통";
          else this.status = "적음";
          this.fetchData(this.searchInfo)
            .then((res) => {
              var index = 0;
              for(var i = 0; i < res.length; i++) {
                if(Number(this.$route.params.id) === res[i].id) {
                  index = i;
                  break;
                }
              }
              if(index === 0) {this.cars.push(res[1]); this.cars.push(res[2]); this.cars.push(res[3]); this.cars.push(res[4]);}
              else if(index === 1) {this.cars.push(res[0]); this.cars.push(res[2]); this.cars.push(res[3]); this.cars.push(res[4]);}
              else if(index === res.length-1) {this.cars.push(res[index-4]); this.cars.push(res[index-3]); this.cars.push(res[index-2]); this.cars.push(res[index-1]);}
              else if(index === res.length-2) {this.cars.push(res[index-3]); this.cars.push(res[index-2]); this.cars.push(res[index-1]); this.cars.push(res[index+1]);}
              else {this.cars.push(res[index-2]); this.cars.push(res[index-1]); this.cars.push(res[index+1]); this.cars.push(res[index+2]);}
            })
            .catch((err) => {
              err;
            })
        })
        .catch((err) => {
          console.log(err);
        });
      
    },
    methods: {
      ...mapActions(["buyCar", "getInfo", "fetchData"]),
      getImagesUrl(i) {
        return api.ROUTES.IMG.getUrl + this.info.data.src[i];
      },
      getImageUrl(src) {
        return api.ROUTES.IMG.getUrl + src;
      }
    }
  }
</script>

<style scoped>
  hr {
    margin-top: 10px;
    margin-bottom: 40px;
    margin-right: 120px;
  }
  h3 {
    margin-top: 50px;
  }
  h4 {
    margin: 10px;
    margin-top: 20px;
    width: 100px;
  }
  .carInfo {
    display: flex;
  }
  .spec {
    margin-left: 250px;
    margin-top: 10px;
    display: flex;
  }
  .price {
    width: 500px;
    height: 100px;
    align-content: center;
    text-align: right;
    display: flex;
    float: right;
    margin-top: 20px;
    position: relative;
    padding-bottom: 0;
  }
  .detailInfo {
    margin-top: 100px;
    color : black
  }
  .recommend {
    margin-right: 60px;
    left: 0;
  }
  .optionList {
    text-align: center;
    width: 110px;
    margin-top: 10px;
  }
  .recommend p {
    margin-top: 5px;
    margin-left: 20px;
    margin-bottom: 0px;
    opacity: 0.7;
  }
  .thumbnail {
    width: 100%;
    height: 200px;
    margin-bottom: 10px;
  }
  .headerClass{
    white-space: nowrap ;
    word-break: normal;
    overflow: hidden ;
    text-overflow: ellipsis;
  }
</style>