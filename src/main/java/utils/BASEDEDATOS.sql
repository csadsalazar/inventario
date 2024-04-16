					-- Crear base de datos
					DROP DATABASE IF EXISTS Gestion_inventario;
					CREATE DATABASE IF NOT EXISTS Gestion_Inventario;
					USE Gestion_Inventario;

					-- Tabla de Usuarios
					CREATE TABLE IF NOT EXISTS MA_Usuarios (
						PK_idUsuario INT AUTO_INCREMENT PRIMARY KEY,
						nombre VARCHAR(50),
						usuario VARCHAR(15),
						cedula INT,
						contrasena VARCHAR(20),
						dependencia INT NOT NULL,
						cargo VARCHAR(20) NOT NULL,
						contrato VARCHAR(20) NOT NULL,
						sede VARCHAR(30) NOT NULL
					);

					-- Tabla de Bienes
					CREATE TABLE IF NOT EXISTS MA_Bienes (
						idBien INT AUTO_INCREMENT PRIMARY KEY,
						PK_Codigo INT,
						nombre VARCHAR(50),
						placa VARCHAR(6),
						descripcion VARCHAR(150),
						valor INT,
						FK_Usuario INT,
						FK_Dependencia INT,
						estado VARCHAR(12),
						imagen1 VARCHAR(50),
						imagen2 VARCHAR(50),
						FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuarios(PK_idUsuario) ON DELETE CASCADE 
					);

					-- Tabla de Bienes por Usuario
					CREATE TABLE IF NOT EXISTS PA_BienesPorUsuario (
						PK_idBienPorUsuario INT AUTO_INCREMENT PRIMARY KEY,   
						FK_Usuario INT,
						FK_Bien INT,
						asunto VARCHAR(25),
						informacion VARCHAR(200),
						fechaObservacion DATE NOT NULL,
						FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuarios(PK_idUsuario), 
						FOREIGN KEY (FK_Bien) REFERENCES MA_Bienes(idBien) ON DELETE CASCADE
					);

					-- Tabla de Usuarios Independiente
					CREATE TABLE IF NOT EXISTS MA_Administradores (
						PK_idAdministrador INT AUTO_INCREMENT PRIMARY KEY,
						usuario VARCHAR(15),
						estado VARCHAR(8)
					);


					CREATE TABLE IF NOT EXISTS MA_Dependencias (
						PK_idDependencia INT AUTO_INCREMENT PRIMARY KEY,
						nombreDependencia VARCHAR(130) UNIQUE
					);

					-- Procedimientos almacenados para Bienes

					DELIMITER //

					CREATE PROCEDURE AgregarBien (
						IN codigo INT,
						IN nombre VARCHAR(50),
						IN placa VARCHAR(6),
						IN descripcion VARCHAR(150),
						IN valor INT,
						IN FK_Usuario INT,
						IN FK_Dependencia INT,
						IN estado VARCHAR(12),
						IN imagen1 VARCHAR(50),
						IN imagen2 VARCHAR(50)
					)
					BEGIN
						INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2)
						VALUES (codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2);
					END //

					CREATE PROCEDURE ListarBienes()
					BEGIN
						SELECT b.idBien,
							   b.PK_Codigo,
							   b.placa,
							   b.nombre,
							   u.usuario,
							   u.dependencia,
							   b.valor 
						FROM MA_Bienes b 
						INNER JOIN MA_Usuarios u ON b.FK_Usuario = u.PK_idUsuario;
					END //



					DELIMITER //
					CREATE PROCEDURE AgregarObservacion (
						IN usuario INT,
						IN bien INT,
						IN asunto VARCHAR(50),
						IN informacion VARCHAR(250), 
						IN fechaObservacion DATE
					)
					BEGIN
						INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion)
						VALUES (usuario, bien, asunto, informacion, fechaObservacion);
					END //

					CREATE PROCEDURE ListarObservaciones()
					BEGIN
						SELECT bu.FK_Usuario,
							   u.PK_idUsuario,
							   u.usuario,
							   u.dependencia,
							   bu.asunto,
							   bu.informacion 
						FROM PA_BienesPorUsuario bu 
						INNER JOIN MA_Usuarios u ON bu.FK_Usuario = u.PK_idUsuario;
					END //

					-- INSERCIONES 
					INSERT INTO MA_Usuarios (nombre, usuario, cedula, contrasena, dependencia, cargo, contrato, sede)
					VALUES ('NombreUsuario1', 'usuario1', 123456789, 'contrasena1', 1, 'Cargo1', 'Contrato1', 'INVIMA'),
						   ('NombreUsuario2', 'usuario2', 987654321, 'contrasena2', 2, 'Cargo2', 'Contrato2', 'INVIMA'),
						   ('NombreUsuario3', 'usuario3', 456789123, 'contrasena3', 3, 'Cargo3', 'Contrato3', 'INVIMA');
                           
                    INSERT INTO MA_Administradores (usuario, estado)
					VALUES ('usuario1', 'Activo');       
                    
					INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2)
					VALUES (1, 'NombreBien1', '1000', 'Descripción1', 1000, 1, '1', 'No reportado', 'Imagen1', 'Imagen1.1'),
						   (2, 'NombreBien2', '2000', 'Descripción2', 2000, 2, '2', 'Reportado', 'Imagen2', 'Imagen2.1'),
						   (3, 'NombreBien3', '3000', 'Descripción3', 3000, 3, '3', 'Reportado', 'Imagen3', 'Imagen3.1');

					INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion)
					VALUES (1, 1, 'Asunto1', 'Información1', '2024-04-07'),
						   (2, 2, 'Asunto2', 'Información2', '2024-04-08'),
						   (3, 3, 'Asunto3', 'Información3', '2024-04-09');
						   
					INSERT INTO MA_Dependencias (nombreDependencia) 
                    VALUES
                    ('DIRECCION GENERAL'),
					('DIRECCIÓN DE MEDICAMENTOS  Y PRODUCTOS BIOLÓGICOS (INCLUYE LOS 12 GRUPOS)'),
					('DIRECCIÓN DE ALIMENTOS Y BEBIDAS  (INCLUYE LOS 6 GRUPOS)'),
					('DIRECCIÓN DE OPERACIONES SANITARIAS (INCLUYE TODOS LOS GRUPOS UBICADOS EN EL PISO 10)'),
					('DIRECCIÓN DE RESPONSABILIDAD SANITARIA (INCLUYE LOS 6 GRUPOS)'),
					('DIRECCION DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS (INCLUYE LOS 4 GRUPOS)'),
					('ATENCION AL CIUDADANO PISO (INCLUYE LOS 2 GRUPOS)'),
					('DIRECCIÓN DE COSMETICOS, ASEO, PLAGUICIDAS Y PRODUCTOS DE HIGIENE DOMESTICA'),
					('OFICINA DE ASUNTOS INTERNACIONALES (INCLUYE LOS 2 GRUPOS)'),
					('SECRETARIA GENERAL'),
					('OFICINA DE CONTROL INTERNO'),
					('GRUPO DE TESORERIA'),
					('GRUPO DE SOPORTE TECNOLOGICO'),
					('OFICINA DE TECNOLOGIAS DE LA INFORMACIÓN (INCLUYE LOS 2 GRUPOS)'),
					('GRUPO DE GESTION ADMINISTRATIVA (INCLUYE CAFETERIAS, GUARDAS Y ZONAS COMUNES)'),
					('OFICINA ASESORA JURÍDICA (INCLUYE LOS 5 GRUPOS)'),
					('OFICINA ASESORA DE PLANEACION (INCLUYE LOS 2 GRUPOS Y EL ÁREA DE RIESGOS)'),
					('GRUPO DE GESTIÓN CONTRACTUAL'),
					('GRUPO FINANCIERO Y PRESUPUESTAL'),
					('GRUPO DE COMUNICACIONES'),
					('GRUPO DE TALENTO HUMANO'),
					('GRUPO UNIDAD DE REACCIÓN INMEDIATA -GURI'),
					('GRUPO DE INSTRUCCION DISCIPLINARIA'),
					('GRUPO LABORATORIO FISICOQUIMICO DE ALIMENTOS Y BEBIDAS'),
					('GRUPO LABORATORIO MICROBIOLOGIA DE ALIMENTOS Y BEBIDAS'),
					('GRUPO LABORATORIO DE FISICOQUÍMICO DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS'),
					('GRUPO DE LABORATORIO DE MICROBIOLOGÍA DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS'),
					('OFICINA DE LABORATORIOS  Y CONTROL DE CALIDAD'),
					('GRUPO LABORATORIO DE PRODUCTOS BIOLOGICOS'),
					('GRUPO DE GESTIÓN DOCUMENTAL Y CORRESPONDENCIA (INCLUYE DIFERENTES SEDES )'),
					('GRUPO LABORATORIO FISICO-MECANICO DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS'),
					('GRUPO LABORATORIO DE ORGANISMOS GENETICAMENTE MODIFICADOS -OGM'),
					('GRUPO RED DE LABORATORIOS Y CALIDAD'),
					('GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 2 (BOGOTA)'),
					('ÁREA DE ALMACEN GENERAL E INVENTARIOS'),
					('GRUPO DE CONTROL EN PUERTOS, AEROPUERTOS Y PASOS DE FRONTERA (AEROPUERTO BOGOTA)'),
					('GRUPO DE INSPECCIÓN VIGILANCIA Y CONTROL DE TRÁFICO POSTAL Y MENSAJERÍA EXPRESA'),
					('OFICINA PUERTO DE SANTA MARTA'),
					('OFICINA PASO FRONTERIZO DE PARAGUACHON (GUAJIRA)'),
					('OFICINA PASO FRONTERIZO ARAUCA'),
					('OFICINA DE IBAGUE'),
					('GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 3 - NEIVA'),
					('OFICINA PASO FRONTERIZO DE LETICIA'),
					('OFICINA PASO FRONTERIZO DE SAN MIGUEL PUTUMAYO'),
					('GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 1 - BARRANQUILLA'),
					('OFICINA PUERTO DE BARRANQUILLA'),
					('OFICINA PUERTO DE CARTAGENA'),
					('GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 2 - CALI'),
					('OFICINA PUERTO DE BUENAVENTURA'),
					('GRUPO DE APOYO A NARIÑO PASTO'),
					('OFICINA PASO FRONTERIZO DE  IPIALES'),
					('GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 1 - MEDELLIN'),
					('OFICINA AEROPUERTO DE RIONEGRO'),
					('GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 1 - BUCARAMANGA'),
					('OFICINA PASO FRONTERIZO DE CÚCUTA'),
					('GRUPO DE TRABAJO TERRITORIAL  ORINOQUIA- VILLAVICENCIO'),
					('GRUPO DE TRABAJO TERRITORIAL  EJE CAFETERO - ARMENIA'),
					('GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 2 - MONTERIA');
                    