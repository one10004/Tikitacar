<template>
  <v-app id="app">
    <v-main style="padding-left: 10%; margin-top: 30px;">
      <p style="font-size: 40px;"><span style="color: blue;">고객</span>님의</p>
      <p style="font-size: 40px;">차량정보를 입력하고</p>
      <p style="font-size: 40px;"><span style="color: blue;">빅데이터 기반</span>의 시세를 알아보세요.</p>
      <h2 style="margin-top: 20px;">Step 1.</h2>
      <p>모델 입력</p>
      <v-row>
        <v-col cols="12" sm="2">
          <h4>제조사</h4>
          <v-select
            :items="manufacturerOptions"
            label="제조사"
            solo
            @change="selectManufactuer($event)"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>모델</h4>
          <v-select
            v-model="model"
            :items="modelOptions"
            label="모델"
            solo
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>세부 모델명</h4>
          <v-text-field
            placeholder="ex) 100D"
            style="width: 100%;"
            v-model="info.name"
          ></v-text-field>
        </v-col>
      </v-row>
      <h2 style="margin-top: 20px;">Step 2.</h2>
      <p>기본정보 입력</p>
      <v-row>
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
      </v-row>
      <h2 style="margin-top: 20px;">Step 3.</h2>
      <p>제원사항 입력</p>
      <v-row>
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
      </v-row>
      <h2 style="margin-top: 20px;">Step 4.</h2>
      <p>기타사항 입력</p>
      <v-row>
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

      <div style="display: none" v-if="result === -1">
      </div>
      <div class="result" v-else>
        <p class="resultSentence">빅데이터로 분석한 고객님의 {{model}} {{info.name}} 예상 금액은</p>
        <p class="price">{{this.info.price}} 만원</p>
        <p class="resultSentence">입니다.</p>
        <v-btn color="primary" @click="sellMyCar()">내 차 팔기</v-btn>
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
      result: -1,
      model: "",
      colorOptions: ["없음","흰색","검정색","진회색","은색","진주색","회색","베이지색","빨간색","진청색","청색","파란색","하늘색","기타색상"],
      gearOptions: ["없음", "자동", "수동"],
      fuelOptions: ["없음", "가솔린", "디젤", "LPG", "전기"],
      manufacturerOptions: ["현대", "기아", "쉐보레", "르노삼성", "쌍용"],
      modelOptions: [],
    }),
    methods: {
      ...mapActions(["predictPrice", "getModels"]),
      predict() {
        this.predictPrice(this.info)
          .then((res) => {
            this.info.price = res.data.price;
            this.result = this.result * -1;
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
      },
      selectManufactuer(event) {
        this.getModels(event)
          .then((res) => {
            this.modelOptions = res;
          })
          .catch((err) => {
            console.log(err);
          })
      },
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
    margin-top: 5x;
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