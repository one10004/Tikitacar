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
                label = "아이디"
                id = "아이디"
                placeholder="아이디"

              >
              </v-text-field>
              <v-text-field
                  readonly
                  label = "이름"
                  id = "이름"
                  placeholder="이름"

              >
              </v-text-field>
              <v-text-field
                  readonly
                  label = "생년월일(YYYYMMDD)"
                  id = "성별"
                  placeholder="XXXXYYDD"
              >

              </v-text-field>
              <v-text-field
                  readonly
                  label = "성별"
                  id = "성별"
                  placeholder="성별"
              >

              </v-text-field>
              <v-text-field
                  readonly
                  label = "핸드폰번호(010XXXXXXXX)"
                  id = "phoneNum"
                  placeholder="XXXXXXXXXXX"
              >

              </v-text-field>
              <v-text-field
                  readonly
                  label = "주소"
                  id = "주소"
                  placeholder="주소"

              >

              </v-text-field>
              <v-text-field
                  label="상세주소"
                  name="addressDetail"
                  id ="addressDetail"
                  type="text"
                  placeholder="상세주소"
              >
              </v-text-field>
            </v-form>
            <v-card-actions>
              <v-btn @click="userDeleteRequest">회원 탈퇴</v-btn>
              <v-btn @click="userInfoUpdateRequest">회원 정보 수정</v-btn>
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

  export default{
    data(){
     // userInfo : {}
    },
    method : {
      userDeleteRequest : {

      },
      userInfoUpdateRequest : {

      }
    }
  }


</script>
<style>

</style>