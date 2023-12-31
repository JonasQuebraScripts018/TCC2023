let dataCalendario = new Date();

$(document).ready(function() {
    setDateListaAtividades(dataCalendario);
    createCalendar(dataCalendario);


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

});

function setDateListaAtividades(data){
    $("#data").text(data.toLocaleDateString()).trigger("change");
}

function createCalendar(data){

    let dataAtual = new Date();
    dataAtual.setHours(0,0,0,0);
    data.setHours(0,0,0,0);
    let isDataAtual = (data.getTime() == dataAtual.getTime());
    let diaAtual = 1;

    if(isDataAtual){
        diaAtual = data.getDate();
    }

    $("#calendarBody").text("");
    const nomeMeses = ["Janeiro","Fevereiro","Março","Abril","Maio","Junho",
                        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"];

    $("#mes").text(nomeMeses[data.getMonth()]);
    $("#ano").text(dataCalendario.getYear()+1900);

    let primeiroDiaMes = new Date(data.setDate(1));
    let diaDaSemana = primeiroDiaMes.getDay();

    let ultimoDiaMes = new Date(data.getYear()+1900,data.getMonth()+1,0);

    let a = 0;

    let linha = 1

    //cria dias do mês anterior.
    let dia = new Date(primeiroDiaMes);
    dia.setDate((diaDaSemana*-1))
    $("#calendarBody").append('<tr id="linha1"></tr>');
    for(let i = 0; i < diaDaSemana; i++){
        a++;
        dia.setDate(dia.getDate()+1);
        $("#linha1").append('<td id="m'+dia.getMonth()+'d'+dia.getDate()+'" class="outro-mes">'+dia.getDate()+'</td>');
    }

    //cria dias do mês atual
    for(let i = 1; i <= ultimoDiaMes.getDate(); i++){
        primeiroDiaMes.setDate(i);
        $("#linha"+linha).append('<td id="m'+primeiroDiaMes.getMonth()+'d'+primeiroDiaMes.getDate()+'" '+(primeiroDiaMes.getDate() == diaAtual ? 'class="dia-ativo"': '' )+'>'+
            primeiroDiaMes.getDate()+'</td>');

        if(primeiroDiaMes.getDay() === 6){
            linha++;
            $("#calendarBody").append('<tr id="linha'+linha+'"></tr>');
        }
    }



    //cria dias do próximo mês
    diaDaSemana = ultimoDiaMes.getDay();
    for(let i = 6; i > diaDaSemana; i--){
        a++;
        let dataTeste = new Date(data.getYear()+1900,data.getMonth()+1,(7-i));
        $("#linha"+linha).append('<td id="m'+dataTeste.getMonth()+'d'+dataTeste.getDate()+'" class="outro-mes">0'+(7-i)+'</td>');
    }
}