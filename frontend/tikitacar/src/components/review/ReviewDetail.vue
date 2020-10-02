<template>
  <v-app id="app">
    <v-main>
      <v-form>
        <v-container>
          <v-row> 제목</v-row>
          <v-row>
            <v-text-field readonly v-model="reviewDetail.title" :counter="50" label="제목" name="title" required maxlength="50"></v-text-field>
          </v-row>
          <v-row> 거래 선택</v-row>
          <v-row>
            <v-text-field readonly v-model="reviewDetail.dealTitle"></v-text-field>
          </v-row>
          <v-row> 평점</v-row>
          <v-row>
            <star-rating read-only
                         v-model="reviewDetail.rating"
            ></star-rating>
          </v-row>
          <v-row> 내용</v-row>
          <v-row>
            <v-textarea filled name="context" readonly  v-model="reviewDetail.content"
                        ></v-textarea>
          </v-row>
          <v-row v-if="equal">
            <v-btn @click="reviewDelete">리뷰 삭제</v-btn>
            <v-btn @click="reviewUpdate">리뷰 수정</v-btn>
          </v-row>
        </v-container>
      </v-form>


    </v-main>

  </v-app>


</template>


<script>
import axios from 'axios';
import api from '@/api/api'
import swal from 'sweetalert'
import router from '@/router/'
export default {

  data : () => ({
    reviewDetail : {

    },
    equal : false
  }),
  created(){
    let review_id = this.$route.params.id;
    let URL = api.ROOT_URL + api.ROUTES.REVIEW.getReviewDetailURL + "/" + review_id;
    let config = this.$store.getters.config;
    axios.get(URL,config).then((res)=>{
      this.reviewDetail = res.data.data;
     // console.dir(this.reviewDetail);
     // console.log(this.reviewDetail.nickname+ " 111" + this.$store.getters.currentUserNickname)
      if(this.reviewDetail.nickname==this.$store.getters.currentUserNickname){
        console.log("일치")
        this.equal = true;
      }
    }).catch((err) =>{
      swal('X', err.response.data.msg, 'error');
    })
  },
  methods : {
    reviewDelete(){
      let review_id = this.$route.params.id;
      let URL = api.ROOT_URL + api.ROUTES.REVIEW.deleteReviewURL + "/" + review_id;
      let config = this.$store.getters.config;
      axios.delete(URL,config).then((res)=>{

        swal('O',res.data.msg,'success');
        router.push({name : 'Review'});
      }).catch((err)=>{

        swal('X',err.response.data.msg,'error');

      });
    },
    reviewUpdate(){
      let review_id = this.$route.params.id;
      router.push({name : "ReviewUpdate",params : {id: review_id}});
    }
  }
}


</script>