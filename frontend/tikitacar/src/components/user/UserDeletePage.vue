<template>
  <v-app id="app">
    <v-main>
      <v-row fluid>
        <v-col>
          <v-card>
            <v-toolbar color="cyan"
                       dark
                       flat>
              <v-toolbar-title>
                회원 탈퇴
              </v-toolbar-title>

            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field
                    label="password"
                  type="password"
                  placeholder="비밀번호"
                    v-model="userDeleteRequest.password"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn @click="deleteUser(userDeleteRequest)" >제출</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-main>

  </v-app>


</template>

<script>

import {mapActions} from 'vuex';
export default{
  data: () => ({
  userDeleteRequest : {

    password : ""
  },
  }),
  methods : {
    ...mapActions(["deleteUser"])
  }

}



</script>