https://www.multichain.com/getting-started/

Con este comando se crea la blockchain

`multichain-util create bdchain`

Con este comando se inicializa la blockchain

`multichaind bdchain -daemon`

El comando anterior da la informacion necesaria para conectarse a la blockchain

En la siguiente direccion se configura el username y el password para la API que se usara desde Java

`sudo nano /home/redes2/.multichain/bdchain/multichain.conf`

Al intentar conectarse con el comando multichaind bdchain@10.0.2.15:6487 deberia fallar por falta de permisos
Con el siguiente comando se conceden permisos de conexion a la direccion especificada

`sudo multichain-cli bdchain grant 1DGBAzCRrM4LRaYsj3NMz2oJVkEdvTkhH6ZYxQ connect`

Con el siguiente comando crearemos un nodo de conexion a la blockchain... creo

`multichaind bdchain@10.0.2.15:6487`

Para poder crear un asset necesitamos lo que creo que es la direccion original del asset
Con el siguiente comando vemos cual es esa direccion y esa sera la que asignemos al admin de oracle

`sudo multichain-cli bdchain listpermissions issue`

Con el siguiente comando creamos el asset, al momento de crearlo todas las coins (units) estaran disponibles
para la direccion original (with 1000000 units, each of which can be subdivided into 100 parts, sending it to itself)

`sudo multichain-cli bdchain issue 1KFH8XmH4jk6zyBYiEDuDPzGDhpm5XsSQcC25s bdcoin 1000000 0.01`

Con el siguiente comando se crean nueas direcciones dentro del mismo asset

`sudo multichain-cli bdchain getnewaddress`

Con este comando se le dan permisos para enviar y recibir a una direccion

`sudo multichain-cli bdchain grant 1WnKjmdgSoNBchtNxVjfUHnUJN7KEvMXoYNa4L send`

`sudo multichain-cli bdchain grant 1WnKjmdgSoNBchtNxVjfUHnUJN7KEvMXoYNa4L receive`

`sudo multichain-cli bdchain grant 1DGBAzCRrM4LRaYsj3NMz2oJVkEdvTkhH6ZYxQ connect`

Con el siguiente comando se envían units de una direccion a otra

`sudo multichain-cli dbchain sendassetfrom 1WnKjmdgSoNBchtNxVjfUHnUJN7KEvMXoYNa4L 1KFH8XmH4jk6zyBYiEDuDPzGDhpm5XsSQcC25s dbcoin 200`

Con el siguiente comando se ven las unidades actuales de una direccion

`sudo multichain-cli dbchain getaddressbalances 1WnKjmdgSoNBchtNxVjfUHnUJN7KEvMXoYNa4L`

Y CREO... que eso es todo lo que necesitamos