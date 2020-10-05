<template>


  <v-app id="app">
    <v-main>
      <v-container fluid>
        <v-data-table :items="reviewList"
                      :headers="headers"
                      :items-per-page="10"
                      class="elevation-1"
                      @click:row="rowClick"
        >

        </v-data-table>
        <div class="text-center pt-2">
          <v-pagination
              v-model="page"
              :length="pageCount"
              @input="getnext"
          ></v-pagination>

        </div>
      </v-container>
      <v-row v-if="$store.getters.isLoggedIn">
        <v-btn outlined color="blue" router-link :to="{name  : 'CreateReview'}">후기 작성</v-btn>
      </v-row>
    </v-main>
  </v-app>
</template>

<script>
import axios from 'axios';
import api from '@/api/api'
//import router from "@/router/";
import swal from "sweetalert";

export default {
  data: () => ({
    page : 1,
    pageCount : 10,
    itemsPerPage : 10,
    reviewList: [],
    headers: [{text: '번호', align: 'left', sortable: false, value: 'id',}, {
      text: '제목',
      value: 'title'
    }, {text : '글쓴이', value : 'nickname'},{text: '작성일', value: 'date'},
    {test : "점수 ", value : 'rating'}],

    totalPage: ""
  }),
  created() {
    let URL = api.ROOT_URL + api.ROUTES.REVIEW.getReviewListURL + "/" + "0";
    let config = this.$store.getters.config;
    console.log(URL + " " + config);
    axios.get(URL, config).then((res) => {
      this.reviewList = res.data.reviewSearchResponseDtoList;
      console.dir(this.reviewList);
      this.totalPage = res.data.totalPage;
    }).catch((err) => {
      swal("X", err.response.data.msg, 'error');
    });

  }, methods : {
    rowClick(data){
      this.$router.push({name :'ReviewDetail',params : {id: data.id}})
    },
    getnext(page){
      let URL = api.ROOT_URL + api.ROUTES.REVIEW.getReviewListURL + "/" + (page-1);
      let config = this.$store.getters.config;
      console.log(URL + " " + config);
      axios.get(URL, config).then((res) => {
        this.reviewList = res.data.reviewSearchResponseDtoList;
        console.dir(this.reviewList);
        this.totalPage = res.data.totalPage;
      }).catch((err) => {
        swal("X", err.response.data.msg, 'error');
      });
    }
  }


}


</script>