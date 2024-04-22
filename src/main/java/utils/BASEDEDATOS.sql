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
							
							-- Tabla de Usuarios Independiente
							CREATE TABLE IF NOT EXISTS MA_Administradores (
								PK_idAdministrador INT AUTO_INCREMENT PRIMARY KEY,
								usuario VARCHAR(15),
                                permiso VARCHAR(2),
								estado VARCHAR(8)
							);

							CREATE TABLE IF NOT EXISTS MA_Dependencias (
								PK_idDependencia INT AUTO_INCREMENT PRIMARY KEY,
								codigo INT UNIQUE,
								nombreDependencia VARCHAR(130) UNIQUE
							);

							-- Tabla de Bienes
							CREATE TABLE IF NOT EXISTS MA_Bienes (
								idBien INT AUTO_INCREMENT PRIMARY KEY,
								PK_Codigo BIGINT UNIQUE,
								nombre VARCHAR(50),
								placa INT UNIQUE,
								descripcion VARCHAR(150),
								valor BIGINT UNIQUE,
								FK_Usuario INT,
								FK_Dependencia INT,
								estado VARCHAR(12),
								imagen1 VARCHAR(50),
								imagen2 VARCHAR(50),
								FOREIGN KEY (FK_Dependencia) REFERENCES MA_Dependencias(PK_idDependencia) ON UPDATE CASCADE,
								FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuarios(PK_idUsuario) ON DELETE CASCADE 
							);

							-- Tabla de Bienes por Usuario
							CREATE TABLE IF NOT EXISTS PA_BienesPorUsuario (
								PK_idBienPorUsuario INT AUTO_INCREMENT PRIMARY KEY,   
								FK_Usuario INT,
								FK_Bien BIGINT,
								asunto VARCHAR(25),
								informacion VARCHAR(200),
								fechaObservacion DATE NOT NULL,
								FOREIGN KEY (FK_Usuario) REFERENCES MA_Usuarios(PK_idUsuario), 
								FOREIGN KEY (FK_Bien) REFERENCES MA_Bienes(PK_Codigo) ON DELETE CASCADE
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
							   d.nombreDependencia,
							   u.dependencia,
							   b.valor 
						FROM MA_Bienes b 
						INNER JOIN MA_Dependencias d ON b.FK_Dependencia = d.PK_idDependencia
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
						   ('NombreUsuario3', 'usuario3', 456789123, 'contrasena3', 3, 'Cargo3', 'Contrato3', 'INVIMA'),
						   ('NombreUsuario4', 'usuario4', 456789123, 'contrasena4', 3, 'Cargo4', 'Contrato4', 'INVIMA');

                           
                           
                    INSERT INTO MA_Administradores (usuario, estado)
					VALUES ('usuario1', 'Activo'),      
						   ('usuario4', 'Inactivo');
                           
					INSERT INTO MA_Dependencias (codigo, nombreDependencia) VALUES
					(1000, 'Dirección General'),
					(1001, 'Grupo de Comunicaciones'),
					(1050, 'Oficina Asesora de Planeación'),
					(1051, 'Grupo de Sistemas Integrados de Gestión'),
					(1052, 'Grupo de Proyectos presupuesto y Estadística'),
					(1100, 'Oficina Asesora Jurídica'),
					(1101, 'Grupo de Apoyo Jurídico Institucional'),
					(1102, 'Grupo de Apoyo Reglamentario'),
					(1103, 'Grupo de Cobro Persuasivo y Coactivo'),
					(1150, 'Oficina de Control Interno'),
					(1200, 'Oficina de Laboratorios y Control de Calidad.'),
					(1201, 'Grupo de Laboratorio de Organismos Genéticamente Modificados'),
					(1202, 'Grupo de Laboratorio de Productos Biológicos'),
					(1204, 'Grupo de Laboratorio Físico-Mecánico de Dispositivos Médicos y Otras tecnologías'),
					(1205, 'Grupo de Laboratorio Fisicoquímico de Alimentos y Bebidas'),
					(1206, 'Grupo de Laboratorio de Microbiología de Alimentos y Bebidas'),
					(1207, 'Grupo Red de Laboratorios y Calidad'),
					(1250, 'Oficina de Tecnologías de la Información.'),
					(1251, 'Grupo Gestión de Información.'),
					(1252, 'Grupo de Informática.'),
					(1300, 'Oficina de Atención al Ciudadano.'),
					(1301, 'Grupo de Tramites y Servicios'),
					(1302, 'Grupo de Procesos y Reclamaciones'),
					(1350, 'Oficina de Asuntos Internacionales.'),
					(1351, 'Grupo de Admisibilidad Sanitaria y Aprovechamiento de Mercados Internacionales.'),
					(1352, 'Grupo de Cooperación y Relacionamiento.'),
					(2000, 'Secretaria General'),
					(2100, 'Grupo de Control Disciplinario Interno.'),
					(2150, 'Grupo de Gestión Administrativa.'),
					(2200, 'Grupo de Gestión Contractual.'),
					(2250, 'Grupo de Gestión Documental y Correspondencia.'),
					(2300, 'Grupo de Soporte Tecnológico.'),
					(2350, 'Grupo de Talento Humano.'),
					(2351, 'Área de Nómina.'),
					(2352, 'Área de Capacitación.'),
					(2353, 'Área de Carrera Administrativa.'),
					(2354, 'Área de Bienestar.'),
					(2355, 'Área Seguridad y Salud en el Trabajo- Salud Ocupacional.'),
					(2356, 'Área de Archivo.'),
					(2400, 'Grupo de Tesorería.'),
					(2450, 'Grupo Financiero y Presupuestal.'),
					(3000, 'Dirección de Medicamentos y Productos Biológicos'),
					(3050, 'Grupo de Apoyo Administrativo a la Dirección.'),
					(3100, 'Grupo de Apoyo de las Salas Especializadas de la Comision Revisora de la Dirección de Medicamentos y Productos Biologicos'),
					(3150, 'Grupo de Articulación y Apoyo Técnico a la Dirección.'),
					(3200, 'Grupo de Publicidad de la Dirección de Medicamentos y Productos Biológicos.'),
					(3250, 'Grupo de Registros Sanitarios de Fito terapéuticos, Medicamentos Homeopáticos y Suplementos Dietarios.'),
					(3300, 'Grupo de Registros Sanitarios de Medicamentos de Síntesis Química.'),
					(3350, 'Grupo Técnico de Medicamentos y Productos Biológicos.'),
					(3400, 'Grupo de Farmacovigilancia.'),
					(3450, 'Grupo de Investigación Clínica.'),
					(3500, 'Grupo Legal De La Direccion De Medicamentos Y Productos Biologicos'),
					(3550, 'Grupo de Registros Sanitarios de Medicamentos Biológicos'),
					(4000, 'Dirección de Alimentos y Bebidas'),
					(4050, 'Grupo de Registros Sanitarios de Alimentos y Bebidas.'),
					(4100, 'Grupo del Sistema de Análisis de Riesgos Químicos en Alimentos y Bebidas.'),
					(4150, 'Grupo Técnico de articulación y Coordinación con las Entidades Territoriales de Salud.'),
					(4200, 'Grupo Técnico de Carnes.'),
					(4250, 'Grupo Técnico de Vigilancia Epidemiológica de Alimentos y Bebidas.'),
					(5000, 'Dirección de Dispositivos Médicos y Otras Tecnologías'),
					(5050, 'Grupo de Registros Sanitarios de Dispositivos Médicos y Otras Tecnologías.'),
					(5100, 'Grupo de Tecno vigilancia.'),
					(5150, 'Grupo de Vigilancia Epidemiológica de Dispositivos Médicos y Otras Tecnologías.'),
					(5200, 'Grupo Técnico de Dispositivos Médicos y Otras Tecnologías'),
					(6000, 'Dirección de Cosméticos, Aseo, Plaguicidas y Productos de Higiene Doméstica.'),
					(6050, 'Grupo de Registros Sanitarios y Asignación de Notificación Sanitaria Obligatoria de Cosméticos, Aseo, Plaguicidas y Productos de Higiene Doméstica.'),
					(6100, 'Grupo Técnico de Cosméticos, Aseo, Plaguicidas Y Productos de Higiene Doméstica.'),
					(7000, 'Dirección de Operaciones Sanitarias'),
					(7050, 'Grupo de Inspección Vigilancia y Control.'),
					(7100, 'Grupo de Apoyo a Nariño.'),
					(7150, 'Grupo de Apoyo Operativo.'),
					(7200, 'Grupo de Autorizaciones y Licencias para Importación y Exportación.'),
					(7250, 'Grupo de Control en Puertos, Aeropuertos y Pasos de Frontera.'),
					(7251, 'Aeropuerto Bogotá'),
					(7252, 'Aeropuerto Palmira'),
					(7253, 'Aeropuerto Rionegro'),
					(7254, 'Puerto Maritimo Santa Marta'),
					(7255, 'Puerto Fluvial Barranquilla'),
					(7256, 'Puerto Maritimo Buenaventura'),
					(7257, 'Puerto Maritimo Cartagena'),
					(7258, 'Paso Fronterizo y Puerto Fluvial Leticia'),
					(7259, 'Paso Fronterizo Arauca'),
					(7260, 'Paso Fronterizo Paraguachon'),
					(7261, 'Paso Fronterizo Cúcuta'),
					(7262, 'Paso Fronterizo San Miguel'),
					(7263, 'Paso Fronterizo Ipiales'),
					(7300, 'Grupo de Trabajo Territorial Costa Caribe 1.'),
					(7301, 'Grupo de Trabajo Territorial Costa Caribe 2.'),
					(7302, 'Grupo de Trabajo Territorial Centro Oriente 1.'),
					(7303, 'Grupo de Trabajo Territorial Centro Oriente 2.'),
					(7304, 'Grupo de Trabajo Territorial Centro Oriente 3.'),
					(7305, 'Grupo de Trabajo Territorial Occidente 1.'),
					(7306, 'Grupo de Trabajo Territorial Occidente 2.'),
					(7307, 'Grupo de Trabajo Territorial Orinoquia.'),
					(7308, 'Grupo de Trabajo Territorial Eje Cafetero.'),
					(7350, 'Grupo de Inspeccion Vigilancia y Control de Trafico Postal y Mensajeria Expresa'),
					(8000, 'Dirección de Responsabilidad Sanitaria'),
					(8050, 'Grupo de Plantas de Beneficio, Derivados Cárnicos y Lácteos de la Dirección de Responsabilidad Sanitaria.'),
					(8100, 'Grupo de Procesos Sancionatorios de Alimentos y Bebidas.'),
					(8150, 'Grupo de Procesos Sancionatorios de Medicamentos, insumos y otros Productos'),
					(8200, 'Grupo de Procesos sancionatorios de Publicidad.'),
					(8250, 'Grupo de Recursos, Calidad y Apoyo a la Gestión'),
					(1105, 'Grupo de Representación Judicial y Extrajudicial en Acciones Constitucionales'),
					(1106, 'Grupo de Representación Judicial y Extrajudicial en Procesos Contencioso Administrativos y otros'),
					(1208, 'Grupo Laboratorio Fisicoquímico de Productos Farmacéuticos y Otras Tecnologías'),
					(1209, 'Grupo de Laboratorio de Microbiología de Productos Farmacéuticos y Otras Tecnologías'),
					(4300, 'Grupo Técnico de Alimentos y Bebidas'),
					(7264, 'Puerto Fluvial de Barrancabermeja'),
					(1053, 'Grupo unidad de Riesgos'),
					(3600, 'Grupo de Registros Sanitarios de Medicamentos con Condicion Especial de Riesgo'),
					(2500, 'Grupo de Unidad de Reaccion Inmediata'),
					(8300, 'Grupo de Secretaria Tecnica'),
					(3750, 'Grupo de Registros Sanitarios de Medicamentos Biológicos y Radiofármacos'),
					(3700, 'Grupo de Registros Sanitarios de Fitoterapeúticos, Medicamentos Homeopáticos y Productos Dietarios.'),
					(3650, 'Grupo de Apoyo Administrativo'),
					(3800, 'Grupo de Articulación y Apoyo Técnico a la Inspección, Vigilancia y Control'),
					(3850, 'Grupo de Bancos de Sangre'),
					(1054, 'Grupo de Gestión y Mejoramiento Organizacional'),
					(1107, 'Grupo de Asesoría Juridica y Conceptos'),
					(1108, 'Grupo de Instrucción Disciplinaria'),
					(1210, 'Grupo de Gestión - Despacho de la Oficina de Laboratorios y Control de Calidad'),
					(3900, 'Grupo de Registros Sanitarios de Medicamentos de Síntesis Química de fabricación nacional'),
					(3950, 'Grupo de Registros Sanitarios de Medicamentos de Síntesis Química de importados'),
					(4350, 'Grupo de Autorizaciones de Comercialización de Alimentos y Bebidas'),
					(4400, 'Grupo técnico de Inspección y Vigilancia y control de Alimentos y Bebidas'),
					(4450, 'Grupo Técnico de Carnes y Productos Cárnicos Comestibles'),
					(5250, 'Grupo de Vigilancia Post-Comercialización'),
					(5300, 'Grupo de Componentes Anatómicos'),
					(5350, 'Grupo de Investigación Clínica y Apoyo a la Sala Especializada de Dispositivos Médicos y Reactivos de Diagnóstico In Vitro'),
					(7309, 'Grupo de Trabajo Territorial Apoyo a Nariño'),
					(8350, 'Grupo de Procesos Sancionatorios de Plantas de Beneficio, Cárnicos y Lácteos'),
					(8400, 'Grupo de Recursos');
                    
					INSERT INTO MA_Bienes (PK_Codigo, nombre, placa, descripcion, valor, FK_Usuario, FK_Dependencia, estado, imagen1, imagen2)
					VALUES (1, 'NombreBien1', '1000', 'Descripción1', 1000, 1, 1, 'No reportado', 'Imagen1', 'Imagen1.1'),
						   (2, 'NombreBien2', '2000', 'Descripción2', 2000, 2, 2, 'Reportado', 'Imagen2', 'Imagen2.1'),
						   (3, 'NombreBien3', '3000', 'Descripción3', 3000, 3, 3, 'Reportado', 'Imagen3', 'Imagen3.1');

					INSERT INTO PA_BienesPorUsuario (FK_Usuario, FK_Bien, asunto, informacion, fechaObservacion)
					VALUES (1, 1, 'Asunto1', 'Información1', '2024-04-07'),
						   (2, 2, 'Asunto2', 'Información2', '2024-04-08'),
						   (3, 3, 'Asunto3', 'Información3', '2024-04-09');
                           
