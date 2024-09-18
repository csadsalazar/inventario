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
            if (xhr.responseText === "success") {
                Swal.fire({
                    title: '¡Éxito!',
                    text: 'Se ha reportado el bien correctamente',
                    icon: 'success',
                    confirmButtonColor: '#139EC8'
                }).then(() => {
                    // Redirigir a la página de inicio para actualizar la lista de bienes
                    window.location.href = "homef.jsp"; // O el nombre de tu servlet que carga la página de inicio
                });
            } else {
                Swal.fire({
                    title: 'Error',
                    text: 'No se pudo reportar el bien',
                    icon: 'error',
                    confirmButtonColor: '#139EC8'
                });
            }
        }
    };
    xhr.send("codigo=" + encodeURIComponent(codigo));
}