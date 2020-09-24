<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h2>{{this.info.data.title}}</h2>
      <hr>
      <div class="carInfo">
        <div>
          <div class="mainImage">
            Image
          </div>
          <div class="subImages">
            <div class="subImage1">sub image1</div>
            <div class="subImage1">sub image2</div>
            <div class="subImage1">sub image3</div>
            <div class="subImage1" style="margin-right: 0px;">sub image4</div>
          </div>
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
                :label="'사고 경험 1회'"
              ></v-checkbox>
            </div>
            <div class="price">
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 20px;">
                <b>판매가격</b>
              </div>
              <div style="width: 80px; height: 60px; position: absolute; bottom: 0; margin-left: 170px;">
                <p style="font-size: 50px; color: blue">{{this.price}}</p>
              </div>
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 300px;">
                <b>만원</b>
              </div>
              <v-btn
                large
                color="primary"
                style="position: absolute; bottom: 0; margin-left: 430px;"
                @click="buyCar(model)"
              >구매</v-btn>
            </div>
          </div>
        </div>
      </div>
      <div class="detailInfo">
        <h3>차량 정보</h3>
        <v-row>
          <v-col cols="12" sm="4">
            <p>차량정보: 12가1234</p>
            <p>주행거리: 8,000km</p>
            <p>변속기: 오토</p>
            <p>차종: 중형차</p>
          </v-col>
          <v-col cols="12" sm="4">
            <p>색상: 하얀색</p>
            <p>연식: 19년형</p>
            <p>연료: 전기</p>
            <p>연비: 8.1km</p>
          </v-col>
          <v-col cols="12" sm="4">
            <p>전손이력: 없음</p>
            <p>침수이력: 없음</p>
            <p>용도이력: 있음</p>
            <p>소유자변경: 없음</p>
          </v-col>
        </v-row>
      </div>
      <div>
        <h3>주행거리 분석</h3>
        <p>주행 거리는 총 <span style="font-size: 30px; color: blue; padding-left: 8px;">{{this.km}} km</span> 로 일반적인 연평균 주행거리 대비 [<span style="color: green; font-weight: bold;">적음</span>] 입니다.</p>
      </div>
      <div>
        <h3>주요옵션</h3>
        <div style="display: flex;">
          <v-icon size="60px" style="margin-right: 30px;">mdi-map-marker</v-icon>
          <v-icon size="60px" style="margin-right: 30px;">mdi-car-key</v-icon>
          <v-icon size="60px" style="margin-right: 30px;">mdi-car-light-high</v-icon>
          <v-icon size="60px" style="margin-right: 30px;">mdi-car-electric</v-icon>
        </div>
      </div>
      <div>
        <h3>판매자 정보</h3>
      </div>
      <div>
        <h3>동급 차량 추천</h3>
        <!-- <v-container> -->
          <v-row class="recommend">
            <v-col cols="12" sm="4">
              <router-link :to="{ name: 'Detail', params: { id: 1 } }" style="text-decoration: none;">
                <v-card style="height: 500px;">
                  <v-card-title class="headline">제네시스 G70</v-card-title>
                </v-card>
              </router-link>
              
            </v-col>
            <v-col cols="12" sm="4">
              <router-link :to="{ name: 'Detail', params: { id: 2 } }" style="text-decoration: none;">
                <v-card style="height: 500px;">
                  <v-card-title class="headline">테슬라 모델 s</v-card-title>
                </v-card>
              </router-link>
            </v-col>
            <v-col cols="12" sm="4">
              <router-link :to="{ name: 'Detail', params: { id: 3 } }" style="text-decoration: none;">
                <v-card style="height: 500px;">
                  <v-card-title class="headline">코나 EV</v-card-title>
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

  export default {
    props: {
      source: String,
    },
    data: () => ({
      info: {
        // email: "",
        // nickname: "",
        // phone: "",
        // address: "",
        // name: "",
        // year: Number,
        // cc: Number,
        // distance: Number,
        // color: "",
        // gear: "",
        // fuel: "",
        // seat: Number,
        // flooding: Number,
        // insurance: Number,
        // releasePrice: Number,
        // price: Number,
        // title: "",
        // content: ""
      }
    }),
    created() {
      this.getInfo(66)
        .then((res) => {
          this.info = res.data;
          console.log(this.info);
        })
        .catch((err) => {
          console.log(err);
        })
    },
    methods: {
      ...mapActions(["buyCar", "getInfo"])
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
  .mainImage {
    line-height: 350px;
    text-align: center;
    width: 450px;
    height: 350px;
    border: solid;
  }
  .subImages {
    display: flex;
    width: 450px;
  }
  .subImage1 {
    line-height: 75px;
    text-align: center;
    margin: 5px 19px 5px 0px;
    width: 98px;
    height: 80px;
    border: solid;
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
  }
  .recommend {
    margin-right: 60px;
    left: 0;
  }
</style>