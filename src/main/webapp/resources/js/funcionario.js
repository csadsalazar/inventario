document.addEventListener('DOMContentLoaded', function() {
// Modal verificar bienes
document.getElementById('verificarModal').addEventListener('click', function() {
  Swal.fire({
      title: 'Esta a punto de verificar el bien',
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
              text: 'Se ha verificado el bien.',
              icon: 'success',
              confirmButtonColor: '#2DA4BE'
          })
      } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire({
              title: 'Cancelado',
              text: 'Se ha cancelado la verificación de su bien.',
              icon: 'error',
              confirmButtonColor: '#2DA4BE'
          })
      }
  });
});

// Modal reporte final de bienes
document.getElementById('reporteModal').addEventListener('click', function() {
  Swal.fire({
      title: 'Esta a punto de dar por finalizado el reporte de sus bienes',
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
              text: 'Se ha cargado de forma exitosa el reporte de sus bienes.',
              icon: 'success',
              confirmButtonColor: '#2DA4BE'
          })
      } else if (result.dismiss === Swal.DismissReason.cancel) {
          Swal.fire({
              title: 'Cancelado',
              text: 'Se ha cancelado el reporte de sus bienes.',
              icon: 'error',
              confirmButtonColor: '#2DA4BE'
          })
      }
  });
});
//cierre de control DOM
});

