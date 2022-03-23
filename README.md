

# Comenzando 

## Prerrequisitos instalar 

1. Entrar al servidor de Jenkins(está en `http://localhost:8080` por defecto) e iniciar sesión.
2. Ir a **Manage Jenkins > Manage Plugins** e instalar el plugin NodeJS.
3. Ir a  **Manage Jenkins > Global Tool Configuration** y bajo **NodeJS**, seleccionar **Add NodeJS**.
4. Dar nombre `NodeJS` a la instalación.
5. En  **Global npm packages to install** ingresar newman.
6. Aplicar y guardar.


# Ejecución por Jenkins
* Crear nuevo pipeline multibranch y añadir como fuente repositorio del proyecto







# Comandos por terminal

### Compilar código
* ./mvnw.cmd clean compile -e

### Probar código
* ./mvnw.cmd clean test -e

### Empaquetar código
* ./mvnw.cmd clean package -e

### Ejecutar código
* Local:      ./mvnw.cmd spring-boot:run &
* Background: nohup bash mvnw.cmd spring-boot:run &

### Ejecutar jar
* java -jar target/devops-0.0.1-SNAPSHOT.jar

## Testing

### Testing Aplicacion
* Abrir navegador: http://localhost:8080/rest/mscovid/test?msg=testing
* 
### Postman
* newman run src/main/resources/postman.json


### JMeter


### Selenium



