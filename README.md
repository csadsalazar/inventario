# Proyecto Invima
- Ticket conexion: Caso 100463
    * File server y conexion a elda
- Ticket base de datos: Caso 100494
    * Creación del usuario: csalazart
    * Creación de base de datos: Gestion_Inventario
    * Instancia: SRVVSANDIEGO\SRVDESARROLLO
    * Rol del usuario: owner de la bd

# Recursos utilizados
1. Visual Studio Code
	## Extensiones
	- Community Server Connectors
	- Extension Pack for Java
 	- Mobile simulator - responsive testing tool **(extension de navegador)** 
2. Java
3. Tomcat: 9.0.76
4. JDK 17
5. Maven 3.9.3
6. Git (Git Hub) como repositorio del proyecto

	## Librerias utilizadas
	- DataTables: gestión de todas las tablas (Buscador, paginacion, filtracion, orden de las columnas)
	- Select2: gestion de las etiquetas select (Buscador dentro de las etiquetas select)
 	- SwalAlert: Creacion de modales (Modales eliminar bien, reporte final, reportar bien, cargar imagenes y  modales de accion)
  	  
# Pasos a seguir
- Se configura variables de entorno en variables del sistema > Path agregar variables de entorno jdk y %MAVEN_HOME%\bin
- Configurar en variables del sistema > Nuevo > En nombre de la JAVA_HOME y MAVEN_HOME > En valor de la se pone la ruta del jdk y maven
- Si se presenta errores ir a la configuracion de visual y buscar el archivo settings.json en el cual cambiar la ruta de "maven.executable.path" en el cual se pone la ruta hasta el archico mvn "\\ruta\hasta\archivo\bin\mvn"
- **Si** se encuentra ya creado un proyecto dar en open folder en el apartado de java project
- **Si NO** se encuentra creado un proyecto dar en Create Java Project:
    1. Maven
    2. maven-archetype-webapp
    3. 1.4
    4. com.example (default) cambiar si es necesario
    5. demo (default) cambiar si es necesario
    6. Seleccionar carpeta donde se va a alojar el proyecto
    7. Saldra en consola la version que se desea poner, dar enter si no se necesita
    8. Saldra una confirmacion, dar enter
    9. Se abrira en otra ventana el proyecto o saldra mensaje en la parte inferior derecha para abrir proyecto en otra ventana
    10. ...

# Proyecto Inventario Personalizado - Documentacion del proyecto
## Indice
El presente desarrollo esta enfocado para el grupo de gestión administrativa con el fin de satisfacer la gestion de los bienes de cada funcionario, este desarrollo se esta haciendo bajo la supervision de la oficina de las tecnologias de la información, se presento el cronograma de trabajo el cual es:

| Semana | Actividades |
| --- | --- |
| Semana1 | Levantamiento de información: Se levanta información sobre el aplicativo, se entrega mockups, requerimientos y acta sobre el aplicativo, quedan debidamente firmadas y además la oficina jurídica hace el procedimiento de control de cambios. |
| Semana2 | Primera semana 20/03/2024 - 27/03/2024: Se realizará la parte frontend del aplicativo: 1. Vistas 2. Fórmulas 3. Ventanas modales 4. Dashboard |
| Semana3 | Segunda semana 28/03/2024 - 04/04/2024: Se realizará la parte backend del aplicativo en rol administrador y funcionarios (conexión ELDA) 1. Creación base de datos 2. Ingreso en Login 3. Administrar roles del aplicativo |
| Semana4 | Tercera semana 05/04/2024 - 12/04/2024: Se realizará la parte backend del aplicativo en rol administrador: 1. Agregar un bien 2. Editar un bien 3. Eliminar un bien 4. Consultar un bien 5. Reportes de las diferentes tablas |
| Semana5 | Cuarta semana 15/04/2024 - 22/04/2024: Se realizará la parte de backend del aplicativo en rol funcionario: 1. Aprobar un bien 2. Agregar observación 3. Finalizar reporte 4. Adjuntar imágenes |
| Semana6 | Quinta semana 15/04/2024 - 22/04/2024: Se realizará la parte de backend del aplicativo en rol funcionario: 1. Aprobar un bien 2. Agregar observación 3. Finalizar reporte 4. Adjuntar imágenes 5. Diagramas de pastel en el home administrador |
| Semana7 | Sexta semana 23/04/2024 - 30/04/2024: Se realizarán pruebas del aplicativo |
| Observaciones | Se tiene que conectar el aplicativo a el directorio activo ELDA. Se necesita tener la ruta del file server, hacer la conexión con el file server y además guardar la ruta de las imágenes en bases de datos. |

