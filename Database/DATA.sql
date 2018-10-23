-- ---------------------------------------------------
-- 23/10/2018 -- BY Miguel Cortegana -- PROJECT: Lavamass
-- Data inicial para poblar tablas recursivas
-- ---------------------------------------------------
USE lavamass;

/* Parámetros Generales - PARAMS */
/* Tipos de usuarios */
INSERT INTO params VALUES ('S', 'USERTYPE','SISTEMA');
INSERT INTO params VALUES ('C', 'USERTYPE','CLIENTE');
/* Unidades de medida */
INSERT INTO params VALUES ('KG', 'UNIDMEDIDA', 'KILOGRAMOS');
INSERT INTO params VALUES ('PAR', 'UNIDMEDIDA', 'PARES');
INSERT INTO params VALUES ('UNID', 'UNIDMEDIDA', 'PRENDA POR UNIDAD');
/* Estados de ordenes de trabajo */
INSERT INTO params VALUES ('OT001', 'OTSTATUS', 'GENERADO');
INSERT INTO params VALUES ('OT002', 'OTSTATUS', 'PENDIENTE');
INSERT INTO params VALUES ('OT003', 'OTSTATUS', 'PROCESO');
INSERT INTO params VALUES ('OT004', 'OTSTATUS', 'FINALIZADO');
INSERT INTO params VALUES ('OT005', 'OTSTATUS', 'ACABADO');
INSERT INTO params VALUES ('OT006', 'OTSTATUS', 'ENTREGADO');
INSERT INTO params VALUES ('OT007', 'OTSTATUS', 'ANULADO');
/* Estados de boletas */
INSERT INTO params VALUES ('BOL01', 'SELLSTATUS', 'GENERADO');
INSERT INTO params VALUES ('BOL02', 'SELLSTATUS', 'CANCELADO');
INSERT INTO params VALUES ('BOL03', 'SELLSTATUS', 'ANULADO');


