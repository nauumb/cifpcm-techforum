<template>
  <div>

    <h3 class="m-5">Ask a public question</h3>

    <div class="card card-container mx-5 mb-5">
      <div class="card-body m-n4">
        <form class="form-group m-4">
          <label for="title-text" class="mb-0">Title</label>
          <small class="d-block">Be specific and imagine you’re asking a question to another person</small>
          <input type="text" id="title-text" class="form-control my-3" placeholder="type your comment title here....">

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
          <prism-editor v-model="code" language="js" :line-numbers="lineNumbers" :readonly="readonly"
            class="my-editor my-3" />
        </form>
      </div>
    </div>
  </div>
</template>
<script>
  import "prismjs";
  import "prismjs/themes/prism.css";

  export default {
    name: 'NewComment',
    data() {
      return {
        code: null,
        lineNumbers: true,
        readonly: false
      };
    },
    computed: {
      currentUser() {
        return this.$store.state.auth.user;
      }
      // },
      // mounted() {
      //   if (!this.currentUser) {
      //     this.$router.push('/login');
      //   }
    }
  };
</script>

<style>
  .my-editor {
    height: 300px;
  }
</style>