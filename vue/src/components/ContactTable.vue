<template>
    <div class="container">
        <h2>Volunteer Contact List</h2>
        <div class="table">
            <table id="volunteer-contact">
                <thead>
                    <tr>
                        <th>Name:</th>
                        <th>Email:</th>
                        <th>Phone Number:</th>
                        <th>Role:</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="search">
                        <td>
                            <input v-model="filter.name" id="nameFilter" type="text">
                        </td>
                        <td>
                            <input v-model="filter.email" id="emailFilter" type="text">
                        </td>
                        <td>
                            <input v-model="filter.phone" id="phoneFilter" type="text">
                        </td>
                        <td>
                          
                        </td>

                    </tr>
                    <tr v-for="volunteer in filteredList" v-bind:key="volunteer.userId" v-bind:volunteer="volunteer">
                        <td>{{ volunteer.appName }}</td>
                        <td>{{ volunteer.appEmail }}</td>
                        <td>{{ volunteer.appPhoneNumber }}</td>
                        <td>{{ getRole(volunteer.userId) }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import ApplicationService from '../services/ApplicationService';
export default {
    data() {
        return {
            selectedVolunteerIds: [],
            filter: {
                name: "",
                email: "",
                phone: "",

            },

        }
    },
    created() {
        this.refresh()
    },
    computed: {
        volunteers() {
            return this.$store.state.volunteers;
        },
        users() {
            return this.$store.state.users;
        },
        filteredList() {
            let volunteers = this.$store.state.volunteers;
            let filteredVolunteers = volunteers.filter((volunteer) =>
                volunteer.status.toLowerCase().includes("approved")
            )

            if (this.filter.name != "") {
                filteredVolunteers = filteredVolunteers.filter((volunteer) =>
                    volunteer.appName.toLowerCase().includes(this.filter.name.toLowerCase())
                )
            }

            if (this.filter.email != "") {
                filteredVolunteers = filteredVolunteers.filter((volunteer) =>
                    volunteer.appEmail.toLowerCase().includes(this.filter.email.toLowerCase())
                )
            }

            if (this.filter.phone != "") {
                filteredVolunteers = filteredVolunteers.filter((volunteer) =>
                    volunteer.appPhoneNumber.includes(this.filter.phone)
                )
            }
   

            return filteredVolunteers;
        },

    },
    methods: {
        refresh() {
            ApplicationService.getApplications().then(
                (response) => {
                    this.$store.commit("SET_VOLUNTEERS", response.data)

                    ApplicationService.getUsers().then(
                        (response) => {
                            this.$store.commit("SET_USERS", response.data)
                        }
                    )
                }
            )

        },
        getRole(userId) {
            let user = this.users.find((user) =>
                user.id == userId
            )
            console.log(user)
            if (user) {
                return user.authorities[0].name;

            }

            return "Role Not Found"
        }

    }
}
</script>

<style>
tr:nth-child(even){
    background-color: #c2c2c2;
}
tr:nth-child(odd){
    background-color: #ffffff;
}
.container{
    background-color: rgb(157, 171, 134, 0.7);
    border: 2px solid black;
    border-radius: 5px;
    height: 320px;
    width: 775px;

}

.table {
    overflow-y: scroll;
    max-height: 280px;
    display: flex; 
    justify-content: center;
    border: 2px solid black;
}

table {
    border-collapse: collapse;
    width: 100%;
}

th, td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

table thead {
    top: 0%;
    position: sticky;
    background: white;
}

.search {
    top: 11%;
    position: sticky;
    background: white;
}

h2 {
    text-align: center;
    margin: 10px;
}

#volunteer-contact{
height: 275px;
}
</style>