<template>
  <div>{{comment}}</div>
</template>

<script>
import CommentService from "@/services/comment.service";

export default {
  props: ["commentId"],
  data() {
    return {
      comment: "",
      message: ""
    };
  },
  mounted() {
      CommentService.get(this.commentId).then(
        response => {
          this.comment = response.data;
          console.log(this.comment);
          this.$router.replace({ path: '/comments/' + this.comment.slug })
          document.title = 'Comment - details';
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
};
</script>



<style scoped>
</style>