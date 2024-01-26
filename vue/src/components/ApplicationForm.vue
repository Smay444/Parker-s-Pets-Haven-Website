<template>
<h1>Please Enter Volunteer Details</h1>
<form v-on:submit.prevent="createApplication()" class="volunteer-form">
    <div class="form-group">
        <label for="volunteerName">Name:</label>
            <input type="text" v-model="volunteer.appName" id="volunteerName" class="form-input" />
    </div>
    <div class="form-group">
        <label for="volunteerPhoneNumber">Phone Number:</label>
            <input type="text" v-model="volunteer.appPhoneNumber" id="volunteerPhoneNumber" class="form-input" />
    </div>
     <div class="form-group">
        <label for="volunteerEmail">Email Address:</label>
            <input type="text" v-model="volunteer.appEmail" id="volunteerEmail" class="form-input" />
    </div>
    <input type="submit" class="submit-button" />
    
    <div class="links-container" v-if="$store.state.user.authorities && ($store.state.user.authorities[0].name === 'ROLE_VOLUNTEER' || $store.state.user.authorities[0].name === 'ROLE_ADMIN')">
        <div class="links" v-bind:to="{name: 'admin'}" >
        <div class="volunteer-table">
            <ContactTable></ContactTable>
        </div>
    </div>
    </div>
   
</form>    

</template>

<script>

import ApplicationService from '../services/ApplicationService.js'
import ContactTable from '../components/ContactTable.vue'
export default {
    components: {ContactTable},
    data(){
        return{
            volunteer:{}
        }
    },
    methods: {
        createApplication(){
            ApplicationService.createApplication(this.volunteer, this.$route.params.applicationId)
                        .then(
                            (response) => {
                                this.$router.push({name: "pets"})
                            }
                        )
            
        }
    },
};
</script>

<style>
.form-title {
    font-size: 24px;
    color:yellow;
    margin-bottom: 20px;
}

.volunteer-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
    }

.form-group {
    margin-bottom: 20px;
    text-align: center;
}

.form-label {
    font-size: 16px;
    color: #555;
    display: block;
    margin-bottom: 8px;
}

.form-input {
    width: 100%;
    padding: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 14px;
}

.submit-button {
    background-color: #4caf50;
    color: #fff;
    padding: 10px 15px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 16px;
}
.submit-button:hover {
    background-color: #45a049;
}

.links-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
}
.volunteer-table {
  text-align: center;  
}

.links {
    text-align: center;
}
</style>