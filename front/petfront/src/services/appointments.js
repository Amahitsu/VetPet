import axios from 'axios';

async function listAvailableSlotsByDate(date) {
    try {
        const response = await axios.get(`http://localhost:8080/api/v1/appointments/available/${date}`);
        return response.data.data;
    } catch (error) {
        console.log("Erro ao listar horários disponíveis");
        console.error(error);
    }
}


export {
    listAvailableSlotsByDate,
}