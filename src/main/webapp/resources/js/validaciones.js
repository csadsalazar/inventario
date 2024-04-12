const number = /^[0-9]{5,10}/
const text = /^[a-zA-ZñÑá-úÁ-Ú0-9]{5,20}/
const email = /\w+@misena\.edu\.co/
const passwordd = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()\-_=+{};:,<.>]).{4,}$/

const form = document.getElementById("frmvalidacion")
const Nodoc = form.Nodoc.value
const f1 = document.querySelector("#Nodoc .feedback")
const Nom = form.Nom.value
const f2 = document.querySelector("#Nom .feedback")
const Apl = form.Apl.value
const f3 = document.querySelector("#Apl .feedback")
const FeNa = form.FeNa.value
const f4 = document.querySelector("#FeNa .feedback")
const Coel = form.Coel.value
const f5 = document.querySelector("#Coel .feedback")
const Pass = form.Pass.value
const f6 = document.querySelector("#Pass .feedback")
const CoPass = form.CoPass.value
const f7 = document.querySelector("#CoPass .feedback")
let edad

// DOCUMENTO DE IDENTIDAD
form.Nodoc.addEventListener('input', e => {
  e.preventDefault()

  console.log("se está escribiendo");
  if (number.test(e.target.value)) {
    form.Nodoc.setAttribute("class","success")
    f1.style.setProperty("visibility","hidden")
    f1.style.setProperty("opacity","0")
  } else {
    form.Nodoc.setAttribute("class","error")
    f1.textContent="Solo puede digitar valores numericos con una longitud minimo de 5 y maximo de 10"
    f1.style.setProperty("visibility","visible")
    f1.style.setProperty("opacity","1")
  }
})

// NOMBRE
form.Nom.addEventListener('input', e => {
  e.preventDefault();

  console.log("se está escribiendo");
  if (text.test(e.target.value)) {
    form.Nom.setAttribute("class","success")
    f2.style.setProperty("visibility","hidden")
    f2.style.setProperty("opacity","0")
  } else {
    form.Nom.setAttribute("class","error")
    f2.textContent="Solo se aceptan valores alfanumericos (minimo 10 y maximo 20 caracteres)"
    f2.style.setProperty("visibility","visible")
    f2.style.setProperty("opacity","1")
  }
})

// APELLIDO
form.Apl.addEventListener('input', e => {
  e.preventDefault();

  console.log("se está escribiendo");
  if (text.test(e.target.value)) {
    form.Apl.setAttribute("class","success")
    f3.style.setProperty("visibility","hidden")
    f3.style.setProperty("opacity","0")
  } else {
    form.Apl.setAttribute("class","error")
    f3.textContent="Solo se aceptan valores alfanumericos (minimo 10 y maximo 20 caracteres)"
    f3.style.setProperty("visibility","visible")
    f3.style.setProperty("opacity","1")
  }
})

// FECHA DE NACIMIENTO
form.FeNa.addEventListener('input', e => {
  e.preventDefault()

  console.log("se está escribiendo");
  const fechaIngresada = new Date(e.target.value);
  const hoy = new Date();
  edad = hoy.getFullYear() - fechaIngresada.getFullYear();

  if (edad < 18) {
    f4.textContent = "Debes ser mayor de 18 años para registrarte"
    f4.style.setProperty("visibility", "visible")
    f4.style.setProperty("opacity", "1")
  } else {
    form.FeNa.setAttribute("class", "success")
    f4.style.setProperty("visibility", "hidden")
    f4.style.setProperty("opacity", "0")
  }
})

// CORREO ELECTRONICO
form.Coel.addEventListener('input', e => {
  e.preventDefault()

  console.log("se está escribiendo");
  if (email.test(e.target.value)) {
    form.Coel.setAttribute("class","success")
    f5.style.setProperty("visibility","hidden")
    f5.style.setProperty("opacity","0")
  } else {
    form.Coel.setAttribute("class","error")
    f5.textContent="Solo se aceptan correos @misena.edu.co"
    f5.style.setProperty("visibility","visible")
    f5.style.setProperty("opacity","1")
  }
})

