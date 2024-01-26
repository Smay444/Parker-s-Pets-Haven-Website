<template>
  <div>
      <h2>Pet Details</h2>
      <pet-details></pet-details>
  </div>
</template>

<script>
import PetDetails from '../components/PetDetails.vue'
import PetServices from '../services/PetService'
export default {    

    components: {PetDetails},
    props:{

    },
    computed: {
        findPet(){
            return this.$store.state.pet.find(pet => pet.id ===
            this.$route.params.petId);
        }
    }
}
</script>

<style>

</style>