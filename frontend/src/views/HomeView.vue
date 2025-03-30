<template>
  <div id="flex-container">
    <div id="filters">

      <!-- Filter Destination -->
      <div class="paar">
        <p>Destination</p>
        <select v-model="selectedDestination">
          <option v-for="airport in destinations" :key="airport.id" :value="airport.id">
            {{ airport.cityName }}
          </option>
        </select>
      </div>

      <!-- Filter Date -->
      <div class="paar">
        <p>Date</p>
        <input v-model="selectedDate" type="date">
      </div>

      <!-- Filter Price -->
      <div class="paar">
        <p>Price</p>
        <input type="number" min="1" max="3000" v-model="priceHigh">
      </div>

      <button id="reset-btn" @click="resetFilters()">Reset Filters</button>
    </div>

    <div>
      <div v-for="flight in flights">
        <div v-if="(this.selectedDestination === flight.destinationAirport.id || this.selectedDestination === null) // Asukoha põhjal filtreerimine
          &&
          (formatToDate(this.selectedDate) == formatToDate(flight.departureTime) || this.selectedDate === null) // Kuupäeva põhjal filtreerimine
          &&
          (priceHigh >= flight.price || priceHigh === null) // Hinna põhjal filtreerimine
        ">
          <FlightCard :flight="flight" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import FlightCard from '@/components/FlightCard.vue';

export default {
  components: {
    FlightCard
  },
  name: 'HomeView',
  data() {
    return {
      kuupaev: Date(),
      priceHigh: null,
      destinations: [],
      selectedDestination: null,
      selectedDate: null,
      flights: []
    };
  },
  mounted() {

    // All city names of airports
    axios.get('http://localhost:8081/api/airport-names')
      .then(response => {
        this.destinations = response.data;
      })
      .catch(error => {
        console.log('Flight codes could not be read', error);
      });

    // kõik lendude objektid
    axios.get('http://localhost:8081/api/flights')
      .then(response => {
        this.flights = response.data;
        console.log("The flight data was read " + JSON.stringify(this.flights));
      })
      .catch(error => {
        console.log('Flights could not be read', error);
      });
  },
  methods: {
    formatToDate(dateString) {
      const date = new Date(dateString);
      return date.toISOString().split('T')[0];
    },
    resetFilters() {
      this.selectedDestination = null;
      this.selectedDate = null;
      this.priceHigh = null;
    }
  }
}
</script>

<style>
html {
  background: linear-gradient(135deg, #7dd5f8, #FFFFFF);
  min-height: 100%;
}

#flex-container {
  display: flex;
}

#filters {
  margin: 2em;
  padding: 2em;
  border-style: solid;
  border-radius: 5px;
}

.paar {
  padding: 0.5em;
}

input,
select,
#reset-btn {
  border-radius: 5px;
  /* border-width: 0; */
  border-style: solid;
  border-width: 0.2em;
  padding: 0.4em;
  background-color: #d4e1ffed;
}

#reset-btn {
  padding: 1em;
  margin-top: 1em;
}
</style>