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

    $.ajax({
        type: "POST",
        url: "/home",
        data: {
            dataini: dataini,
            datafini: datafini,
        },
        success: function (data){
            PintaDia(dataini, datafini);
            alert("Deu bom");
        },
        error: function (){
            alert("Deu Ruim");
        }
    });
}

let a = 1;

function CriarCronograma(dataini, datafini) {
    let startDate = new Date(dataini + 'T00:00:00');
    let endDate = new Date(datafini + 'T00:00:00');

    let numLinhas = $("#listaReservas tbody tr").length + 1;
    $("#listaReservas").prepend('<tr>' +
        '<td>' + startDate.toLocaleDateString() + '</td>' +
        '<td>' + endDate.toLocaleDateString() + '</td>' +
        '<td> <button id="'+a+'" class="btn btn-primary" href="PintaDia('+dataini+', '+datafini+')">Criar</button>' +
        '</tr>');
    a++;
    $('#novaReserva').modal('hide');
}

function PintaDia(dataini, datafini) {
    let startDate = new Date(dataini + 'T00:00:00');
    let endDate = new Date(datafini + 'T00:00:00');

    let currentDate = new Date(startDate);

    while (currentDate <= endDate) {
        let mes = currentDate.getMonth();
        let dia = currentDate.getDate();

        $('#m' + mes + 'd' + dia).removeClass("outro-mes");
        $('#m' + mes + 'd' + dia).addClass("dia-pintado");

        currentDate.setDate(currentDate.getDate() + 1);
    }

    CriarCronograma(dataini, datafini);
}

function CarregarCronograma(dataini, datafini) {
    let startDate = new Date(dataini + 'T00:00:00');
    let endDate = new Date(datafini + 'T00:00:00');

    let numLinhas = $("#listaReservas tbody tr").length + 1;
    $("#listaReservas").prepend('<tr th:if="${usuario.id == cronograma.id_pessoa}" th:each="cronograma: ${cronogramas}">' +
        '<td th:text="${#temporals.format(cronograma.dataini, dd/mm/yyyy HH:mm)}"></td>' +
        '<td th:text="${#temporals.format(cronograma.datafini, dd/mm/yyyy HH:mm)}"></td>' +
        '<td> <button id="banco'+a+'" class="btn btn-primary" href="PintaDia('+dataini+', '+datafini+')">Criar</button>' +
        '</tr>');
    a++;
    $('#novaReserva').modal('hide');
}