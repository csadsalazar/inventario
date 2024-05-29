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
            // Ocultar la fila de la tabla inmediatamente
            var fila = document.querySelector('tr[data-codigo="' + codigo + '"]');
            fila.style.display = "none";
            // Reportar el objeto
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
            // Hacer una solicitud AJAX para verificar el estado de los bienes
            $.ajax({
                type: 'POST',
                url: 'CheckBienesStatus',
                success: function(response) {
                    if (response === 'success') {
                        // Si todos los bienes están en estado 'En espera' o 'Reportado', generar el reporte
                        window.location.href = 'GenerateExcelReport';
                    } else {
                        // Si hay bienes en otros estados, mostrar un mensaje de error
                        Swal.fire({
                            title: 'Error',
                            text: 'No todos los bienes están listos para finalizar el reporte.',
                            icon: 'error',
                            confirmButtonColor: '#139EC8'
                        });
                    }
                }
            });
        }
    });
}