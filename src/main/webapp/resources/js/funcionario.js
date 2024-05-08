function reportar(codigo) {
    Swal.fire({
        title: '¿Está seguro de que desea reportar este bien?',
        text: 'Esta acción no se puede deshacer',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#139EC8',
        cancelButtonColor: '#A8A8A8',
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        customClass: {
            popup: 'custom-modal'
        }
    }).then((result) => {
        if (result.isConfirmed) {
            reportObject(codigo);
        }
    });
}

function reportObject(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ReportObject", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha reportado el bien correctamente',
                icon: 'success',
                confirmButtonColor: '#139EC8'
            }).then(() => {
                // Después de reportar el bien, deshabilitar los enlaces correspondientes
                disableLinks(codigo);
            });
        }
    };
    xhr.send("codigo=" + codigo);
}

function disableLinks(codigo) {
    var links = document.querySelectorAll('.acciones a');
    links.forEach(function(link) {
        // Obtener el código del bien asociado a este enlace
        var bienCodigo = link.getAttribute('data-codigo');
        if (bienCodigo === codigo) {
            link.removeAttribute('onclick');
            link.style.pointerEvents = 'none';
            link.style.opacity = '0.5';
        }
    });
}


function reportfinish() {
    Swal.fire({
        title: 'Esta a punto de dar por finalizado el reporte de sus bienes',
        text: '¿Desea continuar?',
        icon: 'info',
        showCancelButton: true,
        confirmButtonColor: '#139EC8',
        cancelButtonColor: '#A8A8A8',
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        customClass: {
            popup: 'custom-modal'
        }
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha cargado de forma exitosa el reporte de sus bienes.',
                icon: 'success',
                confirmButtonColor: '#139EC8'
            })
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire({
                title: 'Cancelado',
                text: 'Se ha cancelado el reporte de sus bienes.',
                icon: 'error',
                confirmButtonColor: '#139EC8'
            })
        }
    });
}