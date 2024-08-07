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
											nombrePerfil VARCHAR(25),
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
                                            correo VARCHAR(40),
                                            estadoAdmin VARCHAR(8),
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
											('BRAYAN ESTIVEN ALFONSO PALOMA', 1012916689, 'balfonsop', 1, 10, 1, 2),
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
                                            condicion VARCHAR (10),
											imagenuno VARCHAR(255),
											imagendos VARCHAR(255),
											imagentres VARCHAR(255),
											fecha DATETIME,
											fechaAdmin DATETIME,
											observacionAdmin TEXT,
											FK_Usuario INT,
											FK_Dependencia INT,
											FK_UsuarioAdmin INT,
											FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuario(PK_idUsuario) ON DELETE CASCADE,
											FOREIGN KEY (FK_UsuarioAdmin) REFERENCES MA_Usuario(PK_idUsuario) ON UPDATE CASCADE 
											);
                                            
											INSERT INTO MA_Bien (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, condicion, FK_UsuarioAdmin, imagenuno, imagendos, fecha) VALUES
											(1, 'NombreBien1', '1000', 'Descripción1', 1000, 1, 8, 'No reportado','Activo','1', 'Imagen1', 'Imagen1.1', '2024-04-07'),
											(2, 'NombreBien1', '2000', 'Descripción1', 1000, 2, 34, 'No reportado','Inactivo','1', 'Imagen1', 'Imagen1.1', '2024-04-07'),
											(3, 'NombreBien1', '3000', 'Descripción1', 1000, 2, 10, 'No reportado','Inactivo','1', 'Imagen1', 'Imagen1.1', '2024-04-07');

											-- Tabla de Bienes por Usuario
											CREATE TABLE IF NOT EXISTS PA_BienPorUsuario (
											PK_idBienPorUsuario INT AUTO_INCREMENT PRIMARY KEY,   
											FK_Usuario INT,
											FK_Bien BIGINT,
											asunto VARCHAR(100),
											informacion TEXT,
											fechaObservacion DATETIME,
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
                                                -- IN FK_UsuarioAdmin INT,
												IN estado VARCHAR(12),
												IN imagenuno VARCHAR(255),
												IN imagendos VARCHAR(255),
												IN fecha DATETIME,
                                                IN fechaAdmin DATETIME
												
											)
											BEGIN
												INSERT INTO MA_Bien (PK_Codigo, nombre, cedula, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagenuno, imagendos, fecha, fechaAdmin)
												VALUES (codigo, nombre, cedula, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagenuno, imagendos, fecha, fechaAdmin);
											END //

									DELIMITER //
									CREATE PROCEDURE AgregarObservacion (
										IN usuario INT,
										IN bien INT,
										IN asunto VARCHAR(100),
										IN informacion TEXT, 
										IN fechaObservacion DATETIME
									)
									BEGIN
										INSERT INTO PA_BienPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion)
										VALUES (usuario, bien, asunto, informacion, fechaObservacion);
									END //
                                    
                                    
                                    -- PROCEDIMIENTOS PARA LISTAR
                                    
									DELIMITER //

									CREATE PROCEDURE ListarBienesActivos()
									BEGIN
										SELECT 
											b.idBien,
											b.PK_Codigo,
											b.placa,
											b.nombre,
											b.valor,
											b.fecha,
                                            b.fechaAdmin,
											b.estado,
											b.observacionAdmin,
                                            b.condicion,
											u.usuario,
											a.usuario AS usuario_admin,
											d.nombredependencia
										FROM MA_Bien b 
										INNER JOIN MA_Dependencia d ON b.FK_Dependencia = d.PK_idDependencia
										INNER JOIN MA_Usuario u ON b.FK_Usuario = u.PK_idUsuario
										LEFT JOIN MA_Usuario a ON b.FK_UsuarioAdmin = a.PK_idUsuario
                                        WHERE condicion = 'Activo'; -- Cambié INNER JOIN a LEFT JOIN para el administrador, ya que podría ser nulo

									END //

									DELIMITER //

									CREATE PROCEDURE ListarBienesInactivos()
									BEGIN
										SELECT 
											b.idBien,
											b.PK_Codigo,
											b.placa,
											b.nombre,
											b.valor,
											b.fecha,
											b.fechaAdmin,
											b.estado,
											b.observacionAdmin,
                                            b.condicion,
											u.usuario,
											a.usuario AS usuario_admin,
											d.nombredependencia
										FROM MA_Bien b 
										INNER JOIN MA_Dependencia d ON b.FK_Dependencia = d.PK_idDependencia
										INNER JOIN MA_Usuario u ON b.FK_Usuario = u.PK_idUsuario
										LEFT JOIN MA_Usuario a ON b.FK_UsuarioAdmin = a.PK_idUsuario
                                        WHERE condicion = 'Inactivo'; -- Cambié INNER JOIN a LEFT JOIN para el administrador, ya que podría ser nulo

									END //
									DELIMITER ;


                                    DELIMITER //
                                    CREATE PROCEDURE ListarAdministradores()
									BEGIN
                                    SELECT 
                                    u.PK_idUsuario,
                                    u.usuario,
                                    p.nombrePerfil
                                    FROM MA_Usuario u
                                    INNER JOIN MA_Perfil p ON u.FK_Perfil = p.PK_idPerfil
                                    WHERE u.FK_Perfil = 1;
                                    END //
                                    
                                    DELIMITER //
                                    CREATE PROCEDURE ListarObservaciones()
                                    SELECT
										BU.asunto,
										BU.informacion,
										U.usuario,
										D.nombreDependencia
									FROM
										PA_BienPorUsuario BU
										INNER JOIN MA_Usuario U ON BU.FK_Usuario = U.PK_idUsuario
										INNER JOIN MA_Dependencia D ON U.FK_Dependencia = D.PK_idDependencia;
                                    END //    
                                    DELIMITER ;
                                    
									INSERT INTO MA_Usuario (nombre, cedula, usuario, FK_Cargo, FK_Dependencia, FK_TipoDocumento, FK_Perfil) VALUES 
									('CESAR SALAZAR', 1012916689, 'csalazart', 1, 10, 1, 2);
									UPDATE MA_Usuario set FK_Perfil = 1 WHERE PK_idUsuario=3;
                                    UPDATE MA_Usuario set FK_Perfil = 2 WHERE PK_idUsuario=3;
                                    
									UPDATE MA_Bien set condicion = 'Activo' WHERE PK_Codigo=5;
									UPDATE MA_Bien set FK_UsuarioAdmin = 1 WHERE PK_Codigo=1


                                    