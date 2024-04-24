# Proyecto Invima
- Ticket conexion: Caso 100463
    * File server y conexion a elda
- Ticket base de datos: Caso 100494
    * Creación del usuario: csalazart
    * Creación de base de datos: Gestion_Inventario
    * Instancia: SRVVSANDIEGO\SRVDESARROLLO
    * Rol del usuario: owner de la bd

# Recursos informaticos utilizados
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
7. MySQL Worbench (Base de datos antes de utilizar base de datos definitiva)
8. XAMPP (Para base de datos antes de utilizar base de datos definitiva)
9. SQLServer (Base de datos definitiva)
10. Navegadores Chrome y Microsoft Edge

	## Librerias utilizadas
	- DataTables: gestión de todas las tablas (Buscador, paginacion, filtracion, orden de las columnas)
	- Select2: gestion de las etiquetas select (Buscador dentro de las etiquetas select)
 	- SwalAlert: Creacion de modales (Modales eliminar bien, reporte final, reportar bien, cargar imagenes y  modales de accion)
  	- JavaMail: Para facilitar el envio de correos electronicos se utilizara esta libreria de Java para realizar el debido proceso de envio de correos, todavia no se ha imnplementado y podria cambiar si se encuntra otra libreria que facilite el envio de correos
   	- Generacion de reportes: Para la generacion de reportes se usa las librerias Biblioteca PDFBox para generar archivos PDF y Biblioteca JExcelAPI para generar archivos Excel
   	- Grafica: Para la grafica respectiva se necesita saber mas sobre como realizar graficas en Java, la primera opcion es la posibilidad de usar power bi
    	- A la espera de utilizar mas librerias las cuales aporten en la legibilidad del proyecto en distintos aspectos
       ## Dependencias utilizadas en el POM.XML
      	- El siguiente codigo porporcionado son todas las dependencias utilizadas en el pom.xml para facilitar la transaccion o facilitar distintas tareas en el aplcativo
	<dependencies>
	        <dependency>
	            <groupId>javax.servlet</groupId>
	            <artifactId>javax.servlet-api</artifactId>
	            <version>3.1.0</version>
	            <scope>provided</scope>
	        </dependency>
	        <dependency>
	            <groupId>javax.servlet</groupId>
	            <artifactId>jstl</artifactId>
	            <version>1.2</version>
	        </dependency>
	        <dependency>
	            <groupId>com.microsoft.sqlserver</groupId>
	            <artifactId>mssql-jdbc</artifactId>
	            <version>11.2.1.jre17</version>
	        </dependency>
	        <dependency>
	            <groupId>mysql</groupId>
	            <artifactId>mysql-connector-java</artifactId>
	            <version>8.0.27</version>
	        </dependency>
	        <dependency>
	            <groupId>com.google.code.gson</groupId>
	            <artifactId>gson</artifactId>
	            <version>2.8.8</version>
	        </dependency>
	        <!-- Biblioteca PDFBox para generar archivos PDF -->
	        <dependency>
	            <groupId>org.apache.pdfbox</groupId>
	            <artifactId>pdfbox</artifactId>
	            <version>2.0.24</version>
	        </dependency>
	        <!-- Biblioteca JExcelAPI para generar archivos Excel -->
	        <dependency>
	            <groupId>net.sourceforge.jexcelapi</groupId>
	            <artifactId>jxl</artifactId>
	            <version>2.6.12</version>
	        </dependency>
    	</dependencies>	  	

# Hardware utilizado
Portaltil HP con las siguientes caracteristicas:
	- Nombre del SO: Microsoft Windows 11 Pro
	- Modelo del sistema: HP ProBook 445 14 inch G9 Notebook PC
	- Tipo de sistema: PC basado en x64
	- Procesador: AMD Ryzen 7 5825U with Radeon Graphics, 2000 Mhz, 8 procesadores principales, 16 procesadores lógicos
	- Versión y fecha de BIOS: HP U88 Ver. 01.14.00, 1/02/2024
	- Memoria física instalada (RAM): 16,0 GB
	- Tipo de disco: SSD
	- Capacidad del disco: 477 GB

