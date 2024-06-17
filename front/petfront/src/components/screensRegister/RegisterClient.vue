<template>
    <section>
        <form>
            <h2 v-if="!customerId">Cadastro de Cliente</h2>
            <h2 v-if="customerId">Edição de Cliente</h2>

            <div class="row">
                <div :class="[customerId ? 'col-md-6' : 'col-md-12']">
                    <div class="row mt-3">
                        <div class="col-md-9">
                            <label for="inputName" class="form-label">Nome Completo</label>
                            <div class="d-flex">
                                <input type="text" class="form-control" placeholder="Digite Nome Completo"
                                    maxlength="45" aria-label="Nome Completo" v-model="name" required>
                                <div class="col-md-3 d-flex align-itens-center gap-3">
                                    <div class="form-check md-3">
                                        <input class="form-check-input single-checkbox" name="statusClient" type="radio" value=""
                                            id="flexCheckDefault" checked>
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Ativo
                                        </label>
                                    </div>
                                    <div class="form-check md-3">
                                        <input class="form-check-input single-checkbox" name="statusClient" type="radio" value=""
                                            id="flexCheckChecked">
                                        <label class="form-check-label" for="flexCheckChecked">
                                            Inativo
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-4">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" id="cpf" @input="formatCpf" maxlength="14" required
                                placeholder="xxx.xxx.xxx-xx" v-model="cpf">
                        </div>
                        <div class="col-md-8">
                            <label for="inputEmail4" class="form-label">Email</label>
                            <input type="email" class="form-control" maxlength="150" id="inputEmail4" v-model="email"
                                placeholder="E-mail">
                        </div>
                        <div class="col-md-4">
                            <label for="cel" class="form-label">Celular</label>
                            <input type="tel" class="form-control" id="cel" maxlength="15" required
                                placeholder="(xx) xxxxx-xxxx" v-model="phone" @input="formatPhone">
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-12">
                            <h4>Endereço</h4>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" class="form-control" id="cep" required placeholder="xxxxx-xxx"
                                v-model="cep" maxlength="9" @input="formatCep">
                        </div>
                        <div class="col-md-8">
                            <label for="street" class="form-label">Rua</label>
                            <input type="text" class="form-control" id="inputStreet" v-model="street" required>
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-4">
                            <label for="inputState" class="form-label">Número</label>
                            <input type="text" class="form-control" id="inputZip" v-model="numberStreet">
                        </div>
                        <div class="col-md-8">
                            <label for="inputZip" class="form-label">Complemento</label>
                            <input type="text" class="form-control" id="inputZip" v-model="complement">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-4">
                            <label for="inputCity" class="form-label">Estado</label>
                            <input type="text" class="form-control" id="inputCity" v-model="state" required>
                        </div>
                        <div :class="[customerId ? 'col-md-4' : 'col-md-6']">
                            <label for="inputState" class="form-label">Cidade</label>
                            <input type="text" class="form-control" id="inputZip" v-model="city" required>
                        </div>

                        <div :class="[customerId ? 'col-md-4' : 'col-md-2']">
                            <label for="inputZip" class="form-label">Bairro</label>
                            <input type="text" class="form-control" id="inputZip" v-model="neighborhood" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-6" v-if="customerId">
                    <ListPetClient ref="listPetClient" />
                </div>
            </div>

            <div class="row mt-3">
                <div class="col-12 d-flex justify-content-end mt-4">
                    <button type="button" class="btn btn-secondary me-2" @click="goToList">Cancelar</button>
                    <button type="button" class="btn btn-primary" @click="saveCustomer">Salvar</button>
                </div>
            </div>
        </form>
    </section>
    <ModalWarning :modalText="modalMessage" id="modal" />
</template>

<script>
import axios from 'axios';
import { getAddressByCep } from '../../services/getAddressByCep.vue';
import ListPetClient from '../screenList/ListPetClient.vue';
import ModalWarning from '../screenMessage/ModalWarning.vue';

