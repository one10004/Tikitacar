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
    for(var i = 0; i < info.files.length; i++) {
      let formData = new FormData();
      formData.append('img', info.files[i].image);
      axios.post(Api.ROOT_URL + Api.ROUTES.IMG.imgURL, formData, {
        headers: {
          'Content-Type' : 'multipart/form-data'
        }
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        })
    }
    // axios.post(Api.ROOT_URL + Api.ROUTES.IMG.imgURL, info.files, "authToken")
    //   .then((res) => {
    //     console.log(res);
    //     axios.post(Api.ROOT_URL + Api.ROUTES.DEAL.registerCarURL, info.dealInfo, "authToken")
    //       .then((res) => {
    //         console.log(res);
    //       })
    //       .catch((err) => {
    //         console.log(err);
    //       });
    //   })
    //   .catch((err) => {

    //   });
    getters;
  }
}