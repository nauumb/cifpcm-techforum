<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
  </div>
</template>

<script>
import UserService from "../../../services/user.service";
export default {
  name: "Administrator",
  data() {
    return {
      content: ""
    };
  },
  mounted() {
    document.title =
      "Administrator board | " + this.$store.state.auth.user.username;
    UserService.getAdministratorBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>