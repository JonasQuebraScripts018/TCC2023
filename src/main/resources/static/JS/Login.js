$("#enviarLogin").click(Login);

function Login(){
    let cpf = $("#cpf").val();
    let senha = $("#senha").val();

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
        },
        error: function (){
            alert(data);
        }
    });
}