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
                flat
              >
                <v-toolbar-title>로그인</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="Id"
                    name="id"
                    id ="id"
                    prepend-icon="mdi-account"
                    type="text"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn dark color="cyan" @click="login">로그인</v-btn>
                <v-btn dark color="cyan" @click="findPassword">비밀번호 찾기</v-btn>
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

                <v-btn dark width = "40%" color="cyan" @click="signup">회원 가입</v-btn>
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
  export default {
    props: {
      source: String,
    },
    methods: {
      ...mapActions(["login"]),
    }
  }
  
</script>