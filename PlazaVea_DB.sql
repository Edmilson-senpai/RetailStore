CREATE DATABASE PlazaVeaBruh3
USE PlazaVeaBruh3

--DROP DATABASE PlazaVeaBruh3

SET DATEFORMAT DMY

CREATE TABLE TB_EMPLEADO(
ID_EMPLEADO varchar(10) PRIMARY KEY NOT NULL,
NOMBRE varchar(50) NULL,
APELLIDO varchar(50) NULL,
SEXO varchar(10) NULL,
DNI varchar(10) NULL,
DIRECCION varchar(50) NULL,
ID_CARGO varchar(10) REFERENCES TB_CARGO NOT NULL
)
--DATOS TABLA EMPLEADO--
INSERT INTO TB_EMPLEADO VALUES('AL003','Albert','Non Einsten','H','82506903','San Martín','CARG01'),
							  ('JO008','John','Arcane Wick','H','74561208','San Carlos','CARG02'),
							  ('TI041','Tiffany','Clark Soles','M','85746341','Calle Lima','CARG03'),
							  ('JE019','Jeffryna','Non Bexoz','H','02573819','Av. Grau','CARG04'),
							  ('EL073','Ella','Musk Stark','M','91846273','Las Casuarinas','CARG05')
------------------------------------------------------------------------
CREATE TABLE TB_USUARIO(
ID_USUARIO int IDENTITY(1,1) PRIMARY KEY NOT NULL,
ID_EMPLEADO varchar(10) REFERENCES TB_EMPLEADO NOT NULL,
NOM_USUARIO varchar(50) NULL,
CONTRASEÑA varchar(99) NULL
)
--DATOS TABLA USUARIO--
INSERT INTO TB_USUARIO VALUES('AL003','Alberto','12345'),
							 ('JO008','Jhonny','1234'),
							 ('TI041','Tiffy','123'),
							 ('JE019','Jeffy','4321'),
							 ('EL073','Musk','54321')
-------------------------------------------------------------------------
CREATE TABLE TB_ALMACEN(
ID_ALMACEN varchar(10) PRIMARY KEY NOT NULL,
NOM_ALMACEN varchar(10) NULL,
DIRECCION varchar(50) NULL,
NUM_ALMACEN varchar(10) NULL,
SUPERVISOR_ALMACEN varchar(50) NULL
)
--DATOS TABLA ALMACEN--
INSERT INTO TB_ALMACEN VALUES('LI001','Lima','Los Olivos','01','Phineas Gunn'),
							 ('IC002','Ica','San Carlos','02','Ben Tenison'),
							 ('NA003','Nazca','San Alberto','03','Marcus Becker'),
							 ('PI004','Piura','Sullana','04','Max Sales')
-------------------------------------------------------------------------
CREATE TABLE TB_CARGO(
ID_CARGO varchar(10) PRIMARY KEY NOT NULL,
CARGO varchar(50) NULL
)
--DATOS TABLA CARGO--
INSERT INTO TB_CARGO VALUES('CARG01','Jefe de almacén'),
						   ('CARG02','Estibador'),
						   ('CARG03','Operario pedidos'),
						   ('CARG04','Operario devoluciones'),
						   ('CARG05','Administrativo de recepcion')
-------------------------------------------------------------------------
CREATE TABLE TB_CATEGORIA(
ID_CATEGORIA varchar(10) PRIMARY KEY NOT NULL,
CATEGORIA varchar(50) NULL
)
--DATOS TABLA CATEGORIA--
INSERT INTO TB_CATEGORIA VALUES('CAT01','Abarrotes'),
							   ('CAT02','Bebidas'),
							   ('CAT03','Cuidado del bebé e infantil'),
							   ('CAT04','Cuidado personal y salud'),
							   ('CAT05','Limpieza'),
							   ('CAT06','Mascotas'),
							   ('CAT07','Desayonos'),
							   ('CAT08','Frutas y verduras'),
							   ('CAT09','Carnes, Aves y pescados'),
							   ('CAT10','Congelados'),
							   ('CAT11','Lácteos y huevos'),
							   ('CAT12','Quesos y friambres'),
							   ('CAT13','Panadería y pasteleria'),
							   ('CAT14','Comidas perparadas')
