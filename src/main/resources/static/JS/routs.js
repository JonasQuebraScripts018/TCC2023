function controleRotasGet(url){
    switch(url){
        case "/logout":
            gerarSwal(url);
            break;
        case "/cadastro/notebooks":
            $.get(url,function(data){
                $(".container").html(data);
                $("#enviar").click(cadastrarNotebooks);
            });
            break;
        case "/cadastro":
            $.get(url,function(data){
                $(".container").html(data);
                $("#enviar").click(cadastrarUsuario);
            });
            break;
        case "/home":
                    $.get(url,function(data){
                        $(".container").html(data);
                    });
                    break;
        case "/edit/usuario":
                            $.get(url,function(data){
                                $(".container").html(data);
                                $("#salvar").click(salvarPerfil);
                            });
                            break;
        default:
            $.get(url,function(data){
                $(".container").html(data);
            });
    }
}