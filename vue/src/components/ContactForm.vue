<template>
  
<form v-on:submit.prevent="sendEmail($event)" name="contact">
    
    <div class="main">
        <div class="second">
        <h2>Send us an email!</h2>
        <h5 v-on:click="copyToClipboard()">parkerspets.shelter@gmail.com</h5>
        <label>Name </label>
            <input class="text" type="text" v-model="templateParams.from_name" />
    
        <label>Email </label>
            <input class="text" type="text" v-model="templateParams.reply_to" />
    
        <label>Message </label>
            <textarea class="message" v-model="templateParams.message" maxlength="500"></textarea>
            <button  id="send">Send</button>
        </div>
    </div>
</form>
</template>

<script>
import emailjs from '@emailjs/browser'
export default {
    data(){
        return{
            templateParams: {
                from_name: '',
                reply_to: '',
                message: ''
            }
        }
    },
    methods: {
  copyToClipboard() {
    const textToCopy = this.$el.querySelector('h5').textContent; // Get text from the clicked element
    navigator.clipboard.writeText(textToCopy)
      .then(() => {
        alert('Text copied to clipboard!'); // Optional success message
      })
      .catch(err => {
        console.error('Failed to copy text: ', err);
      });
  },
  sendEmail(e){
    emailjs.send('service_pvg6gb8', 'template_a6agzxf', this.templateParams,'qRLHw_XqOYBJpIztO')
    .then(
        (response) => {
            alert('success')
        }
    )
  }
}
}
</script>

<style scoped>
div{
    display: flex;
    flex-direction: column; 
    align-items: center;
    border: black 5px solid;
    margin: 20px;
    padding: 20px;
    border-radius: 50px;
}
p{
    color: rgb(167, 80, 14);
 
}

h1, h2{
    text-decoration: underline;
}
h2{
    padding: 0px;
    margin: 0px;
}
.message{
  width: 20vw;
  height: 10vh;
  text-align: left;
  resize: none;
  overflow: auto;
}
.main{
    background-color: rgb(157, 171, 134);
}
.second{
    background-color: rgb(222, 215, 177);
}
button{
    margin: 10px;
}
.text{
    height: 30px;
    width: 390px;
}
#send{
    display: inline-block;
     padding: 12px 24px;
     margin-top: 20px;
     background:linear-gradient(to right, #e74c3c, #c0392b);
     color: rgb(222, 215, 177);
     text-decoration: none;
     border-radius: 5px;
     transition: background-color 0.4s ease;
     border-radius: 25px;
     width: 200px;
     font-size: 18px;
     font-weight: bold;
}

</style>