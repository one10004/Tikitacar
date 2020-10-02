import axios from 'axios';
import router from '../router';
import Api from '../api/api.js';
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
  },
  registerCar({getters}, info) {
    let formData = new FormData();
    for(var i = 0; i < info.files.length; i++) {
      formData.append('img', info.files[i].image);
    }
    axios.post(Api.ROOT_URL + Api.ROUTES.IMG.imgURL, formData, {
      headers: {
        'Content-Type' : 'multipart/form-data'
      }
    })
      .then((res) => {
        info.dealInfo.src = res.data.src;
        console.log(getters.config);
        console.log(this.getters.config);
        axios.post(Api.ROOT_URL + Api.ROUTES.DEAL.registerCarURL, info.dealInfo, this.getters.config)
          .then((res) => {
            console.log(res);
            alert("등록 성공");
          })
          .catch((err) => {
            console.log(err);
          });
      })
      .catch((err) => {
        console.log(err);
      });
    getters;
  },
  fetchData({getters}, searchInfo) {
    return new Promise((resolve, reject) => {
      axios.post(Api.ROOT_URL + Api.ROUTES.DEAL.searchDetailURL, searchInfo)
        .then((res) => {
          console.log(res);
          resolve(res.data.data);
        })
        .catch((err) => {
          reject(err);
        })
        getters;
    })
  },
  getModels({getters}, option) {
    getters;
    return new Promise((resolve, reject) => {
      axios.get(Api.ROOT_URL + Api.ROUTES.CAR.getModelURL + option)
        .then((res) => {
          resolve(res.data.modelList);
        })
        .catch((err) => {
          reject(err);
        })
    })
  },
  predictPrice({getters}, info) {
    getters;
    return new Promise((resolve, reject) => {
      axios.post(Api.ROOT_URL + Api.ROUTES.CAR.getPredictedPrice, info)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        })
    })
  },
  getInfo({getters}, id) {
    getters;
    console.log(id);
    return new Promise((resolve, reject) => {
      axios.get(Api.ROOT_URL + Api.ROUTES.DEAL.getInfoURL + id)
        .then((res) => {
          resolve(res);
        })
        .catch((err) => {
          reject(err);
        })
    }) 
  }
}