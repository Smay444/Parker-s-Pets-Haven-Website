import axios from "axios"



export default {
    getAdoptablePets(){
        const url = 'pets/available';
        return axios.get(url);
    },
    getSuccessStories(){
        const url = 'success-stories'
        return axios.get(url);
    },
    createPetListing(pet){
        const url = 'pets/addNew';
        return axios.post(url, pet);
    },
    updatePetListing(pet, petId){
        const url = 'pets/'+ petId;
        return axios.put(url, pet);
    },
    deletePetListing(id){
        const url = 'pets/' + id;
        return axios.delete(url);
    },
    getAdoptablePetsWithPhotos(photoId){
        const url = 'pets/with-photos/${photoId}';
        return axios.get(url);
    },
    addPetPhoto(photo){
        const url = 'photos/add'
        return axios.post(url, photo)
    },
    getAdoptedPets(){
        const url = 'pets/adopted';
        return axios.get(url);
    },
}