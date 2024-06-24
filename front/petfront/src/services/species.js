import axios from 'axios';

async function listSpecies() {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/species");
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar espécies");
        console.error(error);
    }
}

    export {
    listSpecies
};
