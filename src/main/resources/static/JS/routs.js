function controleRotasGet(url){
    switch(url){
        case "/logout":
            gerarSwal(url);
            break;
        case "/cadastro/notebooks":
            $.get(url,function(data){
                $("#mainFrame").html(data);
                $("#enviar").click(cadastrarNotebooks);
            });
            break;
        case "/cadastro":
            $.get(url,function(data){
                $("#mainFrame").html(data);
                $("#enviar").click(cadastrarUsuario);
            });
            break;
        case "/hominha":
                    $.get(url,function(data){
                        $("#mainFrame").html(data);
                        createCalendar(new Date());
                        $('.id-referer').click(getReservaDateRange);
                        $(".id-referer-r").click(DisPintaDia);
                        $("#next-month").click(function(){
                                dataCalendario.setMonth(dataCalendario.getMonth()+1);
                                setDateListaAtividades(dataCalendario);
                                createCalendar(dataCalendario);
                        });
                        $("#previous-month").click(function(){
                            dataCalendario.setMonth(dataCalendario.getMonth()-1);
                            $("#ano").text(dataCalendario.getYear());
                            setDateListaAtividades(dataCalendario);
                            createCalendar(dataCalendario);
                        });
                        $("#enviarCronograma").click(trySaveInTheBank);
                        $("#excluirCronograma").click(deletaCronograma);
                    });

                    break;
        case "/Perfil":
            $.get(url,function(data){
                    $("#mainFrame").html(data);
                    $("#salvarEditSenha").click(editSenha);
                    $("#salvarEdit").click(editEmail);
                    $("#recuperacao").click(recuperaSenha);
            });
        break;
        default:
            $.get(url,function(data){
                $("#mainFrame").html(data);
            });
    }
}