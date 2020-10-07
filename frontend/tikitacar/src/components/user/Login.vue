<template>
  <v-app id="login">
    <v-main>
      <v-container
          class="fill-height"
        fluid
      >
        <v-spacer></v-spacer>
        <v-spacer>
        </v-spacer>
        <v-row
        >

          <v-col lg7>
            <v-card height="600" class="elevation-0">
              <v-toolbar
                color="primary"
                dark
                flat>
                <v-toolbar-title>로그인</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="email"
                    name="email"
                    id ="email"
                    v-model="loginRequest.email"
                    prepend-icon="mdi-account-circle"
                    type="text"
                    height="50"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    v-model="loginRequest.password"
                    :append-icon="showPassword? 'mdi-eye' : 'mdi-eye-off'"
                    :type="showPassword? 'text' : 'password'"
                    @click:append="showPassword = ! showPassword"
                    height="50"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="login(loginRequest)">로그인</v-btn>
                <v-btn color="primary" router-link :to="{name  : 'PwInquiry'}">비밀번호 찾기</v-btn>
              </v-card-actions>
            </v-card>

          </v-col>
          <v-divider vertical>

          </v-divider>
          <v-col lg5>
            <v-card class="elevation-0">

              <v-card-text>
                <br><br><br>
                TIKITACAR 회원으로 가입하시면<br>
                TIKITACAR의 모든 서비스를<br>
                이용하실 수 있습니다.
                <br>
                <br>
                <br>

              </v-card-text>
              <v-card-actions>
                <v-btn width = "40%" color="primary" router-link :to="{name  : 'SignUp'}">회원 가입</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
import router from "@/router";
import swal from "sweetalert";

//import api from '@/api/api'
//import router from "@/router/";

  export default {
    data: () => ({
        showPassword : false,
        id: "",
        isloggedIn : "",
        loginRequest : {
          email : "",
          password : ""
        }
      })
    ,
    created : function() {
      this.isloggedIn = this.$store.getters.isLoggedIn;
      ////console.dir(this.isloggedIn);
      if (this.isloggedIn) {

        swal('X', '이미 로그인 되있는 상태입니다', 'warning');
        router.push({name: 'Home'});
        // router.go();
      }

    },
    props: {
      source: String,
    },
    methods: {
      ...mapActions(["login"]),
    /*  login(){
        let URL = api.ROOT_URL + api.ROUTES.AUTH.loginURL;
       axios.post(URL,this.loginRequest).then(
           function(data){
             alert(data.data.msg);
             router.push({name : "Home"});
           }
       ).catch(function(error){
          alert(error.response.data.msg);
       });
      }*/

    }
  }
  
</script>