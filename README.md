BACK-END

Prueba de desarrollo de una aplicación mediante Spring Boot para el Backenn.
Servicios Rest con Spring MVC JPA.
Al compilar maven se tiene un archivo .jar
Al ejecutar el archivo .jar se levanta el servidor Apache embebido en Spring en el puerto 9090.
Dentro del paquete migrations se tiene el script para crear la base de datos desarrollada en MySQL.

FRONT-END

El front se encuentra desarrollado con el framework vuejs con vuetify para los estilos utilizan material desing.
Dentro de la carpeta dist se encuentran los archivos compilados para montar en un servidor apache.
Al compilar para producción se realiza la respectiva minificación y ofuscación del código.
Para correrlo localmente se debe tener node en el computador.
Luego se corren los siguientes comandos:
Npm i
Nom run dev
