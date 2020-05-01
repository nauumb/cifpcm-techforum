<template>
  <div id="top-comments-list">
    <div id="fade-in-left-transition" class="list-group">
      <router-link
        :to="{ name: 'commentDetails', params: { commentId: item.id, slug: item.slug}}"
        class="list-group-item list-group-item-action"
        v-for="(item,index) in paginatedData"
        :key="index"
      >
        <div class="d-flex w-100 align-items-center justify-content-between">
          <h5 class="mb-1">{{ item.title }}</h5>
          <small>{{ item.posted | moment }}</small>
        </div>
        <div class="d-flex justify-content-end">
          <small class="text-right">{{ item.author.username}}</small>
        </div>
      </router-link>
    </div>
    <nav id="pagination-nav" class="d-flex justify-content-end mt-2" v-if="content != ''">
      <ul class="pagination">
        <li class="page-item">
          <button
            class="page-link text-dark"
            :disabled="pageNumber === 0"
            @click="prevPage"
          >Previous</button>
        </li>
        <li class="page-item">
          <button
            class="page-link text-dark"
            :disabled="pageNumber >= pageCount -1"
            @click="nextPage"
          >Next</button>
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
import UserService from "@/services/user.service";
import moment from "moment";

export default {
  data() {
    return {
      content: '',
      pageNumber: 0,
      size: 7,
    };
  },
  methods: {
    nextPage() {
      this.pageNumber++;
    },
    prevPage() {
      this.pageNumber--;
    }
  },
  computed: {
    pageCount() {
      let contentSize = this.content.length,
        pageSize = this.size;
      return Math.ceil(contentSize / pageSize);
    },
    paginatedData() {
      const start = this.pageNumber * this.size,
        end = start + this.size;
      return this.content.slice(start, end);
    }
  },
  mounted() {
    UserService.getPublicContent().then(
      response => {
        this.content = response.data;
        var element = document.getElementById("fade-in-left-transition");
        element.classList.add("fade-in-left");
        console.log(this.content);
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  filters: {
    moment: function(date) {
      return moment(date)
        .local()
        .fromNow();
    }
  }
};
</script>

<style scoped>
.fade-in-left {
  animation: fade-in-left 2s;
}
@keyframes fade-in-left {
  0% {
    transform: translateX(-200px);
    opacity: 0;
  }
  100% {
    transform: translateX(0px);
    opacity: 1;
  }
}
</style>