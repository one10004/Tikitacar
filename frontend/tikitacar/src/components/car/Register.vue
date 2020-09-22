<template>
  <v-app id="inspire">
    <v-main style="padding-left: 5%; margin-top: 50px;">
      <h3>차량 등록</h3>
      <h5>제목</h5>
      <v-text-field
        style="width: 80%;"
        v-model="info.dealInfo.title"
      ></v-text-field>

      <form enctype="multipart/form-data">
        <div class="text-center">
          <label for="file-input">
            <v-icon style="font-size:60px;">mdi-image-plus</v-icon>
          </label>
          <h4>이미지 업로드</h4>
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
          <h5>모델명</h5>
          <v-text-field
            placeholder="ex) 테슬라 모델 S"
            style="width: 100%;"
            v-model="info.dealInfo.name"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>연식</h5>
          <v-text-field
            placeholder="ex) 2018"
            style="width: 100%;"
            v-model="info.dealInfo.year"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>배기량(cc)</h5>
          <v-text-field
            placeholder="ex) 5000"
            style="width: 100%;"
            v-model="info.dealInfo.cc"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>주행거리</h5>
          <v-text-field
            placeholder="ex) 20000"
            style="width: 100%;"
            v-model="info.dealInfo.distance"
            type="number"
            suffix="km"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>색상</h5>
          <v-text-field
            placeholder="ex) 빨간색"
            style="width: 100%;"
            v-model="info.dealInfo.color"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>기어</h5>
          <v-text-field
            placeholder="ex) 자동"
            style="width: 100%;"
            v-model="info.dealInfo.gear"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>연료 타입</h5>
          <v-text-field
            placeholder="ex) 전기"
            style="width: 100%;"
            v-model="info.dealInfo.fuel"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>좌석 수</h5>
          <v-text-field
            placeholder="ex) 5"
            style="width: 100%;"
            v-model="info.dealInfo.seat"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>침수</h5>
          <v-text-field
            placeholder="ex) 1"
            style="width: 100%;"
            v-model="info.dealInfo.flooding"
            type="number"
            suffix="회"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>보험</h5>
          <v-text-field
            placeholder="ex) 2"
            style="width: 100%;"
            v-model="info.dealInfo.insurance"
            type="number"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>출고 가격</h5>
          <v-text-field
            placeholder="ex) 12000"
            style="width: 100%;"
            v-model="info.dealInfo.releasePrice"
            type="number"
            suffix="만원"
          ></v-text-field>
        </v-col>
        <v-col cols="12" sm="2">
          <h5>판매 가격</h5>
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
          sellerId: 7,
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
        dto: {
          img: ""
        },
      },

      uploadImageIndex: 0
    }),
    methods: {
      ...mapActions(["registerCar"]),
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
          this.info.dto.img = this.$refs.images.files[i];
          console.log(this.info.dto);
        }
        this.uploadImageIndex =
          this.uploadImageIndex + num + (1);
      },
      imageDelete(e) {
        const name = e.target.getAttribute("name");
        this.info.files = this.info.files.filter((data) => data.number !== Number(name));
      },
    },
  }
</script>

<style scoped>
  h3 {
    margin-top: 50px;
  }
  h5 {
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
</style>