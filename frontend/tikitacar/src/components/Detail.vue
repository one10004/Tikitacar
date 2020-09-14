<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h1>Detail page</h1>
      <h3>Car name</h3>
      <div class="carInfo">
        <div>
          <div class="mainImage">
            Image
          </div>
          <div class="subImages">
            <div class="subImage1">sub image1</div>
            <div class="subImage1">sub image2</div>
            <div class="subImage1">sub image3</div>
            <div class="subImage1" style="margin-right: 0px;">sub image4</div>
          </div>
        </div>
        <div>
          <div class="spec">
            <h4>모델: </h4>
            <v-text-field 
              readonly
              v-model="model"
            ></v-text-field>
          </div>
          <div class="spec">
            <h4>연식: </h4>
            <v-text-field 
              readonly
              v-model="year"
            ></v-text-field>
          </div>
          <div class="spec">
            <h4>주행거리: </h4>
            <v-text-field 
              readonly
              v-model="km"
              suffix="km"
            ></v-text-field>
          </div> 
        </div>     
      </div>
    </v-main>
  </v-app>
</template>

<script>
  export default {
    props: {
      source: String,
    },
    data: () => ({
      model: 'Tesla Model 3',
      year: 2019,
      km: "8,000"
    })
  }
</script>

<style scoped>
  h3 {
    margin-top: 50px;
    margin-left: 8px;
  }
  h4 {
    margin: 10px;
    margin-top: 20px;
    width: 100px;
  }
  .carInfo {
    display: flex;
  }
  .mainImage {
    line-height: 350px;
    text-align: center;
    width: 450px;
    height: 350px;
    border: solid;
    
  }
  .subImages {
    display: flex;
    width: 450px;
  }
  .subImage1 {
    line-height: 75px;
    text-align: center;
    margin: 5px 19px 5px 0px;
    width: 98px;
    height: 80px;
    border: solid;
  }
  .spec {
    margin-left: 100px;
    margin-top: 10px;
    display: flex;
  }
</style>