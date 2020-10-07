<template>
  <v-app id="app">
    <v-main>
      <v-container class="fill-height" fluid>
        <v-row justify="start">

          <v-col offset="2" md="6">
            <v-card class="elevation-20">
              <v-card-title class="justify-center">
                <img src="@/assets/TIKITACAR_LOGO_2.jpg" class="logo">
              </v-card-title>
              <v-form>
                <v-text-field
                    label="Email"

                    type="text"
                    v-model="signupData.email"
                    v-on:change="checking.email=false"

                ><v-icon slot="prepend" :class="checking.email">
                  mdi-check-bold
                </v-icon>
                  <template slot="append">
                  <v-btn :class="checking.email" outlined style="margin-bottom: 6px"  @click="emailDuplicateCheck($event)">
                    <v-icon left>mdi-magnify</v-icon>
                    이메일 중복 확인
                  </v-btn>

                </template>
                </v-text-field>
                <v-text-field
                    label="닉네임"
                    name="nickname"
                    id ="nickname"
                    type="text"
                    v-model="signupData.nickname"
                    v-on:change="checking.nickname=false, color='red'"

                    prepend-icon="mdi-check-bold"

                    required
                > <template slot="append">
                  <v-btn outlined style="margin-bottom: 6px" @click="nicknameDuplicateCheck($event)">
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
                    prepend-icon="mdi-check-bold"
                    required
                ></v-text-field>

                  <v-text-field
                      label="비밀번호"
                      name="pw"
                      id ="pw"
                      type="password"
                      v-model="signupData.password"
                      append-icon="mdi-lock"
                      prepend-icon="mdi-check-bold"
                      required
                  ></v-text-field>
                    <v-text-field
                        label="비밀번호 확인"
                        name="passwordConfirm"
                        type="password"
                        v-model="signupData.passwordConfirm"
                        append-icon="mdi-lock"
                        prepend-icon="mdi-check-bold"
                        v-on:blur="passwordCheck($event)"
                        required
                    >
                </v-text-field>
                <v-text-field
                    label="생년월일(YYYY-MM-DD)"
                    name="birth"
                    id ="birth"
                    type="text"
                    v-model="signupData.birth"
                    prepend-icon="mdi-check-bold"
                    required
                >
                </v-text-field>

                <v-select
                    label="성별"
                   :items="['남', '여']"
                    id="gender"
                    v-model="signupData.gender"
                    prepend-icon="mdi-check-bold"
                    required
                >

                </v-select>
                <v-text-field
                    label="핸드폰번호(0000000000)"
                    name="phone"
                    id ="phone"
                    type="text"
                    prepend-icon="mdi-check-bold"
                    v-model="signupData.phone"
                required
                >
                </v-text-field>

                <v-text-field
                    label="주소"
                    name="address"
                    id ="address"
                    v-model="signupData.address"
                    :readonly = true
                    type="text"
                    prepend-icon="mdi-check-bold"
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
                    v-model="signupData.addressDetail"
                    prepend-icon="mdi-check-bold"
                >
                </v-text-field>
              </v-form>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="userCreateSubmit">회원가입</v-btn>
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
import router from '@/router/';
import swal from "sweetalert";
export default{
  data(){
    return {
      addressModal : false,
      result : "",
      checking : {
        email: "",
        nickname: "",
        birth: "",
        gender: "",
        phone: "",
        address: "",
        address_detail: "",
        name: "",
        password : "",
        passwordConfirm : "",
      },
      passwordConfirmed : false
      ,
      signupData : {
        email: "",
        nickname: "",
        birth: "",
        gender: "",
        phone: "",
        address: "",
        address_detail: "",
        name: "",
        password : "",
        passwordConfirm : "",

      }
    }
  },
  methods : {
    handleAddress : function(data){
      this.signupData.address = data.address;
      this.addressModal = false;
    },
    emailDuplicateCheck : function(event){
      if(this.signupData.email==""){
        alert("비어있습니다.");
        return;
      }
      //this.checking.email = false;
      //console.dir(this.checking);
      //console.log("before axios");
      let URL = api.ROOT_URL + api.ROUTES.USERS.emailDuplicateCheckURL + "/" + this.signupData.email;

      axios.get(URL, {}).then((response) =>{
        //console.dir(response.data.msg);
        event.target.style.color="green";
        this.checking.email = true;
        swal('O', response.data.msg, 'success');
      }).catch((error) =>{
        //console.dir(error);
        alert(error.response.data.msg);
        event.target.style.color="red";

        this.checking.email = false;
        return;
      }).finally(()=>{
        //console.dir(this.checking);
      });


    },
    nicknameDuplicateCheck : function(event){
      if(this.signupData.nickname==""){
        alert("비어있습니다.");
        return;
      }
      //this.checking.nickname = false;

      let URL = api.ROOT_URL + api.ROUTES.USERS.nicknameDuplicateCheckURL + "/" + this.signupData.nickname;

      axios.get(URL, {}).then((response) =>{
        alert(response.data.msg);
        event.target.style.color="green";
        this.checking.nickname = true;
        ////console.dir(this.checking.email);
        //console.log("통과");
      }).catch((error) =>{
        alert(error.response.data.msg);
        event.target.style.color="red";
        this.checking.nickname = false;
        return;
      }).finally(()=>{
        //console.dir(this.checking);
      });
    //console.log("중복 X");





    },
    userCreateSubmit : function(){
      if(!this.checking.email || !this.checking.nickname){
        //console.log(this.checking.email + " " + this.checking.nickname);
        swal('X', "중복 확인을 해주세요", 'error');

        return;
      }
      if(!this.checking.password){
       // alert("비밀번호가 일치하지 않습니다.");
        swal('X', "비밀번호가 일치하지 않습니다.", 'error');
        return;
      }
      let URL = api.ROOT_URL + api.ROUTES.USERS.createUserURL;
      axios.post(URL, this.signupData).then(function(response){
        alert(response.data.msg);
        router.push({name : "Home"});
      }).catch(function(error){
        alert(error.response.data.msg);
      });
    },
    passwordCheck : function(){
      //event.target.

      if(this.signupData.password != this.signupData.passwordConfirm){
         alert("비밀번호가 일치하지 않습니다.");
         this.checking.password = false;
         this.signupData.passwordConfirm = "";
      }else{
         this.checking.password = true;
       }
    }
  },
  computed :{

  }

}




</script>


<style>
  .done {
    color : green;
  }

  .logo {
    width : 200px;
    heigth : 200px;

  }
</style>