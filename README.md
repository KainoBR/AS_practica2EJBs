Actualizado: 2020-12-23

# AS_practica2EJBs
Práctica 2 de la asginatura "Arquitectura del Software" del plan 2010 de la Escuela de Ingeniería Informática de la Universidad de Las Palmas de Gran Canaria.

El objetivo de esta práctica es la de implementar la capa de negocio haciendo uso de los componentes de Java EE. Esta segunda práctica es una continuación de la práctica anterior (AS_practica1FrontController)

## Repositorio

 - *DigitalReadingEJB2:*  código fuente de la aplicación
 - *EJBLibrary*: Contiene los EJB remotos
 - *Librerias:* librerias utilizadas (por si acaso ocurre cualquier problema con ellas, tenerlas a mano)
 - *Enunciado_practica2_EJB:*  pdf del enunciado de la práctica
 - *Informe_practica2_AS_EJB:*  pdf explicando la práctica junto con lo que se solicitaba en el enunciado
 - *digitalreading.sql:*  archivo que contiene los comandos sql para volver a generar la base de datos utilizada en la práctica


### Herramientas utilizadas

- Entorno de desarrollo: [Netbeans 8.2](https://netbeans.org/downloads/8.2/rc/) (tip: instalarlo con todo)
- Servidor y base de datos local: [XAMPP](https://www.apachefriends.org/es/index.html)


### "Como hacer que funcione"
*Similiar a la [práctica 1](https://github.com/SrMiki/AS_practica1_FrontController) con diferencias en el paso 2 y 7*

1) Descargar las herramientas indicadas e intalarlas:
    - Con netbeans si utilizamos la instalación con todo (all) solamente habría que activar los plugins correspondientes; si utilizas otro diferente tendrás que añadir java EE y glasfish por tu cuenta. 

2) Descargar el proyecto y abrirlo con Netbeans. 
    - Verifica que las librerias javax.servlet y mysql-conector están agregadas al proyecto, en caso contrario, en la carpeta "librerias" están los .jar
    - Verifica que está descargado el archivo *EJBLibrary* 
    - Verifica que están descargados los módulos *DigitalReadingEJB2-war* y *DigitalReadingEJB2-ejb*. Puedes asegurarte activándolos haciendo "click" en Java EE module del proyecto *DigitalReadingEB2*
    
    - Si no puedes abrir el proyecto comprueba si tienes activado java EE
    - Si te da un error con el glasfish deberás instalarlo y añadirlo manualmente (con la instalación de Netbeans (all) no da este error) 

3) Una vez puedas visualizar el proyecto hacer un "clean&build" del proyecto (realizarlo desde *DigitalReadingEJB2*) y comprobamos si hay algún fallo (no debería) 

4) Abrir la herramienta XAMPP e iniciar el módulo de Apache y MySQL (nunca encender el módulo tomcat) 

5) Accedemos a phpMyAdmin:
    - opción 1) Escribiendo en el navegador: http://localhost/phpmyadmin/
    - opcion 2) Haciendo "click" en el botón "admin" del módulo de MySQL

6) Agregamos la Base de Datos 
    - opción 1) Utilizando la opción de importar y seleccionamos el fichero "digitalreading.sql"
    - opción 2) Creamos un nueva BBDD llamada "digitalreading" y agregamos, a través de comandos SQL, las diferentes tablas del fichero "digitalreading.sql" (ojo con las comillas)
    - Si creamos solo la Base de Datos con las tablas correspondientes ya funcionaría pero, lógicamente, no habría catálogo ni usuarios.

7) Ejecutamos la applicación (run) en Netbeans. Esperamos a que termine de compilar y cuando el glasfish indique *"DigitalReadingEJB2 was successfully deployed"* abrimos el navegador y nos dirigimos a http://localhost:8080/DigitalReadingEJB2-war/

### Anotaciones

 - Para que esta práctica pueda funcionar hay que utilizar las herramientas indicadas y cargar la base de datos
 - Revisar imports, enlaces y plugins
 - En el "Informe_practica2_AS_EJB.pdf" se detallan los diferentes apartados de la práctica
 - Base de datos: SQL utilizando [phpmyadmin](https://www.phpmyadmin.net/)
 - Servidor: [glashfish](https://www.oracle.com/middleware/technologies/glassfish-server.html)
