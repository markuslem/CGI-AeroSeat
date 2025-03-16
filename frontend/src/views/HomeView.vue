<template>
  <div id="flex-container">
    <div id="filters">
      <div class="paar">
        <p>Sihtkoht</p>
        <select id="sihtkohad-select">
          <option v-for="koht in sihtkohad">{{ koht }}</option>
        </select>
      </div>
      <div class="paar">
        <p>Kuupäev</p>
        <input type="date">
      </div>
      <div class="paar">
        <p>Lennuaeg</p>
        <input type="time">
        <p>-</p>
        <input type="time">
      </div>
      <div class="paar">
        <p>Hind</p>
        <div style="display: flex;">
          <input type="number" min="1" max="3000" v-model="hind1">
          <p>-</p>
          <input type="number" min="1" max="3000" v-model="hind2">
        </div>
      </div>
    </div>
    <div>
      <h1>{{ message }}</h1>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HomeView',
  data() {
    return {
      message: '',
      sihtkoht: '',
      kuupaev: Date(),
      aeg: '',
      hind1: "500",
      hind2: "1000",
      sihtkohad: ['AAR', 'AXT', 'BVA', 'TLL', 'SUV', 'PWM', 'LAX']
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

<style>
#flex-container {
  display: flex;
}

#filters {
  margin: 2em;
  padding: 2em;
  border-style: solid;
}

.paar {
  border-style: solid;
  padding: 0.5em;
}
</style>