-------------------------------------------------------------------------
CREATE TABLE TB_ENTRADA(
ID_ENTRADA int PRIMARY KEY NOT NULL,
ID_PROVEEDOR varchar(10) REFERENCES TB_PROVEEDOR NOT NULL,
ID_ALMACEN varchar(10) REFERENCES TB_ALMACEN NOT NULL,
FEC_ENTRADA varchar(50) NULL,
NUM_FACTURA int NULL,
FEC_FACTURA varchar(50) NULL,
ID_EMPLEADO varchar(10) REFERENCES TB_EMPLEADO NOT NULL,
FACTURA varchar(10) NULL
)
--DATOS TABLA ENTRADA--
INSERT INTO TB_ENTRADA VALUES('1','COS002','LI001','12/05/23','1','12/10/23','JO008','879651324'),
							 ('2','COS002','LI001','11/04/23','2','11/09/23','EL073','745821903')

INSERT INTO TB_ENTRADA VALUES('7','GLO002','LI001','12/05/23','3','12/10/23','JO008','987465210'),
							 ('4','GLO002','LI001','10/09/23','4','18/09/23','EL073','786512415'),
							 ('5','GLO002','LI001','19/10/23','5','28/10/23','JO008','914862375'),
							 ('6','COS002','LI001','01/12/23','6','11/12/23','JO008','496718735')
-------------------------------------------------------------------------
select * from TB_DETA_ENTRADA
CREATE TABLE TB_DETA_ENTRADA(
ID_DETA_ENTRADA int IDENTITY(1,1) PRIMARY KEY NOT NULL,
ID_ENTRADA int REFERENCES TB_ENTRADA NOT NULL,
ID_PRODUCTO varchar(10) REFERENCES TB_PRODUCTO NOT NULL,
ID_MOTIVO int REFERENCES TB_MOTIVO NOT NULL,
CANTIDAD varchar(10) NULL,
PRECIO_COMPRA varchar(10) NULL,
IGV varchar(10) NULL
)
--DATOS TABLA DETALLE ENTRADA--
INSERT INTO TB_DETA_ENTRADA VALUES('1','AR047','5','10','24.90','18%'),
							      ('2','ME021','3','5','5.79','18%')

INSERT INTO TB_DETA_ENTRADA VALUES('3','AR047','5','10','24.90','18%'),
							      ('4','AC062','3','8','5.79','18%'),
							      ('5','AC062','5','18','5.79','18%'),
							      ('6','AC062','3','19','5.79','18%')
/*
SELECT TB_ENTRADA.ID_ENTRADA, TB_ENTRADA.ID_PROVEEDOR, TB_ENTRADA.ID_ALMACEN, TB_ENTRADA.ID_EMPLEADO, TB_ENTRADA.FEC_ENTRADA, TB_ENTRADA.NUM_FACTURA, TB_ENTRADA.FEC_FACTURA, TB_ENTRADA.FACTURA, TB_DETA_ENTRADA.ID_PRODUCTO, TB_DETA_ENTRADA.ID_MOTIVO, TB_DETA_ENTRADA.CANTIDAD, TB_DETA_ENTRADA.PRECIO_COMPRA
FROM TB_ENTRADA
INNER JOIN TB_DETA_ENTRADA ON TB_ENTRADA.ID_ENTRADA = TB_DETA_ENTRADA.ID_ENTRADA;
*/
-------------------------------------------------------------------------
CREATE TABLE TB_SALIDA(
ID_SALIDA int PRIMARY KEY NOT NULL,
ID_ALMACEN varchar(10) REFERENCES TB_ALMACEN NOT NULL,
FEC_AUTORIZACION_ADMIN varchar(50) NULL,
ID_EMPLEADO varchar(10) REFERENCES TB_EMPLEADO NOT NULL,
FEC_ENTREGA varchar(50) NULL,
PERSONA_RECIBE varchar(10) NULL
)
--DATOS TABLA SALIDA--
INSERT INTO TB_SALIDA VALUES('1','IC002','11/05/23','JE019','13/05/23','Jeff'),
							('2','IC002','14/07/23','JE019','16/07/23','Juan')

