<template>


  <v-app id="app">
    <v-main>
      <v-container >
        <v-row>
          <h2>후기 게시판</h2></v-row>
        <v-row >
        <v-layout child-flex>
          <v-data-table :items="reviewList"
                        :headers="headers"
                        :hide-default-footer="true"
                        class="elevation-1"
          >
            <template v-slot:item="props">
              <tr align="center" @click="rowClick(props.item)">
                <td width="10%">{{props.item.id}}</td>
                <td width="50%">{{props.item.title}}</td>
                <td width="10%">{{props.item.nickname}}</td>
                <td width="10%"><star-rating star-size="25" v-model="props.item.rating" :show-rating=false  read-only></star-rating></td>
                <td width="10%">{{props.item.date}}</td>
              </tr>
            </template>
          </v-data-table>
        </v-layout>




       <!--<div class="text-center pt-2">


        </div> -->
        </v-row>
        <v-row>
          <v-pagination
              v-model="page"
              :length="totalPage"
              @input="getnext"
          ></v-pagination>
        </v-row>
      </v-container>

      <v-row v-if="$store.getters.isLoggedIn">
        <v-btn  color="primary" router-link :to="{name  : 'CreateReview'}">후기 작성</v-btn>
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
   // pageCount : 10,
    itemsPerPage : 10,
    reviewList: [],
    headers: [{text: '번호', align: 'center', sortable: false, value: 'id',}, {
      text: '제목',align: 'center',
      value: 'title'
    },{text : '글쓴이', align: 'center',value : 'nickname'}, {text : "점수",align: 'center', value : 'rating'},{text: '작성일', align: 'center',value: 'date'}]
    ,

    totalPage: ""
  }),
  created() {
    let URL = api.ROOT_URL + api.ROUTES.REVIEW.getReviewListURL + "/" + "0";
    let config = this.$store.getters.config;
    ////console.log(URL + " " + config);
    axios.get(URL, config).then((res) => {
      this.reviewList = res.data.reviewSearchResponseDtoList;
      ////console.dir(this.reviewList);
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
      ////console.log(URL + " " + config);
      axios.get(URL, config).then((res) => {
        this.reviewList = res.data.reviewSearchResponseDtoList;
        ////console.dir(this.reviewList);
        this.totalPage = res.data.totalPage;
      }).catch((err) => {
        swal("X", err.response.data.msg, 'error');
      });
    }
  }


}


</script>