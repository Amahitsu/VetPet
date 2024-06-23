import axios from 'axios';

async function listAnimals() {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/animals");
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar animais");
        console.error(error);
    }
}
    async function findAnimalsByCustomer(customerId) {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/animals", {
                params: { customerId }
            })
            return response.data.data;
        } catch (error) {
            console.log("Erro ao buscar animais de um bliente");
            console.error(error);
        }
    }


    export {
        listAnimals,
        findAnimalsByCustomer
    }