INSERT INTO TB_SALIDA VALUES('3','IC002','11/05/23','JE019','13/08/23','Jeff'),
							('4','IC002','14/07/23','JE019','14/09/23','Juan'),
							('5','IC002','14/07/23','JE019','15/10/23','Jeff'),
							('6','IC002','14/07/23','JE019','17/11/23','Juan')
-------------------------------------------------------------------------
CREATE TABLE TB_DETA_SALIDA(
ID_DETA_SALIDA int IDENTITY(1,1) PRIMARY KEY NOT NULL,
ID_SALIDA int REFERENCES TB_SALIDA NOT NULL,
ID_PRODUCTO varchar(10) REFERENCES TB_PRODUCTO NOT NULL,
ID_MOTIVO int REFERENCES TB_MOTIVO NOT NULL,
CANT_SOLICITADA varchar(10) NULL,
CANT_ENTREGADA varchar(10) NULL
)
--DATOS TABLA DETALLE SALIDA--
INSERT INTO TB_DETA_SALIDA VALUES('1','AC062','3','40','40'),
							     ('2','GO093','3','51','51')

INSERT INTO TB_DETA_SALIDA VALUES('3','AC062','3','40','40'),
							     ('4','GO093','4','37','37'),
							     ('5','AC062','3','27','27'),
							     ('6','GO093','5','15','15')

SELECT TB_SALIDA.ID_SALIDA, TB_SALIDA.ID_ALMACEN, TB_SALIDA.FEC_AUTORIZACION_ADMIN, TB_SALIDA.ID_EMPLEADO, TB_SALIDA.FEC_ENTREGA, TB_SALIDA.PERSONA_RECIBE, TB_DETA_SALIDA.ID_PRODUCTO, TB_DETA_SALIDA.ID_MOTIVO, TB_DETA_SALIDA.CANT_SOLICITADA, TB_DETA_SALIDA.CANT_ENTREGADA
FROM TB_SALIDA
INNER JOIN TB_DETA_SALIDA ON TB_SALIDA.ID_SALIDA = TB_DETA_SALIDA.ID_SALIDA
-------------------------------------------------------------------------
CREATE TABLE TB_MOTIVO(
ID_MOTIVO int IDENTITY(1,1) PRIMARY KEY NOT NULL,
MOTIVO varchar(50) NULL,
DESCRIPCION varchar(50) NULL
)
--DATOS TABLA MOTIVO--
INSERT INTO TB_MOTIVO VALUES('AGREGAR MOTIVO','Nuevo campo'),
							('Defectuoso','Posee alguna(s) imperfección'),
							('Caducado','Perdida eficacia o virtualidad'),
							('Error de compra','Producto deseado no obtenido'),
							('Mal estado','Fuera de los terminos establecidos'),
							('Cantidad erronea','Mayor o menor catidad de la estipulada'),
							('Pedido erroneo','Error de solicitud o recepcion'),
							('Compra','Productos comprados'),
							('Promocion de compra','Extra incluido dentro de una compra')
-------------------------------------------------------------------------
CREATE TABLE TB_PRODUCTO(
ID_PRODUCTO varchar(10) PRIMARY KEY NOT NULL,
ID_CATEGORIA varchar(10) REFERENCES TB_CATEGORIA NOT NULL,
ID_PROVEEDOR varchar(10) REFERENCES TB_PROVEEDOR NOT NULL,
ID_UBI_ALMACEN varchar(10) REFERENCES TB_UBICACION NOT NULL,
ID_ALMACEN varchar(10) REFERENCES TB_ALMACEN NOT NULL,
SUBCATEGORIA varchar(50) NULL,
NOMBRE varchar(50) NULL,
DESCRIPCION varchar(50),
PRECIO decimal(8, 2)NULL,
MONEDA varchar(10) NULL,
CODIGO_BARRAS varchar(10) NULL,
STOCK int NULL,
CANT_MINSTOCK varchar(10) NULL,
CANT_MAXSTOCK varchar(10) NULL,
PRECIO_COMPRA decimal(8, 2) NULL,
FEC_CADUCIDAD varchar(50) NULL,
IMAGEN varchar(10) NULL,
MARCA varchar(10) NULL,
ESTADO varchar(10) NULL,
TAMAÑO varchar(10) NULL,
PESO varchar(10) NULL
)
--DATOS TABLA PRODUCTO--
INSERT INTO TB_PRODUCTO VALUES('AR047','CAT01','COS002','UAIC01','NA003','Arroz','Arroz COSTEÑO','Arroz Extra COSTEÑO','20.42','Soles','5901212347','40','10','60','24.90','10/01/2025','.jpg','COSTEÑO','Nuevo','Grande','5kg'),
							  ('AC062','CAT01','GLO002','UAIC01','NA003','Aceite','Aceite PRIMOR','Aceite Vegetal PRIMOR Premium','8.96','Soles','4815648762','30','10','60','10.93','11/02/2025','.jpg','PRIMOR','Nuevo','Mediano','900ml'),
							  ('ME021','CAT01','COS002','UAIC01','NA003','Menestras','Lenteja COSTEÑO','Lenteja COSTEÑO prime','4.75','Soles','3245988721','38','10','60','5.79','12/03/2025','.jpg','COSTEÑO','Nuevo','Pequeño','500g'),
							  ('GO093','CAT01','GLO002','UAIC01','NA003','Golosinas','Chocolates BELLS','Galleta con Chocolate BELLS','13.86','Soles','8274516793','26','10','60','16.90','13/04/2025','.jpg','BELLS','Nuevo','Pequeño','500g')
							  -->ABARROTES
							  
							  -------------------------------------------------------------
