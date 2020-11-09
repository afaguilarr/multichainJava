DROP TABLE BLOQUE;

CREATE TABLE BLOQUE (
id NUMBER(30) PRIMARY KEY,
hash VARCHAR(100),
time TIMESTAMP,
size_bloque NUMBER(30),  -- Esta tuvo que cambiar de nombre
miner VARCHAR(100),
extra_data_hex VARCHAR(100),
difficulty VARCHAR(100),
gas_used NUMBER(30),
gas_limit NUMBER(30),
logs_bloom VARCHAR(1000),
mix_hash VARCHAR(1000),
nonce NUMBER(30),
receipts_root VARCHAR(100),
sha3_uncles VARCHAR(100),
state_root VARCHAR(100),
total_difficulty NUMBER(30),
transactions_root VARCHAR(100),
uncle_count NUMBER(30),
transaction_count NUMBER(30),
synthetic_transaction_count NUMBER(30),
call_count NUMBER(30),
synthetic_call_count NUMBER(30),
value_total NUMBER(30),
value_total_usd NUMBER(30),
internal_value_total NUMBER(30),
internal_value_total_usd NUMBER(30),
generation NUMBER(30),
generation_usd NUMBER(30),
uncle_generation NUMBER(30),
uncle_generation_usd NUMBER(30),
fee_total NUMBER(30),
fee_total_usd FLOAT(30),
reward NUMBER(30),
reward_usd NUMBER(30)
);


DROP TABLE TRANSACCION;

CREATE TABLE TRANSACCION (
block_id NUMBER(30), -- Esta es una clave foranea hacia BLOQUE
CONSTRAINT clave_foranea_bloque FOREIGN KEY (block_id) REFERENCES BLOQUE(id),
index_transaccion NUMBER(30) PRIMARY KEY, -- Esta cambio de nombre
hash VARCHAR(100),
time TIMESTAMP,
failed NUMBER(1,0), -- 1 es true y 0 es false
type VARCHAR(100),
sender VARCHAR(100),
recipient VARCHAR(100),
call_count NUMBER(30),
value NUMBER(30),
value_usd NUMBER(30),
internal_value NUMBER(30),
internal_value_usd NUMBER(30),
fee NUMBER(30),
fee_usd FLOAT(30),
gas_used NUMBER(30),
gas_limit NUMBER(30),
gas_price NUMBER(30),
input_hex VARCHAR(200),
nonce NUMBER(30),
v VARCHAR(100),
r VARCHAR(100),
s VARCHAR(100),
x NUMBER(30),
y NUMBER(30)
);

INSERT INTO BLOQUE(id, miner, time) values('1','holi', TO_DATE('09/18/20 11:30', 'mm/dd/yy hh24:mi'));
INSERT INTO BLOQUE(id, miner, time) values('2','holi', TO_DATE('09/19/20 11:30', 'mm/dd/yy hh24:mi'));
INSERT INTO BLOQUE(id, miner, time) values('3','otro miner', TO_DATE('09/20/20 11:30', 'mm/dd/yy hh24:mi'));
INSERT INTO BLOQUE(id, miner, time) values('4','otro miner mas', TO_DATE('09/21/20 11:30', 'mm/dd/yy hh24:mi'));
INSERT INTO BLOQUE(id, miner, time) values('5','otro miner mas mas', TO_DATE('09/22/20 11:30', 'mm/dd/yy hh24:mi'));

INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(1, 1, 10000);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(1, 2, 1000);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(2, 3, 5000);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(2, 4, 500);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(3, 5, 7000);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(4, 6, 10000);
INSERT INTO TRANSACCION(block_id, index_transaccion,value_usd) values(1, 7, 0);

SELECT * FROM BLOQUE;
SELECT * FROM TRANSACCION;

SELECT TRANSACCION.value_usd FROM TRANSACCION
INNER JOIN BLOQUE
ON BLOQUE.id = TRANSACCION.block_id
WHERE BLOQUE.miner = 'holi' AND TRANSACCION.value_usd > 0 AND
TO_CHAR(BLOQUE.time,'hh24:mi') BETWEEN '03:29' AND '21:30';
