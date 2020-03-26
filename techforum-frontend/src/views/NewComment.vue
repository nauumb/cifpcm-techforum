<template>
  <div id="app">
      NewComment page.
  </div>
</template>

<script>
export default {
  name: 'NewComment',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }
  }
};
</script>
