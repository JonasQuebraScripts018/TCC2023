$('a').click(function(event){
    if(!$(this).hasClass('dropdown-toggle')){
        event.preventDefault();
        if(!$(this).hasClass('btn')){
            $('a').removeClass('active disabled');
            $(this).addClass('active disabled');
        }
        controleRotasGet($(this).attr("href"));
    }
});

$('.navbar-brand').off('click');

function gerarSwal(urlSucesso){
    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success me-2',
            cancelButton: 'btn btn-danger ms-2'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: 'Sair?',
        text: "Você realmente deseja sair da aplicação?",
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '<i class="fa-solid fa-thumbs-up"></i> Sim!',
        cancelButtonText: '<i class="fa-solid fa-thumbs-down"></i> Não!',
        reverseButtons: false
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href=urlSucesso;
        }
    });
}

function alertaSucesso(mensagem){
    Swal.fire({
        position: 'top-end',
        toast: true,
        icon: 'success',
        title: mensagem,
        showConfirmButton: false,
        timer: 2000
    });
}

$("#enviarCronograma").click(trySaveInTheBank);

function trySaveInTheBank(){
    let dataini = $("#dataini").val();
    let datafini = $("#datafini").val();

    if (dataini > datafini) {
            alert("A Data Inicial não pode ser despois à Data Final.");
            return;
    }

    $.ajax({
        type: "POST",
        url: "/home",
        data: {
            dataini: dataini,
            datafini: datafini,
        },
        success: function (data){
            if(data.sucesso){
                PintaDia(dataini, datafini, data.id);
            }
            alert(data.mensagem);
        },
        error: function (){
            alert("Deu Ruim");
        }
    });
}

function CriarCronograma(dataini, datafini, id) {
    let startDate = new Date(dataini);
    let endDate = new Date(datafini);

    let numLinhas = $("#listaReservas tbody tr").length + 1;
    $("#listaReservas").prepend('<tr>' +
        '<td id="dataini'+id+'">' + startDate.toLocaleDateString() +' '+ startDate.toLocaleTimeString() + '</td>' +
        '<td id="datafini'+id+'">' + endDate.toLocaleDateString() + ' ' + endDate.toLocaleTimeString() + '</td>' +
        '<td> <button id="'+id+'" class="btn btn-sm btn-primary id-referer">Criar</button>' +
        '<td> <button id="r'+id+'" class="btn btn-sm btn-danger id-referer-r">Remover</button>' +
        '</tr>');
    $('#novaReserva').modal('hide');
    $('#'+id).click(PintaDia2);
    $('#r'+id).click(DisPintaDia);
}

function PintaDia(dataini, datafini, id) {
    let startDate = new Date(dataini);
    let endDate = new Date(datafini);

    let currentDate = new Date(startDate);

    while (currentDate <= endDate) {
        let mes = currentDate.getMonth();
        let dia = currentDate.getDate();

        $('#m' + mes + 'd' + dia).removeClass("outro-mes");
        $('#m' + mes + 'd' + dia).addClass("dia-pintado");

        currentDate.setDate(currentDate.getDate() + 1);
    }

    CriarCronograma(dataini, datafini, id);
}

function PintaDia2(dataini, datafini) {
    let startDate = new Date(dataini);
    let endDate = new Date(datafini);

    let currentDate = new Date(startDate);

    while (currentDate <= endDate) {
        let mes = currentDate.getMonth();
        let dia = currentDate.getDate();

        $('#m' + mes + 'd' + dia).removeClass("outro-mes");
        $('#m' + mes + 'd' + dia).addClass("dia-pintado");

        currentDate.setDate(currentDate.getDate() + 1);
    }
}

$('.id-referer').click(getReservaDateRange);

function getReservaDateRange() {
    let id = $(this).attr("id");

    // Obter as datas de início e fim com base no ID
    let dataIniElement = $("#dataini" + id);
    let dataFimElement = $("#datafini" + id);

    // Obter os valores de texto das datas
    let dataIniText = dataIniElement.text();
    let dataFimText = dataFimElement.text();

    // Dividir as datas de início e fim em dia, mês e ano
    let partesDataIni = dataIniText.split('/');
    let partesDataFim = dataFimText.split('/');

    // Verificar se as datas possuem três partes (dia, mês e ano)
    if (partesDataIni.length !== 3 || partesDataFim.length !== 3) {
        return;
    }

    let diaIni = partesDataIni[0];
    let mesIni = partesDataIni[1];
    let anoIni = partesDataIni[2];

    let diaFim = partesDataFim[0];
    let mesFim = partesDataFim[1];
    let anoFim = partesDataFim[2];

    // Formatar as datas americanas de início e fim
    let dataAmericanaIni = `${mesIni}/${diaIni}/${anoIni}`;
    let dataAmericanaFim = `${mesFim}/${diaFim}/${anoFim}`;

    PintaDia2(dataAmericanaIni, dataAmericanaFim);
}

$(".id-referer-r").click(DisPintaDia);

function DisPintaDia() {
    let id = $(this).attr("id");

    id = id.substring(1);

    // Obter as datas de início e fim com base no ID
    let dataIniElement = $("#dataini" + id);
    let dataFimElement = $("#datafini" + id);

    // Obter os valores de texto das datas
    let dataIniText = dataIniElement.text();
    let dataFimText = dataFimElement.text();

    // Dividir as datas de início e fim em dia, mês e ano
    let partesDataIni = dataIniText.split('/');
    let partesDataFim = dataFimText.split('/');

    // Verificar se as datas possuem três partes (dia, mês e ano)
    if (partesDataIni.length !== 3 || partesDataFim.length !== 3) {
        return;
    }

    let diaIni = partesDataIni[0];
    let mesIni = partesDataIni[1];
    let anoIni = partesDataIni[2];

    let diaFim = partesDataFim[0];
    let mesFim = partesDataFim[1];
    let anoFim = partesDataFim[2];

    // Formatar as datas americanas de início e fim
    let dataAmericanaIni = `${mesIni}/${diaIni}/${anoIni}`;
    let dataAmericanaFim = `${mesFim}/${diaFim}/${anoFim}`;

     let currentDate = new Date(dataAmericanaIni);

    while (new Date(currentDate) <= new Date(dataAmericanaFim)) {
        let mes = currentDate.getMonth();
        let dia = currentDate.getDate();

        $('#m' + mes + 'd' + dia).removeClass("dia-pintado");

        currentDate.setDate(currentDate.getDate() + 1);
    }
}