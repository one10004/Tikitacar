
export default {
    ROOT_URL : process.env.TIKITACAR?"j3a106.p.ssafy.io/" :"http://localhost:3080/",
    ROUTES : {
        USERS: {
            emailDuplicateCheckRequest: "/user/email/",
            nicknameDuplicateCheckRequest: "/user/nickname/",
            getUserInfoRequest : "/user",
            createUserRequest : "/user",
            updateUserRequest : "/user",
            deleteUserRequest : "/user",
        },
        AUTH : {
            loginRequest : "/auth/login",
            logoutRequest : "/auth/logout"
        }
    }

}