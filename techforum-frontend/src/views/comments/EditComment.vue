<template>
  <div id="newcomment-form" class="m-auto">
    <div class="row align-items-center mt-5">
      <div class="col">
        <div class="card card-container mb-5">
          <div class="card-body m-n4">
            <form class="form-group m-4" @submit.prevent="handleEditComment">
              <div v-if="comment.parent_id === null">
                <label for="title-text" class="mb-0">Title</label>
                <small
                  class="d-block"
                >Be specific and imagine you’re asking a question to another person</small>
                <input
                  v-model="comment.title"
                  type="text"
                  id="title-text"
                  class="form-control my-3"
                  placeholder="type your comment title here...."
                />
              </div>

              <label class="mb-0 d-block mt-3">Body</label>
              <small
                class="d-block mb-3"
              >Include all the information someone would need to answer your question</small>

              <vue-editor v-model="comment.text" :editorToolbar="customToolbar"></vue-editor>

              <div id="live-preview-container" v-if="comment.text">
                <b-col md="6" class="m-3">
                  <b-icon icon="circle-fill" animation="throb" font-scale="1"></b-icon>
                  <span class="ml-2">Live preview</span>
                </b-col>
                <div class="mt-3 mx-5 border border-muted rounded">
                  <div v-html="comment.text" class="m-3"></div>
                </div>
              </div>

              <div class="form-group d-flex justify-content-end my-5">
                <button class="btn btn-sm btn-outline-dark">
                  <span>Save edits</span>
                </button>

                <button v-on:click="discard = true" class="btn btn-sm btn-outline-danger ml-3">
                  <span>Discard draft</span>
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Comment from "../../models/comment";
import CommentService from "../../services/comment.service";
import { VueEditor } from "vue2-editor";

export default {
  props: ["commentId", "parentId"],

  components: {
    VueEditor
  },
  name: "NewComment",
  data() {
    return {
      comment: new Comment("", ""),
      customToolbar: [
        [{ header: [false, 1, 2, 3, 4, 5, 6] }],
        ["bold", "italic", "underline", "strike"], // toggled buttons
        ["blockquote", "code-block"],
        [{ list: "ordered" }, { list: "bullet" }],
        [{ color: [] }, { background: [] }], // dropdown with defaults from theme
        ["link"],
        ["clean"] // remove formatting button
      ],
      discard: false,
      message: ""
    };
  },
  methods: {
    handleEditComment() {
      if (!this.discard) {
        CommentService.edit(this.comment).then(
          response => {
            this.message = response.data;

            if (this.parentId === null || this.parentId === undefined) {
              this.$router.push("/comments/" + this.commentId);
            } else {
              this.$router.push("/comments/" + this.parentId);
            }
          },
          error => {
            this.message =
              (error.response && error.response.data) ||
              error.message ||
              error.toString();
            this.loading = false;
          }
        );
      } else {
        if (this.parentId === null || this.parentId === undefined) {
          this.$router.push("/comments/" + this.commentId);
        } else {
          this.$router.push("/comments/" + this.parentId);
        }
      }
    }
  },
  mounted() {
    CommentService.get(this.commentId).then(
      response => {
        this.comment = response.data;
        document.title = "Comment | edit";
        this.dataLoaded = true;
      },
      error => {
        this.message =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>

<style scoped>
#newcomment-form {
  min-width: 70%;
  max-width: 70%;
}
</style>





