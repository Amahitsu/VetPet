import axios from 'axios';

async function listWorkers() {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/worker");
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar funcionários");
        console.error(error);
    }
}

    export {
        listWorkers,
    }