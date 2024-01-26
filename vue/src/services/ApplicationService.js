import axios from 'axios';



export default {

    createApplication(volunteer){
        const url = "/Applications/apply/";
        return axios.post(url, volunteer);
    },

    getApplications(){
        const url = '/Applications'
        return axios.get(url)
    },

    deleteApplication(id){
        const url = 'delete/' //THIS IS A PLACEHOLDER
        return axios.delete(id);
    },

    approveApplication(id, volunteer){
        const url = '/Applications/' + id + '/approve'
        return axios.put(url, volunteer)
    },
    makeAdmin(id, volunteer){
        const url = '/Applications/promote/' + id;
        return axios.put(url, volunteer)
    },
    rejectApplication(id, volunteer){
        const url = '/Applications/' + id + '/reject';
        return axios.put(url, volunteer)
    },
    getUsers(){
        const url = 'users'
        return axios.get(url)
    },
    getUserById(id){
        const url  = '/users/adopted/' + id;
        return axios.get(url)
    }

}