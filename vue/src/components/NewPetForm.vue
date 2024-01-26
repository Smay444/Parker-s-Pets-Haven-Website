<template>
    <div class="form" v-on:submit.prevent="addPet()">
        <form>
            <div class="add">Add/Update Pet</div>
            <div class="block">
                <div>
                    <label>Pet Name:</label>
                    <input class="input" v-model="pet.petName" type="text">
                </div>
                <div>
                    <label>Breed:</label>
                    <input class="input" v-model="pet.petBreed" type="text">
                </div>
                <div>
                    <label>Color:</label>
                    <input class="input" v-model="pet.petColor" type="text">
                </div>
                <div>
                    <label>Age in Years:</label>
                    <input class="input" v-model="pet.petAge" type="number">
                </div>
                <div class="radio">
                    <label for="available">Available</label>
                    <input name="isAvailable" type="radio" id="available" v-model="pet.available" value=true> |

                    <label for="unavailable">Unavailable</label>
                    <input name="isAvailable" type="radio" id="unavailable" v-model="pet.available" value=false>
                </div>

                <div>
                    <label>Weight:</label>
                    <input class="input" v-model="pet.petWeight" type="number">
                </div>
                <div>
                    <label>ZIP Code:</label>
                    <input class="input" v-model="pet.zipCode" type="number">
                </div>

                <div>
                    <label>City:</label>
                    <input class="input" v-model="pet.petCity" type="text">
                </div>
                <div>
                    <label>State:</label>
                    <input class="input" v-model="pet.petState" type="text">
                </div>
                <div>
                    <label class="desc-label">Description:</label>
                    <textarea class="desc" v-model="pet.petDescription" maxlength="500"></textarea>
                </div>
                <div>
                    Pet Id:<input class="input" type="number" v-model.number="pet.petId">
                </div>

                <div>
                    <label>Adopted by:</label>
                    <input class="input" v-model="pet.adoptedBy" type="text">
                </div>
                <input class="button" id="submit" type="submit" v-on:click.prevent="addPet()">
            </div>
        </form>
    </div>
</template>

<script>
import PetService from '../services/PetService.js'
export default {
    data() {
        return {
            pet: {}
        }
    },

    methods: {

        createPet() {
            PetService.createPetListing(this.pet, this.pet.petId).then(
                (response) => {
                    this.$router.push({ name: "pets" })
                }
            )
        },
        addPet() {
            if (!this.pet.petId) {
                this.createPet();
            } else {
                this.updatePet();
            }
        },
        updatePet() {
            PetService.updatePetListing(this.pet, this.pet.petId).then(
                (response) => {
                    this.$router.push({ name: 'pets' })
                }
            )
        }

    }


}

</script>

<style scoped>
div {
    margin: 5px;
}

div .form {
    display: flex;
    align-content: center;
    justify-content: center;
    border-style: solid;
    border-width: 2px;
    border-color: black;
    border-radius: 5px;
    background-color: rgb(157, 171, 134, 0.7);
    padding: 10px;
    margin: 2px;

}

div .form input {
    display: flex;
    align-content: center;
    justify-content: center;
}

.radio {
    display: flex;
    justify-content: center;
    padding: 2px;
    justify-content: space-evenly;
    margin: 10px;
}

input {
    margin: 2px;
}

.desc {
    text-align: left;
    resize: none;
    overflow: auto;
    height: 5vh;
    width: 300px;


}

.desc-label {
    display: flex;
    align-content: flex-start;
}

.add {
    display: flex;
    justify-content: center;
    align-content: center;
    font-weight: bolder;
    text-decoration: underline;
    margin-bottom: 10px;
    width: 325px;
    text-align: center;
    margin: 5px;
    font-size: 25px;
    color: rgb(0, 66, 37)
}

#submit {
    display: flex;
    text-align: center;
    padding: 12px 24px;
    margin-top: 20px;
    background: linear-gradient(to right, #e74c3c, #c0392b);
    color: rgb(222, 215, 177);
    text-decoration: none;
    border-radius: 5px;
    transition: background-color 0.4s ease;
    border-radius: 25px;
    width: 200px;
    font-size: 18px;
    font-weight: bold;
}

.input {
    width: 300px;
    height: 2vh;
}

.button {
    display: block;
    margin: 0 auto;
}


</style>