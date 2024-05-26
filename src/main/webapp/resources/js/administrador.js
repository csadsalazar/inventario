function confirmarEliminacion(codigo) {
    Swal.fire({
        title: '¿Está seguro de que desea eliminar este bien?',
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
            eliminarObjeto(codigo);
        }
    });
}

function eliminarObjeto(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "DeleteObject", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha eliminado el bien correctamente',
                icon: 'success',
                confirmButtonColor: '#139EC8'
            })}
    };
    xhr.send("codigo=" + codigo);
}

function eliminarSeleccionados() {
    Swal.fire({
        title: '¿Está seguro de que desea eliminar el o los bienes?',
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
        document.getElementById("deleteForm").submit();
        Swal.fire({
            title: '¡Éxito!',
            text: 'Se ha eliminado correctamente',
            icon: 'success',
            confirmButtonColor: '#139EC8'
        })}
    });
}

function selectAllCheckboxes() {
var checkboxes = document.getElementsByName('selectedObjects');
var selectAllCheckbox = document.getElementById('selectAllCheckbox');

for (var i = 0; i < checkboxes.length; i++) {
    checkboxes[i].checked = selectAllCheckbox.checked;
}
}

function deleteAllObjects() {
Swal.fire({
    title: '¿Está seguro de que desea eliminar todos los bienes del almacén?',
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
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "DeleteObject", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                Swal.fire({
                    title: '¡Éxito!',
                    text: 'Se han eliminado todos los bienes del almacén',
                    icon: 'success',
                    confirmButtonColor: '#139EC8'
                }).then((result) => {
                    window.location.href = "managementobjects.jsp";
                    window.location.load();
                });
            }
        };
        xhr.send("eliminarTodos=true");
    }
});
}

function eliminar(codigo) {
    Swal.fire({
        title: '¿Está seguro de que desea eliminar este administrador?',
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
            deleteAdmin(codigo);
        }
    });
}

function deleteAdmin(codigo) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "DeleteAdmin", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            Swal.fire({
                title: '¡Éxito!',
                text: 'Se ha eliminado el administrador correctamente',
                icon: 'success',
                confirmButtonColor: '#139EC8'
            }).then((result) => {
                window.location.href="managementadmins.jsp";
                window.location.load();
            });
        }
    };
    xhr.send("codigo=" + codigo);
}