<template>
  <div>
    <div class="row my-5 mx-4 justify-content-center">
      <div class="col mb-5">
        <div id="avatar-container">
          <b-avatar class="d-block mb-2" variant="dark" square size="10em">
            <span id="userNameAbbreviation">{{getUserNameAbbreviation}}</span>
          </b-avatar>
          <div class="p-1">Website:</div>
          <div class="p-1">Github:</div>
        </div>
      </div>

      <div class="col-9">
        <div class="card-deck">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">User details</h5>
              <div class="card-text">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item">
                    <span class="font-italic pr-2">Username:</span>
                    <span>{{currentUser.username}}</span>
                  </li>
                  <li class="list-group-item">
                    <span class="font-italic pr-2">Email:</span>
                    <span>{{currentUser.email}}</span>
                  </li>
                  <li class="list-group-item">
                    <span class="font-italic pr-2">Gender:</span>
                  </li>
                  <li class="list-group-item">
                    <span class="font-italic pr-2">Location:</span>
                  </li>
                </ul>
                <div class="">
                  <span class="font-italic">Authorities:</span>
                  <ul class="">
                    <li
                      class=""
                      v-for="(role,index) in currentUser.roles"
                      :key="index"
                    >
                      <span>{{role}}</span>
                    </li>
                  </ul>
                </div>
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

    <div class="row mx-5 justify-content-center">
      <div class="col">
        <div class="card-deck">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">About</h5>
              <div
                class="card-text"
              >Lorem ipsum dolor sit amet consectetur adipisicing elit. Asperiores exercitationem, rerum obcaecati ad nostrum molestiae illo illum repudiandae dolores possimus dicta pariatur mollitia laborum porro impedit quaerat in provident sit ab, iste modi error. Animi culpa debitis est dolorem odio nobis id consequatur, quae molestias tempore numquam illo! Veritatis iste, esse illo eum, facere, aliquid reprehenderit itaque quis rerum deserunt ipsam impedit dicta amet velit inventore? Harum consequatur mollitia neque repellat at laboriosam dolorem minima fugit placeat nulla? Nemo fuga molestias tempora excepturi voluptates, sit eaque, quisquam ullam mollitia, tenetur dolore in voluptatem odit aspernatur optio quas aut. Ipsam corrupti, aperiam aliquam iste natus quis mollitia aliquid. Porro ab facere error voluptate voluptas in quidem earum eligendi, officia quod dolorum ut id repudiandae autem architecto fuga consequuntur odit optio facilis rem cupiditate iure beatae, delectus accusantium? Consequatur commodi voluptas rerum repudiandae ex reiciendis maiores minima iusto nam. Ullam, culpa minus. Quo saepe, reiciendis expedita magnam quae ullam id veniam voluptas, facilis ipsam, tenetur ipsum autem. Illo magni magnam qui amet et dolorum nihil odio eaque dicta, veritatis suscipit exercitationem veniam voluptate, voluptas, iusto quisquam. Illo ex labore dolores, deleniti commodi, possimus dolorem modi repudiandae fugit est sint. Neque, omnis id?</div>
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
  }
};
</script>

<style scoped>
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
</style>