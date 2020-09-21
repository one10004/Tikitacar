<template>
  <v-app id="app">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row justify="start">

          <v-col offset="4">
            <v-card class="elevation-20">
              <v-card-title>
                TIKITACAR LOGO
              </v-card-title>
              <v-form>
                <v-text-field
                    label="Email"
                    name="email"
                    id ="email"
                    type="text"
                    v-model="signupData.email"
                > <template slot="append">
                  <v-btn outlined style="margin-bottom: 6px" @click="emailDuplicateCheck">
                    <v-icon left>mdi-magnify</v-icon>
                    이메일 중복 확인
                  </v-btn>
                </template></v-text-field>
                <v-text-field
                    label="닉네임"
                    name="nickname"
                    id ="nickname"
                    type="text"
                    v-model="signupData.nickname"
                > <template slot="append">
                  <v-btn outlined style="margin-bottom: 6px" @click="nicknameDuplicateCheck">
                    <v-icon left>mdi-magnify</v-icon>
                    닉네임 중복 확인
                  </v-btn>
                </template></v-text-field>
                <v-text-field
                    label="이름"
                    name="name"
                    id ="name"
                    v-model="signupData.name"
                    type="text"
                ></v-text-field>

                  <v-text-field
                      label="비밀번호"
                      name="pw"
                      id ="pw"
                      type="password"
                      v-model="signupData.password"
                      append-icon="mdi-lock"

                  ></v-text-field>
                    <v-text-field
                        label="비밀번호 확인"
                        name="passwordConfirm"
                        id ="pwconfirm"
                        type="password"
                        v-model="signupData.passwordConfirm"
                        append-icon="mdi-lock"

                    >
                </v-text-field>

q

                <v-text-field
                    label="생년월일(YYYYMMDD)"
                    name="birth"
                    id ="birth"
                    type="text"
                    v-model="signupData.birth"
                >
                </v-text-field>

                <v-select
                    label="성별"
                   :items="['여자', '남자']"
                    id="gender"
                    v-model="signupData.gender"
                >

                </v-select>
                <v-text-field
                    label="핸드폰번호(0000000000)"
                    name="phone"
                    id ="phone"
                    type="text"
                v-model="signupData.phone"
                >
                </v-text-field>

                <v-text-field
                    label="주소"
                    name="address"
                    id ="address"
                    v-model="signupData.address"
                    :readonly = true
                    type="text"
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
                    v-model="signupData.address_detail"
                >
                </v-text-field>
              </v-form>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="cyan" @click="userCreateSubmit">회원가입</v-btn>
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
import axios from 'axios';

export default{
  data(){
    return {
      addressModal : false,
      result : "",
      signupData : {
        email: "",
        nickname: "",
        birth: "",
        gender: "",
        phone: "",
        address: "",
        address_detail: "",
        name: "",

      }
    }
  },
  methods : {
    handleAddress : function(data){
      this.signupData.address = data.address;
      this.addressModal = false;
    },
    emailDuplicateCheck : function(){
      let URL = api.ROOT_URL + api.ROUTES.USERS.emailDuplicateCheckURL + "/" + this.signupData.email;

      axios.get(URL, {}).then(function(){
            alert("사용 가능한 이메일입니다.");
          }

      ).catch(function(error){
        alert(error.msg);
      });
    },
    nicknameDuplicateCheck : function(){
      let URL = api.ROOT_URL + api.ROUTES.USERS.nicknameDuplicateCheckURL + "/" + this.signupData.nickname;

      axios.get(URL, {}).then(function(){
            alert("사용 가능한 이메일입니다.");
          }

      ).catch(function(error){
        alert(error.msg);
      });
    },
    userCreateSubmit : function(){

      let URL = api.ROOT_URL + api.ROUTES.USERS.createUserURL;
      console.dir(this.signupData);
      axios.post(URL, this.signupData).then(function(){

      }).catch(function(error){
        alert(error.msg);
      });
    }
  },
  computed :{

  }

}




</script>


<style>


</style>