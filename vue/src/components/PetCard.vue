Adoption Form: - PetCard

<template>
  <div class="card">

    <h1 class="pet-name">{{ pet.petName }}</h1>
    <h3 v-if="pet.available === false">Adopted By: {{ pet.adoptedBy }}</h3>
    <h3>Breed: {{ pet.petBreed }}</h3>
    <h3>Age: {{ pet.petAge }} Years</h3>
    <h3>Pet Id: {{ pet.petId }}</h3>
    <div class="photo-container">
      <button @click="prevPhoto" class="arrow">❮</button>
      <div v-for="photo in photos" :key="photo.photoId" v-show="currentPhoto === photo">
        <img class="photo" :src="photo.photoUrl" alt="pet photo">
      </div>
      <button @click="nextPhoto" class="arrow">❯</button>
    </div>
    <h4>Color: {{ pet.petColor }}</h4>
    <h4>Weight: {{ pet.petWeight }}lbs</h4>
    <h4>City: {{ pet.petCity }}</h4>
    <h4>State/Zip: {{ pet.petState }}, {{ pet.zipCode }}</h4>

    <div class="description">
      <p>{{ pet.petDescription }}</p>
    </div>

    <div class="buttons">
      <button v-show="pet.available === true" class="adopt" v-on:click="adoptPet(pet, pet.petId)">ADOPT ME</button>
    </div>

  </div>
</template>

<script>
import PetService from '../services/PetService.js'
import PhotoService from '../services/PhotoService.js'


export default {
  data() {
    return {
      photos: [],
      currentPhoto: null,
      users: [],
     
    }
  },
  props:{
    pet: Object,
  },
  components: {

  },
  computed: {

  },
  methods: {
    nextPhoto() {
      const currentIndex = this.photos.indexOf(this.currentPhoto);
      const nextIndex = (currentIndex + 1) % this.photos.length;
      this.currentPhoto = this.photos[nextIndex];
    },
    prevPhoto() {
      const currentIndex = this.photos.indexOf(this.currentPhoto);
      const prevIndex = (currentIndex - 1 + this.photos.length) % this.photos.length;
      this.currentPhoto = this.photos[prevIndex];
    },
    setToFalse() {
      this.condition = false;
    },
    adoptPet(pet, petId) {
      
        pet.available = false;
        pet.adoptedBy = this.$store.state.user.name;
        PetService.updatePetListing(pet, petId).then (
          (response) => {
           
            this.$router.push({name: 'adopted'});
            this.$store.commit('UPDATE_PETS', false);
            alert("Thanks for adopting. Come Again!")
          }
          
        )
    },

  },
  created() {
    if (this.pet.petId !== undefined) {
      PhotoService.listPhotos(this.pet.petId).then(
        (response) => {
          if (response.data.length > 0) {
            this.photos = response.data;
            this.currentPhoto = this.photos[0];
          }
        }
      )
    }
  }
}
</script>

<style scoped>
.card {
  border: 5px solid black;
  border-radius: 30px;
  width: 450px;
  height: 950px;
  margin: 20px;
  text-align: center;
  background: linear-gradient(to bottom, rgb(157, 171, 134), rgb(52, 109, 50));
  font-family: Arial;
  box-shadow: 10px 10px 20px 10px rgb(204, 115, 81);
  text-decoration-color: black;
}

.photo-container {
  position: relative;
  overflow: hidden;
}

.photo {
  width: 200px;
  height: 300px;
  border-radius: 30px;
  object-fit: cover;
  border: 3px black solid;
  display: block;
  margin: 0 auto;
  box-shadow: 5px 5px 10px rgb(222, 215, 177);
}

.description {
  overflow-y: scroll;
  height: 120px;
  padding-bottom: 20px;
}

.arrow {
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  font-size: 24px;
  background: none;
  border: none;
  cursor: pointer;
  outline: none;
}

.arrow:first-child {
  left: 10px;
}

.arrow:last-child {
  right: 10px;
}

button {

     display: inline-block;
     padding: 12px 24px;
     margin-top: 20px;
     background:linear-gradient(to right, #e74c3c, #c0392b);
     color: rgb(222, 215, 177);
     text-decoration: none;
     border-radius: 5px;
     transition: background-color 0.4s ease;
     border-radius: 25px;
     width: 200px;
     font-size: 18px;
     font-weight: bold;

}

 p{
     font-size: 20px;
     line-height: 1.4;
     color: black;
    font-style: italic;
 }

.description{
  height: 150px;
}
.pet-name{
  font-family: "Orbitron", sans-serif;
  font-optical-sizing: auto;
  font-weight: bold; /* Replace <weight> with an appropriate value like normal or bold */
  font-style: normal;
}
</style>