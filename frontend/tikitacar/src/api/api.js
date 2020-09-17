
export default {
    ROOT_URL : process.env.TIKITACAR?"j3a106.p.ssafy.io/" :"http://localhost:3080/",
    ROUTES : {
        USERS: {
            emailDuplicateCheckURL: "user/email",
            nicknameDuplicateCheckURL: "user/nickname",
            getUserInfoURL : "user/",
            createUserURL : "user",
            updateUserURL : "user",
            deleteUserURL : "user",
        },
        AUTH : {
            loginURL : "auth/login",
            logoutRequest : "auth/logout"
        }
    }

}