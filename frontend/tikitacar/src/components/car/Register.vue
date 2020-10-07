<template>
  <v-app id="app">
    <v-main style="padding-left: 10%; margin-top: 10px;">
      <h3>빠른 시세 검색</h3>
      <v-card class="carSearch">
        <v-container fluid>
          <v-row
            align="center"
          >
            <v-col cols="12" sm="3">
              <v-select
                :items="fromOptions"
                label="수입/국산"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="3">
              <v-select
                :items="manufacturerOptions"
                label="제조사"
                solo
                @change="selectManufactuer($event)"
              ></v-select>
            </v-col>
            <v-col cols="12" sm="3">
              <v-select
                v-model="model"
                :items="modelOptions"
                label="모델"
                solo
              ></v-select>
            </v-col>
            <v-col cols="12" sm="2">
              <div class="searchBtn">
                <v-btn
                  color="primary"
                  @click="quickSearch()"
                >Search</v-btn>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
      <div style="display: none" v-if="result === -1">
      </div>
      <div class="result" v-else>
        <p class="resultSentence">{{model}}의 시세 가격은</p>
        <p class="price">{{priceLow}} 만원 <span class="resultSentence">~</span> {{priceHigh}} 만원</p>
        <p class="resultSentence">입니다.</p>
      </div>



      <h3>차량 등록</h3>
      <h4>제목</h4>
      <v-text-field
        style="width: 80%;"
        v-model="info.dealInfo.title"
      ></v-text-field>

      <form enctype="multipart/form-data">
        <div class="text-center">
          <label for="file-input">
            <v-icon style="font-size:60px;">mdi-image-plus</v-icon>
          </label>
          <h4 style="margin-top: 0px;">이미지 업로드</h4>
          <input
            id="file-input"
            style="display:none;"
            multiple
            type="file"
            ref="images"
            name="img"
            @change="imageSelection"
          />

          <!-- <v-btn type="button" @click="onClickImageUpload">이미지 업로드</v-btn> -->
          <div class="image-preview-container">
            <span
              v-for="(image, index) in info.files"
              :index="index"
              :key="index"
              class="image-preview-wrapper"
            >
              <v-icon 
                @click="imageDelete" 
                :name="image.number"
                style="padding: 0px; margin: 0px;"
              >mdi-close-circle</v-icon>
              <img style="padding: 0px;" :src="image.preview" />
            </span>
          </div>
        </div>
      </form>
      <v-row style="margin-right: 100px;">
        <v-col cols="12" sm="2">
          <h4>모델명</h4>
          <v-text-field
            placeholder="ex) 테슬라 모델 S"
            style="width: 100%;"
            v-model="info.dealInfo.name"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>연식</h4>
          <v-text-field
            placeholder="ex) 2018"
            style="width: 100%;"
            v-model="info.dealInfo.year"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>배기량(cc)</h4>
          <v-text-field
            placeholder="ex) 5000"
            style="width: 100%;"
            v-model="info.dealInfo.cc"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>주행거리</h4>
          <v-text-field
            placeholder="ex) 20000"
            style="width: 100%;"
            v-model="info.dealInfo.distance"
            type="number"
            suffix="km"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>색상</h4>
          <v-select
            placeholder="ex) 빨간색"
            style="width: 100%;"
            v-model="info.dealInfo.color"
            :items="colorOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>기어</h4>
          <v-select
            placeholder="ex) 자동"
            style="width: 100%;"
            v-model="info.dealInfo.gear"
            :items="gearOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>연료 타입</h4>
          <v-select
            placeholder="ex) 전기"
            style="width: 100%;"
            v-model="info.dealInfo.fuel"
            :items="fuelOptions"
          ></v-select>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>좌석 수</h4>
          <v-text-field
            placeholder="ex) 5"
            style="width: 100%;"
            v-model="info.dealInfo.seat"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>침수</h4>
          <v-text-field
            placeholder="ex) 1"
            style="width: 100%;"
            v-model="info.dealInfo.flooding"
            type="number"
            suffix="회"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>보험</h4>
          <v-text-field
            placeholder="ex) 2"
            style="width: 100%;"
            v-model="info.dealInfo.insurance"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>출고 가격</h4>
          <v-text-field
            placeholder="ex) 12000"
            style="width: 100%;"
            v-model="info.dealInfo.releasePrice"
            type="number"
            suffix="만원"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h4>판매 가격</h4>
          <v-text-field
            placeholder="ex) 6000"
            style="width: 100%;"
            v-model="info.dealInfo.price"
            type="number"
            suffix="만원"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-text-field
        solo
        height="150px"
        style="margin-right: 100px;"
        v-model="info.dealInfo.content"
      ></v-text-field>
      <v-btn
        color="primary"
        @click="registerCar(info)"
      >등록</v-btn>
    </v-main>
  </v-app>
