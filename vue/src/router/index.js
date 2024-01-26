import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex';
import { createApp } from 'vue';



// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import PetView from '../views/PetView.vue';
import AdminView from '../views/AdminView.vue';
import AddPhotoForm from '../components/AddPhotoForm.vue';
import ApplicationForm from '../components/ApplicationForm.vue';
import ContactUs from '../components/ContactUs.vue';
import AdoptedView from '../views/AdoptedView2.vue';
import PetDetailsView from '../views/PetDetailsView.vue';

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const Role = {
  Admin: 'ADMIN',

 };
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,

  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path:"/pets",
    name: "pets",
    component: PetView ,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/admin",
    name: "admin",
    component: AdminView,
    meta: {
      requiresAuth: true

    }
  },
  {
    path:"/addPhoto",
    name: "addPhoto",
    component: AddPhotoForm,
    meta: {
      requiresAuth: false
    }
  },
  {
    path:"/volunteer",
    name: "volunteer",
    component: ApplicationForm,
    meta: {
      requiresAuth: false
    }
  },
  {
    path:"/contactUs",
    name: "contactUs",
    component: ContactUs,

  },
  {
    path:"/adopted",
    name: "adopted",
    component: AdoptedView,
    meta: {
      requiresAuth: false
    }

  },
  {
    path:"/details",
    name: "details",
    component: PetDetailsView,

  },
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to, from, next) => {

  // Get the Vuex store
  const store = useStore();


  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"

  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  const requiresAdmin = to.matched.some(x => x.meta.requiresAdmin)
  
  // Otherwise, do nothing and they'll go to their next destination
  
  next();

  
});

export default router;
