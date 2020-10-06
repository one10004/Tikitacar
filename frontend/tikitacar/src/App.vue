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
            <v-icon
              style="margin-top:15px;font-size:65px;padding-bottom:0px;"
            >mdi-account-circle</v-icon>
            <p>로그인 해주세요!</p>
          </router-link>
        </div>
        <div v-else>
          <v-card>
            <v-card-actions>
              <v-btn router-link :to="{name  : 'MyPage'}">마이 페이지</v-btn>
              <v-btn @click="logout">로그 아웃</v-btn>
            </v-card-actions>
          </v-card>
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
              <v-icon color="blue">mdi-car</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title style="font-size: 15px;">차량 후기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>

        <router-link :to="{name: 'Predict'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon color="yellow">mdi-currency-usd</v-icon>
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
    image: require('@/assets/TIKITACAR_LOGO_2.jpg')
  }),
  created : function(){
   // this.isloggedIn=this.;

  },
  methods : {
    ...mapActions(["logout"]),

  }, computed : {
    isLoggedin (){ return this.$store.getters.isLoggedIn}
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