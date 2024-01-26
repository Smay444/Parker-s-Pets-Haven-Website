<template>
    <div>
        <h1>Celebrate our Adopted Animals</h1>
        <audio  autoplay src="../media/who-let-the-dogs-out-song.mp3" type="audio/mp3" volume="0.1"></audio>
    </div>
    <div class="pet-list">
      <pet-card v-for="pet in pets" v-bind:key="pet.id" v-bind:pet="pet" class="slide-in" ></pet-card>
    </div>
  </template>


  <script>
  
  
  import PetCard from '../components/PetCard.vue';
  import PetService from '../services/PetService.js';
  
  
  export default {
    components:{
        PetCard
    },
    computed: {
      pets(){
          return this.$store.state.adopted.filter(pet => pet.available === false)
      
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
    opacity: 0;
    animation: slideInFromRight 1s forwards;
  }

  @keyframes slideInFromRight {
    from {
      transform: translateX(100%);
    }
    to {
      transform: translateX(0);
      opacity: 1;
    }
  }

  .slide-in {
    animation: slideInFromRight 1s forwards;
  }
  
  .adopt{
    background-color: red;
  }
  </style>