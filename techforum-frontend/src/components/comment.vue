<template>
  <div class="list-group">
    <router-link
      :to="{ name: 'commentDetails', params: { commentId: item.id}}"
      class="list-group-item list-group-item-action"
      v-for="(item,index) in content"
      :key="index"
    >
      <div class="d-flex w-100 justify-content-between">
        <h5 class="mb-1">{{ item.title }}</h5>
        <small>{{ item.posted | moment }}</small>
      </div>
      <div class="d-flex justify-content-end">
        <small class="text-right">{{ item.author.username}}</small>
      </div>
    </router-link>
  </div>
</template>

<script>
import UserService from "@/services/user.service";
import moment from "moment";

export default {
  data() {
    return {
      content: ""
    };
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
        console.log(this.content);
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
    moment: function(date) {
      return moment(date)
        .local()
        .fromNow();
    }
  }
};
</script>

<style scoped>
</style>