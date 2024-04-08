document.addEventListener('DOMContentLoaded', function() {
//Modal eliminar bien
document.getElementById('eliminarBien').addEventListener('click', function() {
    Swal.fire({
        title: 'Esta a punto de elminar el bien',
        text: '¿Desea continuar?',
        icon: 'info',
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
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha eliminado el bien.',
                icon: 'success',
                confirmButtonColor: '#2DA4BE'
            })
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire({
                title: 'Cancelado',
                text: 'Se ha cancelado la eliminación del bien.',
                icon: 'error',
                confirmButtonColor: '#2DA4BE'
            })
        }
    });
  });
//cierre de control DOM
});