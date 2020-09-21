<template>
  <v-app id="inspire">
    <v-navigation-drawer
      app
      left
    >
      <router-link :to="{name: 'Main'}" style="text-decoration: none;">
        <h1>tikitaCAR</h1>
      </router-link>

      
        <div style="text-align: center;">
          <router-link :to="{name: 'Login'}" style="text-decoration: none;">
            <v-icon
              style="margin-top:15px;font-size:65px;padding-bottom:0px;"
            >mdi-account-circle</v-icon>
          </router-link>
        </div>
        
      
      <v-list dense>
        <router-link :to="{name: 'Search'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon>mdi-magnify</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>검색</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>

        <router-link :to="{name: 'Register'}" style="text-decoration: none;">
          <v-list-item link>
            <v-list-item-action>
              <v-icon color="blue">mdi-car</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title>내 차 팔기</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </router-link>
      </v-list>
    </v-navigation-drawer>

    <v-main>
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
export default {
  props: {
      source: String,
  },
  data: () => ({

  }),
  name: 'App',
  components: {}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #6DCEF5;
  margin-top: 60px;
}
h1 {
  margin-top:15px;
  font-size:50px;
  padding-bottom:0px;
  text-align: center;
}
</style>