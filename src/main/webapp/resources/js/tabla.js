document.addEventListener('DOMContentLoaded', function() {
// Buscar todas las tablas con la clase 'table' excepto aquellas con los IDs 'verbien', 'verbiena' y 'agregar bien'
document.querySelectorAll('.table:not(#verbien):not(#verbiena):not(#agregarbien)').forEach(function(table) {
    new DataTable(table, {
        pagingType: 'simple_numbers',
        language: {
            search: 'Buscar:',
            lengthMenu: 'Mostrar _MENU_ registros por página',
            info: 'Mostrando _START_ a _END_ de _TOTAL_ registros',
            infoEmpty: 'Mostrando 0 a 0 de 0 registros',
            infoFiltered: '(filtrado de _MAX_ registros totales)',
            zeroRecords: 'No se encontraron registros coincidentes',
            paginate: {
                previous: 'Anterior',
                next: 'Siguiente',
            }
        }
    });
});
//cierre de control DOM
});