INSERT INTO TB_PRODUCTO VALUES('CE047','CAT02', 'EV069', 'UAIC02', 'IC002', 'Licores', 'Cerveza Cristal', 'Cerveza botella vidrio', '6.00', 'Soles', '5901234123', '40', '10', '60', '3.50', '2025-01-10', '.jpg', 'CRISTAL', 'Nuevo', 'Grande', '0.65kg'),
							  ('IN012','CAT02', 'EV069', 'UAIC02', 'IC002', 'Gaseosas', 'Inca Kola', 'Gaseosa Inca Kola', '5.00', 'Soles', '1234567890', '50', '15', '70', '3.50', '2024-12-31', '.jpg', 'Inca Kola', 'Nuevo', 'Grande', '0.75kg'),
							  ('AG098','CAT02', 'EV069', 'UAIC02', 'IC002', 'Aguas Minerales', 'San Mateo', 'Agua Mineral San Mateo', '2.50', 'Soles', '9876543210', '30', '5', '50', '1.75', '2024-10-15', '.jpg', 'San Mateo', 'Nuevo', 'Pequeño', '0.5kg'),
						  	  ('PI045','CAT02', 'EV069', 'UAIC02', 'IC002', 'Licores', 'Pisco Sour', 'Bebida Alcohólica Pisco Sour', '12.00', 'Soles', '4567890123', '20', '5', '30', '5.50', '2024-09-30', '.jpg', 'MarcaX', 'Nuevo', 'Mediano', '0.7kg'),
							  ('CO065','CAT02', 'EV069', 'UAIC02', 'IC002', 'Gaseosas', 'Coca-Cola', 'Gaseosa Coca-Cola', '4.00', 'Soles', '6543210987', '60', '20', '80', '2.50', '2025-03-15', '.jpg', 'Coca-Cola', 'Nuevo', 'Grande', '1kg'),
							  ('AG089','CAT02', 'EV069', 'UAIC02', 'IC002', 'Aguas Minerales', 'San Luis', 'Agua Mineral San Luis', '3.00', 'Soles', '8901234567', '35', '10', '50', '1.25', '2024-11-20', '.jpg', 'San Luis', 'Nuevo', 'Mediano', '0.6kg')
							  -->BEBIDAS
							  
							  -------------------------------------------------------------
