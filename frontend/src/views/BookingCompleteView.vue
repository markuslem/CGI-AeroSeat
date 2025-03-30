<template>
    <div>
        <h1>Booking complete!</h1>
        <p>Your seat(s):</p>
        <div id="seat-info">
            <br>
            <p v-for="seat in seats.seats">{{ seat.seatRow }}{{ seat.seatColumn }}</p>
        </div>

        <p>Thank you for booking a flight with us!</p>
    </div>
</template>
<script>
export default {
    name: 'BookingCompleteView',
    data() {
        return {
            seats: JSON.parse(localStorage.getItem('seats')) || [],
        };
    },
}
</script>
<style>
#seat-info {
    display: inline-block;

    margin: 0.5em auto;
    padding: 0.5em;
}

#seat-info>p {
    font-weight: 800;
}
</style>