# Rendimiento del sistema en ambiente de desarrollo y pruebas
El proyecto corre normamente bien, no se le ha hecho pruebas de inserciones a gran cantidad, esto se realizara en la ultima semana la cual el plan es entrar a pruebas sea en la oficina administrativa o en la oficina de las tecnologias de la informacion. 

     
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
El presente desarrollo esta enfocado para el grupo de gestión administrativa con el fin de satisfacer la gestion de los bienes de cada funcionario, este desarrollo se esta haciendo bajo la supervision de la oficina de las tecnologias de la información, se presento el [cronograma](https://github.com/csadsalazar/inventario/blob/main/DOCUMENTACION/DOCUMENTOS/CRONOGRAMA.xlsx) de trabajo el cual es:

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

# Documentacion del proyecto
El desarrollo se siguio segun cronograma, evidencia de esto se puede evidenciar en el siguinte archivo [DOCUMENTO TECNICO](https://github.com/csadsalazar/inventario/blob/main/DOCUMENTACION/DOCUMENTOS/DOCUMENTO%20TECNICO.docx) en el cual se encontrara lo siguiente:

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

Ademas de imagenes sobre el desarrollo.
Por otra parte se encontrara los requisitos de administrador [administrador](https://github.com/csadsalazar/inventario/tree/main/DOCUMENTACION/REQUISITOS/ADMIN), [funcionario]([https://pages.github.com/](https://github.com/csadsalazar/inventario/tree/main/DOCUMENTACION/REQUISITOS/FUNCIONARIO), [funcionales y no funcionales](https://github.com/csadsalazar/inventario/blob/main/DOCUMENTACION/REQUISITOS/REQUERIMIENTOS.xlsx), por ultimo el [acta del desarrollo](https://github.com/csadsalazar/inventario/blob/main/DOCUMENTACION/DOCUMENTOS/ACTA%20DEL%20DESARROLLO.pdf)


A fecha de hoy 22/04/2024 se tiene como finalizado la CRUD de bienes
- Faltan algunas validaciones como longitud de los algunas letras y demas 
- Falta la posibilidad de poder cambiar de Inactivo a Activo el estado de los usuarios administradores, dentro de esta posibilidad no se esta mostrando la informacion de el administrador ya que por medio de la ruta se pierde el codigo
- Faltan indicadores
- Falta la posibilidad del envio de correos
- Falta comexion a ELDA
- Falta conexion a file server, por ende la posibilidad de agregar fotos tanto al file server como el link para quedar registrado en la base de datos
Serian los desarrollos faltantes frente al proyecto en general, se tiene estimado dejar como finalizado esto para la proxima semana aproximadamente.
Ademas de mirar resto de documentacion ya que no se sabe si con la documentacion generada en este espacio es la correcta.


# Documentacion tecnica del proyecto 
## Estructura del proyecto, el proyecto cuenta con la siguiente estructura de carpetas

	- **.idea** (Generado automaticamente por visual)
 
	- .vscode **(Generado automaticamente por visual)**
 
	- directorioactivo **(Modulos de conexion para Elda)**
 	
	- DOCUMENTACION 
  		-DOCUMENTOS (Documentacion tecnica del desarrollo)
    		ACTA DEL DESARROLLO.pdf
      		CRONOGRAMA.XSLX
		DOCUMENTO TECNICO.docx
  		DOCUMENTO.docx
  		-REQUISITOS
  			-ADMIN (Cuenta con todos los requerimientos frente a los modulos del rol administrador)
  			-FUNCIONARIO (Cuenta con todos los requerimientos frente a todos los modulos del rol funcionario)
  			REQUERIMIENTOS.xlxs
	- src
  		-java
  			--controllers (Servlets del aplicativo)
     			// Todos los servlets son
			AgregarAdmin.java
   			AgregarBien.java
      			AgregarObservacion.java
	 		CerrarSesion.java
    			EditarAdministrador.java
       			EditarBien.java
	  		EliminarAdmin.java
     			EliminarBien.java
     			ExcelController.java
			ListarAdministradores.java
   			ListarAdministradoresporCodigo.java
      			ListarBienes.java
	 		ListarBienPorCodigo.java
    			ListarDependencias.java
       			ListarObservaciones.java
	  		ListarUsuarios.java
     			Login.java
			Observacion.java
   			ObtenerDependenciasServlet.java
      			PDFController.java
	 		ReportarBien.java
    			ReporteFinal.java
       			UsuarioController.java
	  
  			--models (Modelos del aplicativo)
	 		// Todos los modelos son
    			Administrador.java
       			Bien.java
	  		Dependencia.java
     			Observacion.java
			Usuario.java

  			--utils (Conexion a base de datos)
			//Todos los archivos
   			BASEDEDATOS.sql
      			ConexionBD.java
     		
  		-webapp **JSPs del proyecto**
  			--META-INF
  			context.xml
		-	-resources
  				--css
      				estilos.css
	  			style.css
      				styles.css
	  
  				--img (imagenes en formato png, jpg o svg)
  				--js
				administrador.js
    				dashboard.js
				funcionario.js
    				imagen.js
				index.js
    				librerias.js
	  	
  			-WEB-INF
  				-lib **Librerias para el proyecto**
  			web.xml
  		agregaradmin.jsp
    		agregarbien.jsp
    		agregarobservacion.jsp
      		editaradmin.jsp
		editarbien.jsp
  		footera.jsp
    		footerf.jsp
      		formulario.jsp
		gestionadmin.jsp
  		gestionbienes.jsp
    		gestionobservaciones.jsp
      		headera.jsp
		headerf.jsp
  		homea.jsp
    		homef.jsp
      		index.jsp
		nava.jsp
  		navf.jsp
    		reportesyalertas.jsp
      		usuario.jsp
		verbien.jsp
  		verbienf.jsp
    
		- target **Maven guardará el proyecto después de compilarlo**
		apuntes.txt
		pom.xml **Project Object Model (POM) para describir el proyecto de software a construir, sus dependencias de otros módulos y componentes externos, y el orden de construcción de los elementos**
		README.md
 

