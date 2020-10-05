<template>
  <v-app id="app">
    <v-main>
      <v-container fluid>

        <v-row justify="start">

          <v-col offset="2" md="6">
            <v-card id = "myinfo">
              <v-toolbar
                  color="primary"
                  dark
                  flat>
                <v-toolbar-title> 회원 정보 수정</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-form>
                <v-text-field
                    readonly
                    label = "email"
                    id = "아이디"
                    placeholder="아이디"
                    v-model="userInfo.email"
                >
                </v-text-field>
                <v-text-field
                    readonly
                    label = "닉네임"
                    id = "이름"
                    placeholder="이름"
                    v-model="userInfo.nickname"
                >
                </v-text-field>
                <v-text-field
                    label="비밀번호"
                    name="pw"
                    id ="pw"
                    type="password"
                    v-model="userInfo.password"
                    append-icon="mdi-lock"
                    required
                ></v-text-field>

                <v-text-field
                    label="비밀번호 확인"
                    name="passwordConfirm"
                    type="password"
                    v-model="userInfo.passwordConfirm"
                    append-icon="mdi-lock"
                    v-on:blur="passwordCheck($event)"
                    required
                >
                </v-text-field>
                <v-text-field
                    label = "생년월일(YYYY-MM-DD)"
                    id = "성별"
                    placeholder="XXXXYYDD"
                    v-model="userInfo.birth"
                >

                </v-text-field>

                <v-select
                    label="성별"
                    :items="['남', '여']"
                    id="gender"
                    v-model="userInfo.gender"
                    required
                >
                </v-select>

                <v-text-field

                    label = "핸드폰번호(010XXXXXXXX)"
                    id = "phoneNum"
                    placeholder="XXXXXXXXXXX"
                    v-model="userInfo.phone"
                >

                </v-text-field>
                <v-text-field
                    label="주소"
                    name="address"
                    id ="address"
                    v-model="userInfo.address"
                    :readonly = true
                    type="text"

                    required
                >
                  <template slot="append">
                    <v-btn outlined style="margin-bottom: 6px" @click="addressModal = true">
                      <v-icon left>mdi-magnify</v-icon>
                      주소 검색
                    </v-btn>
                  </template>
                </v-text-field>
                <v-text-field
                    label="상세주소"
                    name="addressDetail"
                    id ="addressDetail"
                    type="text"
                    placeholder="상세주소"
                    v-model="userInfo.addressDetail"
                >
                </v-text-field>
              </v-form>
              <v-card-actions>
                <v-btn color="primary" @click="updateUserInfo(userInfo)">수정 제출</v-btn>
                <v-dialog
                    v-model="addressModal">
                  <vue-daum-postcode @complete="handleAddress"></vue-daum-postcode>
                </v-dialog>
              </v-card-actions>

            </v-card>
          </v-col>

        </v-row>
      </v-container>

    </v-main>


  </v-app>


</template>



<script>

import api from "@/api/api";
import axios from "axios";
import swal from "sweetalert";
import router from "@/router/"

export default {
  data : () =>({
    updateUserInfoRequest : {

    },
    userInfo : {

    },
    checking : {

    },
    addressInfo : "",
    addressModal : false
  }),
  created(){

    let URL = api.ROOT_URL + api.ROUTES.USERS.getUserInfoURL;
    let config = {
      headers : {
        "Authorization" : "Bearer" + " " + this.$store.getters.getAuthToken
      }
    };
    //console.log(config);
    axios.get(URL,config).then((res) => {

      this.userInfo = res.data.user;

    }).catch((err) => {
      swal('X', err.response.data.msg, 'warning');
    });

  },
  methods : {
    handleAddress : function(data){
      this.userInfo.address = data.address;
      this.addressModal = false;
    },
    updateUserInfo : function(request){
        let URL = api.ROOT_URL + api.ROUTES.USERS.updateUserURL;
        if(!this.checking.password){
        // alert("비밀번호가 일치하지 않습니다.");
        swal('X', "비밀번호가 일치하지 않습니다.", 'error');
        return;
       }
        let config = {
          headers : {
            "Authorization" : "Bearer" + " " + this.$store.getters.getAuthToken
          }
        };
        axios.put(URL,request,config).then((res) =>{
          console.dir(res);
          console.dir(res.data)
          alert(res.data.msg);
          router.push({name : "MyPage"});
        //  router.push({name : "Home"});
        }).catch((err) => {
          console.dir(err);
          swal('X', err.response.data.msg, 'warning');
        })


    },
    passwordCheck : function(){
      //event.target.

      if(this.userInfo.passwordConfirm != this.userInfo.password){
        alert("비밀번호가 일치하지 않습니다.");
        this.checking.password = false;
        this.userInfo.passwordConfirm = "";
      }else{
        this.checking.password = true;
      }
    }
  }
}

</script>


<style>


</style>