El desarrollo se siguio segun cronograma, evidencia de esto se puede evidenciar en el siguinte archivo [DOCUMENTO TECNICO](https://pages.github.com/) en el cual se encontrara lo siguiente:

## Misión
La misión del aplicativo es gestionar el control de inventarios satisfaciendo la necesidad de los participantes que serán administradores con su rol de gestión de bienes y de los funcionarios con su rol de verificar el bien a nombre de este.
## Visión
La visión del aplicativo es satisfacer las necesidades de todas las partes mediante un control de bienes personalizados de forma eficiente pero además atractiva en cuanto a diseño
## Objetivo General:
Desarrollar un sistema de información para la supervisión y optimización en el control de reportar los bienes de un usuario.
## Objetivos específicos
Principalmente los objetivos específicos son:
## Identificar el problema:
Identificar las necesidades a la hora de controlar los reportes de bienes por parte de los usuarios.
## Analizar el problema:
Analizar los requisitos planteados para implementar el software
## Diseñar el software: 
Diseñar el software que se quiere implementar con los siguientes requisitos:
### 1.	Administrador:  
- Login: Vista de iniciar sesión en el aplicativo (ligado directorio activo)
- Home: Vista para visualización de graficas de pastel las cuales darán porcentaje de los bienes reportados en general y de todas las áreas. 
- Gestión de bienes: Vista para visualizar los bienes que se encuentran cargados en el aplicativo, además de poder realizar acciones a los bienes, como ver, editar y eliminar. 
- Ver bien: Vista para visualizar la toda la información posible del bien seleccionado. 
- Editar bien: Vista para visualizar la información del bien seleccionado y si es el caso poder editar la información que se necesite.  
- Eliminar bien: Vista para validar la eliminación del bien en caso de que se necesite. 
- Agregar bien: Vista para poder agregar bien que sea necesario agregar. 
- Reportes: Vista para poder generar reportes en formato Excel los cuales serán de forma general y de las diferentes áreas. 
- Observaciones: Vista para poder visualizar las observaciones que surgieron de los usuarios frente a un bien o inquietud que se presente. 
### 2.	Funcionario:   
- Login: Vista de iniciar sesión en el aplicativo (ligado directorio activo)
- Home: Vista la cual le permitirá ver los bienes a nombre del funcionario además de poder cargar imágenes, ver bienes a detalle, verificar bienes, agregar observaciones y finalizar reporte de bienes 
- Adjuntar imágenes: Ventana la cual permitirá cargar imágenes respectivas al bien que se seleccione, dos imágenes por bien (bien y placa). 
- Ver bien: Vista la cual permitirá ver a detalle la información del bien seleccionado, además de poder dar una observación acerca del bien.  
- Verificar bien: Ventana la cual validará el estado del bien, (Reportado, Pendiente). 
- Observaciones: Vista la cual permitirá agregar observaciones correspondientes a algún bien no encontrado o alguna otra situación respectiva a un bien. 
- Finalizar reporte: Ventana la cual validará que se quiere dar por finalizado el reporte de los bienes a nombre del funcionario. 
## Probar el software:
Probar el software con el fin de la revisión de su capacidad de satisfacer las necesidades planteadas y por ende funcionalidad respectiva.
## Evaluar el software:
Evaluar si el software cumple con los establecido.
## Beneficiarios:
- Por parte de la administración del aplicativo el primer beneficiado es la GRUPO DE GESTION ADMINISTRATIVA ya que podrán llevar a cabo el control del reporte de los bienes que los usuarios lleven a cabo previamente.
- Por parte de la utilidad del aplicativo el segundo beneficiario son los funcionarios del INVIMA ya que podrán verificar y reportar de forma eficiente los bienes a nombre de la persona.
- Por parte de desarrollo el tercer beneficiario es Instituto Nacional de Vigilancia de Medicamentos y Alimentos, ya que contara con un aplicativo el cual permita a sus funcionarios facilitar una tarea que se lleva a cabo de forma tediosa y dispendiosa.
## Alcance
El objetivo central del aplicativo es posibilitar la gestión efectiva de los bienes disponibles en el aplicativo. Para llevar a cabo esta iniciativa, el aplicativo se manejará en lenguajes de programación como HTML, JAVA, CSS, SQL y JAVASCRIPT.
Los módulos esenciales incluirán interfaces para la visualización y gestión de los bienes apoyándose de reportes y gráficas, así como la opción de brindar por parte de los funcionarios la verificación de sus bienes mediante un correcto cargue de imágenes las cuales permiten llevar a cabo el control del bien individual.
## Meta final
Como resultado final, el aplicativo ofrecerá una interfaz intuitiva y funcional para gestionar eficientemente los bienes, adaptándose a las necesidades específicas, representando un logro técnico y eficaz a la hora de hacer gestión de inventario por parte de los funcionarios.
## Estado del aplicativo
Para el ciclo de vida del aplicativo se plantean cuatro fases; análisis, desarrollo, pruebas y entrega, actualmente se encuentra en la fase de desarrollo, se tiene ya desarrollado la parte visual del aplicativo, los cuales son todos sus módulos que se mencionan en los objetivos específicos en el apartado de diseñar software. Como evidencia de esto a continuación se plasman evidencias graficas de los módulos.

Ademas de imagenes sobre el desarrollo

Por otra parte se encontrara


