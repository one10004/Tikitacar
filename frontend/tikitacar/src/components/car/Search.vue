<template>
  <v-app id="app">
    <v-main style="padding-left: 10%; margin-top: 10px; font-family: 'Do Hyeon', sans-serif;">
      <h3>빠른 시세 검색</h3>
      <v-card class="carSearch">
        <v-container fluid>
          <v-row
            align="center"
          >
            <v-col cols="12" sm="3">
              <v-select
                :items="fromOptions"
                label="수입/국산"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="3">
              <v-select
                :items="manufacturerOptions"
                label="제조사"
                solo
                @change="selectManufactuer($event)"
              ></v-select>
            </v-col>
            <v-col cols="12" sm="3">
              <v-select
                v-model="model"
                :items="modelOptions"
                label="모델"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <div class="searchBtn">
                <v-btn
                  color="primary"
                  @click="quickSearch()"
                >Search</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
      <div style="display: none" v-if="result === -1">
      </div>
      <div class="result" v-else>
        <p class="resultSentence">{{model}}의 시세 가격은</p>
        <p class="price">{{priceLow}} 만원 <span class="resultSentence">~</span> {{priceHigh}} 만원</p>
        <p class="resultSentence">입니다.</p>
      </div>

      <div style="display: flex;">
        <div style="width : 250px;">
          <h3> 세부 검색 </h3>
          <v-expansion-panels multiple class="specificSearch">
            <v-expansion-panel>
              <v-expansion-panel-header>모델명</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.name"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>가격</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.price"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>주행거리</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.distance" suffix="km"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>연식</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.year1"></v-text-field>
                ~
                <v-text-field v-model="searchInfo.year2"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>침수여부</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.flooding" suffix="회"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>색상</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-select :items="colorOptions" v-model="searchInfo.color"></v-select>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>보험여부</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.insurance" suffix="개"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>변속기</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-select :items="gearOptions" v-model="searchInfo.gear"></v-select>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>연료타입</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-select :items="fuelOptions" v-model="searchInfo.fuel"></v-select>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>좌석수(인승)</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.seat" suffix="인승"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>배기량(cc)</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-text-field v-model="searchInfo.cc" suffix="cc"></v-text-field>
              </v-expansion-panel-content>
            </v-expansion-panel>
            <v-expansion-panel>
              <v-expansion-panel-header>판매상태</v-expansion-panel-header>
              <v-expansion-panel-content>
                <v-select :items="statusOptions" v-model="searchInfo.status"></v-select>
              </v-expansion-panel-content>
            </v-expansion-panel>
          </v-expansion-panels>
          <v-btn color="primary" style="margin-top: 20px;" @click="search()">search</v-btn>
        </div>
        <div style="width: 100%; padding-left: 20px;">
          <h3> 검색 결과 </h3>
          <v-container>
            <v-row class="recommend">
              <v-col cols="12" sm="4" v-for="car in cars" :key="car.id">
                <router-link :to="{ name: 'Detail', params: { id: car.id } }" style="text-decoration: none;">
                  <v-card style="height: 500px;">
                    <v-card-title><div class="headerClass">{{car.name}}</div></v-card-title>
                    <img class="thumbnail" :src="getImageUrl(car.src)" />
                    <p>연식: {{car.year}}</p>
                    <p>주행거리: {{car.distance}} km</p>
                    <p>출시가격: {{car.releasePrice}}</p>
                    <p>색상: {{car.color}}</p>
                    <p>판매자: {{car.nickname}}</p>
                    <div style="display: flex;">
                      <p><span style="font-size: 30px; color: blue;">{{car.price}}</span>만원</p>
                      <p v-if="car.status === '판매완료'" 
                        style="color: gray; float: right; margin-top: 20px; margin-left: 120px; font-size: 18px; color: red;">판매완료</p>
                    </div>
                  </v-card>
                </router-link>
              </v-col>         
            </v-row>
          </v-container>
        </div>
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
      model: "",
      priceLow: 0,
      priceHigh: 0,
      result: -1,
      name: "",
      cars: [],
      fromOptions: ["국산"],
      manufacturerOptions: ["현대", "기아", "쉐보레", "르노삼성", "쌍용"],
      modelOptions: [],
      colorOptions: ["없음","흰색","검정색","진회색","은색","진주색","회색","베이지색","빨간색","진청색","청색","파란색","하늘색","기타색상"],
      gearOptions: ["없음", "자동", "수동"],
      fuelOptions: ["없음", "가솔린", "디젤", "LPG", "전기"],
      statusOptions: ["없음", "판매중", "판매완료"],
    }),
    methods: {
      ...mapActions(["fetchData", "getModels", "getMinMax"]),
      selectManufactuer(event) {
        this.getModels(event)
          .then((res) => {
            this.modelOptions = res;
            //console.log(res);
          })
          .catch((err) => {
            //console.log(err);
          })
      },
      search() {
        this.fetchData(this.searchInfo)
        .then((res) => {
          this.cars = res;
        })
        .catch((err) => {
          //console.log(err);
        });
      },
      getImageUrl(src) {
        return api.ROUTES.IMG.getUrl + src;
      },
      quickSearch() {
        this.result = this.result * -1;
        this.getMinMax(this.model)
          .then((res) => {
            this.priceLow = res.priceLow;
            this.priceHigh = res.priceHigh;
          })
          .catch((err) => {
            //console.log(err);
          })
      },
    },
    created() {
      this.search();
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
  .specificSearch{
    margin-top: 20px;
  }
  .headerClass{
    white-space: nowrap ;
    word-break: normal;
    overflow: hidden ;
    text-overflow: ellipsis;
  }
  .thumbnail {
    width: 100%;
    height: 200px;
    margin-bottom: 10px;
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
  .recommend p {
    margin-top: 5px;
    margin-left: 20px;
    margin-bottom: 0px;
    opacity: 0.7;
  }
</style>