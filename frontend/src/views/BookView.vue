<template>
    <div id="booking-container">
        <h1>Select seats</h1>
        <div id="preferences">
            <p style="font-weight: 700;">Your preferences:</p>
            <p style="padding-bottom: 0.5em;">(Highlighted in dark green)</p>
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
            <div>
                <label>Multiple seats:</label>
                <input type="checkbox" v-model="multipleSeats" />
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
                <!-- Kui mitte hõivatud istekoht valitakse hiireklõpsuga, siis selle värv muutub.
                Mitme Istekoha valimisel valitakse valikute ühisosa. -->
                <button v-if="!seat.occupied" @click="selectSeat(seat)" :class="['un-occupied-seat', {
                    'selected-seat': selected.includes(seat),
                    'seat-highlight': highlightSeat(seat) || nextToUnoccupied(seat),
                }]">{{ seat.seatRow +
                    seat.seatColumn
                    }}</button>
            </div>
        </div>
    </div>
    <button v-if="this.selected.length > 0" id="confirm-btn" @click="confirmChoices()">Confirm choices ({{
        this.selected.length }})</button>
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
            exit: false,
            multipleSeats: false,
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
        // Meetod, mis ütleb, kas istekoht peaks olema esile tõsteud
        highlightSeat(seat) {
            const count = [this.windowSeat, this.legroom, this.exit].filter(Boolean).length;
            const window = this.windowSeat && (seat.seatColumn === 'A' || seat.seatColumn === 'D');
            const legroom = this.legroom && seat.seatRow % 30 === 1;
            const exit = this.exit && (seat.seatRow % 30 < 4 || seat.seatRow % 30 > 29 ||
            this.seats[this.seats.length - 1].seatRow - seat.seatRow < 3);

            if (count === 0) {
                return false;
            } else if (count === 1) {
                return (window || legroom || exit);
            } else if (count === 2) {
                return (window && legroom) || (window && exit) || (legroom && exit);
            } else if (count === 3) {
                return (window && legroom && exit);
            }
        },

        // Kui istekoht paikneb vaba istekoha kõrval ja soovitakse kõrvuti istuda, siis istekoht tõstetakse esile
        nextToUnoccupied(seat) {
            if (this.multipleSeats && !seat.occupied) {
                const seatIndex = this.seats.indexOf(seat);
                let nextSeat;
                let prevSeat;
                if (seat.seatColumn === 'D' || seatIndex === this.seats.length - 1) { // Sellele istekohale ei ole paremal ühtegi järgnevat
                    nextSeat = true;
                } else {
                    nextSeat = this.seats[seatIndex + 1].occupied;
                }
                if (seat.seatColumn === 'A') { // Selle istekoha vasakul pool ei ole ühtegi istekohta
                    prevSeat = true;
                } else {
                    prevSeat = this.seats[seatIndex - 1].occupied;
                }
                return (!nextSeat) || (!prevSeat); // Kui vasak või parem isekoht ei ole hõivatud
            }
            return false;
        },

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

    border: 4px solid rgb(28, 158, 109);
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
    width: 50%;align-items: center;
    margin-bottom: 4em;

    box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2), 0px 6px 20px rgba(0, 0, 0, 0.19);
    border-radius: 10px;
}

@media (max-width: 550px) {

    #seat-grid,
    #column-box {
        width: 85%;
    }

    #preferences {
        width: 70%;
    }
}

#seat-grid>div {
    display: flex;
    justify-content: center;
}

#seat-grid>div>button {
    padding: 0.5em;
    margin: 0.5em;
    width: 3em;
    cursor: pointer;
    border-style: none;
    font-weight: bold;
}

/* Väljapääsude juurde lisatakse tühja ruumi */
#seat-grid>div:nth-child(120n+1),
#seat-grid>div:nth-child(120n+2),
#seat-grid>div:nth-child(120n+3),
#seat-grid>div:nth-child(120n+4) {
    margin-top: 5em;
}


.occupied-seat {
    background-color: rgba(184, 184, 184, 0.518);
    text-align: center;
    border-radius: 5px;
}

.un-occupied-seat {
    border-radius: 5px;
    background-color: rgb(115, 219, 120);
}

.seat-highlight {
    background-color: rgb(24, 146, 15);
    color: white;
}

.selected-seat {
    background-color: black;
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