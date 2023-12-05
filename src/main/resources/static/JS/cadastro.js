$("#enviarCadastro").click(cadastro);

    function cadastro(){
        let nome = $("#nome").val();
        let idade = $("#idade").val();
        let email = $("#email").val();
        let cpf = $("#cpf").val();
        let senha = $("#senha").val();

        $.ajax({
            type: "POST",
            url: "/cadastro",
            data:{
                nome: nome,
                idade: idade,
                email: email,
                cpf: cpf,
                senha: senha,
            },
            success: function (data){
                if(data){
                    alert("Cadastro realizado com sucesso");
                    window.location.href="/";
                }else{
                    alert(data.mensagem);
                }
            },
            error: function (){
                alert("Erro ao cadastrar");
            }
        });
    }