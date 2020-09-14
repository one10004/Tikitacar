<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%;">
      <h3>Detail page</h3>
      <div class="mainImage">
        Image
      </div>
      <div class="subImages">
        <div class="subImage1">sub image1</div>
        <div class="subImage1">sub image2</div>
        <div class="subImage1">sub image3</div>
        <div class="subImage1">sub image4</div>
      </div>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    props: {
      source: String,
    },
  }
</script>

<style scoped>
  h3 {
    margin-top: 50px;
    margin-left: 8px;
  }
  .mainImage {
    text-align: center;
    width: 500px;
    height: 450px;
    border: solid;
    
  }
  .subImages {
    display: flex;
  }
  .subImage1 {
    margin: 5px 20px 5px 0px;
    width: 110px;
    height: 90px;
    border: solid;
  }
</style>