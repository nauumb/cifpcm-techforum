<template>
  <div class="row mx-4 align-items-center">
    <div class="col">
      <header class="my-4">
        <h3>Top Comments</h3>
      </header>
      <div class="d-flex my-2">
        <router-link class="ml-auto btn btn-outline-dark" to="/comments/ask">Ask a question</router-link>
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
