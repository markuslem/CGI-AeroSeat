<template>
    <div>
        <h1>Booking complete!</h1>
        <div id="seat-info">
            <p>Your seat(s):</p>
            <br>
            <div v-for="seat in seats.seats">
                <p>{{ seat.seatRow }}{{ seat.seatColumn }}</p>
            </div>
        </div>

        <p>Thank you for booking a flight with us!</p>
    </div>
</template>
<script>
export default {
    name: 'BookingCompleteView',
    data() {
        return {
            seats: JSON.parse(localStorage.getItem('seats')) || []
        };
    },
}
</script>
<style>
#seat-info {
    display: inline-block;

    margin: 0.5em auto;
    padding: 0.5em;
    border: 1px solid black;
}
#seat-info > div {
    font-weight: 800;
}

</style>
