module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  devServer : {
    port : process.env.VUE_APP_PORT || 3080
  },
  lintOnSave : false
}