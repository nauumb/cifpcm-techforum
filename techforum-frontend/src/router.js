import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('./views/Home.vue'),
    },
    {
      path: '/home',
      component: () => import('./views/Home.vue'),
    },
    {
      path: '/login',
      component: () => import('./views/authentication/Login.vue'),
    },
    {
      path: '/register',
      component: () => import('./views/authentication/Register.vue'),
    },
    {
      path: '/profile',
      name: 'profile',
      component: () => import('./views/user/Profile.vue'),
    },
    {
      path: '/administrator',
      name: 'administrator',
      component: () => import('./views/user/boards/BoardAdministrator.vue'),
    },
    {
      path: '/moderator',
      name: 'moderator',
      component: () => import('./views/user/boards/BoardModerator.vue'),
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('./views/user/boards/BoardUser.vue'),
    },
    {
      path: '/comments/ask',
      name: 'NewComment',
      component: () => import('./views/comments/NewComment.vue'),
    },
    { 
      path: '/comments/:commentId',
      name: 'commentDetails',
      component: () => import('./views/comments/CommentDetails.vue'),
      props: true,
    }
  ],
})

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home']
  const authRequired = !publicPages.includes(to.path)
  const loggedIn = localStorage.getItem('user')

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login')
  } else {
    next()
  }
})
