<template>
  <v-app id="app">
    <v-navigation-drawer
      absolute
      permanent
      app
      left
      style="width: 250px;"
    >
        <router-link :to="{name: 'Main'}" style="text-decoration: none;">
          <v-img style="left: 20%;" width="150px" height="150px" :src="image" />
        </router-link>
        <div style="text-align: center;" v-if="!isLoggedin">
          <router-link :to="{name: 'Login'}" style="text-decoration: none;">
            <div style="border: 1px solid; width: 100px; margin: 0 auto; border-radius: 2px; display: flex;">
              <v-icon
                style="margin: 6px 0px 5px 8px; font-size:30px;"
              >mdi-account-circle</v-icon>
              <p style="margin: 8px 0px 0px 10px;">로그인</p>
            </div>
          </router-link>
        </div>
        <div style="text-align: center;" v-else>
          <router-link :to="{name  : 'MyPage'}" style="text-decoration: none;">
            <v-icon
              style="margin-top:15px;font-size:65px;padding-bottom:0px;"
            >mdi-account-circle</v-icon>
            <p style="margin-bottom: 0px; color: black;">{{this.$store.getters.currentUserNickname}}님 반갑습니다!</p>
          </router-link>
          <div style="display: flex; text-align: center;">
            <router-link :to="{name  : 'MyPage'}" style="text-decoration: none;">
              <p style="text-decoration: none; color: blue; font-size: 13px; cursor: pointer; margin-left: 70px;">마이페이지</p>
            </router-link>
            <p style="text-decoration: none; color: blue; font-size: 13px; cursor: pointer; margin-left: 15px;" @click="logout">로그 아웃</p>
          </div>
        </div>
        
      
      <v-list dense>
        <router-link :to="{name: 'Search'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon>mdi-magnify</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title style="font-size: 15px;">내 차 사기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>

        <router-link :to="{name: 'Register'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon color="blue">mdi-car</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title style="font-size: 15px;">내 차 팔기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>

        </router-link>
        <router-link router-link :to="{name  : 'Review'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action >
              <v-icon color="orange">mdi-message-draw</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title style="font-size: 15px;">차량 후기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>

        <router-link :to="{name: 'Predict'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon color="#ECF739">mdi-currency-usd</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title style="font-size: 15px;">내 차 가격 조회</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>
      </v-list>
    </v-navigation-drawer>

    <v-main style="padding-left: 180px;">
      <router-view :key="$route.fullPath" />
    </v-main>

    <v-footer
      color="#6DCEF5"
      app
    >
      <v-spacer></v-spacer>

      <span class="white--text">&copy; {{ new Date().getFullYear() }} tikitaCAR</span>
    </v-footer>
  </v-app>
</template>

<script>
import { mapActions } from "vuex";
export default {
  props: {
      source: String,
  },
  data: () => ({
    image: require('@/assets/TIKITACAR_LOGO_2.jpg'),
    nickname: "",
  }),
  methods : {
    ...mapActions(["logout"]),

  }, computed : {
    isLoggedin (){ return this.$store.getters.isLoggedIn},
    // currentUserNickname() {return this.$store.getters.currentUserNickname}
  },
  name: 'App',
  components: {}
}
</script>

<style>
#app {
  font-family: 'Do Hyeon', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  /* color: #6DCEF5; */
  /* margin-top: 60px; */
}
h1 {
  margin-top:15px;
  font-size:30px;
  padding-bottom:0px;
  text-align: center;
}
</style>