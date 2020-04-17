<template>
  <div id="app">
    <div>
      <b-navbar class="py-1" toggleable="md" type="dark" variant="dark">
        <b-navbar-brand @click.prevent class>CIFPCM Tech Forum</b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav>
            <b-nav-item>
              <router-link to="/home" class="nav-link">Home</router-link>
            </b-nav-item>

            <b-nav-item v-if="showAdministratorBoard">
              <router-link to="/administrator" class="nav-link">Administrator Board</router-link>
            </b-nav-item>

            <b-nav-item v-if="showModeratorBoard">
              <router-link to="/moderator" class="nav-link">Moderator Board</router-link>
            </b-nav-item>

            <b-nav-item>
              <router-link v-if="currentUser" to="/user" class="nav-link">User Board</router-link>
            </b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav v-if="!currentUser" class="ml-auto">
            <b-nav-item variant="primary">
              <router-link
                class="mr-1 btn btn-secondary btn-sm nav-link text-light"
                to="/register"
              >Sign Up</router-link>
            </b-nav-item>

            <b-nav-item variant="primary">
              <router-link
                class="ml-1 btn btn-secondary btn-sm nav-link text-light"
                to="/login"
              >Login</router-link>
            </b-nav-item>
          </b-navbar-nav>

          <b-navbar-nav v-if="currentUser" class="ml-auto">
            <div class="d-none d-md-block">
              <b-nav-item-dropdown right>
                <b-dropdown-item disabled>Signed in as {{currentUser.username}}</b-dropdown-item>
                <b-dropdown-divider></b-dropdown-divider>
                <template v-slot:button-content>
                  <b-avatar square size="2em">{{getUserNameAbbreviation}}</b-avatar>
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

    <div>
      <div class="container-fluid">
        <div class="row">
          <div class="d-sm-block col-md-2 py-5 px-4 d-sm-block">
            <div class="">
              <div class="list-group">
                <div class="list-group-item d-flex justify-content-between align-items-center">
                  Cras justo odio
                  <span class="badge badge-pill badge-dark">Dark</span>
                </div>

                <div class="list-group-item d-flex justify-content-between align-items-center">
                  Dapibus ac facilisis in
                  <span class="badge badge-pill badge-dark">Dark</span>
                </div>

                <div class="list-group-item d-flex justify-content-between align-items-center">
                  Morbi leo risus
                  <span class="badge badge-pill badge-dark">Dark</span>
                </div>
              </div>
            </div>
          </div>
          <div class="col px-4">
            <router-view />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  computed: {
    getUserNameAbbreviation() {
      let username = this.$store.state.auth.user.username;
      return username.charAt(0);
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