<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h3>내 차 가격 조회</h3>
      <v-row style="margin-right: 100px;">
        <v-col cols="12" sm="2">
          <h5>모델명</h5>
          <v-text-field
            placeholder="ex) 테슬라 모델 S"
            style="width: 100%;"
            v-model="info.name"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>연식</h5>
          <v-text-field
            placeholder="ex) 2018"
            style="width: 100%;"
            v-model="info.year"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>배기량(cc)</h5>
          <v-text-field
            placeholder="ex) 5000"
            style="width: 100%;"
            v-model="info.cc"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>주행거리</h5>
          <v-text-field
            placeholder="ex) 20000"
            style="width: 100%;"
            v-model="info.distance"
            type="number"
            suffix="km"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>색상</h5>
          <v-text-field
            placeholder="ex) 빨간색"
            style="width: 100%;"
            v-model="info.color"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>기어</h5>
          <v-text-field
            placeholder="ex) 자동"
            style="width: 100%;"
            v-model="info.gear"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>연료 타입</h5>
          <v-text-field
            placeholder="ex) 전기"
            style="width: 100%;"
            v-model="info.fuel"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>좌석 수</h5>
          <v-text-field
            placeholder="ex) 5"
            style="width: 100%;"
            v-model="info.seat"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>침수</h5>
          <v-text-field
            placeholder="ex) 1"
            style="width: 100%;"
            v-model="info.flooding"
            type="number"
            suffix="회"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>보험</h5>
          <v-text-field
            placeholder="ex) 2"
            style="width: 100%;"
            v-model="info.insurance"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>출고 가격</h5>
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
        @click="predict(info)"
      >조회</v-btn>

      <div style="display: none" v-if="result === 0">
      </div>
      <div class="result" v-else>
        <p class="resultSentence">차량의 예상 금액은</p>
        <p class="price">{{this.price}} 만원</p>
        <p class="resultSentence">입니다.</p>
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
        cc: 0,
        distance: 0,
        color: "",
        gear: "",
        fuel: "",
        seat: 0,
        flooding: 0,
        insurance: 0,
        releasePrice: 0,
      },
      price: 2300,
      result: 0,
    }),
    methods: {
      ...mapActions(["predictPrice"]),
      predict() {
        this.result = 1;
        this.predictPrice(this.info)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          })
      }
    },
  }
</script>

<style scoped>
  h3 {
    margin-top: 50px;
  }
  h5 {
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