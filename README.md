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
