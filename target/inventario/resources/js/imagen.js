document.addEventListener('DOMContentLoaded', function() {

// Modal cargue de imágenes
document.getElementById('cargarImagenes').addEventListener('click', function() {
    Swal.fire({
        title: 'Cargar Imágenes',
        html: `
            <label for="fileInput1">Foto de placa:</label>
            <input type="file" id="fileInput1" accept="image/*" title="Foto de placa">
            <br>
            <label for="fileInput2">Foto del bien:</label>
            <input type="file" id="fileInput2" accept="image/*" title="Foto del bien">
        `,
        showCancelButton: true,
        confirmButtonColor: '#2DA4BE',
        cancelButtonColor: '#808080',
        confirmButtonText: 'Aceptar',
        cancelButtonText: 'Cancelar',
        preConfirm: () => {
            // Aquí manejar la lógica de procesamiento de imágenes
            const file1 = document.getElementById('fileInput1').files[0];
            const file2 = document.getElementById('fileInput2').files[0];
            if (file1 || file2) {
                console.log('Se han cargado al menos una imagen');
            } else {
                console.log('No se han cargado imágenes');
                Swal.showValidationMessage('Se requiere al menos una imagen');
            }
        },
        customClass: {
            popup: 'custom-modal'
        }
    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se han cargado las imágenes con éxito.',
                icon: 'success',
                confirmButtonColor: '#2DA4BE'
            })
        } else if (result.dismiss === Swal.DismissReason.cancel) {
            Swal.fire({
                title: 'Cancelado',
                text: 'Se ha cancelado la carga de imágenes.',
                icon: 'error',
                confirmButtonColor: '#2DA4BE'
            })
        }
    });
  });
//cierre de control DOM
});