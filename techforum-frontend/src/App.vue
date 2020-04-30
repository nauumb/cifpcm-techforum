<template>
  <div id="page-content">
    <div id="navbar">
      <b-navbar class="py-1 border" toggleable="md" type="light" variant="light">
        <router-link to="/home">
          <b-navbar-brand>
            <img
              id="logo-image"
              src="https://cifpcesarmanrique.es/wp-content/uploads/2019/01/LogoCMTransparente-BrilloExt.png"
            />
          </b-navbar-brand>
        </router-link>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav v-if="!currentUser" class="ml-auto">
            <b-nav-item>
              <router-link class="mr-1 nav-link" to="/register">Sign Up</router-link>
            </b-nav-item>

            <b-nav-item>
              <router-link class="ml-1 nav-link" to="/login">Login</router-link>
            </b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav v-if="currentUser" class="ml-auto">
            <div class="d-none d-md-block">
              <b-nav-item-dropdown right>
                <b-dropdown-item disabled>Signed in as {{currentUser.username}}</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <template v-slot:button-content>
                  <b-avatar variant="dark" text rounded size="2.2em">
                    <span id="userNameAbbreviation">{{getUserNameAbbreviation}}</span>
                  </b-avatar>
                </template>
                <b-dropdown-item>
                  <router-link to="/profile" class="nav-link text-dark">Your profile</router-link>
                </b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <b-dropdown-item>
                  <a href @click.prevent="logOut" class="nav-link text-dark">Sign out</a>
                </b-dropdown-item>
              </b-nav-item-dropdown>
            </div>

            <div class="d-block d-md-none">
              <b-nav-item>
                <router-link to="/profile" class="nav-link">Your profile</router-link>
              </b-nav-item>

              <b-nav-item>
                <a href @click.prevent="logOut" class="nav-link">Sign out</a>
              </b-nav-item>
            </div>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>

    <div id="body-content">
      <div class="container-fluid">
        <div id="sidebar-button" class v-b-toggle.sidebar-footer>
          <b-icon id="button-icon" icon="list"></b-icon>
        </div>

        <b-sidebar id="sidebar-footer" aria-label="Sidebar with custom footer" no-header shadow>
          <template v-slot:footer="{ hide }">
            <div class="d-flex text-light align-items-center justify-content-end mx-3 my-2">
              <b-button class="bg-light text-dark" size="sm" @click="hide">
                <b-icon icon="x"></b-icon>
              </b-button>
            </div>
          </template>
          <div class="mx-3 my-2">
            <b-navbar-nav>
              <b-nav-item>
                <router-link to="/home" class="nav-link text-dark">Home</router-link>
              </b-nav-item>

              <b-nav-item v-if="showAdministratorBoard">
                <router-link to="/administrator" class="nav-link text-dark">Administrator Board</router-link>
              </b-nav-item>

              <b-nav-item v-if="showModeratorBoard">
                <router-link to="/moderator" class="nav-link text-dark">Moderator Board</router-link>
              </b-nav-item>

              <b-nav-item>
                <router-link v-if="currentUser" to="/user" class="nav-link text-dark">User Board</router-link>
              </b-nav-item>
            </b-navbar-nav>
          </div>
        </b-sidebar>

        <div id="route-content">
          <vue-page-transition name="fade-in-left">
            <router-view />
          </vue-page-transition>
        </div>
      </div>
    </div>

    <div id="push"></div>

    <footer id="sticky-footer" class="footer py-4 bg-dark text-white-50">
      <div class="container text-center">
        <small>Copyright &copy; Your Website</small>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  computed: {
    getUserNameAbbreviation() {
      let username = this.$store.state.auth.user.username;
      return username.charAt(0).toUpperCase();
    },
    currentUser() {
      return this.$store.state.auth.user;
    },
    showAdministratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes("ROLE_ADMINISTRATOR");
      }
      return false;
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser.roles) {
        return this.currentUser.roles.includes("ROLE_MODERATOR");
      }
      return false;
    }
  },
  methods: {
    logOut() {
      this.$store.dispatch("auth/logout");
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
#page-content {
  min-height: calc(100vh - 70px);
}
#route-content {
  margin-left: 80px;
  margin-right: 30px;
}
#push {
  margin-bottom: 8em;
}
#logo-image {
  height: 45px;
}
#sidebar-button {
  position: fixed;
  top: 50%;
  transform: translateY(-50%);
}
#button-icon {
  height: 80px;
  width: 30px;
  font-size: 2em;
}
#userNameAbbreviation {
  margin-top: 9px;
}
#sticky-footer {
  height: 70px;
  width: 100%;
}
</style>