// CONTRASEÑA
form.Pass.addEventListener('input', e => {
  e.preventDefault()

  console.log("se está escribiendo");
  if (passwordd.test(e.target.value)) {
    form.Pass.setAttribute("class","success")
    f6.style.setProperty("visibility","hidden")
    f6.style.setProperty("opacity","0")
  } else {
    form.Pass.setAttribute("class","error")
    f6.textContent="Ingrese mínimo una letra mayúscula, un número, una letra minúscula y un caracter especial(mínimo 10 caracteres)"
    f6.style.setProperty("visibility","visible")
    f6.style.setProperty("opacity","1")
  }
})

// CONFIRMAR CONTRASEÑA
form.CoPass.addEventListener('input', e => {
  e.preventDefault();
  console.log("se está escribiendo sobre el input")

  if (passwordd.test(e.target.value)) {
    form.CoPass.setAttribute("class","success")
    f7.style.setProperty("visibility","hidden")
    f7.style.setProperty("opacity","0")
  } else {
    form.CoPass.setAttribute("class","error")
    f7.textContent="Ingrese mínimo una letra mayúscula, un número, una letra minúscula y un caracter especial(mínimo 10 caracteres) y debe coincidir con la contraseña posteriormente digitada"
    f7.style.setProperty("visibility","visible")
    f7.style.setProperty("opacity","1")
  }
})

form.addEventListener("submit", e => {
  e.preventDefault()
  let flag = true

  if (form.Nodoc.value == null || form.Nodoc.value == 0 || !number.test(form.Nodoc.value)) {
    alert("Debe ingresar un número de documento válido")
    form.Nodoc.focus()
    form.Nodoc.setAttribute("class", "error")
    flag = false
  }

  if (form.Nom.value == null || form.Nom.value == 0 || !text.test(form.Nom.value)) {
    alert("Debe ingresar un nombre válido")
    form.Nom.focus()
    form.Nom.setAttribute("class", "error")
    flag = false
  }

  if (form.Apl.value == null || form.Apl.value == 0 || !text.test(form.Apl.value)) {
    alert("Debe ingresar un apellido válido")
    form.Apl.focus()
    form.Apl.setAttribute("class", "error")
    flag = false
  }



  if (form.FeNa.value == null || form.FeNa.value == 0 || edad < 18) {
    alert("Debe ingresar una fecha de nacimiento válida (debes ser mayor de 18 años para registrarte)")
    form.FeNa.focus()
    form.FeNa.setAttribute("class", "error")
    flag = false
  }

  if (form.Coel.value == null || form.Coel.value == 0 || !email.test(form.Coel.value)) {
    alert("Debe ingresar un correo válido (solo se aceptan correos @misena.edu.co)")
    form.Coel.focus()
    form.Coel.setAttribute("class", "error")
    flag = false
  }

  if (form.Pass.value == null || form.Pass.value == 0 || !passwordd.test(form.Pass.value)) {
    alert("Debe ingresar una contraseña válida (mínimo una letra mayúscula, un número, una letra minúscula y un caracter especial, mínimo 10 caracteres)")
    form.Pass.focus()
    form.Pass.setAttribute("class", "error")
    flag = false
  }

  if (form.CoPass.value == null || form.CoPass.value == 0 || !passwordd.test(form.CoPass.value) || form.CoPass.value != form.Pass.value) {
    alert("Debe ingresar una contraseña válida y que coincida con la contraseña ingresada anteriormente")
    form.CoPass.focus()
    form.CoPass.setAttribute("class", "error")
    flag = false
  }

  if (!document.getElementById('Te').checked) {
    alert("No has aceptado los términos y condiciones")
    flag = false
  }

  if (flag) {
    form.submit()
  }
})

let btnModal = document.getElementById("abrirModal")
let venModal = document.getElementById("ventanaModal")
let cerModal = document.querySelector(".cerrarModal")
let cerrarModalButton = document.querySelector(".cerrarModalButton")


btnModal.addEventListener("click", () => {
  venModal.style.display = "block";
})

cerModal.addEventListener("click", () => {
  venModal.style.display = "none";
})

cerrarModalButton.addEventListener("click", () => {
    venModal.style.display = "none";
})