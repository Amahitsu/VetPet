
<template>
    <form>
        <div class="flex">
            <div class="col">
                <label for="Email">E-mail</label>
                <input type="text" id="Email" name="Email" />
            </div>

            <div class="col">
                <label for="Name">Nome</label>
                <input type="text" id="nome" name="nome" />
            </div>
        </div>

        <div class="flex">
            <div class="col">
                <label for="telefone">Telefone</label>
                <input type="tel" id="telefone" class="input-padrao" required placeholder="(xx) xxxxx-xxxx" />
            </div>

            <div class="col">
                <label for="DataNascimento">Data de Nascimento</label>
                <input type="date" id="dataNascimento" />
            </div>
        </div>

        <div class="col">
            <label for="Message">Mensagem</label>
            <textarea id="mensagem" name="mensagem" rows="5" cols="33">
        </textarea>
        </div>
    </form>
</template>

