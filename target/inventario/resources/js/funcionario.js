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
    // Hacer una solicitud GET al servlet que genera el archivo Excel
    fetch('GenerateReportExcelServlet', {
        method: 'GET'
    })
    .then(response => {
        if (response.ok) {
            // Si la respuesta es exitosa, descarga el archivo Excel
            response.blob().then(blob => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = 'reporte_bienes_usuario.xls';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
            });
        } else {
            // Manejar el caso en el que la respuesta no sea exitosa
            console.error('Error al generar el archivo Excel');
        }
    })
    .catch(error => {
        console.error('Error en la solicitud:', error);
    });
}