export default {
    components: {
        ModalWarning,
        ListPetClient
    },
    props: {
        id: Number
    },
    data() {
        return {
            customerId: null,
            name: '',
            cpf: '',
            email: '',
            phone: '',
            cep: '',
            street: '',
            numberStreet: '',
            complement: '',
            state: '',
            city: '',
            neighborhood: '',
            customerPets: [],
            modalMessage: ''
        };
    },
    mounted() {
        this.customerId = this.$router.currentRoute.value.params.customerId;
        this.loadCustomer(this.customerId);
    },
    methods: {
        async getAddressData(cepValue) {
            try {
                return await getAddressByCep(cepValue.replace('-', ''));
            } catch (error) {
                console.error('Erro ao buscar o endereço:', error);
                return null;
            }
        },
        async formatCep(event) {
            let cepValue = event.target.value.replace(/\D/g, '');
            if (cepValue.length > 5) {
                cepValue = cepValue.slice(0, 5) + '-' + cepValue.slice(5, 8);
            }
            this.cep = cepValue;

            if (this.cep.length === 9) {
                const address = await this.getAddressData(cep.value);
                if (address) {
                    this.street = address.logradouro;
                    this.neighborhood = address.bairro;
                    this.city = address.localidade;
                    this.state = address.uf;
                }
            }
        },
        async formatCpf(event) {
            let cpfValue = event.target.value.replace(/\D/g, '');

            if (cpfValue.length > 3) {
                cpfValue = cpfValue.slice(0, 3) + '.' + cpfValue.slice(3);
            }
            if (cpfValue.length > 7) {
                cpfValue = cpfValue.slice(0, 7) + '.' + cpfValue.slice(7);
            }
            if (cpfValue.length > 11) {
                cpfValue = cpfValue.slice(0, 11) + '-' + cpfValue.slice(11);
            }

            this.cpf = cpfValue;
        },
        async formatPhone(event) {
            let phoneNumber = event.target.value.replace(/\D/g, '');

            if (phoneNumber.length > 0) {
                phoneNumber = '(' + phoneNumber;
            }
            if (phoneNumber.length > 3) {
                phoneNumber = phoneNumber.slice(0, 3) + ') ' + phoneNumber.slice(3);
            }
            if (phoneNumber.length > 10) {
                phoneNumber = phoneNumber.slice(0, 10) + '-' + phoneNumber.slice(10);
            }

            this.phone = phoneNumber;
        },
        saveCustomer() {
            const address = `cep: ${this.cep}, rua: ${this.street}, numero: ${this.numberStreet}, complemento: ${this.complement}, bairro: ${this.neighborhood}, cidade: ${this.city}, estado: ${this.state}`;
            const data = {
                name: this.name,
                cpf: this.cpf,
                phone: this.phone,
                email: this.email,
                address: address
            };

            if (this.customerId)
                this.editCustomer(this.customerId, data)
            else
                this.createCustomer(data)
        },
        createCustomer(data) {
            axios.post("http://localhost:8080/api/v1/customers", data)
                .then(response => {
                    this.goToList();
                })
                .catch(error => {
                    console.error('Erro ao criar cliente:', error);
                });
        },
        editCustomer(customerId, data) {
            if (!customerId)
                return;

            axios.put(`http://localhost:8080/api/v1/customers/${customerId}`, data)
                .then(response => {
                    this.goToList();
                })
                .catch(error => {
                    console.error('Erro ao editar cliente:', error);
                });
        },
        goToList() {
            this.$router.push({ path: `/clientes` });
        },
        loadCustomer(customerId) {
            if (!customerId)
                return;

            axios.get(`http://localhost:8080/api/v1/customers/${customerId}`)
                .then(response => {
                    let data = response.data.data;
                    let address = this.parseAddress(data.address);

                    this.name = data.name;
                    this.cpf = data.cpf;
                    this.email = data.email;
                    this.phone = data.phone;

                    this.cep = address.cep || '';
                    this.street = address.rua || '';
                    this.numberStreet = address.numero || '';
                    this.complement = address.complemento || '';
                    this.state = address.estado || '';
                    this.city = address.cidade || '';
                    this.neighborhood = address.bairro || '';

                    this.loadCustomerPets(this.customerId);
                })
                .catch(error => {
                    console.error('Erro ao criar cliente:', error);
                });
        },
        loadCustomerPets(customerId) {
            this.$refs.listPetClient.loadCustomerPets(customerId);
        },
        parseAddress(addressString) {
            const parts = addressString.split(', ');

            const address = {};

            parts.forEach(part => {
                const [key, value] = part.split(': ');

                if (value)
                    address[key.trim()] = value.trim();
            });

            return address;
        }

    }
}
</script>