<template>
    <div id="booking-container">
        <h1>Select seats</h1>
        <div id="preferences">
            <p style="font-weight: 700;">Your preferences:</p>
            <p style="padding-bottom: 0.5em;">(Highlighted in orange)</p>
            <!-- Kasutaja eelistuste valimine -->
            <div>
                <label>Next to window:</label>
                <input type="checkbox" v-model="windowSeat" />
            </div>
            <div>
                <label>More legroom:</label>
                <input type="checkbox" v-model="legroom" />
            </div>
            <div>
                <label>Near exit:</label>
                <input type="checkbox" v-model="exit" />
            </div>
        </div>
        <div id="column-box">
            <div v-for="letter in ['A', 'B', 'C', 'D']">
                <p>{{ letter }}</p>
            </div>
        </div>
        <div id="seat-grid">
            <div v-for="seat in seats">
                <!-- Kuvame istekohta vastaval sellele, kas ta on hõivatud või mitte -->
                <button v-if="seat.occupied" class="occupied-seat">{{ seat.seatRow + seat.seatColumn }}</button>
                <!-- Kui mitte hõivatud istekoht valitakse hiireklõpsuga, siis selle värv muutub -->
                <button v-if="!seat.occupied" @click="selectSeat(seat)" :class="['un-occupied-seat', {
                                    'selected-seat': selected.includes(seat),
                                    'seat-highlight': (windowSeat && (seat.seatColumn === 'A' || seat.seatColumn === 'D')) ||
                                        (legroom && seat.seatRow % 30 === 1) || (exit && (seat.seatRow % 30 < 3 || seat.seatRow % 30 > 28)),
                                }]">{{ seat.seatRow +
                                    seat.seatColumn
                                }}</button>
            </div>
        </div>
    </div>
    <button id="confirm-btn" @click="confirmChoices()">Confirm choices ({{ this.selected.length }})</button>
</template>
<script>
import axios from 'axios';
export default {
    props: {
        flightId: { // Lennu ID antakse ette parameetrina
            type: Number,
            required: true
        }
    },
    data() {
        return {
            seats: null, // Kõik istekohad
            selected: [], // Valitud istekohad
            // Kasutaja eelistused istekoha asukoha suhtes
            windowSeat: false,
            legroom: false,
            exit: false
        }
    },
    mounted() {
        // Leiame lennule vastavad istekohad
        axios.get(`http://localhost:8081/api/seats/${this.flightId}`)
            .then(response => {
                this.seats = response.data;
            })
            .catch(error => {
                console.error('The message could not be read', error);
            });
    },
    methods: {
        // Kui istekoht valitakse kasutaja poolt, siis see lisatakse valitud istekohtade listi
        selectSeat(seat) {
            if (this.selected.includes(seat)) {
                this.selected = this.selected.filter(s => s !== seat);
            } else {
                this.selected.push(seat);
            }
        },
        // Istekohtade valikute kinnitamine
        // Istekohtadele vastavad ID-d saadetakse back-end serverisse
        confirmChoices() {
            axios.post('http://localhost:8081/api/seats/booking', this.selected.map(s => s.id), {
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    console.log("Updated seats: " + this.selected.map(s => s.id));
                    console.log(JSON.stringify(response.data));
                    // Salvestame valitud istekohad localstorage-isse
                    localStorage.setItem('seats', JSON.stringify(response.data));
                    this.$router.push({
                        name: 'booking-complete',
                    });
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

#preferences {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-bottom: 1em;
    padding: 0.5em;

    border: 4px solid rgb(61, 133, 181);
    border-radius: 16px;
    width: 30%;

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

    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2), 0px 6px 20px rgba(0, 0, 0, 0.19);
    border-radius: 10px;
}

@media (max-width: 550px) {
    #seat-grid {
        width: 85%;
    }
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

/* Väljapääsude juurde lisatakse tühja ruumi */
#seat-grid>div:nth-child(120n+1), #seat-grid>div:nth-child(120n+2), 
#seat-grid>div:nth-child(120n+3), #seat-grid>div:nth-child(120n+4) {
    margin-top: 5em;
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

.seat-highlight {
    background-color: rgb(233, 183, 34);
}

.selected-seat {
    background-color: rgb(42, 94, 198);
    color: white;
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