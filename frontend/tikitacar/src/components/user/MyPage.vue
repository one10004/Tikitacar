<template>
  <v-app id="app">
    <v-main>

      <v-container fluid>

       <v-row>

        <v-col offset="2" md="6">
          <v-card id = "myinfo">
            <v-toolbar
                color="primary"
                dark
                flat>
              <v-toolbar-title>내 회원 정보</v-toolbar-title>
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
                  v-model="userInfo.nickname"
              >
              </v-text-field>
              <v-text-field
                                    readonly
                                    label = "성별"
                                    id = "성별"
                                    placeholder="성별"
                                    v-model="userInfo.gender"
                                >

                             </v-text-field>

              <v-text-field
                  label = "생년월일(YYYY-MM-DD)"
                  id = "성별"
                  placeholder="XXXXYYDD"
                  v-model="userInfo.birth"
              >

              </v-text-field>

              <v-text-field
                  readonly
                  label = "핸드폰번호(010XXXXXXXX)"
                  id = "phoneNum"
                  placeholder="XXXXXXXXXXX"
                  v-model="userInfo.phone"
              >

              </v-text-field>
              <v-text-field
                  readonly
                  label = "주소"
                  id = "주소"
                  placeholder="주소"
                  v-model="userInfo.address"
              >

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
              <v-btn color="red" dark router-link :to="{name  : 'UserDeletePage'}">회원 탈퇴</v-btn>
              <v-btn color="primary" router-link :to="{name  : 'UserUpdatePage'}">회원 정보 수정</v-btn>
            </v-card-actions>

          </v-card>
        </v-col>

      </v-row>
        <v-spacer></v-spacer>
      <v-divider> </v-divider>
        <v-spacer></v-spacer>


      </v-container>
    </v-main>

  </v-app>

</template>


<script>
import axios from 'axios';
import api from '@/api/api'
import router from "@/router/";
import swal from "sweetalert";
router;
  export default{
    data: () => ({
      userDeleteRequest : {

      },
      userInfo : {

      },
    })

    ,created(){

        let URL = api.ROOT_URL + api.ROUTES.USERS.getUserInfoURL;
        let config = {
          headers : {
            "Authorization" : "Bearer" + " " + this.$store.getters.getAuthToken
          }
        };
       // //console.log(config);
        axios.get(URL,config).then((res) => {

          this.userInfo = res.data.user;
          this.userInfo.password="";
          this.userInfo.passwordConfirm="";

        }).catch((err) => {
          swal('X', err.response.data.msg, 'warning');
        });

    },
    methods : {

      updateUser(){
        let URL = api.ROOT_URL + api.ROUTES.USERS.updateUserURL;
        axios.delete(URL,this.userUpdateRequest).then(function(response){
          alert(response.data.msg);
        }).catch(function(error){
          alert(error.response.data.msg);
        });
      },

    }
  }


</script>
<style>

</style>