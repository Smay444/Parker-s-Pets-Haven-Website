import axios from "axios";

export default{
    sendApproval(applicant){
        const url = '/email/approved'
        return axios.post(url, applicant)
    },
    sendDenial(applicant){
        const url = '/email/denied'
        return axios.post(url, applicant)
    }
}