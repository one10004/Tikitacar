// import axios from 'axios';
import router from '../router';
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
  }

}