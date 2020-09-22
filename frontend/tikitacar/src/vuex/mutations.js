// import vuex from '../vuex'
import cookies from 'vue-cookies'


export default {
    SET_USER(state, res) {
        cookies.set('auth-token', res.data.token);
        // state.userInfo.authToken = res.headers.authorization
        // state.userInfo.userNickname = res.data.loginUser.nickName
        // state.userInfo.userCoin = res.data.loginUser.coin
        // state.userInfo.userEmail = res.data.loginUser.email
    },
    DEL_USER() {
        cookies.set('auth-token', "");

    },
}