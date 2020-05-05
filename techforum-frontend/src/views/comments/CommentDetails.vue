<template>
  <div id="commentdetails-content">
    <div class="row align-items-center justify-content-center col mt-5">
      <div id="comment">
        <h4 class>{{comment.title}}</h4>
        <small class="muted">Asked {{comment.posted | shortDate}}</small>
        <hr />
        <div class="card card-container mb-5">
          <div class="card-body m-n3">
            <div id="comment-text" v-html="comment.text" class="card-text m-3"></div>
          </div>
          <div class="d-flex p-2 ml-4 mt-3">
            <div id="post-menu" v-if="currentUser.id === this.comment.author.id">
              <router-link
                :to="{ name: 'editComment', params: { commentId: this.comment.id}}"
                class="card-link"
              >Edit</router-link>
              <router-link :to="{ name: '', params: {}}" class="card-link">Delete</router-link>
            </div>

            <div class="ml-3">
              <a href="#" class="card-link">Add a comment</a>
            </div>

            <div
              class="d-flex flex-wrap justify-content-start align-items-center rounded ml-auto m-2 p-2"
              id="user-information"
            >
              <span
                v-if="dataLoaded"
                id="user-action-time"
                class="text-muted pb-2 w-100"
              >Asked {{comment.posted | completeDate}}</span>
              <b-avatar variant="dark" text rounded size="2.2em">
                <span id="userNameAbbreviation">{{getUserNameAbbreviation}}</span>
              </b-avatar>
              <span
                v-if="dataLoaded"
                id="username"
                class="align-self-start text-muted ml-2"
              >{{comment.author.username}}</span>
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

export default {
  props: ["commentId"],
  data() {
    return {
      comment: "",
      message: "",
      dataLoaded: false
    };
  },
  mounted() {
    CommentService.get(this.commentId).then(
      response => {
        this.comment = response.data;
        console.log(this.comment);
        document.title = "Comment | details";
        this.dataLoaded = true;
      },
      error => {
        this.message =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
        console.log(this.message);
      }
    );
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    getUserNameAbbreviation() {
      if (this.dataLoaded) {
        let username = this.comment.author.username;
        return username.charAt(0).toUpperCase();
      }
      return "";
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
    }
  }
};
</script>
<style>
#user-information {
  background-color: #e1ecf4;
  max-width: 170px;
  width: 170px;
}
#user-action-time {
  font-size: 13px;
}
#user-information > #username {
  font-size: 13px;
}
#comment {
  min-width: 70%;
  max-width: 70%;
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
</style>