$("#enviarCadastro").click(cadastro);

    $("#matricula").keyup(function(event){
            if(event.key === "Enter"){
               $("senha").focus();
            }
        });

    $("#senha").keyup(function(event){
        if(event.key === "Enter"){
            enviarLogin();
        }
    });

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
                    alert("Deu bom");
                }else{
                    alert("Deu ruim")
                }
            },
            error: function{
                alert("Deu muito ruim");
            }
        });
    }