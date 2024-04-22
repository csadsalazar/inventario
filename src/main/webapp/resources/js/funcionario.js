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
            reportarBien(codigo);
        }
    });
}

function reportarBien(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "ReportarBien", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha reportado el bien correctamente',
                icon: 'success',
                confirmButtonColor: '#139EC8'
            }).then((result) => {
                window.location.reload();
            });
        }
    };
    xhr.send("codigo=" + codigo);
}

// Modal reporte final de bienes
document.getElementById('reporteModal').addEventListener('click', function() {
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
});