<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h3>차량 등록</h3>
      <h5>제목</h5>
      <v-text-field
        style="width: 80%;"
        v-model="info.title"
      ></v-text-field>
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
        <v-col cols="12" sm="2">
          <h5>판매 가격</h5>
          <v-text-field
            placeholder="ex) 6000"
            style="width: 100%;"
            v-model="info.price"
            type="number"
            suffix="만원"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-text-field
        solo
        height="150px"
        style="margin-right: 100px;"
        v-model="info.content"
      ></v-text-field>
      <v-btn
        color="primary"
        @click="registerCar(info)"
      >등록</v-btn>
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
        // sellerId: Number,
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
        price: Number,
        title: "",
        content: "",
        src: []
      },
    }),
    methods: {
      ...mapActions(["registerCar"])
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
</style>