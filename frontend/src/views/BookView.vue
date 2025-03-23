<template>
    <p>Booking page</p>
    <p>{{ this.message }}</p>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            message: null
        }
    },
    mounted() {
        axios.get('http://localhost:8081/api/seats/1')
            .then(response => {
                this.message = response.data;
            })
            .catch(error => {
                console.error('The message could not be read', error);
            });
    }
}
</script>
<style></style>