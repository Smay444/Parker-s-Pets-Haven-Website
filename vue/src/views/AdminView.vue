
<template>
    <div  class="admin-container" v-if="$store.state.user.authorities && ($store.state.user.authorities[0].name === 'ROLE_ADMIN' ) ">

        <div>
            <VolunteerTable></VolunteerTable>
        </div>

        <div>
    
            <new-pet-form></new-pet-form>
           
            <add-photo-form-vue></add-photo-form-vue>
        </div>

        <div>

            <UserTable></UserTable>
        </div>

        <!--second table to VIEW who the admins are - admin only status-->

    </div>
</template>

<script>
import NewPetForm from '../components/NewPetForm.vue'
import VolunteerTable from '../components/VolunteerTable.vue'
import AddPhotoFormVue from '../components/AddPhotoForm.vue'
import UserTable from '../components/UserTable.vue'

export default {
    components: {
        NewPetForm,
        VolunteerTable,
        AddPhotoFormVue,
        UserTable
    },
    data() {
        return {
            users: [],
           
        }
    },
    // created(){
    //     if (this.$store.state.user.role !== 'ROLE_ADMIN') {
    //         this.$router.push('/');
    //     }
    // }

}
</script>

<style>
.admin-container {
    display: flex;
    justify-content: space-evenly;
    flex-wrap: wrap;

}
</style>