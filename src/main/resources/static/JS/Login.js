$("#enviarLogin").click(Login);

    $("#cpf").keyup(function(event){
            if(event.key === "Enter"){
               $("senha").focus();
            }
        });

    $("#senha").keyup(function(event){
        if(event.key === "Enter"){
            enviarLogin();
        }
    });

function Login(){
    let cpf = $("#cpf").val();
    let senha = $("$senha").val();

    $.ajax({
        type: "POST",
        url: "/",
        data:{
            cpf: cpf,
            senha: senha,
        },
        success: function(data){
            if(data){
                window.location.href="/home";
            }else{
                alert(data);
            }
        }
        error: function (){
            alert("Deu muito ruim!!");
        }
    });
}