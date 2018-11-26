# IBM APP Test

<h3>Instalación</h3>

El proyecto está divido en 2 partes, app-backend y app-frontend. El proyecto backend está desarrollado en Eclipse con Maven y el frontend con Angular6. A continuación se relacionan mas específicamente los requisitos técnicos:

<ul>
<li><h3>APP-BACKEND</h3></li>
-JDK Java7, Eclipse, Servidor de Aplicaciones (Apache Tomcat 7 preferiblemente)
  

<li><h3>Corre el proyecto</h3></li>

Importar el proyecto en eclipse, hacer clean al proyecto. Luego hacer en la consola de comandos de maven, ejecutar "mvn clean" "mvn install". Luego correr el proyecto en el servidor web


  
<li><h3>APP FRONTEND</h3></li>
-NodeJS 9, Angular CLI, Angular 6, Editor de código cualquiera (recomendado Visual Studio Code)
</ul>

<ol>
<li><h3>Node JS</h3></li>
Descargar el instalador en la pagina <a href="https://nodejs.org/en/"> NodeJs</a> la última versión. No requiere ninguna configuracion especial. Igualmente si desea ver la instalación en detalle,  puede ver este video <a href="https://www.youtube.com/watch?v=eeb4IJLKLaY"> Instalar NodeJs</a> 
  
  <li><h3>Angular CLI</h3></li>
Para instalar Angular, solo basta con seguir el paso a paso de la instalación de la página oficial. <a href="https://angular.io/guide/quickstart"> Instalar Angular</a> 

<li><h3>Corriendo el servidor de Aplicaciones de Angular</h3></li>
En la consola de comandos, ir a la ruta base del proyecto frontend (app-frontend) y ejecutar "npm install", con esto se dará inicio a la instalacion de las dependencias Nota: Esta instalación puede tomar un tiempo en completarse. Una vez finalizado, ejecutaremos el comando "ng serve", al finalizar, podremos acceder al backend a través de la ruta http://localhost:4200/app/



</ol>

<h3>Notas</h3>
La ruta base para el consumo de los servicios REST es:http://localhost:<puerto>/ibm/services
