function action(codigo) {
    Swal.fire({
        title: '¿Está seguro de que desea eliminar este bien?',
        text: 'Esta acción no se puede deshacer',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#2DA4BE',
        cancelButtonColor: '#808080',
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        customClass: {
            popup: 'custom-modal'
        }
    }).then((result) => {
        if (result.isConfirmed) {
            eliminarBien(codigo);
        }
    });
}

function eliminarBien(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "EliminarBien", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha eliminado el bien correctamente',
                icon: 'success',
                confirmButtonColor: '#2DA4BE'
            }).then((result) => {
                window.location.href = "gestionbienes.jsp";
            });
        }
    };
    xhr.send("codigo=" + codigo);
}
