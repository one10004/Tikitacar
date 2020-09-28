<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h3>시세 검색</h3>
      <v-card class="carSearch">
        <v-container fluid>
          <v-row
            align="center"
          >
            <v-col cols="12" sm="2">
              <v-select
                v-model="this.from"
                :items="this.fromOptions"
                label="수입/국산"
                :placeholder="this.from"
                solo
                @change="selectFrom($event)"
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <v-select
                v-model="value"
                :items="this.manufacturerOptions"
                label="제조사"
                :placeholder="this.manufacturer"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <v-select
                v-model="value"
                :items="this.modelOptions"
                label="모델"
                :placeholder="this.model"
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

      <div style="display: flex;">
        <div style="width : 250px;">
          <h3> 세부 검색 </h3>
          <v-select
            label="option1"
          ></v-select>
          <v-select
            label="option1"
          ></v-select>
          <v-select
            label="option1"
          ></v-select>
          <v-select
            label="option1"
          ></v-select>
        </div>
        <div style="width: 100%; padding-left: 20px;">
          <h3> 검색 결과 </h3>
          <v-container>
            <v-row class="recommend">
              <v-col cols="12" sm="4" v-for="car in cars" :key="car.name">
                <router-link :to="{ name: 'Detail', params: { id: 66 } }" style="text-decoration: none;">
                  <v-card style="height: 500px;">
                    <v-card-title class="headline">{{car.name}}</v-card-title>
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

  export default {
    props: {
      source: String,
    },
    data: () => ({
      searchInfo: {
        from: "수입",
        manufacturer: "Tesla",
        model: 'Model 3',
      },
      from: "",
      manufacturer: "",
      model: "",
      cars: [],
      fromOptions: ["국산", "수입"],
      manufacturerOptions: [],
      modelOptions: [],
    }),
    methods: {
      ...mapActions(["fetchData", "search", "getMfrOptions"]),
      selectFrom(event) {
        this.getMfrOptions(event)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          })
      }
    },
    created() {
      this.from = this.$route.query.from;
      this.manufacturer = this.$route.query.manufacturer;
      this.model = this.$route.query.model;
      this.fetchData()
        .then((res) => {
          this.cars = res;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  }
</script>

<style scoped>
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
</style>