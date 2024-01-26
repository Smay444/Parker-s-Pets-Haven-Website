import axios from "axios";

export default {
    createPhoto(photo){
        const url = '/photos/add';
        return axios.post(url, photo);
    },
    getPhoto(photoId){
        const url = '/photos/' + photoId;
        return axios.get(url);
    },
    listPhotos(petId){
        const url = '/photos/pet/' + petId;
        return axios.get(url);
    },
    removePhoto(photoId){
        const url = '/photos/remove/' + photoId;
        return axios.delete(url);
    }
}

