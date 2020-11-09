DROP TABLE BLOQUE;

CREATE TABLE BLOQUE (
id VARCHAR(30) PRIMARY KEY,
hash VARCHAR(100),
time TIMESTAMP,
size_bloque VARCHAR(100),  -- Esta tuvo que cambiar de nombre
miner VARCHAR(100),
extra_data_hex VARCHAR(100),
difficulty VARCHAR(100),
gas_used VARCHAR(100),
gas_limit VARCHAR(100),
logs_bloom VARCHAR(1000),
mix_hash VARCHAR(1000),
nonce VARCHAR(100),
receipts_root VARCHAR(100),
sha3_uncles VARCHAR(100),
state_root VARCHAR(100),
total_difficulty VARCHAR(100),
transactions_root VARCHAR(100),
uncle_count VARCHAR(100),
transaction_count VARCHAR(100),
synthetic_transaction_count VARCHAR(100),
call_count VARCHAR(100),
synthetic_call_count VARCHAR(100),
value_total VARCHAR(100),
value_total_usd VARCHAR(100),
internal_value_total VARCHAR(100),
internal_value_total_usd VARCHAR(100),
generation VARCHAR(100),
generation_usd VARCHAR(100),
uncle_generation VARCHAR(100),
uncle_generation_usd VARCHAR(100),
fee_total VARCHAR(100),
fee_total_usd VARCHAR(100),
reward VARCHAR(100),
reward_usd VARCHAR(100)
);


DROP TABLE TRANSACCION;

CREATE TABLE TRANSACCION (
block_id VARCHAR(30), -- Esta es una clave foranea hacia BLOQUE
CONSTRAINT clave_foranea_bloque FOREIGN KEY (block_id) REFERENCES BLOQUE(id),
index_transaccion VARCHAR(30), -- Esta cambio de nombre
hash VARCHAR(100),
time TIMESTAMP,
failed VARCHAR(5), -- 1 es true y 0 es false
type VARCHAR(100),
sender VARCHAR(100),
recipient VARCHAR(100),
call_count VARCHAR(30),
value VARCHAR(30),
value_usd FLOAT(30),
internal_value VARCHAR(30),
internal_value_usd VARCHAR(30),
fee VARCHAR(30),
fee_usd FLOAT(30),
gas_used VARCHAR(30),
gas_limit VARCHAR(30),
gas_price VARCHAR(30),
input_hex VARCHAR(1000),
nonce VARCHAR(30),
v VARCHAR(1000),
r VARCHAR(1000),
s VARCHAR(1000),
x FLOAT(30),
y FLOAT(30)
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
