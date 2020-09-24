// import vuex from '../vuex'

export default {
    isLoggedIn: state => !!state.userInfo.authToken,
    config: state => ({
        headers : {
            "Authorization" : "Bearer" + " " + `${state.userInfo.authToken}`
        }
    }),
}