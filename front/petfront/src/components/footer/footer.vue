<template>
    <footer class="colorFooter text-light py-3">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h6>Contato</h6>
                    <ul class="list-unstyled">
                        <li>
                            <p>E-mail: suporte@vetpet.com</p>
                        </li>
                        <li>
                            <p>Telefone: (47) 99263-3887</p>
                        </li>
                        <li>
                            <p>Endereço: Av. Brasil, 610 - Ponta Aguda, Blumenau - SC, 89050-000</p>
                        </li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h6>Links Úteis</h6>
                    <ul class="list-unstyled">
                        <li><a href="#">Termos de Uso</a></li>
                        <li><a href="#">Política de Privacidade</a></li>
                        <li><a href="#">FAQ</a></li>
                    </ul>
                </div>
                <div class="col-md-3">
                    <h6>Redes Sociais</h6>
                    <ul class="list-unstyled">
                        <li><a href="#"><i class="bi bi-facebook"></i> Facebook</a></li>
                        <li><a href="#"><i class="bi bi-twitter"></i> Twitter</a></li>
                        <li><a href="#"><i class="bi bi-instagram"></i> Instagram</a></li>
                    </ul>
                </div>
                <div class="col-md-3 text-md-end">
                    <p class="mb-0">© 2024 Empresa. Todos os direitos reservados.</p>
                </div>
            </div>
        </div>
    </footer>
</template>
<script>
export default {
    name: 'FooterComponent',
}
</script>

<style>
.colorFooter {
    background-color: #1e2627;
    bottom: 0%;
}
</style>