import axios from 'axios';
import router from '../router';
import Api from '../api/api.js';
// import cookies from 'vue-cookies'
// import swal from "sweetalert";


export default {
  login() {
    alert("Login Success");
    router.push({name: 'Main'});
  },
  buyCar({commit}, model) {
    console.log(model);
    alert("Buy " + model);
    commit();
  },
  search({commit}, searchInfo) {
    console.log(searchInfo);
    alert("search " + searchInfo.manufacturer + " " + searchInfo.model);
    router.push({
      name: "Search",
      query: { from: searchInfo.from, manufacturer: searchInfo.manufacturer, model: searchInfo.model },
    });
    commit();
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
        axios.post(Api.ROOT_URL + Api.ROUTES.DEAL.registerCarURL, info.dealInfo, {
          headers: {
            'Authorization' : 'RandomTokenString'
          }
        })
          .then((res) => {
            console.log(res);
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