import axios from 'axios';

async function listBreeds() {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/breeds`);
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar as raças.");
        console.error(error);
    }
}

async function findBreedsBySpecies(id_specie) {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/breeds", {
            params: { id_specie }
        })
        return response.data.data;
    } catch (error) {
        console.log("Erro ao buscar raças de uma espécie");
        console.error(error);
    }
}

    export {
    findBreedsBySpecies, listBreeds
};

