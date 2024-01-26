<template>
  
  <div class="page">

  <div class="search">
    <span>
      <label for="name-filter">Name</label>
      <input v-model="filter.name" id="name-filter" type="text" class="input-large">
    </span>
    <span>
      <label for="breed-filter">Breed</label>
      <input v-model="filter.breed" id="breed-filter" type="text" class="input-large">
    </span>
    <span>
      <label for="color-filter">Color</label>
      <input v-model="filter.color" id="color-filter" type="text" class="input-large">
    </span>
    <span>
      <label for="age-filter">Age In Years</label>
      <input v-model="filter.age" type="number" class="input-large">
    </span>
  </div>

  <div class="pet-list" v-if="showPetList"> 
    <PetCard v-for="pet in filteredList" v-bind:key="pet.petId" v-bind:pet="pet"></PetCard>
  </div>
  
  </div>
</template>

<script>
import PetCard from '../components/PetCard.vue';
import PetService from '../services/PetService.js';


export default {
  data(){
    return{
      filter: {
        name: "",
        breed: "",
        color: "",
        age: "",
      },
      showPetList: false,
    };
  },
  computed: {
    pets(){
        return this.$store.state.pets
    },
    filteredList(){
      let filteredPets = this.$store.state.pets
      if(this.filter.name != ""){
        filteredPets = filteredPets.filter((pet) =>
          pet.petName.toLowerCase().includes(this.filter.name.toLowerCase())
        )
      }
      if(this.filter.breed != ""){
        filteredPets = filteredPets.filter((pet) =>
          pet.petBreed.toLowerCase().includes(this.filter.breed.toLowerCase())
        )
      }
      if(this.filter.color != ""){
        filteredPets = filteredPets.filter((pet) =>
          pet.petColor.toLowerCase().includes(this.filter.color.toLowerCase())
        )
      }
      if(this.filter.age != ""){
        filteredPets = filteredPets.filter((pet) => 
          pet.petAge === this.filter.age
        )
      }


      return filteredPets;
    }
  },
    components:{PetCard},
   created() {
     PetService.getAdoptablePets().then(
       (response) => {
         this.$store.commit("SET_PETS", response.data);
         this.showPetList = true;
       }
     )
   },
}
</script>

<style scoped> 
body, html {
  margin: 0;
  padding: 0;
}

.pet-list{
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
  opacity: 0;
  animation: slideInFromLeft 1s forwards; 
}

@keyframes slideInFromLeft {
  from {
    transform: translateY(-100%);
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

.search{
  display: flex;
  justify-content: center;
  width: 100%;
  background-color: #FFE5CA;
  margin-top: -10px;
  padding-top: 10px;
  background: transparent;
  position: sticky;
  top: 0;
  z-index: 1;
}

.search label{
  display: block;
  text-align: center;
}
.search span{
  padding: 10px;
}
.input{
  
  width: 175px;
  height: 25px;
  margin:5px;
}

.input-large {
  font-size: 16px;
}


</style>