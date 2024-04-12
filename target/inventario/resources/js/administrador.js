function action(){
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
}
document.addEventListener("DOMContentLoaded", function() {
    // Obtener elementos del DOM
    var modal = document.getElementById('myModal');
    var openModalBtn = document.getElementsByClassName('openModal');
    var closeModalBtn = document.getElementsByClassName('close')[0];

    // Función para abrir el modal
    function openModal() {
      modal.style.display = 'block';
    }

    // Función para cerrar el modal
    function closeModal() {
      modal.style.display = 'none';
    }

    // Evento de clic para abrir el modal
    openModalBtn.addEventListener('click', function() {
      openModal();
    });

    // Evento de clic para cerrar el modal
    closeModalBtn.addEventListener('click', function() {
      closeModal();
    });

    // Evento para cerrar el modal si se hace clic fuera del contenido
    window.addEventListener('click', function(event) {
      if (event.target == modal) {
        closeModal();
      }
    });
});

