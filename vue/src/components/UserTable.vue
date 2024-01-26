<template>
    <div class="container">
        <div class="user-container">Users Directory</div>
        <div class="table">

            <table id="users">
                <thead>
                    <tr id="headers">

                        <th>User ID</th>
                        <th>Username</th>
                        <th>Name</th>
                        <th>Role</th>

                    </tr>
                </thead>
                <tbody>

                    <tr class="search">

                        <td>
                            <input v-model="filter.userId" type="text" id="userId">
                        </td>
                        <td>
                            <input v-model="filter.username" type="text" id="username">
                        </td>
                        <td>
                            <input v-model="filter.name" type="text" id="name">
                        </td>
                        <td>
                            <input v-model="filter.role" type="text" id="role">
                        </td>

                    </tr>

                    <tr v-for="user in filteredList" v-bind:key="user.userId" v-bind:user="user">

                        <td>{{ user.id }}</td>
                        <td>{{ user.username }}</td>
                        <td>{{ user.name }}</td>
                        <td>{{ user.authorities[0].name }}</td>

                    </tr>
                </tbody>
            </table>
        </div>

    </div>
</template>

<script>
import ApplicationService from '../services/ApplicationService'
export default {
    data() {
        return {
            selectedUserIds: [],
            filter: {
                userId: "",
                username: "",
                name: "",
                role: ""
            }
        }
    },
    created() {
        this.refresh();
    },
    computed: {
        users() {
            return this.$store.state.users;
        },
        filteredList() {
            let filteredUsers = this.$store.state.users;
            if (this.filter.userId != "") {
                filteredUsers = filteredUsers.filter((user) =>
                    user.id.includes(this.filter.userId)
                )
            }
            if (this.filter.username != "") {
                filteredUsers = filteredUsers.filter((user) =>
                    user.username.toLowerCase().includes(this.filter.username.toLowerCase())
                )
            }
            if (this.filter.name != "") {
                filteredUsers = filteredUsers.filter((user) =>
                    user.name.includes(this.filter.name)
                )
            }
            if (this.filter.role != "") {
                filteredUsers = filteredUsers.filter((user) =>
                    user.authorities.includes(this.filter.authorities)
                )
            }

            return filteredUsers;
        }
    },
    methods: {
        refresh() {
            ApplicationService.getUsers().then(
                (response) => {
                    this.$store.commit("SET_USERS", response.data)
                }
            )
        }
    }
}
</script>

<style scoped>
tr:nth-child(even) {
    background-color: #c2c2c2;
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

.user-container {
    font-weight: bolder;
    margin: 5px;
    text-decoration: underline;
    font-size: 25px;
    color: rgb(0, 66, 37)
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

#users {
    top: 0;
    position: sticky;
    background-color: #fff;
    z-index: 1;
    margin-left: 30px;
    margin-left: 30px;

}
</style>