<template>
  <v-app id="app">
    <v-main>

      <v-container>

       <v-row>

        <v-col offset="2">
          <v-card id = "myinfo">
            <v-toolbar
                color="cyan"
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
                  placeholder="이름"
                  v-model="userInfo.nickname"
              >
              </v-text-field>
              <v-text-field
                  readonly
                  label = "생년월일(YYYY-MM-DD)"
                  id = "성별"
                  placeholder="XXXXYYDD"
                  v-model="userInfo.birth"
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
              <v-btn @click="userDeleteRequest">회원 탈퇴</v-btn>
              <v-btn @click="updateUser">회원 정보 수정</v-btn>
            </v-card-actions>

          </v-card>
        </v-col>

      </v-row>
        <v-spacer></v-spacer>
      <v-divider> </v-divider>
        <v-spacer></v-spacer>

        <v-row >
        <v-col offset="2">
          <v-card id ="mysales">
            <v-toolbar
                color="cyan"
                dark
                flat>
              <v-toolbar-title>내 게시물 목록</v-toolbar-title>
              <v-spacer></v-spacer>
            </v-toolbar>
            <v-row>
              <v-col>
                <v-card>
                  게시물 1
                </v-card>

              </v-col>
              <v-col>
                <v-card>
                  게시물 2
                </v-card>
              </v-col>
              <v-col>
                <v-card>
                  게시물 3
                </v-card>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
      </v-container>
    </v-main>

  </v-app>



</template>



<script>
import axios from 'axios';
import api from '@/api/api'
import router from "@/router/";
router;
  export default{
    data: () => ({

      userUpdateRequest : {

      },
      userInfo : {

      }
    })

    ,created(){

        let URL = api.ROOT_URL + api.ROUTES.USERS.getUserInfoURL;
        console.log(URL);
        let config = {
          headers : {
            "Authorization" : "Bearer" + " " + this.$store.getters.getAuthToken
          }
        };
        console.log(config);
        axios.get(URL,config).then((res) => {

          this.userInfo = res.data.user;

        }).catch((err) => {
          console.dir(err);
        });

    },
    method : {
      deleteUser() {
        let URL = api.ROOT_URL + api.ROUTES.USERS.deleteUserURL;
        axios.delete(URL,this.userDeleteRequest).then(function(response){
          alert(response.data.msg);
        }).catch(function(error){
          alert(error.response.data.msg);
        });
      },
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