</template>

<script>
import {mapActions} from "vuex";

  export default {
    props: {
      source: String,
    },
    data: () => ({
      info: {
        dealInfo: {
          sellerId: Number,
          name: "",
          cc: Number,
          distance: Number,
          color: "",
          gear: "",
          fuel: "",
          seat: Number,
          flooding: Number,
          insurance: Number,
          releasePrice: Number,
          price: Number,
          title: "",
          content: "",
          src: []
        },
        files: [],
      },
      uploadImageIndex: 0,
      model: "",
      priceLow: 0,
      priceHigh: 0,
      result: -1,
      colorOptions: ["없음","흰색","검정색","진회색","은색","진주색","회색","베이지색","빨간색","진청색","청색","파란색","하늘색","기타색상"],
      gearOptions: ["없음", "자동", "수동"],
      fuelOptions: ["없음", "가솔린", "디젤", "LPG", "전기"],
      fromOptions: ["국산"],
      manufacturerOptions: ["현대", "기아", "쉐보레", "르노삼성", "쌍용"],
      modelOptions: [],
    }),
    methods: {
      ...mapActions(["registerCar", "getMinMax", "getModels"]),
      imageSelection() {
        let num = -1;
        for (let i = 0; i < this.$refs.images.files.length; i++) {
          this.info.files = [
            ...this.info.files,
            {
              image: this.$refs.images.files[i],
              preview: URL.createObjectURL(this.$refs.images.files[i]),
              number: i + this.uploadImageIndex,
            },
          ];
          num = i;
        }
        this.uploadImageIndex =
          this.uploadImageIndex + num + (1);
      },
      imageDelete(e) {
        const name = e.target.getAttribute("name");
        this.info.files = this.info.files.filter((data) => data.number !== Number(name));
      },
      selectManufactuer(event) {
        this.getModels(event)
          .then((res) => {
            this.modelOptions = res;
          })
          .catch((err) => {
            console.log(err);
          })
      },
      quickSearch() {
        this.result = this.result * -1;
        this.getMinMax(this.model)
          .then((res) => {
            this.priceLow = res.priceLow;
            this.priceHigh = res.priceHigh;
          })
          .catch((err) => {
            console.log(err);
          })
      },
    },
    created() {
      this.info.dealInfo.cc = this.$route.query.info.cc;
      this.info.dealInfo.color = this.$route.query.info.color;
      this.info.dealInfo.distance = this.$route.query.info.distance;
      this.info.dealInfo.flooding = this.$route.query.info.flooding;
      this.info.dealInfo.fuel = this.$route.query.info.fuel;
      this.info.dealInfo.gear = this.$route.query.info.gear;
      this.info.dealInfo.insurance = this.$route.query.info.insurance;
      this.info.dealInfo.name = this.$route.query.info.name;
      this.info.dealInfo.releasePrice = this.$route.query.info.releasePrice;
      this.info.dealInfo.seat = this.$route.query.info.seat;
      this.info.dealInfo.year = this.$route.query.info.year;
      this.info.dealInfo.price = this.$route.query.info.price;
    }
  }
</script>

<style scoped>
  #app {
    font-family: 'Do Hyeon', sans-serif;
  }
  h3 {
    margin-top: 50px;
  }
  h4 {
    margin-top: 20px;
  }
  .carSearch {
    margin-top: 10px;
    width: 90%;
    height: 100px;
    padding: 0px;
    margin-left: 10px;
  }
  .searchBtn {
    position: absolute; 
    top: 30%; 
    right: 5%;
  }
  .text-center {
    margin-right: 10%;
  }
  .image-preview-wrapper {
    padding: 5px;
    position: relative;
  }
  .image-preview-wrapper img {
    padding: 5px;
    height: 80px;
    width: 80px;
  }
  .result {
    margin-top: 25px;
    margin-left: 10px;
  }
  .resultSentence {
    font-size: 40px;
  }
  .price {
    font-size: 60px;
    font-weight: bold;
    color: #10A5F5;
  }
</style>