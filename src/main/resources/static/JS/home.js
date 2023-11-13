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
            alert("Deu bom");
            PintaDia(dataini, datafini);
        },
        error: function (){
            alert("Deu Ruim");
        }
    });
}

function CriarCronograma(dataini,datafini) {
        let numLinhas = $("#listaReservas tbody tr").length + 1;
        $("#listaReservas").prepend('<tr>' +
                '<td>' + new Date(dataini).toLocaleDateString() + '</td>' +
                '<td>' + new Date(datafini).toLocaleDateString() + '</td>' +
                '</tr>');

        $('#novaReserva').modal('hide');
}

function PintaDia(dataini, datafini){
    let gambarra = new Date(dataini + '00:00:00 -0300');
    let mes;
    let dia;

    while(new Date(gambarra) <= new Date(dataini + '00:00:00 -0300')){
        mes = new Date(gambarra).getMonth();
        dia = new Date(gambarra).getDate();

        $('#m'+mes+'d'+dia).removeClass("outro-mes");
        $('#m'+mes+'d'+dia).addClass("dia-pintado");

        gambarra = new Date(gambarra).setDate(new Date(gambarra).getDate() + 1);
    }
    CriarCronograma(dataini, datafini);
}