<template>
  <div id="home-content" class="row align-items-center justify-content-center m-auto">
    <div class="col-11 mb-auto p-0">
      <header class="my-4">
        <h3>Top Comments</h3>
      </header>
      <div class="d-flex my-2">
        <router-link class="btn btn-sm btn-outline-dark ml-auto " to="/comments/post">Ask a question</router-link>
      </div>
      <comment></comment>
    </div>
  </div>
</template>

<script>
import Comment from "@/components/Comment.vue";
export default {
  name: "Home",
  components: {
    Comment
  },
  data() {
    return {
      content: ""
    };
  },
  mounted() {
    document.title = "CIFPCM Tech Forum | Home";
  }
};
</script>

<style scoped>
#home-content {
  min-width: 70%;
  max-width: 70%;
}
</style>