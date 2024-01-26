import { createStore as _createStore } from 'vuex';
import axios from 'axios';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      adopted: [],
      pets: [
        {
          id: 1,
          name: "YiaYia",
          breed: "Alpaca",
          color: "White/Beige",
          age: "8",
          isAvailable: false,
          description: "A spirited and spunky alpaca rescued from a breeder",
          weight: "106",
          zip: 45371,
          city: "Tipp City",
          state: "Ohio",
          img: "src/images/yiayia.jpg",
          gallery: ""
        },

      ],

      volunteers: [],
      photo: [],
      users: []
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_PETS(state, pets) {
        state.pets = pets;
      },
      SET_VOLUNTEERS(state, volunteers){
        state.volunteers = volunteers;
      },
      SET_PHOTO(state, photo){
        state.photo = photo;
      },
      SET_ADOPTED(state, adopted){
        state.adopted = adopted;
      },
      SET_USERS(state, users){
        state.users = users;
      },
      UPDATE_PETS(state, status){
        state.pets.isAvailable = status;
      }
    },
  });
  return store;
}