INSERT INTO TB_PRODUCTO VALUES('LA061','CAT05','ZO049','UAIC03','PI004','Cuidado del hogar','Lavavajillas Magia','Detergente líquido para lavavajillas','12.50','Soles','5896012345','50','10','100','14.99','01/01/2024','.jpg','Magia','Nuevo','Mediano','1L'),
							  ('PA012','CAT05','ZO049','UAIC03','PI004','Baños','Papel Higiénico Suave','Papel higiénico suave y absorbente','6.75','Soles','4823456789','80','20','200','8.99','01/01/2025','.jpg','Suave','Nuevo','Grande','4 rollos'),
							  ('JA089','CAT05','ZO049','UAIC03','PI004','Cocina','Jabón en Barra Frescura','Jabón en barra multiusos','2.95','Soles','3801234567','120','30','300','4.50','01/01/2025','.jpg','Frescura','Nuevo','Pequeño','150g'),
							  ('DE089','CAT05','ZO049','UAIC03','PI004','Hogar','Desinfectante Purifico','Desinfectante de superficies','8.25','Soles','5801234567','60','15','150','10.99','01/01/2024','.jpg','Purifico','Nuevo','Mediano','500ml');
							  -->LIMPIEZA
							  /*
							  -------------------------------------------------------------
							  ('','CAT06','ZO049','UAIC04','NA003','Perros','','','','','','','','','','','','','','',''),
							  ('','CAT06','ZO049','UAIC04','NA003','Gatos','','','','','','','','','','','','','','',''),
							  ('','CAT06','ZO049','UAIC04','NA003','Perros','','','','','','','','','','','','','','',''),
							  ('','CAT06','ZO049','UAIC04','NA003','Gatos','','','','','','','','','','','','','','',''),
							  -->MASCOTAS*/
-------------------------------------------------------------------------
CREATE TABLE TB_PROVEEDOR(
ID_PROVEEDOR varchar(10) PRIMARY KEY NOT NULL,
ID_TIPO_PROV varchar(10) REFERENCES TB_TIPO_PROVEEDOR NOT NULL,
NOMBRE varchar(50) NULL,
DIRECCION varchar(50) NULL,
NUMERO int NULL,
CORREO varchar(50) NULL,
RUC varchar(50) NULL
)
--DATOS TABLA PROVEEDOR--
INSERT INTO TB_PROVEEDOR VALUES('COS002','TPR02','COSTEÑO','Lima, San Borja','958632147','costeño@gmail.com','78455612369'),
							   ('GLO002','TPR02','Gloria','Lima, Los molinos','987654321','gloria@gmail.com','85416352749')
-------------------------------------------------------------------------
CREATE TABLE TB_TIPO_PROVEEDOR(
ID_TIPO_PROV varchar(10) PRIMARY KEY NOT NULL,
TIPO_PROVEEDOR varchar(10) NULL,
DESCRIPCION varchar(10) NULL
)
--DATOS TABLA TIPO PROVEEDOR--
INSERT INTO TB_TIPO_PROVEEDOR VALUES('TPR01','Persona',''),
							        ('TPR02','Empresa','')
-------------------------------------------------------------------------
CREATE TABLE TB_UBICACION(
ID_UBI_ALMACEN varchar(10) PRIMARY KEY NOT NULL,
SECTOR varchar(10) NULL,
ESTANTE varchar(10) NULL
)
--DATOS TABLA UBICACION--
INSERT INTO TB_UBICACION VALUES('UAIC01','Este','General'),
							   ('UAIC02','Oeste','General'),
							   ('UAIC03','Norte','General'),
							   ('UAIC04','Sur','General')
----------------------------------------------------------------------------------------------------------------------------------

SELECT TB_ENTRADA.ID_ENTRADA, TB_ENTRADA.FEC_ENTRADA, TB_DETA_ENTRADA.ID_PRODUCTO, TB_DETA_ENTRADA.CANTIDAD
FROM TB_ENTRADA
INNER JOIN TB_DETA_ENTRADA ON TB_ENTRADA.ID_ENTRADA = TB_DETA_ENTRADA.ID_ENTRADA
WHERE TB_ENTRADA.FEC_ENTRADA >= '01/04/23' AND TB_ENTRADA.FEC_ENTRADA <= '20/05/23';

SELECT
	ID_PRODUCTO
FROM TB_ENTRADA
WHERE FEC_ENTRADA BETWEEN '2005-01-01' AND '2005-07-29'

SELECT
	ID_PRODUCTO,
	COUNT(*) AS TOTAL
FROM TB_DETA_ENTRADA de
INNER JOIN 
    TB_ENTRADA e ON de.ID_ENTRADA = e.ID_ENTRADA
WHERE e.FEC_ENTRADA >= '2023-01-01' AND e.FEC_ENTRADA <= '2023-06-11'
GROUP BY ID_PRODUCTO 
HAVING COUNT(*) > 0