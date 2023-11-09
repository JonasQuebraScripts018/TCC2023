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

function addReserva() {
    if ($("#dataI").val() != '' && $("#dataF").val() != '') {
        let dataReserva = $("#dataI").val() + ' 00:00:00 -0300';
        let dataFim = $("#dataF").val() + ' 00:00:00 -0300';
        let numLinhas = $("#listaReservas tbody tr").length + 1;
        let a = 1;
        $("#listaReservas")
            .prepend('<tr>' +
                '<td>' + new Date(dataReserva).toLocaleDateString() + '</td>' +
                '<td>' + new Date(dataFim).toLocaleDateString() + '</td>' +
                '</tr>');

        document.getElementById('dataI').value = '';
        document.getElementById('dataF').value = '';
        $('#novaReserva').modal('hide');



        let gambarra = dataReserva;
        let mes;
        let dia;
        while(new Date(gambarra) <= new Date(dataFim)){

            mes = new Date(gambarra).getMonth();
            dia = new Date(gambarra).getDate();

            $('#otoMeis'+a).removeClass("outro-mes");
            $('#otoMeis'+a).addClass("dia-pintado");

            new Date(gambarra).setDate(new Date(gambarra).getDate() + 1);
        }

        for(mesA; mesA <= mesb; mesA++){
            if(mesA == mesb){
                for(diaI; diaI <= diaF; diaI ++){
                    $('#dia'+diaI).addClass("dia-pintado");
                }
            }

            a++;
        }

    } else {
        alert("Data inválida");
    }
}