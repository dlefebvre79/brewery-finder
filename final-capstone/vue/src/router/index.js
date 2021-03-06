import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Breweries from '@/views/Breweries.vue'
import BreweryInformation from '@/components/BreweryInformation.vue'
import BeerInformation from '@/components/BeerInformation.vue'
import BeerList from '@/components/BeerList.vue'
import Users from '@/views/Users.vue'
import AddBeer from '@/components/AddBeer.vue'
import DeleteBeer from '@/components/DeleteBeer.vue'
import CreateBrewery from '@/components/CreateBrewery.vue'
import AddAReview from '@/components/AddAReview.vue'
import ReviewList from '@/components/ReviewList.vue'
import ImageList from '@/components/ImageList.vue'


Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path:"/brewery/:id",
      name:"brewery",
      component: BreweryInformation
    },
    {
      path:"/breweries",
      name: "brewery-list",
      component: Breweries
    },
    {
      path:"/beer/:id",
      name:"beer-information",
      component: BeerInformation
    },
    {
      path:"/beer/brewery/:id",
      name:"beer-list",
      component: BeerList
    },
    {
      path:"/brewery/:id/add-beer",
      name: "add-beer",
      component: AddBeer

    },
    {
      path:"/brewery/:id/delete-beer",
      name: "delete-beer",
      component: DeleteBeer

    },
    {
        path: "/user",
        name: "users",
        component: Users,
        meta: {
            requiresAdmin: true
          }
    },
    {
        path:"/create-brewery",
        name:"create-brewery",
        component: CreateBrewery,
        meta: {
            requiresAdmin: true
          }
    },
    {
      path:"/add-review",
      name: "add-review",
      component: AddAReview
    },
    {
      path: "/review-list",
      name: "review-list",
      component: ReviewList
    },
    {
      path: "/images",
      name: "image-list",
      component: ImageList
    },
    ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);
  const requiresAdmin = to.matched.some(x => x.meta.requiresAdmin);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
  // If it does and they are not logged in, send the user to "/login"
  if (requiresAdmin && store.state.user.authorities[0].name != 'ROLE_ADMIN') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
