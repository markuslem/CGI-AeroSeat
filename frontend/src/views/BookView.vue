<template>
    <h1>Booking page</h1>
    <div id="booking-container">
        <div id="column-box">
            <div v-for="letter in ['A', 'B', 'C', 'D']">
                <p>{{ letter }}</p>
            </div>
        </div>
        <div id="seat-grid">
            <div v-for="seat in seats">
                <p v-if="seat.occupied" class="occupied-seat">{{ seat.seatNumber }}</p>
                <p v-if="!seat.occupied" class="un-occupied-seat">{{ seat.seatNumber }}</p>
            </div>
        </div>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data() {
        return {
            seats: null
        }
    },
    mounted() {
        axios.get('http://localhost:8081/api/seats/1')
            .then(response => {
                this.seats = response.data;

            })
            .catch(error => {
                console.error('The message could not be read', error);
            });
    }
}
</script>
<style>
#booking-container {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

#column-box {
    display: grid;
    grid-template-columns: 0.5fr 2fr 2fr 0.5fr;
    width: 50%;
    border-width: 5px;
    border-color: rgb(50, 50, 50);
    border-style: solid;
    margin-bottom: 1em;
    padding: 0.4em;
    color: rgb(70, 70, 70);
    border-radius: 6px;
}

#column-box > div {
    display: flex;
    justify-content: center;
}

#seat-grid {
    display: grid;
    grid-template-columns: 0.5fr 2fr 2fr 0.5fr;
    width: 50%;

    /* Representing plane borders */
    border-left: 10px;
    border-right: 10px;
    border-left-style: solid;
    border-right-style: solid;
    padding-left: 2em;
    padding-right: 2em;
    border-color: rgba(201, 201, 201, 0.839);
}

#seat-grid > div {
    display: flex;
    justify-content: center;
}

#seat-grid > div > p {
    padding-bottom: 0.5em;
    margin: 0.5em;
    width: 3em;
}

.occupied-seat {
    background-color: rgba(184, 184, 184, 0.887);
    text-align: center;
}

.un-occupied-seat {
    background-color: rgb(49, 170, 55);
}
</style>