DROP TABLE BLOQUE;

CREATE TABLE BLOQUE (
id NUMBER(30) PRIMARY KEY,
hash VARCHAR(100),
time TIMESTAMP,
size_bloque NUMBER(30),  -- Esta tuvo que cambiar de nombre
miner VARCHAR(100),
extra_data_hex VARCHAR(100),
difficulty NUMBER(30),
gas_used NUMBER(30),
gas_limit NUMBER(30),
logs_bloom VARCHAR(512),
mix_hash VARCHAR(100),
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
block_id NUMBER(30),
index_transaccion NUMBER(30), -- Esta cambio de nombre
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
