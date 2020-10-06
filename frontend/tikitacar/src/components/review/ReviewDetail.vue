<template>
  <v-app id="app">
    <v-main>
      <v-form>
        <v-container fluid>
          <v-row> 제목</v-row>
          <v-row>
            <v-text-field readonly v-model="reviewDetail.title"></v-text-field>
          </v-row>
          <v-row> 거래 선택</v-row>
          <v-row>
            <v-text-field readonly v-model="reviewDetail.dealTitle"></v-text-field>
          </v-row>
          <v-row>차량 상세 정보</v-row>
          <v-row>
            <v-col cols="12" sm="4">
              <p>차량정보: {{this.info.data.name}}</p>
              <p>주행거리: {{this.info.data.distance}}km</p>
              <p>변속기: {{this.info.data.gear}}</p>
              <p>차종: 중형차</p>
            </v-col>
            <v-col cols="12" sm="4">
              <p>색상: {{this.info.data.color}}</p>
              <p>연식: {{this.info.data.year}}</p>
              <p>연료: {{this.info.data.fuel}}</p>
              <p>연비: 8.1km</p>
            </v-col>
            <v-col cols="12" sm="4">
              <p>전손이력: 없음</p>
              <p>침수이력: {{this.info.data.flooding}} 회</p>
              <p>용도이력: 있음</p>
              <p>소유자변경: 없음</p>
            </v-col>
          </v-row>
          <v-row>
            <h3>주행거리 분석</h3>
          </v-row>
          <v-row>
            <p>주행 거리는 총 <span style="font-size: 30px; color: blue; padding-left: 8px;">{{this.info.data.distance}} km</span> 로 일반적인 연평균 주행거리 대비 [<span style="color: green; font-weight: bold;">{{this.status}}</span>] 입니다.</p>
          </v-row>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-row>
            <h3>주요옵션</h3>
          </v-row>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-row>

              <v-icon size="60px" style="margin-right: 30px;">mdi-map-marker</v-icon>
              <v-icon size="60px" style="margin-right: 30px;">mdi-car-key</v-icon>
              <v-icon size="60px" style="margin-right: 30px;">mdi-car-light-high</v-icon>
              <v-icon size="60px" style="margin-right: 30px;">mdi-car-electric</v-icon>

         </v-row>
          <v-spacer></v-spacer>
          <v-spacer></v-spacer>
          <v-row> 평점</v-row>
          <v-row>
            <star-rating read-only
                         v-model="reviewDetail.rating"
            ></star-rating>
          </v-row>
          <v-row> 내용</v-row>
          <v-row>
            <v-textarea filled readonly  v-model="reviewDetail.content"
                        ></v-textarea>
          </v-row>
          <v-row v-if="equal">
            <v-btn color="red" dark @click="reviewDelete">리뷰 삭제</v-btn>
            <v-btn color="primary" @click="reviewUpdate">리뷰 수정</v-btn>
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
import {mapActions} from "vuex";
export default {

  data : () => ({
    reviewDetail : {

    },
    equal : false,
    info : {},
  }),
  created(){
    let review_id = this.$route.params.id;
    let URL = api.ROOT_URL + api.ROUTES.REVIEW.getReviewDetailURL + "/" + review_id;
    let config = this.$store.getters.config;
    axios.get(URL,config).then((res)=>{
      this.reviewDetail = res.data.data;
     // console.dir(this.reviewDetail);
     // console.log(this.reviewDetail.nickname+ " 111" + this.$store.getters.currentUserNickname)
      console.dir(this.reviewDetail)
      console.log(this.$store.getters.currentUserNickname)
      if(this.reviewDetail.nickname==this.$store.getters.currentUserNickname){
        console.log("일치")
        this.equal = true;
      }

      this.getInfo(this.reviewDetail.dealId)
          .then((res) => {
            this.info = res.data;
            console.log("??")
            console.dir(this.info);
            if(this.info.data.distance > 50000) this.status = "많음";
            else if(this.info.data.distance > 20000) this.status = "보통";
            else this.status = "적음";
          })
          .catch((err) => {
            console.log(err);
          })
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
    }  , ...mapActions(["buyCar", "getInfo"]),
  }
}


</script>

<style scoped>
*{
  padding: 3px;
}
.detailInfo {
  margin-top: 100px;
}
</style>