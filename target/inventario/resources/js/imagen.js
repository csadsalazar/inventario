document.addEventListener('DOMContentLoaded', function() {

    // Modal carga de imágenes
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
            confirmButtonColor: '#139EC8',
            cancelButtonColor: '#A8A8A8',
            confirmButtonText: 'Aceptar',
            cancelButtonText: 'Cancelar',
            preConfirm: () => {
                return new Promise((resolve) => {
                    const file1 = document.getElementById('fileInput1').files[0];
                    const file2 = document.getElementById('fileInput2').files[0];
                    
                    if (file1 || file2) {
                        const formData = new FormData();
                        if (file1) formData.append('file1', file1);
                        if (file2) formData.append('file2', file2);

                        fetch('/uploadFiles', {
                            method: 'POST',
                            body: formData
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error al cargar archivos');
                            }
                            return response.json();
                        })
                        .then(result => {
                            Swal.fire({
                                title: '¡Éxito!',
                                text: 'Se han cargado las imágenes con éxito.',
                                icon: 'success',
                                confirmButtonColor: '#139EC8'
                            });
                            resolve();
                        })
                        .catch(error => {
                            Swal.fire({
                                title: 'Error',
                                text: 'Ocurrió un error al cargar las imágenes.',
                                icon: 'error',
                                confirmButtonColor: '#139EC8'
                            });
                            resolve();
                        });
                    } else {
                        Swal.showValidationMessage('Se requiere al menos una imagen');
                        resolve();
                    }
                });
            },
            customClass: {
                popup: 'custom-modal'
            }
        });
    });
});
