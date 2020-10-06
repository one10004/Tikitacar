<template>
  <v-app id="app">
    <v-main>
      <v-row>

        <v-col offset-md="2" md="5">
          <v-card>
            <v-card-title>
              <v-toolbar color="primary"
              dark
              >
                비밀번호 찾기
              </v-toolbar>
            </v-card-title>
            <v-card-text>
              <v-form>
                <v-text-field
                    type=text
                    placeholder="이메일을 입력해주세요"
                    v-model="UserFindPasswordRequest.email"
                >

                </v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn color="primary" @click="findPassword(UserFindPasswordRequest)">
                비밀번호 찾기
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
       </v-main>
  </v-app>

</template>

<script>
//import {mapActions} from 'vuex';
import api from "@/api/api";
import axios from "axios";
import swal from "sweetalert";
import router from "@/router";
export default{
  data : ()=>({
    UserFindPasswordRequest : {
      email : ""
    }
  }),
  methods :{
    findPassword(){
      if(!this.UserFindPasswordRequest.email)
      {
        swal('에러','이메일이 비어있습니다.','error');
        return;
      }
      let URL = api.ROOT_URL + api.ROUTES.AUTH.pwInquiryURL;

      axios.post(URL,this.UserFindPasswordRequest).then((res) =>{
        swal(res.data.msg,'success');
        router.push({ name: 'Home' });
      }).catch((err) => {

        swal('실패', err.response.data.msg, 'error');
      });
    }
  }
}

</script>