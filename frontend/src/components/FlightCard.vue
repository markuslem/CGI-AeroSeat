<template>
  <div id="container">
    <!-- From -> To -->
    <div id="airport-codes">
      <h1>{{ flight.departureAirport.airportCode }}&#11106;{{ flight.destinationAirport.airportCode }}</h1>
      <p>{{ flight.departureAirport.cityName }} - {{ flight.destinationAirport.cityName }}</p>
    </div>

    <!-- Price of flight -->
    <div class="pair">
      <img src="../assets/euro.svg" alt="Price:" width="35em">
      <h2>{{ flight.price }}</h2>
    </div>
    <!-- Free seats -->
    <div class="pair">
      <img src="../assets/seat.svg" alt="Free seats:" width="35em">
      <h2>{{ flight.plane.numberOfSeats - flight.occupiedSeats }}</h2>
    </div>

    <!-- Departure and arrival time -->
    <div class="pair">
      <img src="../assets/scedule-icon.svg" alt="Departure and arrival time:" width="35em">
      <h2>{{ formatTime(flight.departureTime) }}-{{ formatTime(flight.arrivalTime) }}</h2>
    </div>

    <!-- Date of flight -->
    <div class="pair">
      <img src="../assets/calendar.svg" alt="Date of flight:" width="35em">
      <h2>{{ formatDate(flight.departureTime) }}</h2>
    </div>


    <!-- Plane -->
    <div class="pair">
      <img src="../assets/plane.svg" alt="Price:" width="35em">
      <h3>{{ flight.plane.name }}</h3>
    </div>

    <!-- Book button -->
     <button type="button" id="book-button" @click="openBooking()">Book now!</button>
  </div>
</template>
<script>
export default {
  name: 'FlightCard',
  props: {
    flight: {
      type: Object,
      required: true
    }
  },
  methods: {
    
    // Kasutajale kuvatakse ainult tunnid ja minutid
    formatTime(timeString) {
      const date = new Date(timeString);
      const hours = date.getHours().toString().padStart(2, '0');
      const minutes = date.getMinutes().toString().padStart(2, '0');
      return hours + ":" + minutes;
    },

    // Tagastab ainult kuupäeva osa "timeString"-ist
    formatDate(timeString) {
      const date = new Date(timeString);
      return date.toISOString().split('T')[0];
    },
    
    // Avame BookingView.vue koos parameetriga flightId = this.flight.id
    openBooking() {
      console.log("Routing to booking page");
      this.$router.push({ name: "book", params: { flightId: Number(this.flight.id)} });
    }
  }
}
</script>
<style>
#container {
  padding: 0.7em;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 1em;
  border-style: solid;
  border-radius: 8px;
  margin: 0.5em;
  background-color: white;
}

.pair {
  display: flex;
  align-items: center;
  margin: 0px;
}

#airport-codes {
  grid-column: span 2;
  text-align: center;
}

#airport-codes > h1, p {
  margin:0px;
}

#book-button {
  background-color: rgb(179, 237, 79);
  border-width: 0px;
  border-radius: 1em;
  font-size: large;
  font-weight: 600;
  cursor: pointer;

}
</style>
