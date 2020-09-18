<template>
  <v-app id="login">
    <v-main>
      <v-container
        class="fill-height"
        fluid

      >
        <v-row
            align="end"
          justify="center"
        >
          <v-spacer></v-spacer>
          <v-col lg7>
            <v-card class="elevation-0">
              <v-toolbar
                color="cyan"
                dark
                flat>
                <v-toolbar-title>로그인</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="Id"
                    name="id"
                    id ="id"
                    v-model="loginRequest.id"
                    prepend-icon="mdi-account-circle"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    v-model="loginRequest.pw"
                    :append-icon="showPassword? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPassword? 'text' : 'password'"
                    @click:append="showPassword = ! showPassword"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn dark color="success" @click="login">로그인</v-btn>
                <v-btn dark color="info" router-link :to="{name  : 'PwInquiry'}">비밀번호 찾기</v-btn>
              </v-card-actions>
            </v-card>

          </v-col>
          <v-divider vertical>

          </v-divider>
          <v-col lg5>
            <v-card class="elevation-0">

              <v-card-text>

                TIKITACAR 회원으로 가입하시면<br>
                TIKITACAR의 모든 서비스를<br>
                이용하실 수 있습니다.


              </v-card-text>
              <v-card-actions>

                <v-btn dark width = "40%" color="cyan" router-link :to="{name  : 'SignUp'}">회원 가입</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
//import { mapActions } from "vuex";
import axios from 'axios';
import api from '@/api/api'

  export default {
    data: () => ({
        showPassword : false,
        id: "",
        loginRequest : {
          id : "",
          pw : ""
        }
      })
    ,
    props: {
      source: String,
    },
    methods: {
      login(){
        let URL = api.ROOT_URL + api.ROUTES.AUTH.loginURL;

       axios.post(URL,this.loginRequest).then(
           function(){
             alert("로그인에 성공했습니다.");
           }
       ).catch(function(error){
         alert(error);
       });
      }

    }
  }
  
</script>