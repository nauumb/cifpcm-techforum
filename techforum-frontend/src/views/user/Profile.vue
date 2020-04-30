<template>
  <div id="profile-content">
    <div class="row my-5 justify-content-center">
      <div class="col-xs-12 col-md-5 col-lg-2 mb-5">
        <div id="avatar-container">
          <b-avatar class="d-block mb-2" variant="dark" square size="10em">
            <span id="userNameAbbreviation">{{getUserNameAbbreviation}}</span>
          </b-avatar>
          <div class="p-1">Website:</div>
          <div class="p-1">Github:</div>
        </div>
      </div>

      <div class="col">
        <div class="card-deck">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">User details</h5>
              <div class="card-text">
                <ul class="list-group list-group-flush mt-4">
                  <li class="list-group-item">
                    <span class="pr-2">Username:</span>
                    <span>{{currentUser.username}}</span>
                  </li>
                  <li class="list-group-item">
                    <span class="pr-2">Email:</span>
                    <span>{{currentUser.email}}</span>
                  </li>
                  <li class="list-group-item">
                    <span class="pr-2">Authorities:</span>
                    <span class="pr-1" v-for="(role,index) in currentUser.roles" :key="index">
                      <span v-if="index != 0">,</span>
                      {{role | formatRole}}
                    </span>
                  </li>
                </ul>
              </div>
            </div>
          </div>

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Personal details</h5>
              <ul class="list-group list-group-flush">
                <li class="list-group-item">First name:</li>
                <li class="list-group-item">Last name:</li>
                <li class="list-group-item">Gender:</li>
                <li class="list-group-item">Location:</li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div id="about-lastestpost" class="row justify-content-center">
      <div class="col-xs-12 col-md-6">
        <div class="card-deck">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">About</h5>
              <div class="card-text"></div>
            </div>
          </div>
        </div>
      </div>
      <div class="col-xs-12 col-md-6">
        <div class="card-deck">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Lastest posts</h5>
              <div class="card-text"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

        // <div id="profile-button" class="pr-1 py-1 mr-3 my-3 bd-highlight">
        //   <b-button size="sm">Edit profile</b-button>
        // </div>

<script>
export default {
  name: "Profile",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    getUserNameAbbreviation() {
      let username = this.$store.state.auth.user.username;
      return username.charAt(0).toUpperCase();
    }
  },
  mounted() {
    document.title = "Profile | " + this.$store.state.auth.user.username;
  },
  filters: {
    formatRole: function(role) {
      let cutPoint = role.indexOf("_");
      role = role.substring(cutPoint + 1).toLowerCase();
      return role;
    }
  }
};
</script>

<style scoped>
body {
  height: 100%;
}
#avatar-container {
  min-width: 10em;
  max-width: 10em;
}

#userNameAbbreviation {
  margin-top: 0.65em;
  font-size: 4em;
}
#profile-button > button {
  width: 11.5em;
  min-width: 11.5em;
}

@media (min-width: 1200px) {
  .card-deck {
    min-height: 200px;
  }
  #about-lastestpost {
    margin-top: 100px;
  }
}
@media (min-width: 576px) {
  .card-deck {
    min-height: 200px;
  }
}
</style>