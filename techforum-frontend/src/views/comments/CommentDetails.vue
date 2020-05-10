<template>
  <div id="commentdetails-content">
    <div class="row align-items-center justify-content-center col mt-5">
      <div id="comment">
        <h4 class>{{comment.title}}</h4>
        <small class="muted">Asked {{comment.posted | shortDate}}</small>
        <hr />
        <div class="card card-container mb-2">
          <div class="card-body m-n3">
            <div id="comment-text" v-html="comment.text" class="card-text m-3"></div>
          </div>
          <div id="post-menu" v-if="this.dataLoaded === true" class="d-flex p-2 ml-4 mt-3">
            <div
              v-if="(currentUser.id === this.comment.author.id) && (this.editorVisible === false)"
            >
              <router-link class="text-secondary" :to="{ name: 'editComment', params: { commentId: this.comment.id}}">Edit</router-link>

              <a class="ml-2 text-danger" v-on:click="handleDeleteComment">Delete</a>
            </div>

            <div
              class="user-information d-flex flex-wrap justify-content-start align-items-center rounded ml-auto m-2 p-2"
            >
              <span
                v-if="dataLoaded"
                class="user-action-time text-muted pb-2 w-100"
              >Asked {{comment.posted | completeDate}}</span>
              <b-avatar variant="dark" text rounded size="2.2em">
                <span
                  id="userNameAbbreviation"
                >{{this.comment.author.username | getUserNameAbbreviation}}</span>
              </b-avatar>
              <span
                v-if="dataLoaded"
                id="username"
                class="align-self-start text-muted ml-2"
              >{{comment.author.username}}</span>
            </div>
          </div>
          <div>
            <button
              v-if=" this.editorVisible === false"
              v-on:click="editorVisible = true"
              class="m-4 btn btn-outline-dark btn-sm"
            >Post your answer</button>
          </div>
        </div>

        <form class="fade-in-top" v-if="editorVisible" @submit.prevent="handleNewAnswer">
          <vue-editor v-model="answer.text" :editorToolbar="customToolbar"></vue-editor>

          <div class="d-flex justify-content-end mt-2">
            <button class="btn btn-sm btn-outline-dark">
              <span>Add comment</span>
            </button>
            <button
              @click.prevent="editorVisible = false"
              class="btn btn-sm btn-outline-danger ml-3"
            >
              <span>Cancel</span>
            </button>
          </div>
        </form>

        <div id="answers-content" class="ml-3 mt-4" v-if="this.answers.length > 0">
          <h5>Answers {{answers.length}}</h5>
          <div v-for="(item,index) in answers" :key="index">
            <hr />
            <div class="card blur">
              <div class="card-body">
                <div class="card-text" v-html="item.text"></div>
                <div id="post-menu" class="d-flex p-2 ml-4 mt-3">
                  <div
                    v-if="(currentUser.id === comment.author.id || currentUser.id === item.author.id)  && editorVisible === false"
                  >
                    <router-link class="text-secondary"
                      :to="{ name: 'editAnswer', params: { commentId: item.id, parentId: comment.id}}"
                    >Edit</router-link>

                    <a class="ml-2 text-danger" v-on:click="handleDeleteAnswer(item.id, index)">Delete</a>
                  </div>

                  <div
                    class="user-information d-flex flex-wrap justify-content-start align-items-center rounded ml-auto m-2 p-2 bg-light"
                  >
                    <span
                      class="user-action-time text-muted pb-2 w-100"
                    >answered {{item.posted | completeDate}}</span>
                    <b-avatar variant="dark" text rounded size="2.2em">
                      <span
                        id="userNameAbbreviation"
                      >{{ item.author.username | getUserNameAbbreviation}}</span>
                    </b-avatar>
                    <span
                      id="username"
                      class="align-self-start text-muted ml-2"
                    >{{item.author.username}}</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script scoped>
import CommentService from "@/services/comment.service";
import moment from "moment";
import { VueEditor } from "vue2-editor";

export default {
  props: ["commentId"],
  components: {
    VueEditor
  },
  data() {
    return {
      comment: new Comment("", ""),
      answer: new Comment("", "", ""),
      answers: [],
      message: "",
      dataLoaded: false,
      editorVisible: false,
      customToolbar: [
        [{ header: [false, 1, 2, 3, 4, 5, 6] }],
        ["bold", "italic", "underline", "strike"], // toggled buttons
        ["blockquote", "code-block"],
        [{ list: "ordered" }, { list: "bullet" }],
        [{ color: [] }, { background: [] }], // dropdown with defaults from theme
        ["link"],
        ["clean"] // remove formatting button
      ]
    };
  },
  methods: {
    handleDeleteComment() {
      CommentService.delete(this.comment.id).then(
        response => {
          this.message = response.data;
          this.$router.push("/");
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    handleDeleteAnswer: function(answer_id, index) {
      CommentService.delete(answer_id).then(
        response => {
          this.message = response.data;
          this.answers.splice(index, 1);
          this.$router.push("/comments/" + this.commentId);
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    handleNewAnswer() {
      this.answer.parent_id = this.comment.id;
      this.answer.title = this.comment.id;

      CommentService.ask(this.answer).then(
        response => {
          this.message = response.data;
          this.editorVisible = false;
          this.answers.push(this.message);
          this.answer.text = "";
        },
        error => {
          this.message =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    }
  },
  mounted() {
    CommentService.get(this.commentId).then(
      response => {
        this.comment = response.data;
        document.title = "Comment | details";
        this.dataLoaded = true;
      },
      error => {
        this.message =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
    CommentService.getThread(this.commentId).then(
      response => {
        this.answers = response.data;
      },
      error => {
        this.message =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  filters: {
    shortDate: function(date) {
      return moment(date)
        .local()
        .fromNow();
    },
    completeDate: function(date) {
      return moment(date).format("MMMM Do YYYY, h:mm");
    },
    getUserNameAbbreviation: function(username) {
      return username.charAt(0).toUpperCase();
    }
  }
};
</script>
<style>
.user-information {
  background-color: #e1ecf4;
  max-width: 200px;
  width: 200px;
}
.user-action-time {
  font-size: 13px;
}
.user-information > #username {
  font-size: 13px;
}
#comment {
  min-width: 50%;
  max-width: 50%;
}
pre {
  border-radius: 5px;
  padding: 20px;
  background-color: #eff0f1;
}
#comment-text > pre {
  max-height: 500px;
}
hr {
  margin-top: 1rem;
  margin-bottom: 1rem;
  border: 0;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}
.blur{
   animation: fade-in-top 0.8s;
}
@keyframes fade-in-top {
  0% {
    opacity: 0;
  }
  100% {
    opacity: 1;
  }
}

.fade-in-top {
  animation: fade-in-top 0.8s;
}
@keyframes fade-in-top {
  0% {
    transform: translateY(-30px);
    opacity: 0;
  }
  100% {
    transform: translateY(0px);
    opacity: 1;
  }
}
</style>