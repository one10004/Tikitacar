// import vuex from '../vuex'

export default {
    isLoggedIn: state => !!state.userInfo.authToken,
    getAuthToken : state => state.userInfo.authToken
}