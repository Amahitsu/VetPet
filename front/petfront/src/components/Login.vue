
<script>
  import Footer from './footer/footer.vue';

  export default {
    components: {
      Footer,
    },
  }
</script>

<template>
    <section class="vh-100 gradient-custom">
        <div class="container py-5 h-100">
            <div class="row d-flex justify-content-center align-items-center h-100">
                <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                    <div class="card bg-info text-white" style="border-radius: 1rem">
                        <div class="card-body p-5 text-center">
                            <div class="mb-md-5 mt-md-4 pb-5">
                                <h2 class="fw-bold mb-2">Bem vindo ao VetPet</h2>
                                <p class="text-bold-50 mb-5">
                                    Por favor, digite usuário e senha!
                                </p>

                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <label class="form-label" for="typeEmailX">Usuário</label>
                                    <input type="email" id="typeEmailX" class="form-control form-control-lg" />

                                </div>

                                <div data-mdb-input-init class="form-outline form-white mb-4">
                                    <label class="form-label" for="typePasswordX">Senha</label>
                                    <input type="password" id="typePasswordX" class="form-control form-control-lg" />
                                </div>

                                <RouterLink to = '/Cadastro/Cliente'>
                                <button data-mdb-button-init data-mdb-ripple-init
                                    class="btn btn-outline-dark btn-lg px-5" type="submit">
                                    Entrar
                                </button>
                            </RouterLink>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <Footer/>

    </section>
</template>

