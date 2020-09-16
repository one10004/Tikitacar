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
                    label="이름"
                    name="name"
                    id ="name"
                    type="text"
                ></v-text-field>
                <v-text-field
                    label="Email"
                    name="email"
                    id ="email"
                    type="text"

                ></v-text-field>
                  <v-text-field
                      label="비밀번호"
                      name="pw"
                      id ="pw"
                      type="password"

                      append-icon="mdi-lock"

                  ></v-text-field>
                    <v-text-field
                        label="비밀번호 확인"
                        name="pwConfirm"
                        id ="pwConfirm"
                        type="password"
                        append-icon="mdi-lock"

                    >
                </v-text-field>
                <v-text-field
                    label="생년월일(YYYYMMDD)"
                    name="dob"
                    id ="dob"
                    type="text"
                >
                </v-text-field>

                <v-select
                    label="성별"
                :items="['여자', '남자']"
                    id="gender"
                >

                </v-select>
                <v-text-field
                    label="핸드폰번호(0000000000)"
                    name="phoneNum"
                    id ="phoneNum"
                    type="text"
                >
                </v-text-field>

                <v-text-field
                    label="주소"
                    name="address"
                    id ="address"
                    :placeholder="address"
                    :readonly = true
                    type="text"
                    v-on:click="addressModal = true"
                >
                </v-text-field>
                <v-text-field
                    label="상세주소"
                    name="addressDetail"
                    id ="addressDetail"
                    type="text"
                >
                </v-text-field>
              </v-form>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="cyan" >회원가입</v-btn>
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

export default{
  data(){
    return {
      addressModal : false,
      result : "",
      address : ""
    }
  },
  methods : {
    handleAddress : function(data){
      this.address = data.address;
      this.addressModal = false;
    }
  },
  computed :{

  }

}




</script>


<style>


</style>