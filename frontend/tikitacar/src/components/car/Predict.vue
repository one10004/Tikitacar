<template>
  <v-app id="app">
    <v-main style="padding-left: 10%; margin-top: 50px;">
      <v-row>
        <p style="font-size:40px;"><span style="color : red;">30</span>초만에 내 차 시세 알아보기 !!</p><br>
        <p style="font-size:40px;"><span style="color : red;">5,000</span>건의 중고차 거래데이터를 기반하여 가격을 예측합니다.</p>
      </v-row>

      <v-row style="margin-right: 100px;">
        <v-col cols="12" sm="2">
          <h4>모델명</h4>
          <v-text-field
            placeholder="ex) 테슬라 모델 S"
            style="width: 100%;"
            v-model="info.name"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>연식</h4>
          <v-text-field
            placeholder="ex) 2018"
            style="width: 100%;"
            v-model="info.year"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>배기량(cc)</h4>
          <v-text-field
            placeholder="ex) 5000"
            style="width: 100%;"
            v-model="info.cc"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>주행거리</h4>
          <v-text-field
            placeholder="ex) 20000"
            style="width: 100%;"
            v-model="info.distance"
            type="number"
            suffix="km"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>색상</h4>
          <v-select
            placeholder="ex) 빨간색"
            style="width: 100%;"
            v-model="info.color"
            :items="colorOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>기어</h4>
          <v-select
            placeholder="ex) 자동"
            style="width: 100%;"
            v-model="info.gear"
            :items="gearOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>연료 타입</h4>
          <v-select
            placeholder="ex) 전기"
            style="width: 100%;"
            v-model="info.fuel"
            :items="fuelOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>좌석 수</h4>
          <v-text-field
            placeholder="ex) 5"
            style="width: 100%;"
            v-model="info.seat"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>옵션</h4>
          <v-text-field
            placeholder="ex) 1"
            style="width: 100%;"
            v-model="info.option"
            type="number"
            suffix="개"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>침수</h4>
          <v-text-field
            placeholder="ex) 1"
            style="width: 100%;"
            v-model="info.flooding"
            type="number"
            suffix="회"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>보험</h4>
          <v-text-field
            placeholder="ex) 2"
            style="width: 100%;"
            v-model="info.insurance"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>출고 가격</h4>
          <v-text-field
            placeholder="ex) 12000"
            style="width: 100%;"
            v-model="info.releasePrice"
            type="number"
            suffix="만원"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-btn
        color="primary"
        @click="predict()"
      >조회</v-btn>
      <v-dialog hide-overlay
          v-model="result" scrollable origin="top center">

          <v-card>
          <v-card-title>
            차량 정보
          </v-card-title>
          <hr>
          <v-row>
            <v-col cols="12" sm="4">
              <p>차량정보: {{this.info.name}}</p>
              <p>주행거리: {{this.info.distance}}km</p>
              <p>변속기: {{this.info.gear}}</p>
              <p>출시가격: {{this.info.releasePrice}}</p>
            </v-col>
            <v-col cols="12" sm="4">
              <p>색상: {{this.info.color}}</p>
              <p>연식: {{this.info.year}}</p>
              <p>인승: {{this.info.seat}} 인승</p>
            </v-col>
            <v-col cols="12" sm="4">
              <p>침수이력: {{this.info.flooding}} 회</p>
              <p>보험: {{this.info.insurance}} 개</p>
              <p>연료: {{this.info.fuel}}</p>
            </v-col>
          </v-row>
        <hr>
          <p><span style="font-weight: bold">TIKITACAR</span>가 예측한 당신의 차량의 예상 금액은
          <p style="color : blue; font-weight: bold;"> {{this.info.price}}만원
          <p>입니다.
          <v-card-actions>
            <v-btn color="primary" @click="sellMyCar()">내 차 팔러 가기</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <div style="display: none" v-if="result === -1">
      </div>
      <div class="result" v-else>

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
        name: "",
        cc: Number,
        distance: Number,
        color: "",
        gear: "",
        fuel: "",
        seat: Number,
        flooding: Number,
        insurance: Number,
        releasePrice: Number,
        option: Number,
        year: Number
      },
      result: false,
      colorOptions: ["없음","흰색","검정색","진회색","은색","진주색","회색","베이지색","빨간색","진청색","청색","파란색","하늘색","기타색상"],
      gearOptions: ["없음", "자동", "수동"],
      fuelOptions: ["없음", "가솔린", "디젤", "LPG", "전기"],
    }),
    methods: {
      ...mapActions(["predictPrice"]),
      predict() {
        this.predictPrice(this.info)
          .then((res) => {
            this.info.price = res.data.price;
            this.result = !this.result;
          })
          .catch((err) => {
            //console.log(err);
          })
      },
      sellMyCar() {
        this.$router.push({ 
          name: "Register", 
          query: {
            info : this.info,
          }
        });
      }
    },
  }
</script>

<style scoped>
  #app {
    font-family: 'Do Hyeon', sans-serif;
  }
  h3 {
    margin-top: 50px;
  }
  h4 {
    margin-top: 20px;
  }
  .carSearch {
    margin-top: 10px;
    width: 90%;
    height: 100px;
    padding: 0px;
    margin-left: 10px;
  }
  .searchBtn {
    position: absolute; 
    top: 30%; 
    right: 5%;
  }
  .text-center {
    margin-right: 10%;
  }
  .image-preview-wrapper {
    padding: 5px;
    position: relative;
  }
  .image-preview-wrapper img {
    padding: 5px;
    height: 80px;
    width: 80px;
  }
  .result {
    margin-top: 25px;
    margin-left: 10px;
  }
  .resultSentence {
    font-size: 40px;
  }
  .price {
    font-size: 60px;
    font-weight: bold;
    color: #10A5F5;
  } 
</style>
<style>
  input[type="number"]::-webkit-outer-spin-button,
  input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
  }
</style>