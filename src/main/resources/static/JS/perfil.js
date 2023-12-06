$("#salvarEditSenha").click(editSenha);
$("#salvarEdit").click(editEmail);

    function editEmail(){
        let nome = $("#nome").val();
        let idade = $("#idade").val();
        let emailAtual = $("#email").val();
        let emailNovo = $("#emailNovo").val();
        let cpf = $("#cpf").val();
        let senha = $("#senha").val();

        $.ajax({
            type: "POST",
            url: "/email",
            data:{
                nome: nome,
                idade: idade,
                emailNovo: emailNovo,
                emailAtual: emailAtual,
                cpf: cpf,
                senha: senha,
            },
            success: function (data){
                if(data){
                    alert("Edição realizada com sucesso");
                }else{
                    alert(data.mensagem);
                }
            },
            error: function (){
                alert("Erro ao editar");
            }
        });
    }

    function editSenha(){
            let nome = $("#nomeSenha").val();
            let idade = $("#idadeSenha").val();
            let email = $("#emailSenha").val();
            let cpf = $("#cpfSenha").val();
            let senhaAtual = $("#senhaAtual").val();
            let senhaNova = $("#senhaNova").val();
            let confSenha = $("#confSenha").val();

            $.ajax({
                type: "POST",
                url: "/senha",
                data:{
                    nome: nome,
                    idade: idade,
                    email: email,
                    cpf: cpf,
                    senhaAtual: senhaAtual,
                    senhaNova: senhaNova,
                    confSenha: confSenha,
                },
                success: function (data){
                    if(data){
                        alert("Edição realizada com sucesso");
                    }else{
                        alert(data.mensagem);
                    }
                },
                error: function (){
                    alert("Erro ao editar");
                }
            });
    }