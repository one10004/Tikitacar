// import vuex from '../vuex'
import cookies from 'vue-cookies'


export default {
    SET_USER(state, res) {
        cookies.set('auth-token', res.data.token);
        let now = new Date();
        now.setSeconds(now.getSeconds() + res.data.validTime/1000);
        cookies.config('auth-token', now);
        state.userInfo.authToken=res.data.token;
        //cookies.set('auth-token', res.data.validTime/1000);
        // state.userInfo.authToken = res.headers.authorization
        // state.userInfo.userNickname = res.data.loginUser.nickName
        // state.userInfo.userCoin = res.data.loginUser.coin
        // state.userInfo.userEmail = res.data.loginUser.email
    },
    DEL_USER(state) {
        cookies.set('auth-token', "");
        state.userInfo.authToken="";
    },
}