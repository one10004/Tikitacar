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
            <div class="spec">
              <h4>사고유무: </h4>
              <v-checkbox
                v-model="accident"
                readonly
                :label="'사고 경험 1회'"
              ></v-checkbox>
            </div>
            <div class="price">
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 30px;">
                <b>판매가격</b>
              </div>
              <div style="width: 80px; height: 60px; position: absolute; bottom: 0; margin-left: 170px;">
                <p style="font-size: 50px; color: blue">3,465</p>
              </div>
              <div style="width: 80px; height: 25px; position: absolute; bottom: 0; margin-left: 270px;">
                <b>만원</b>
              </div>
              <!-- <p style="margin-right: 50px;">판매가격</p>
              <p style="font-size: 50px; color: blue;">3,265</p>
              <v-btn
                style="margin-left: 100px;"
              >구매</v-btn> -->
            </div>
            <v-btn
              color="primary"
              absolute
              right
              large
              style="margin: 75px 150px 70px 0px;"
            >구매</v-btn>
          </div>
        </div>
      </div>
      <div class="detailInfo">
        <h3>차량 정보</h3>
        <v-row>
          <v-col cols="12" sm="4">
            <p>차량정보: 12가1234</p>
            <p>주행거리: 8,000km</p>
            <p>변속기: 오토</p>
            <p>차종: 중형차</p>
          </v-col>
          <v-col cols="12" sm="4">
            <p>색상: 하얀색</p>
            <p>연식: 19년형</p>
            <p>연료: 전기</p>
            <p>연비: 8.1km</p>
          </v-col>
        </v-row>
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
      km: "8,000",
      accident: true,
    })
  }
</script>

<style scoped>
  h3 {
    margin-top: 50px;
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
    margin-left: 250px;
    margin-top: 10px;
    display: flex;
  }
  .price {
    width: 500px;
    height: 100px;
    align-content: center;
    text-align: right;
    display: flex;
    float: right;
    margin-top: 20px;
    position: relative;
    padding-bottom: 0;
  }
  .detailInfo {
    margin-top: 100px;
  }
</style>