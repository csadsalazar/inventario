											-- Crear base de datos
											DROP DATABASE IF EXISTS Gestion_inventario;
											CREATE DATABASE IF NOT EXISTS Gestion_Inventario;
											USE Gestion_Inventario;

											CREATE TABLE IF NOT EXISTS MA_Dependencia (
											PK_idDependencia INT AUTO_INCREMENT PRIMARY KEY ,
											centroDeCosto varchar (5),
											nombreDependencia VARCHAR(130) UNIQUE
											);
                                            
                                            INSERT INTO MA_Dependencia (centroDeCosto, nombreDependencia) VALUES
											("600", 'DIRECCIÓN DE MEDICAMENTOS Y PRODUCTOS BIOLÓGICOS'),
											("400", 'DIRECCIÓN DE ALIMENTOS Y BEBIDAS'),
											("700", 'DIRECCIÓN DE OPERACIONES SANITARIAS'),
											("500", 'DIRECCION DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS'),
											("160", 'ATENCION AL CIUDADANO PISO'),
											("300", 'DIRECCIÓN DE COSMETICOS, ASEO, PLAGUICIDAS Y PRODUCTOS DE HIGIENE DOMESTICA'),
											("170", 'OFICINA DE ASUNTOS INTERNACIONALES'),
											("800", 'DIRECCIÓN DE RESPONSABILIDAD SANITARIA'),
											("100", 'DIRECCION GENERAL'),
											("200", 'SECRETARIA GENERAL'),
											("130", 'OFICINA DE CONTROL INTERNO'),
											("203", 'GRUPO DE TESORERIA'),
											("211", 'GRUPO DE SOPORTE TECNOLOGICO'),
											("150", 'OFICINA DE TECNOLOGIAS DE LA INFORMACIÓN'),
											("208", 'GRUPO DE GESTION ADMINISTRATIVA'),
											("120", 'OFICINA ASESORA JURÍDICA'),
											("110", 'OFICINA ASESORA DE PLANEACION'),
											("202", 'GRUPO DE GESTIÓN CONTRACTUAL'),
											("201", 'GRUPO FINANCIERO Y PRESUPUESTAL'),
											("161", 'GRUPO DE COMUNICACIONES'),
											("204", 'GRUPO DE TALENTO HUMANO'),
											("214", 'GRUPO UNIDAD DE REACCIÓN INMEDIATA -GURI'),
											("210", 'GRUPO DE INSTRUCCION DISCIPLINARIA'),
											("142", 'GRUPO LABORATORIO FISICOQUIMICO DE ALIMENTOS Y BEBIDAS'),
											("143", 'GRUPO LABORATORIO MICROBIOLOGIA DE ALIMENTOS Y BEBIDAS'),
											("144", 'GRUPO LABORATORIO DE FISICOQUÍMICO DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS'),
											("145", 'GRUPO DE LABORATORIO DE MICROBIOLOGÍA DE PRODUCTOS FARMACÉUTICOS Y OTRAS TECNOLOGÍAS'),
											("140", 'OFICINA DE LABORATORIOS Y CONTROL DE CALIDAD'),
											("146", 'GRUPO LABORATORIO DE PRODUCTOS BIOLOGICOS'),
											("212", 'GRUPO DE GESTIÓN DOCUMENTAL Y CORRESPONDENCIA (INCLUYE DIFERENTES SEDES)'),
											("148", 'GRUPO LABORATORIO FISICO-MECANICO DE DISPOSITIVOS MEDICOS Y OTRAS TECNOLOGIAS'),
											("147", 'GRUPO LABORATORIO DE ORGANISMOS GENETICAMENTE MODIFICADOS -OGM'),
											("141", 'GRUPO RED DE LABORATORIOS Y CALIDAD'),
											("704", 'GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 2 (BOGOTA)'),
											("205", 'ÁREA DE ALMACEN GENERAL E INVENTARIOS'),
											("713", 'GRUPO DE CONTROL EN PUERTOS, AEROPUERTOS Y PASOS DE FRONTERA (AEROPUERTO BOGOTA)'),
											("713A", 'GRUPO DE INSPECCIÓN VIGILANCIA Y CONTROL DE TRÁFICO POSTAL Y MENSAJERÍA EXPRESA'),
											("724", 'OFICINA PUERTO DE SANTA MARTA'),
											("720", 'OFICINA PASO FRONTERIZO DE PARAGUACHON (GUAJIRA)'),
											("716", 'OFICINA PASO FRONTERIZO ARAUCA'),
											("714", 'OFICINA DE IBAGUE'),
											("705", 'GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 3 - NEIVA'),
											("719", 'OFICINA PASO FRONTERIZO DE LETICIA'),
											("725", 'OFICINA PASO FRONTERIZO DE SAN MIGUEL PUTUMAYO'),
											("706", 'GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 1 - BARRANQUILLA'),
											("721", 'OFICINA PUERTO DE BARRANQUILLA'),
											("723", 'OFICINA PUERTO DE CARTAGENA'),
											("709", 'GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 2 - CALI'),
											("722", 'OFICINA PUERTO DE BUENAVENTURA'),
											("712", 'GRUPO DE APOYO A NARIÑO PASTO'),
											("718", 'OFICINA PASO FRONTERIZO DE IPIALES'),
											("708", 'GRUPO DE TRABAJO TERRITORIAL OCCIDENTE 1 - MEDELLIN'),
											("715", 'OFICINA AEROPUERTO DE RIONEGRO'),
											("703", 'GRUPO DE TRABAJO TERRITORIAL CENTRO ORIENTE 1 - BUCARAMANGA'),
											("717", 'OFICINA PASO FRONTERIZO DE CÚCUTA'),
											("710", 'GRUPO DE TRABAJO TERRITORIAL ORINOQUIA- VILLAVICENCIO'),
											("711", 'GRUPO DE TRABAJO TERRITORIAL EJE CAFETERO - ARMENIA'),
											("707", 'GRUPO DE TRABAJO TERRITORIAL COSTA CARIBE 2 - MONTERIA');

											CREATE TABLE IF NOT EXISTS PA_TipoDocumento(
                                            PK_idTipoDocumento INT AUTO_INCREMENT PRIMARY KEY,
                                            nombre VARCHAR(15) UNIQUE
                                            );
											
											INSERT INTO PA_TipoDocumento (nombre) VALUES 
											('TI'),
											('CC'),
											('CE');
                                            
											CREATE TABLE IF NOT EXISTS PA_Cargo(
                                            PK_idCargo INT AUTO_INCREMENT PRIMARY KEY,
                                            nombre VARCHAR(25) UNIQUE
                                            );
                                            
											INSERT INTO PA_Cargo (nombre) VALUES 
											('Jefe de oficina'),
											('Coordinador'),
											('Asesores'),
											('Profesional especializado'),
											('Profesional universitario'),
											('Tecnico operativo'),
											('Tecnico asistencial'),
											('Tecnico administrativo'),
											('Secretario'),
											('Auxiliar administrativo');
                                            
											CREATE TABLE IF NOT EXISTS MA_Perfil (
											PK_idPerfil INT AUTO_INCREMENT PRIMARY KEY,
<<<<<<< HEAD
											nombrePerfil VARCHAR(25),
=======
											nombrePerfil VARCHAR(15),
>>>>>>> 4dca191fcd0ab9f340f9e5009ff8338063259500
                                            descripcionPerfil VARCHAR(30)
											);
                                            
                                            INSERT INTO MA_Perfil (nombrePerfil, descripcionPerfil) VALUES 
											('Administrador', 'Administrador del sistema'),
											('Usuario', 'Usuario del sistema');

											-- Tabla de Usuarios
											CREATE TABLE IF NOT EXISTS MA_Usuario (
											PK_idUsuario INT AUTO_INCREMENT PRIMARY KEY,
											nombre VARCHAR(50),
											cedula BIGINT NOT NULL,
											usuario VARCHAR(20) NOT NULL,
											FK_Cargo INT,
											FK_Dependencia INT,
											FK_TipoDocumento INT,
											FK_Perfil INT,
											FOREIGN KEY (FK_Cargo) REFERENCES PA_Cargo(PK_idCargo) ON UPDATE CASCADE,
											FOREIGN KEY (FK_Dependencia) REFERENCES MA_Dependencia(PK_idDependencia) ON UPDATE CASCADE,
											FOREIGN KEY (FK_TipoDocumento) REFERENCES PA_TipoDocumento(PK_idTipoDocumento) ON UPDATE CASCADE,
											FOREIGN KEY (FK_Perfil) REFERENCES MA_Perfil(PK_idPerfil) ON UPDATE CASCADE
											);
                                            
											INSERT INTO MA_Usuario (nombre, cedula, usuario, FK_Cargo, FK_Dependencia, FK_TipoDocumento, FK_Perfil) VALUES 
											('CESAR DAVID SALAZAR TORRES', 1012916688, 'csalazart', 1, 10, 1, 1),
											('AIKO MARYOM CAMACHO RAMIREZ', 1012916689, 'acamachor', 1, 1, 1, 2);
                                        
											-- Tabla de Bienes
											CREATE TABLE IF NOT EXISTS MA_Bien (
											idBien INT AUTO_INCREMENT PRIMARY KEY,
											PK_Codigo BIGINT UNIQUE,
											nombre VARCHAR(50),
											placa INT UNIQUE,
											descripcion TEXT,
											valor BIGINT,
											estado VARCHAR(12),
											imagen1 VARCHAR(255),
											imagen2 VARCHAR(255),
											imagen3 VARCHAR(255),
											fecha DATE,
											fechaAdmin DATE,
											observacionAdmin TEXT,
											FK_Usuario INT,
											FK_Dependencia INT,
											FK_UsuarioAdmin INT,
											FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuario(PK_idUsuario) ON DELETE CASCADE,
											FOREIGN KEY (FK_UsuarioAdmin) REFERENCES MA_Usuario(PK_idUsuario) ON UPDATE CASCADE 
											);
                                            
											INSERT INTO MA_Bien (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2, fecha) VALUES
											(1, 'NombreBien1', '1000', 'Descripción1', 1000, 1, 1, 'No reportado', 'Imagen1', 'Imagen1.1', '2024-04-07'),
											(2, 'NombreBien2', '2000', 'Descripción2', 2000, 2, 2, 'Reportado', 'Imagen2', 'Imagen2.1', '2024-04-07'),
											(3, 'NombreBien3', '3000', 'Descripción3', 3000, 1, 3, 'Reportado', 'Imagen3', 'Imagen3.1', '2024-04-07');

											-- Tabla de Bienes por Usuario
											CREATE TABLE IF NOT EXISTS PA_BienPorUsuario (
											PK_idBienPorUsuario INT AUTO_INCREMENT PRIMARY KEY,   
											FK_Usuario INT,
											FK_Bien BIGINT,
											asunto VARCHAR(100),
											informacion TEXT,
											fechaObservacion DATE NOT NULL,
											FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuario(PK_idUsuario), 
											FOREIGN KEY (FK_Bien) REFERENCES MA_Bien(PK_Codigo) ON DELETE CASCADE
											);
                                            
                                            INSERT INTO PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion) VALUES 
											(1, 1, 'Asunto1', 'Información1', '2024-04-07'),
											(2, 2, 'Asunto2', 'Información2', '2024-04-08'),
											(1, 3, 'Asunto3', 'Información3', '2024-04-09');
                                            
											-- Procedimientos almacenados para Bienes
											DELIMITER //

											CREATE PROCEDURE AgregarBien (
												IN codigo INT,
												IN nombre VARCHAR(50),
												IN cedula BIGINT,
												IN placa VARCHAR(6),
												IN descripcion TEXT,
												IN valor INT,
												IN FK_Usuario INT,
												IN FK_Dependencia INT,
												IN estado VARCHAR(12),
												IN imagen1 VARCHAR(50),
												IN imagen2 VARCHAR(50),
												IN fecha DATE
												
											)
											BEGIN
												INSERT INTO MA_Bien (PK_Codigo, nombre, cedula, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2, fecha)
												VALUES (codigo, nombre, cedula, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2, fecha);
											END //

									CREATE PROCEDURE ListarBienes()
									BEGIN
										SELECT b.idBien,
											b.PK_Codigo,
											b.placa,
											b.nombre,
											b.valor, 
											b.fecha,
											b.estado,
											u.usuario,
   											d.nombreDependencia								
										FROM MA_Bien b 
										INNER JOIN MA_Dependencia d ON b.FK_Dependencia = d.PK_idDependencia
										INNER JOIN MA_Usuario u ON b.FK_Usuario = u.PK_idUsuario;

									END //

									DELIMITER //
									CREATE PROCEDURE AgregarObservacion (
										IN usuario INT,
										IN bien INT,
										IN asunto VARCHAR(100),
										IN informacion TEXT, 
										IN fechaObservacion DATE
									)
									BEGIN
										INSERT INTO PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion)
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
										FROM PA_BienPorUsuario bu 
										INNER JOIN MA_Usuario u ON bu.FK_Usuario = u.PK_idUsuario;
<<<<<<< HEAD
									END //
=======
									END //
>>>>>>> 4dca191fcd0ab9f340f9e5009ff8338063259500
