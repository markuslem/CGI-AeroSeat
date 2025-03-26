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
                <button v-if="seat.occupied" class="occupied-seat">{{ seat.seatNumber }}</button>
                <button v-if="!seat.occupied" @click="selectSeat(seat)" class="un-occupied-seat">{{ seat.seatNumber
                }}</button>
            </div>
        </div>
    </div>
    <button id="confirm-btn" @click="confirmChoices()">Confirm choices</button>
</template>
<script>
import axios from 'axios';
export default {
    props: {
        flightId: {
            type: Number,
            required: true
        }
    },
    data() {
        return {
            seats: null,
            selected: []
        }
    },
    mounted() {
        axios.get(`http://localhost:8081/api/seats/${this.flightId}`)
            .then(response => {
            this.seats = response.data;
            })
            .catch(error => {
                console.error('The message could not be read', error);
            });
    },
    methods: {
        selectSeat(seat) {
            if (this.selected.includes(seat)) {
                this.selected = this.selected.filter(s => s !== seat);
            } else {
                this.selected.push(seat);
            }
        },
        confirmChoices() {
            axios.post('http://localhost:8081/api/seats/booking', this.selected.map(s => s.id), {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log("Updated seats: " + this.selected.map(s => s.id));
                    console.log(response.data);
                    this.$router.push("/");
                })
                .catch(error => {
                    console.error('The message could not be read', error);
                });
        }
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

#column-box>div {
    display: flex;
    justify-content: center;
}

#seat-grid {
    display: grid;
    grid-template-columns: 0.5fr 2fr 2fr 0.5fr;
    width: 50%;
    margin-bottom: 4em;

    /* Representing plane borders */
    border-left: 10px;
    border-right: 10px;
    border-left-style: solid;
    border-right-style: solid;
    padding-left: 2em;
    padding-right: 2em;
    border-color: rgba(201, 201, 201, 0.839);
}

#seat-grid>div {
    display: flex;
    justify-content: center;
}

#seat-grid>div>button {
    padding-bottom: 0.5em;
    margin: 0.5em;
    width: 3em;
    cursor: pointer;
    border-style: none;
}

.occupied-seat {
    background-color: rgba(184, 184, 184, 0.518);
    text-align: center;
    border-radius: 5px;
}

.un-occupied-seat {
    border-radius: 5px;
    background-color: rgb(61, 181, 67);
}

#confirm-btn {
    background-color: rgb(61, 133, 181);
    color: white;
    border-radius: 5px;
    padding: 0.5em;
    cursor: pointer;
    margin-top: 1em;
    border-style: none;
    width: 50%;
    font-size: 1.2em;
    font-weight: 600;

    /* Confirm choices nupp püsib all */
    position: fixed;
    bottom: 1em;
    left: 50%;
    transform: translateX(-50%);
}
</style>