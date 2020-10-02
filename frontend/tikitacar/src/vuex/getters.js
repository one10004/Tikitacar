// import vuex from '../vuex'

export default {
    isLoggedIn: state => !!state.userInfo.authToken,
    getAuthToken : state => state.userInfo.authToken,
    currentUer : state => state.userInfo.username,
    currentUserNickname : state => state.userInfo.nickname,
    config: state => ({
        headers : {
            "Authorization" : "Bearer" + " " + `${state.userInfo.authToken}`
        }
    }),
}