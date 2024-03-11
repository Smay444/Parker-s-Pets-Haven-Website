<template>
    <div class="container">
        <div id="admin-portal">Volunteers/Admins</div>
        <div class="table">
            <table id="volunteers">
                <thead>
                    <tr>

                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Status</th>
                        <th>Approve</th>
                        <th>Reject</th>

                    </tr>
                </thead>
                <tbody>

                    <tr class="search">

                        <td>
                            <input v-model="filter.name" type="text" id="firstNameFilter">
                        </td>
                        <td>
                            <input v-model="filter.email" type="text" id="emailFilter">
                        </td>
                        <td>
                            <input v-model="filter.phone" type="text" id="phoneNumberFilter">
                        </td>
                        <td>
                            <select id="statusFilter" v-model="filter.status">
                                <option value>Show All</option>
                                <option value="pending">Pending</option>
                                <option value="Approved">Approved</option>
                                <option value="Rejected">Rejected</option>
                            </select>
                        </td>
                        <td>
                            <p></p>
                        </td>
                        <td>
                            <p></p>
                        </td>
                    </tr>

                    <tr v-for="volunteer in filteredList" v-bind:key="volunteer.userId" v-bind:volunteer="volunteer">

                        <td>{{ volunteer.appName }}</td>
                        <td>{{ volunteer.appEmail }}</td>
                        <td>{{ volunteer.appPhoneNumber }}</td>
                        <td>{{ volunteer.status }}</td>
                        <td>
                            <button v-on:click="approve(volunteer)" v-show="volunteer.status != 'Approved'">Approve</button>
                            <button id="promote" v-on:click="promote(volunteer.userId)"
                                v-show="volunteer.status == 'Approved' && !isHidden">Make Admin</button>
                        </td>
                        <td>
                            <button v-on:click="reject(volunteer)"
                                v-show="volunteer.status != 'Approved' && volunteer.status != 'Rejected'">Reject</button>

                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </div>
</template>

<script>
import ApplicationService from '../services/ApplicationService'
import MailService from '../services/MailService';
export default {

    data() {
        return {
            selectedVolunteerIds: [],
            filter: {
                name: "",
                email: "",
                phone: "",
                status: "",
            },
            isHidden: false
        }
    },

    created() {
        this.refresh();
    },
    computed: {
        volunteers() {
            return this.$store.state.volunteers;
        },
        filteredList() {
            let filteredVolunteers = this.$store.state.volunteers;
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
            if (this.filter.status != "") {
                filteredVolunteers = filteredVolunteers.filter((volunteer) =>
                    volunteer.status.includes(this.filter.status)
                )
            }

            return filteredVolunteers;
        }
    },
    methods: {

        //TODO: add functionality to grab id of selected volunteer
        delete() {
            ApplicationService.deleteApplication(this.selectedVolunteerIds).then(
                (response) => {
                    this.$router.push({ name: 'admin' })
                }
            )
        },
        approve(volunteer) {
            let id = volunteer.applicationId
            
            ApplicationService.approveApplication(id, volunteer).then(
                (response) => {
                    this.refresh();
                    MailService.sendApproval(volunteer).then(
                        (response) => {
                            alert(response.data)
                        } 
                        )
                }
            )
        },
        reject(volunteer) {
            let id = volunteer.applicationId
           
            ApplicationService.rejectApplication(id, volunteer).then(
                (response) => {
                    this.refresh();
                    MailService.sendDenial(volunteer).then(
                        (response) => {
                            alert(response.data)
                        }
                    )
                }
            )
        },
        refresh() {
            ApplicationService.getApplications().then(
                (response) => {
                    this.$store.commit("SET_VOLUNTEERS", response.data)
                }
            )
        },
        promote(id) {

            ApplicationService.makeAdmin(id).then(
                (response) => {
                    this.refresh();
                    alert("Promoted to Admin")
                }
            )
        }
    }
}
</script>

<style scoped>
tr:nth-child(even) {
    background-color: #e7c79c;
}

tr:nth-child(odd) {
    background-color: #ffffff;
}

.container {
    background-color: rgb(157, 171, 134, 0.7);
    border: 2px solid black;
    border-radius: 5px;
    height: 350px;
    width: 1000px;
    margin: 15px;
}

.table {
    overflow-y: scroll;
    max-height: 300px;

}

table {
    border-collapse: collapse;
    width: 100%;
}

th,
td {
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
    top: 0;
    position: sticky;
    background-color: #fff;
    z-index: 1;
}

#admin-portal {
    font-weight: bolder;
    margin: 5px;
    text-decoration: underline;
    font-size: 25px;
    color: rgb(0, 66, 37)
}
.table{
    border: 2px black solid;
}

#users{
    border: 2px black solid;
}
</style>