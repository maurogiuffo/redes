## Trabajo Practico Final - Redes

### Builds

En la carpeta builds se encuentran los ejecutables del servidor y cliente. Se pueden iniciar utilizando los archivos .bat que los acompañan.

### Source code

El proyecto esta dividido en 2 packages:

**Main class aplicacion Cliente:**

https://github.com/maurogiuffo/redes/blob/master/tp-sockets/src/main/java/org/client/MainClient.java


**Main class aplicacion Servidor:**

https://github.com/maurogiuffo/redes/blob/master/tp-sockets/src/main/java/org/server/Server.java



### Investigacion
⦁	¿Qué es un puerto? 
La noción de puerto, es introducida por la capa de transporte para distinguir entre los distintos destinos, dentro del mismo host, al que va dirigida la información.
La capa de red solamente necesita, para dirigir la información entre dos ordenadores, las direcciones IP del origen y el destino. La capa de transporte añade la noción de puerto.
Un ordenador puede estar ejecutando a la vez varios procesos distintos, por ello no es suficiente indicar la dirección IP del destino, además hay que especificar el puerto al que va destinado el mensaje.
Cada aplicación utiliza un número de puerto distinto. Cuando una aplicación está esperando un mensaje, lo hace en un puerto determinado, se dice que está "escuchando un puerto".
Un puerto es un número de 16 bits, por lo que existen 216=65536 números de puerto posibles, en cada ordenador. Las aplicaciones utilizan estos puertos para enviar y recibir mensajes.

⦁	¿Como están formados los endpoints?
Cada punto de conexión está compuesto de cuatro propiedades:
-Una dirección que indica dónde se puede encontrar el punto de conexión.
-Un enlace que especifica cómo un se puede comunicar un cliente con el punto de conexión.
-Un contrato que identifica las operaciones disponibles.
-Un conjunto de comportamientos que especifican detalles de implementación local del punto de conexión.

⦁	¿Que es un socket? 
Un socket . Es un método para la comunicación entre un programa del cliente y un programa del servidor en una red. Un socket se define como el punto final en una conexión. Los sockets se crean y se utilizan con un sistema de peticiones o de llamadas de función a veces llamados interfaz de programación de aplicación de sockets (API, application programming interface).
Un socket es también una dirección de Internet, combinando una dirección IP (la dirección numérica única de cuatro partes que identifica a un ordenador particular en Internet) y un número de puerto (el número que identifica una aplicación de Internet particular, como FTP, Gopher, o WWW).

⦁	¿A qué capa del modelo TPC/IP pertenecen los sockets? ¿Porque? 
La comunicación mediante sockets es una interfaz (o servicio) en la capa de transporte (nivel 4) de la jerarquía OSI. La filosofía de la división por capas de un sistema es encapsular, dentro de cada una de ellas, detalles que conciernen sólo a cada capa, y presentársela al usuario de tal forma que este pueda trabajar con ella sin necesidad de conocer sus detalles de implementación. La interfaz de acceso a la capa de transporte del sistema UNIX de Berkeley no está totalmente aislada de las capas inferiores, por lo que a la hora de trabajar con sockets, es necesario conocer algunos detalles sobre esas capas. En concreto, a la hora de establecer una conexión mediante sockets, es necesario conocer la familia o dominio de la conexión, y el tipo de conexión.
 

⦁	¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets? 
Los sockets pueden ser uno de dos tipos: Servidor (y como tal escuchan) ó Cliente (y como tal, se conectan a un socket que escucha) . Nos referimos entonces a los distintos tipos de socket como Server ó Client.
Los servidores, utilizan sockets tipo listener, para generar y administrar las conexiones de los clientes que a ellos se conectan . Un socket tipo listener, es aquel que está “escuchando” mediante un puerto TCP, peticiones de clientes que desean conectarse al servidor. Es decir un socket listener ES un server socket.
Un server socket: nace, se ata a una interfaz (bind), escucha conexiones entrantes(listen), las acepta (accept) y se apaga/cierra (shutdown & close).

⦁	¿Cuales son las causas comunes por la que la conexión entre cliente/servidor falle?

Hay muchas pero comentamos algunas:
1-Conflictos con direcciones IP
2-Fallas en switches o routes
3-Equipos conectados desordenadamente
4-Problemas en NetBIOS
5-Tarjetas de red defectuosas
6-Insuficiente ancho de banda
7-Errores de DNS

Entre muchas otras..

⦁	Diferencias entre sockets UDP y TCP 8. Diferencia entre sync & async sockets?

