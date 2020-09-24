import axios from 'axios';
import router from '../router';
// import cookies from 'vue-cookies'
// import swal from "sweetalert";
import api from '@/api/api'
import swal from "sweetalert";

export default {
  login({ commit }, loginData) {
      let URL = api.ROOT_URL + api.ROUTES.AUTH.loginURL;
    axios.post(URL, loginData)
        .then((res) => {
           //console.dir(res);
            swal('로그인 성공', '환영합니다 :)', 'success')

          commit('SET_USER', res)
          router.push({ name: 'Home' })
         // router.go()
        })
        .catch((err) => {
         // console.log(err)
          swal('실패', err.response.data.msg, 'error');
        });
  },
    logout({commit}){
      let URL = api.ROOT_URL + api.ROUTES.AUTH.logoutURL;
      axios.post(URL)
          .then(() => {
          swal('로그아웃 성공','success');
          commit('DEL_USER')
        router.push({ name: 'Home' });
        //router.go();
      }).catch((err) => {
            swal('실패', err.response.data.msg, 'error');
        });
    },
    deleteUser({commit}, userDeleteRequest){
      let checkURL = api.ROOT_URL + api.ROUTES.USERS.checkPasswordURL;
      let deleteURL = api.ROOT_URL + api.ROUTES.USERS.deleteUserURL;
      //console.dir(this.getters.getAuthToken);
          let config = {
            headers : {
                "Authorization" : "Bearer" + " " + this.getters.getAuthToken
            }
        };

      axios.post(checkURL,userDeleteRequest,config).then(()=>{
          axios.delete(deleteURL,config).then((res)=>{
              swal("O", res.data.msg,"success");
              commit('DEL_USER');
              router.push({ name : 'Home'});
              return;
          }).catch((err) => {
              swal("X", err.response.data.msg,"error");
        });

      }).catch((err) => {
          swal("X", err.response.data.msg,"error");
      }

      );
    }

}