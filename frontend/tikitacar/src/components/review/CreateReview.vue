<template>
  <v-app id="app">
    <v-main>
      <v-form>
        <v-container>
          <v-row> 제목</v-row>
          <v-row>
            <v-text-field :counter="50" label="제목" name="title" required v-model="createReviewRequestData.title" maxlength="50"></v-text-field>
          </v-row>
          <v-row> 거래 선택</v-row>
          <v-row>
          <v-select :items="completeDealList"
                    item-text="title"
                    item-value="id"
                    v-model="createReviewRequestData.deal"
          >

          </v-select>
          </v-row>
          <v-row> 평점</v-row>
          <v-row>
            <star-rating v-model="createReviewRequestData.rating"></star-rating>
          </v-row>
          <v-row> 내용</v-row>
          <v-row>
            <v-textarea filled name="context" hint="내용을 입력해주세요." v-model="createReviewRequestData.content" :counter="1000"
                        maxlength="1000"></v-textarea>
          </v-row>
          <v-row>
            <v-btn block outlined color="blue" @click="createReviewRequest"> 등록</v-btn>
          </v-row>
        </v-container>
      </v-form>


    </v-main>

  </v-app>


</template>


<script>
import axios from 'axios';
import api from '@/api/api'
import swal from 'sweetalert';
import router from "@/router";
export default {
  data: () => ({
    createReviewRequestData: {
        "writer" : "",
        "deal" : "",

  },
    completeDealList: [],
  }),
  mounted() {
    let URL = api.ROOT_URL + api.ROUTES.CAR.dealSearchMyURL;
    let config = this.$store.getters.config;

    axios.get(URL, config).then((res) => {
      this.completeDealList = res.data.data;
      console.dir(res);
    }).catch((err) => {
      swal('X', err.response.data.msg, 'error');
    })
  },
  methods: {
    createReviewRequest: function () {
      let URL = api.ROOT_URL + api.ROUTES.REVIEW.createReviewURL
      let config = this.$store.getters.config;

      axios.post(URL, this.createReviewRequestData,config).then((res) => {

        swal('O', res.data.msg, 'success');
        router.push({name : "Review"});
      }).catch((err) => {

        swal("X", err.response.data.msg, 'error');
      });
    },
    getCompletedDealsRequest: function () {

    }
  }
}
</script>