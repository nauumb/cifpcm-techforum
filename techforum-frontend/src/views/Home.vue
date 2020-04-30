<template>
  <div id="home-content" class="row ml-3 align-items-center">
    <div class="col-9 mb-auto">
      <header class="my-4">
        <h3>Top Comments</h3>
      </header>
      <div class="d-flex my-2">
        <router-link class="ml-auto btn btn-outline-dark" to="/comments/ask">Ask a question</router-link>
      </div>
      <comment></comment>
    </div>
    <div
      class="col d-flex flex-column align-items-center justify-content-center align-self-start mt-5"
    >
      <div class="card mb-5" style="width: 20rem;">
        <div class="card-header">Categories</div>
        <ul class="list-group">
          <li class="list-group-item">JavaScript</li>
          <li class="list-group-item">Java</li>
          <li class="list-group-item">C#</li>
        </ul>
      </div>
      <div class="card" style="width: 20rem;">
        <div class="card-header">Hot Network Questions</div>
        <ul class="list-group">
          <li class="list-group-item">question</li>
          <li class="list-group-item">question</li>
          <li class="list-group-item">question</li>
        </ul>
      </div>
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
