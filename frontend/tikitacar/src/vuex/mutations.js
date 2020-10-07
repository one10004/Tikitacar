// import vuex from '../vuex'
import cookies from 'vue-cookies'


export default {
    SET_USER(state, res) {
        cookies.set('auth-token', res.data.token);
        let now = new Date();
        now.setSeconds(now.getSeconds() + res.data.validTime/1000);
        cookies.config('auth-token', now);
        //console.dir(res.data);
        state.userInfo.authToken=res.data.token;
        cookies.set('nickname', res.data.nickname);
        cookies.set('email', res.data.email);

        //cookies.set('auth-token', res.data.validTime/1000);
        // state.userInfo.authToken = res.headers.authorization
        // state.userInfo.userNickname = res.data.loginUser.nickName
        // state.userInfo.userCoin = res.data.loginUser.coin
        // state.userInfo.userEmail = res.data.loginUser.email
    },
    DEL_USER(state) {
        cookies.set('auth-token', "");
        cookies.set('nickname', "");
        cookies.set('email', "");
        state.userInfo.authToken="";
        state.userInfo.nickname="";
        state.userInfo.email="";
    },
}