
export default {
    ROOT_URL : process.env.NODE_ENV=="local"?"http://localhost:8081/" :"http://j3a106.p.ssafy.io/api/",
    ROUTES : {
        USERS: {
            emailDuplicateCheckURL: "user/email",
            nicknameDuplicateCheckURL: "user/nickname",
            getUserInfoURL : "user",
            createUserURL : "user/new",
            updateUserURL : "user",
            deleteUserURL : "user",
            checkPasswordURL : "/user/check"
        },
        REVIEW :{
            createReviewURL : "review",
            updateReviewURL : "review",
            deleteReviewURL : "review",
            getReviewListURL : "review/all",
            getReviewDetailURL : "review/view"
        },
        AUTH : {
            loginURL : "auth/login",
            logoutURL : "auth/logout",
            pwInquiryURL : "auth/password"
        },
        DEAL: {
            registerCarURL: "deal",
            searchResultURL: "deal/all/",
            searchDetailURL: "deal/search",
            getInfoURL: "deal/view/",
            buyCarURL: "deal/status/"
        },
        CAR: {
            getModelURL: "price/",
            getModelPriceURL: "price/model/",
            getPredictedPrice: "car/predict",
            dealSearchMyURL : "deal/search/my",
        },
        IMG: {
            imgURL: "img",
            getUrl: "http://j3a106.p.ssafy.io/img/"
        },
        HOMEURL : ""
    }

}