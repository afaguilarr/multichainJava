https://docs.oracle.com/cd/B19306_01/server.102/b14215/ldr_concepts.htm

Un ejemplo de lo que debemos hacer en SQL Loader.

Infile se remplaza por el nombre del csv creo.

El separador de registros es:`"str '|\n'"`, por lo que en nuestro caso sería `"str '\n'"`.

El separador de columnas es `','`, por lo que en nuestro caso sería `';'`.

Creeeo que se puede remover el optionally enclosed by.

`(col1...)` debe ser la lista de columnas que se va a llenar con los registros y columnas. 

Table por la tabla :O.

```
load data
 infile 'example.dat'  "str '|\n'"
 into table example
 fields terminated by ',' optionally enclosed by '"'
 (col1 char(5),
  col2 char(7))
 
 example.dat:
 hello,world,|
 james,bond,|
```