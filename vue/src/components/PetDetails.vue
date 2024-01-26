<template>
  <div>
    
  </div>
</template>

<script>
import PetCard from '../components/PetCard.vue';

export default {

  props: {
    pet: Object,
    enableAdd: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    pets() {
      return this.$store.state.pets
    },
  }

}
</script>

<style></style>