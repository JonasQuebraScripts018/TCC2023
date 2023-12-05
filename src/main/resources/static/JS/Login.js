$("#enviarLogin").click(Login);

$("#cpf").keyup(function(event){
    if (event.key === "Enter") {
        $("#senha").focus();
    }
});

$("#senha").keyup(function(event){
    if (event.key === "Enter") {
        Login();
    }
});

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
                alert("Login efetuado com sucesso!")
                window.location.href="/home";
            }else{
                alert("Login Invalido, revise os dados inseridos!");
            }
        },
        error: function (){
            alert(data);
        }
    });
}