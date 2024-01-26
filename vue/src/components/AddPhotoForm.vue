<template>
  <div class="photo-form" v-on:submit="addPhoto()">
    <form>
      <div id="upload">Upload Photos</div>
      <br>
      <div>
        <label>Photo URL:</label>
        <input class="input" type="text" v-model="newPhoto.photoUrl">
      </div>
      <div>
        <label>Pet ID</label>
        <input class="input" type="text" v-model="newPhoto.petId">
      </div>
      <input class="button" id="submit" type="submit" v-on:click.prevent="addPhoto()">
    </form>
  </div>
</template>

<script>
import PetService from '../services/PetService';
import PhotoService from '../services/PhotoService';
export default {
  data() {
    return {
      newPhoto: {}
    }
  },

  methods: {
    addPhoto() {
      PetService.addPetPhoto(this.newPhoto, this.$route.params.photoId).then(
        (response) => {
          this.$router.push({ name: "pets" })
        }
      )
    },
    created() {
      PhotoService.getPhoto().then(
        (response) => {
          this.$store.commit("SET_PHOTOS", response.data)
        }
      )
    }
  }
}
</script>

<style scoped>
div .photo-form {
  display: flex;
  align-content: center;

  border-style: solid;
  border-width: 2px;
  border-color: black;
  border-radius: 5px;
  background-color: rgb(157, 171, 134, 0.7);
  padding: 10px;
  margin: 2px;
}

input {
  display: flex;
  justify-content: center;

}

#upload {
  display: flex;
  justify-content: center;
  align-content: center;
  font-weight: bolder;
  text-decoration: underline;
  margin-bottom: 10px;
  width: 325px;
  text-align: center;
  margin: 5px;
  font-size: 25px;
  color: rgb(0, 66, 37)
}

#submit {
  display: flex;
  justify-content: center;
  align-content: center;
  padding: 12px 24px;
  margin-top: 20px;
  background: linear-gradient(to right, #e74c3c, #c0392b);
  color: rgb(222, 215, 177);
  text-decoration: none;
  border-radius: 5px;
  transition: background-color 0.4s ease;
  border-radius: 25px;
  width: 200px;
  font-size: 18px;
  font-weight: bold;
}

.input {
  text-align: left;
  margin-top: 10px;
  height: 2vh;
  width: 10vh;
  width: 300px;
  height: 2vh;
}

.button{
  display: block;
  margin: 0 auto;
}
</style>