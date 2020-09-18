
export default {
    ROOT_URL : process.env.NODE_ENV=="local"?"http://localhost:3080" :"http://j3a106.p.ssafy.io//",
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
            logoutURL : "auth/logout"
        }
    }

}