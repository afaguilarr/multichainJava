https://docs.oracle.com/cd/B19306_01/server.102/b14215/ldr_concepts.htm

Un ejemplo de lo que debemos hacer en SQL Loader.

Infile se remplaza por el nombre del csv creo.

El separador de registros es:`"str '|\n'"`, por lo que en nuestro caso sería `"str '\n'"`.

El separador de columnas es `','`, por lo que en nuestro caso sería `';'`.

Creeeo que se puede remover el optionally enclosed by.

`(col1...)` debe ser la lista de columnas que se va a llenar con los registros y columnas. 

Table por la tabla :O.

```
sqlldr 
load data 
 infile 'MuestraBloques.csv'  "str '\n'" 
 into table BLOQUE 
 fields terminated by ';' 
 (id,
  hash,
  time,
  size_bloque,
  miner,
  extra_data_hex,
  difficulty,
  gas_used,
  gas_limit,
  logs_bloom,
  mix_hash,
  nonce,
  receipts_root,
  sha3_uncles,
  state_root,
  total_difficulty,
  transactions_root,
  uncle_count,
  transaction_count,
  synthetic_transaction_count,
  call_count,
  synthetic_call_count,
  value_total,
  value_total_usd,
  internal_value_total,
  internal_value_total_usd,
  generation,
  generation_usd,
  uncle_generation,
  uncle_generation_usd,
  fee_total,
  fee_total_usd,
  reward,
  reward_usd)
 
 example.dat:
 hello,world,|
 james,bond,|
```

sqlldr control=C:\Users\Tiburoncin\IdeaProjects\multichainJava\src\main\java\otherApp\loadbloques.ctl log=C:\Users\Tiburoncin\IdeaProjects\multichainJava\src\main\java\otherApp\loadbloques.log