
# Parcial 1 ARSW-T1
Parcial de primer tercio
# Introducción
Con el fin de de contribuir a programas estadísticos cada vez más robustos se requiere crear una plataforma distribuida que permita la búsqueda de identificadores únicos. Se le ha encargado la tarea de modificar el programa existente debido a que la compañia ha reportado lentitud en la búsqueda de dichos identificadores. Personal técnico de la compañia ha identificado que el programa no está aprovechando las capacidades multinucleo de las máquinas en dónde está desplegada la solución.
# Nota
Punto 1 y 2 Proyecto GuidFinderDesktop Punto 3 Proyecto GuidFinderAPI
# Punto 1
Haga que el programa divida y paralelice la búsqueda  en exactamente 4 hilos de ejecución.
# Punto 2
Haga que el programa mantenga inactivos los hilos (sin esperas activas!), hasta tanto se detecte que el computador no tiene actividad (en este caso, cuando se completen 10 segundos sin detectar actividad en el teclado). Del mismo modo, una vez se detecte actividad del teclado, los hilos deberían nuevamente suspenderse (de nuevo, hasta tanto se detece la inactividad del teclado por al menos 10 segundos)
# Punto 3
En el proyecto GuidFinderAPI construya un API rest que registre y consulte historicos de las busquedas realizadas. Cada vez que finaliza una búsqueda el sistema debería registrar (por medio del api rest )  los siguientes datos
Fecha de busqueda, UUID buscado,cantidad encontrada

De igual manera el API rest debería permitir la consulta de todas las busquedas de UUID´s utilizando la siguiente representación JSON 


`
{
"Fecha":"2019-02-21T05:10:00",
"Guid":"d0692660-c39a-4d73-9496-d9df0c4ebdf3",
"Count":0
}
`

Almacene dicha información en memoria volatil


Utilice el nombre del recurso "uuid" es decir cuando se trate de acceder al recurso para consultarlo la url debería ser
` http://localhost:8080/uuid` utilizando el verbo GET


y para registrarlo debería ser la misma pero usando el verbo POST

# Preguntas Teoría
Conteste las siguientes preguntas en una archivo .txt

Establezca verdadero o falso en las siguientes sentencias:
- Rest es un protocolo que permite la interoperabilidad FALSE
- Rest es una tecnología que está basada sobre el protocolo http FALSE
- SOAP es un protocolo que tiene el objetivo tranferir mensajes entre aplicaciones distribuidas TRUE
- Los componentes de rest son nouns,Adjectives,Representations FALSE
- Los componentes de rest son nouns,verbs,Representations TRUE
- Los componentes de rest son nouns,Adjectives,Representations FALSE
- SOA es un protocolo de transferencia de mensajes entre aplicaciones distribuidas FALSE
- Uno de los principios SOA es baja densidad FALSE

Explique cada uno de los niveles de madurez de REST
Nivel 0: analogía a RPC, rest solo permite hacer uso o llamado de funciones
Nivel 1: se permite hacer consumo o pedido de recursos
Nivel 2: se hacen uso de los verbos de http, se aproxima más a http
Nivel3: se introduce hypermedia para cumplir con el principio de facil descubrimiento

Cuales son los componetes del SOAP-ENVELOPE y para que sirven
header: no es necesario y especifica informacion adicional para la capa de aplicacion
body: informacion del servicio o aplicación, informacion de tipos, bindings, etc...
fault: si ocurre un error lo documenta dentro del body

Defina la diferencia de un estilo arquitectónico cliente servidor y un P2P
en cliente servidor uno de los pares solo puede hacer peticiones y no responder, el otro solo puede recibir peticiones y no realizarlas, mientras que en P2P ambos pares pueden realizar y responder peticiones

Escriba un ejemplo de un programa P2P y explique por que es P2P
Skype porque los involucrados en la llamada sirven de servidor y cliente al tiempo

# Subida

Siga al pie de la letra estas indicaciones para la entrega de este punto. EL HACER CASO OMISO DE ESTAS INSTRUCCIONES PENALIZARÁ LA NOTA.

Limpie los dos proyectos. Entre a los directorios 'GuidFinderDesktop' y 'GuidFinderAPI' y ejecute:

`$ mvn clean`
Configure su usuario de GIT

`$ git config --global user.name "Juan Perez"`
`$ git config --global user.email juan.perez@escuelaing.edu.co`

Desde el directorio raíz (donde está este archivo README.md), haga commit de lo realizado.

`$ git add .`
`$ git commit -m "entrega parcial - Juan Perez"`

Desde este mismo directorio, comprima todo con: (no olvide el punto al final de la instrucción)

`$ zip -r APELLIDO.NOMBRE.zip .`

Abra el archivo ZIP creado, y rectifique que contenga lo desarrollado.

Suba el archivo antes creado (APELLIDO.NOMBRE.zip) en el espacio de moodle correspondiente.

IMPORTANTE!. Conserve una copia de la carpeta y del archivo .ZIP.

