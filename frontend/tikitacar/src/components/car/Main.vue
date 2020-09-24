<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%;">
      <h3>시세 검색</h3>
      <v-card class="carSearch">
        <v-container fluid>
          <v-row
            align="center"
          >
            <v-col cols="12" sm="2">
              <v-select
                v-model="searchInfo.from"
                :items="fromOptions"
                label="수입/국산"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <v-select
                v-model="searchInfo.manufacturer"
                :items="manufacturerOptions"
                label="제조사"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <v-select
                v-model="searchInfo.model"
                :items="modelOptions"
                label="모델"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <v-select
                v-model="value"
                :items="items"
                label="세부모델"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <div class="searchBtn">
                <v-btn
                  color="primary"
                  @click="search(searchInfo)"
                >Search</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    <h3>내차 알아보기</h3>
    <v-container>
      <v-row>
        <v-col cols="12" sm="4">
          <v-text-field
            solo
            label="차량 번호를 입력해주세요."
            clearable
            background-color="#C8E8F6"
          >
            <v-icon 
              slot="append"
              @click="toSearch"
            >mdi-magnify</v-icon>
          </v-text-field>
        </v-col>
      </v-row>
    </v-container>

    <h3>추천 차량</h3>
    <v-container>
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
      ...mapActions(["search"]),
      toSearch() {
        this.$router.push({name: 'Search'});
      }
    }
  }
</script>

<style scoped>
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
  h3 {
    margin-top: 50px;
    margin-left: 8px;
  }
</style>