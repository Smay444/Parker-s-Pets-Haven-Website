<template>
  <div id="capstone-app">
    <header>
      <div class="header-container">
        <img class="img" src="./images/cat.gif" alt="cat gif">
        <img class="img" src="./images/dog.gif" alt="dog gif">
        <img class="img" src="./images/puppy400.gif" alt="sad dog gif">
        <img class="img" src="./images/ferret.gif" alt="ferret gif">
        <img class="img" src="./images/bird.gif" alt="bird gif">
        <img class="img" src="./images/llama.gif" alt="llama gif">
      </div>
    </header>
    <div id="nav">
      <router-link class="links" v-bind:to="{ name: 'home' }">Home</router-link>&nbsp;|&nbsp;
      <router-link v-if="$store.state.user.authorities && $store.state.user.authorities[0].name === 'ROLE_ADMIN'"
        class="links" v-bind:to="{ name: 'admin' }">Admin</router-link> &nbsp;|&nbsp;
      <router-link class="links" v-bind:to="{ name: 'volunteer' }">Volunteer</router-link> &nbsp;|&nbsp;
      <router-link class="links" v-bind:to="{ name: 'contactUs' }">Contact Us</router-link> &nbsp;|&nbsp;
      <router-link class="links" v-bind:to="{ name: 'adopted' }">Adopted Pets</router-link> &nbsp;|&nbsp;
      <router-link class="links" v-bind:to="{ name: 'logout' }">Logout</router-link>
    </div>

    <router-view />
    <footer class="footer" >
      <h1>Adopt, don't shop!</h1>
      <p>© Parker's Pets Animal Shelter</p>
    </footer>

  </div>
</template>

<script>
export default {
  data() {
    return {
      smiley: '',
    }
  },
  methods: {
    showSmiley() {
      this.smiley = '😁🐶';
    },
    hideSmiley() {
      this.smiley = '';
    }
  }
}

</script>

<style scoped>
#capstone-app {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.img {
  height: 100%;
  width: 100%;
  object-fit: cover;
  border: 5px solid black;
  margin: 0;
  padding: 0;
}

#nav {
  color: rgb(247, 183, 135);
  background: rgb(82, 120, 83);

}
</style>


