<template>
  <div>
    <h3 class="m-5">Ask a public question</h3>
    <div class="card card-container mx-5 mb-5">
      <div class="card-body m-n4">
        <form class="form-group m-4" @submit.prevent="handleNewComment">
          <label for="title-text" class="mb-0">Title</label>
          <small class="d-block">Be specific and imagine you’re asking a question to another person</small>
          <input
            v-model="comment.title"
            type="text"
            id="title-text"
            class="form-control my-3"
            placeholder="type your comment title here...."
          />

          <label class="mb-0 d-block mt-3">Body</label>
          <small
            class="d-block"
          >Include all the information someone would need to answer your question</small>

          <vue-editor v-model="comment.text" :editorToolbar="customToolbar"></vue-editor>

          <div v-if="comment.text">
            <b-col md="6" class="m-3">
              <b-icon icon="circle-fill" animation="throb" font-scale="1"></b-icon>
              <span class="ml-2">Live preview</span>
            </b-col>
            <div class="mt-3 mx-5 border border-secondary rounded"><div v-html="comment.text" class="m-3"></div></div>
          </div>

          <div class="form-group d-flex justify-content-end my-5">
            <button class="btn btn-outline-dark" :disabled="loading">
              <span v-show="loading" class="spinner-border spinner-border-sm"></span>
              <span>Post your question</span>
            </button>

            <button class="btn btn-outline-danger ml-3">
              <span>Discard draft</span>
            </button>
          </div>
        </form>
      </div>
    </div>

    <!-- <div class="custom-control custom-checkbox d-inline-block mr-2">
      <input type="checkbox" class="custom-control-input" id="line-numbers" v-model="lineNumbers" />
      <label class="custom-control-label" for="line-numbers">Linenumbers</label>
    </div>-->

    <!-- <div class="custom-control custom-checkbox d-inline-block ml-2 mt-4">
            <input type="checkbox" class="custom-control-input" id="read-only" v-model="readonly" />
            <label class="custom-control-label" for="read-only">Read-only</label>
    </div>-->

    <!-- <prism-editor
            v-model="comment.text"
            language="js"
            :line-numbers="lineNumbers"
            :readonly="readonly"
            class="my-editor my-3"
    />-->
  </div>
</template>
<script>
import Comment from "../../models/comment";
import CommentService from "../../services/comment.service";
import { VueEditor } from "vue2-editor";
// import "prismjs";
// import "prismjs/themes/prism.css";

export default {
  components: {
    VueEditor
  },
  name: "NewComment",
  data() {
    return {
      comment: new Comment("", ""),
      // lineNumbers: true,
      // readonly: false,
      // loading: false,
      customToolbar: [
        [{ header: [false, 1, 2, 3, 4, 5, 6] }],
        ["bold", "italic", "underline", "strike"], // toggled buttons
        [
          { align: "" },
          { align: "center" },
          { align: "right" },
          { align: "justify" }
        ],
        ["blockquote", "code-block"],
        [{ list: "ordered" }, { list: "bullet" }, { list: "check" }],
        [{ indent: "-1" }, { indent: "+1" }], // outdent/indent
        [{ color: [] }, { background: [] }], // dropdown with defaults from theme
        ["link"],
        ["clean"] // remove formatting button
      ],
      message: ""
    };
  },
  methods: {
    handleNewComment() {
      this.loading = true;
      CommentService.ask(this.comment).then(
        response => {
          this.message = response.data;
          console.log(this.message);
          this.$router.push("/");
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
          console.log(this.message);
          this.loading = false;
        }
      );
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    document.title = "Comment | new comment";
  }
};
</script>

<style>
pre {
  background-color: #eff0f1;
}

.my-editor {
  height: 300px;
}
</style>