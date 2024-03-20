<template>
  <div class="home">
    <h1 class="name">Explore the Paw-sibilities <br />at Parker's Pet Haven!</h1>
    <div class="login-link" v-if="!isLoggedIn">
      <router-link to="/login">Login Now</router-link> |
      <router-link to="/register">Register</router-link>

    </div>

    <h2>Browse our Available Pets</h2>
    <pet-view></pet-view>

    <section>
      <h2>Mission Statement</h2>
      <p class="mission-statement">
        Parker's Pet Animal Shelter is dedicated to providing a safe and loving <br>
        haven for animals in need. Our mission is to rescue, rehabilitate, and <br>
        re-home pets, fostering a compassionate community that values the well-being of <br>
        every animal. Through education, advocacy, and adoption services, we strive to <br>
        create a world where every pet has a chance for a happy and fulfilling life
      </p>
    </section>
  </div>





</template>

<script>

import PetView from '@/views/PetView.vue';
export default {
  components: {

    PetView,
  },
  computed: {
    //checks to see if they are logged in.
    isLoggedIn() {
      return this.$store.state.token !== '';
    }
  },

};
</script>

<style scoped>



h1 {
  color: rgb(118, 69, 59);
  justify-content: center;
  text-align: center;
  font-size: 50px;


}


h2 {
  text-align: center;
  font-family: "Orbitron", sans-serif;
  font-optical-sizing: auto;
  font-weight: bold;
  font-style: normal;
}

.mission-statement {
  text-align: center;
  padding: 15px;
  display: -webkit-box;
  -webkit-line-clamp: 5;
  -webkit-box-orient: vertical;
  overflow: hidden;
  font-size: 25px;
  
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }

  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes pulse {

  0%,
  100% {
    transform: scale(1);
  }

  50% {
    transform: scale(1.1);
  }
}

p {
  text-align: center;
  padding: 15px;
}

.name {
  font-family: "Orbitron", sans-serif;
  font-optical-sizing: auto;
  font-weight: bold;
  font-style: normal;
}

.login-link {
  margin-left: 30px;
  font-family: "Orbitron", sans-serif;
  font-optical-sizing: auto;
  font-weight: bold;
  font-style: normal;

}
</style>
