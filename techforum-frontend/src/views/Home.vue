<template>
  <div class="container">
    <header class="my-4"> <h3>Top Comments</h3> </header>
    <div class="d-flex my-2"><router-link class="ml-auto btn btn-outline-secondary" to="/comments/ask">Ask a question</router-link></div>
    <div class="list-group">
      <a href="#" class="list-group-item list-group-item-action"  v-for="(item,index) in content"  :key="index">
      <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{{ item.title }}</h5>
        <small>{{ item.posted | moment }}</small>
      </div>
      <div class="d-flex justify-content-end">
        <small class="text-right"> {{ item.author.username}}</small>
      </div>
  </a>
</div>
  </div>
</template>

<script>
import UserService from '../services/user.service';
import moment from 'moment'

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
        //console.log(this.content)
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  filters: {
    moment: function (date) {
      /*return moment(date).format('MMMM Do YYYY, h:mm:ss a');*/
      return moment(date).local().fromNow();
    }
  }
};
</script>
