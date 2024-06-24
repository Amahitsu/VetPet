import axios from 'axios';

async function listServices() {
    try {
        const response = await axios.get("http://localhost:8080/api/v1/services");
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar serviços");
        console.error(error);
    }
}

    export {
        listServices,
    }