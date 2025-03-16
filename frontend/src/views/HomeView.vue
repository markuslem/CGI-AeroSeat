<template>
  <div>
    <h1>{{ message }}</h1>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HomeView',
  data() {
    return {
      message: ''
    };
  },
  mounted() {
    axios.get('http://localhost:8081/api/message')
        .then(response => {
          this.message = response.data;
        })
        .catch(error => {
          console.error('The message could not be read', error);
        });
  }
};
</script>