<template>
  <div>

    <h3 class="m-5">Ask a public question</h3>

    <div class="card card-container mx-5 mb-5">
      <div class="card-body m-n4">
        <form class="form-group m-4" @submit.prevent="handleNewComment">

          <label for="title-text" class="mb-0">Title</label>
          <small class="d-block">Be specific and imagine you’re asking a question to another person</small>
          <input v-model="comment.title" type="text" id="title-text" class="form-control my-3"
            placeholder="type your comment title here....">

          <label class="mb-0 d-block mt-3">Body</label>
          <small class="d-block">Include all the information someone would need to answer your question</small>

          <div class="custom-control custom-checkbox d-inline-block mr-2">
            <input type="checkbox" class="custom-control-input" id="line-numbers" v-model="lineNumbers">
            <label class="custom-control-label" for="line-numbers">Linenumbers</label>
          </div>
          <div class="custom-control custom-checkbox d-inline-block ml-2 mt-4">
            <input type="checkbox" class="custom-control-input" id="read-only" v-model="readonly">
            <label class="custom-control-label" for="read-only">Read-only</label>
          </div>

          <prism-editor v-model="comment.text" language="js" :line-numbers="lineNumbers" :readonly="readonly"
            class="my-editor my-3" />

          <div class="form-group">
            <button class="btn btn-secondary btn-block" :disabled="loading">
              <span v-show="loading" class="spinner-border spinner-border-sm"></span>
              <span>Post it!</span>
            </button>
          </div>

        </form>
      </div>
    </div>
  </div>
</template>
<script>
  import Comment from '../../models/comment';
  import CommentService from '../../services/comment.service';
  import "prismjs";
  import "prismjs/themes/prism.css";

  export default {
    name: 'NewComment',
    data() {
      return {
        comment: new Comment('', ''),
        lineNumbers: true,
        readonly: false,
        loading: false,
        message: ''
      };
    },
    methods: {
      handleNewComment() {
         this.loading = true;
        CommentService.ask(this.comment).then(
          response => {
            this.message = response.data;
            console.log(this.message)
            this.$router.push('/');
          },
           error => {
             this.message =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
              console.log(this.message)
          }
        );
        this.loading = false;
      }
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
    }
  };
</script>

<style>
  .my-editor {
    height: 300px;
  }
</style>