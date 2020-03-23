<template>
  <div class="container">
    <header class=""> <h3>Post´s</h3> </header>
    <div class="list-group">
      <a href="#" class="list-group-item list-group-item-action"  v-for="(item,index) in content"  :key="index">
      <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{{ item.id }} {{ item.title }}</h5>
        <small>{{ item.posted }}</small>
      </div>
      <p class="mb-1">{{ item.text }}</p>
      <small>{{ item.author.username}} {{ item.author.email}}</small>
  </a>
</div>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Home',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
        console.log(this.content)
        console.log(this.content[0].id)
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
