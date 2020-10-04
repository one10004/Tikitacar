<template>
  <v-app id="inspire">
    <span class="background"></span>
    <v-main style="padding-left: 250px;">

    <h3>추천 차량</h3>
    <v-container style="margin-left: 0px;">
      <v-row class="recommend">
        <v-col cols="12" sm="4">
          <router-link :to="{ name: 'Detail', params: { id: 66 } }" style="text-decoration: none;">
            <v-card style="height: 500px;">
              <v-card-title class="headline">제네시스 G70</v-card-title>
            </v-card>
          </router-link>
          
        </v-col>
        <v-col cols="12" sm="4">
          <router-link :to="{ name: 'Detail', params: { id: 66 } }" style="text-decoration: none;">
            <v-card style="height: 500px;">
              <v-card-title class="headline">테슬라 모델 s</v-card-title>
            </v-card>
          </router-link>
        </v-col>
        <v-col cols="12" sm="4">
          <router-link :to="{ name: 'Detail', params: { id: 66 } }" style="text-decoration: none;">
            <v-card style="height: 500px;">
              <v-card-title class="headline">코나 EV</v-card-title>
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
      fromOptions: ["수입", "국산"],
      manufacturerOptions: ["현대", "기아", "쌍용", "테슬라", "BMW", "벤츠", "아우디"],
      modelOptions: ["제네시스 G80", "티볼리 아머", "모델 S", "이클래스", "A8"]
    }),
    methods: {
      ...mapActions(["search", "getMfrOptions"]),
      toSearch() {
        this.$router.push({name: 'Search'});
      },
      selectFrom(event) {
        this.getMfrOptions(event)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          })
      }
    }
  }
</script>

<style scoped>
  .background {
    background: url("../../assets/background.jpg");
    width: 100%;
    height: 100%;
    position: absolute;
    top: 0;
    left: 0;
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
  h3 {
    margin-top: 50px;
    margin-left: 8px;
  }
</style>