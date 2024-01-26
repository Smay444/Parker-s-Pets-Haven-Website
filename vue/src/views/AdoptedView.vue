<template>
    <div>
        <h1>Celebrate our Adopted Animals</h1>
    </div>
    <div class="pet-list">
     
      <pet-card v-for="pet in pets" v-bind:key="pet.id" v-bind:pet="pet" />
    </div>
  </template>
  
  <script>
  import PetCard from '../components/PetCard.vue';
  import PetService from '../services/PetService.js';
  
  
  export default {
  components: {PetCard},
    computed: {
      pets(){
          return this.$store.state.adopted
      }
    },
    
     created() {
       PetService.getAdoptedPets().then(
         (response) => {
           this.$store.commit("SET_ADOPTED", response.data)
         }
       )
     },


  }
  </script>
  
  <style scoped>
  .pet-list{
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
  }
  .adopt{
    background-color: red;
  }
  </style>