<template>
  <v-app id="app">
    <span class="background"></span>
    <v-main style="padding-left: 10%;">
      <transition name="fade" appear>
        <p style="font-size: 90px; margin-top: 100px;">더이상 <span style="color: blue;">티키타카</span> 하지 마세요,</p>
      </transition>
      <transition name="fade" appear>
        <p style="font-size: 90px; margin-left: 250px;">이제 <span style="color: blue;">티키타Car</span> 하세요.</p>
      </transition>

    <h2>추천 차량</h2>
    <v-container style="margin-left: 0px;">
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
    </v-container>

    </v-main>
  </v-app>
</template>

<script>
import {mapActions} from 'vuex';
import api from '@/api/api.js'
  export default {
    props: {
      source: String,
    },
    data: () => ({
      searchInfo: {
        from: "",
        manufacturer: "",
        model: "",
      },
      info: {
        cc: -1,
        color: "없음",
        distance: -1,
        flooding: -1,
        fuel: "없음",
        gear: "없음",
        insurance: -1,
        name: "없음",
        price: 2000,
        seat: -1,
        status: "없음",
        year1: 2010,
        year2: 2020
      },
      cars: [],
      fromOptions: ["수입", "국산"],
      manufacturerOptions: ["현대", "기아", "쌍용", "테슬라", "BMW", "벤츠", "아우디"],
      modelOptions: ["제네시스 G80", "티볼리 아머", "모델 S", "이클래스", "A8"]
    }),
    methods: {
      ...mapActions(["search", "getMfrOptions", "fetchData"]),
      toSearch() {
        this.$router.push({name: 'Search'});
      },
      search() {
        this.fetchData(this.info)
        .then((res) => {
          for(var i = 0; i < res.length; i++) {
            this.cars.push(res[i]);
            if(i > 6) break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
      },
      getImageUrl(src) {
        return api.ROUTES.IMG.getUrl + src;
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
  .background {
    background: url("../../assets/background.jpg");
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 60px;
    opacity: 0.4;
  }
  .carSearch {
    margin-top: 10px;
    width: 1100px;
    height: 100px;
    padding: 0px;
    margin-left: 10px;
  }
  .searchBtn {
    position: absolute; 
    top: 30%; 
    right: 5%;
  }
  h2 {
    margin-top: 50px;
    margin-left: 8px;
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
  .fade-enter-active, .fade-leave-active {
    transition: opacity 1.8s;
  }
  .fade-enter, .fade-leave-to {
    opacity: 0;
  }
</style>