La principal diferencia entre TCP y UDP pasa fundamentalmente por el sistema de verificación de la transmisión de la información entre el dispositivo emisor y el dispositivo receptor. El protocolo TCP es un protocolo de transporte orientado a conexión, mientras que el protocolo UDP no lo es. De esta manera, el protocolo TCP verifica la correcta transmisión de los datos entre los dispositivos emisores y los receptores, mientras que el protocolo UDP no lo hace.
Por esto, el protocolo UDP cuenta con una velocidad de transmisión superior a la del protocolo TCP, si bien lo hace a costa de una pérdida de precisión en la transmisión de la información.
El protocolo TCP cuenta con un sistema de control de congestión y de flujo del tráfico, mientras que el protocolo UCP carece de él.
Por último, el tamaño de la cabecera del protocolo TCP es de 20 bytes, mientras que el del protocolo UCP es de solo 8 bytes. Esto responde a la necesidad del protocolo TCP de incluir más información en los paquetes para poder comprobar y subsanar después los posibles errores de transmisión.
Un socket de cliente asincrónico no suspende la aplicación mientras espera a que finalicen las operaciones de red. En lugar de eso, procesa la conexión de red en un subproceso mientras la aplicación continúa ejecutándose en el subproceso original. Los sockets asincrónicos son adecuados para aplicaciones que hacen un uso intensivo de la red o que no pueden esperar a que finalicen las operaciones de red antes de continuar.



## Consignas del trabajo

Crear un modelo cliente - servidor utilizando TCP/IP Sockets a partir de los siguientes puntos:

1) Crear un servidor:
a) Debe aceptar conexiones entrantes
b) Usar el port number 3000
c) Utilizar telnet para emular el lado del cliente, y realizar conexiones entrantes
(Para desarrollar el lado del servidor)
d) Recibir data en el TCP/IP socket (Utilizar telnet para emular dicho
comportamiento)
e) La data enviada debe ser texto y se debe mostrar en la consola tanto del lado
del cliente como del servidor los mensajes enviados / recibidos.
f) Utilizar una aplicación telnet de celular para mandar datos al server
i) Utilizar el hostname en vez de la IP para mandar información
g) Verificar que el firewall tenga en inbound rules los permisos correctos para la
entrada y salida de data.
h) El servidor debe tener la funcionalidad de poder responderle al cliente.
i) Si el usuario presiona ‘X’, se corta la conexión. La conexión se debe poder
terminar tanto del lado del cliente como del servidor.


2) Crear un cliente:

a) Debe poder conectarse con un servidor por medio de la IP y el puerto. Dichos
datos se ingresan por consola (configurables). Puede haber más de un cliente
corriendo.
b) Manejar los errores si el usuario ingresa mal la dirección IP y el puerto, y
devolver un mensaje coherente.
c) Mostrar el estado de conexión paso a paso.
d) El cliente debe poder tanto enviar como recibir data.


### Preguntas

1. ¿Que es un puerto?
2. ¿Como estan formados los endpoints?
3. ¿Que es un socket?
4. ¿A qué capa del modelo TPC/IP pertenecen los sockets? ¿Porque?
5. ¿Cómo funciona el modelo cliente-servidor con TCP/IP Sockets?
6. ¿Cuales son las causas comunes por la que la conexión entre cliente/servidor falle?
7. Diferencias entre sockets UDP y TCP
8. Diferencia entre sync & async sockets?


### Notas

- Se puede utilizar cualquier lenguaje de programación. Recomiendo utilizar JAVA.
- La aplicación debe ser de consola. Se debe loguear toda ejecución que se realice del
lado del cliente como del servidor.
- El código debe estar subido a un repositorio github de manera pública. El repositorio
debe tener la información necesaria en un readme para poder clonarlo y dejar andando
ambas app. Recomiendo una carpeta de build con los ejecutables ya buildeados.
- El trabajo se mostrará y se defenderá frente al docente. El grupo traerá un ejemplo (en
vivo) para mostrar y se le hará unas preguntas al equipo para que explique cómo lo
realizó. Puede haber alguna pregunta teórica del trabajo. No hace falta que haya un
documento en su entrega.
- Los grupos serán de 3 o 2 personas únicamente.
- Tener en cuenta el tipo de conexión que utiliza con los sockets, ya que se pide
sockets TCP/IP.
- La entrega del trabajo es hasta el lunes 22 de Junio inclusive. Se enviará un email a
lgarciavandenbosch@gmail.com, donde el subject dira “Trabajo Final de Redes”
seguido del apellido de los integrantes del grupo. El body del email repetirán los
nombres de los integrantes del grupo, un link al repositorio de código donde está
alojado tanto el server como el client, y algunos screenshots de la app funcionando.
- Los días 26 y 29 de Junio, se evaluará de forma oral los trabajos que considere el
profesor con el fin de cerrar las notas del cuatrimestre. Si alguno, finaliza antes de la
fecha de entrega el trabajo, puede entregarlo siguiendo las instrucciones previas.
