<template>
    <div class="main">
        <div class="second">
            <h1>Contact Us</h1>
            <h2>Address</h2>
            <p>123 Avery Lane <br>
                Chicago, IL 12345</p>
            <h2>Phone Number</h2>
            <p>867-5309</p>
            <h2>Email: </h2>
            <p>parkerspets.shelter@gmail.com</p>
        </div>
        <div class="third">
            <iframe id="map"
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3056.482221006552!2d-83.0447110240097!3d39.99767497151014!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x88388e5dee2e1ba9%3A0xaa73aa2d1dc08ad6!2sTech%20Elevator%20Columbus!5e0!3m2!1sen!2sus!4v1705610588414!5m2!1sen!2sus"
                width="600" height="450" style="border-radius:20px;" allowfullscreen="" loading="lazy"
                referrerpolicy="no-referrer-when-downgrade"></iframe> <br>

        </div>

        <button @:click="showAlert()" id="donate">Donate Now</button>
    </div>
    <contact-form></contact-form>
</template>

<script>
import ContactForm from './ContactForm.vue';
export default {
    components: { ContactForm },
    methods: {
        showAlert() {
            alert('Thank you for donating to Parkers Pets Haven! - You will now be deducted $500.00 from your linked account');
        }


    }


}
</script>

<style scoped>
div {
    text-align: center;
    border: black 5px solid;
    margin: 20px;
    padding: 20px;
    border-radius: 50px;
}

p {
    color: rgb(204, 115, 81);
}

h1,
h2 {
    text-decoration: underline;
}

#donate {
    background-color: rgb(82, 120, 83);
    height: 30px;
    width: 50%;
    border-radius: 30px;
    color: white;
    text-shadow: 1px 1px 2px black, 0 0 25px rgb(238, 114, 20), 0 0 5px rgb(249, 232, 217);
}

.main {
    background-color: rgb(157, 171, 134);
}

.second {
    background-color: rgb(222, 215, 177);
}

.third {
    background-color: rgb(222, 215, 177);

}
</style>