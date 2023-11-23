# Desafío JAVA BCI

Evaluación Java API REST

El script para la creación de la base de datos esta en el archivo

```
/main/resources/schema.sql
```

Las configuraciones del sistema están en el archivo el cual se puede eliminar la propiedad de creacion del modelo:
> **application.properties**

```
#JPA - H2
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto= update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true #cambiar por false en caso de ejecutar el script
logging.level.org.hibernate.SQL=DEBUG
spring.sql.init.mode=always
```

## Pasos

Descargar Fuentes de git

```
git clone https://github.com/aandmaldonado/ms-desafio-java-bci.git
```

Una vez descargada las fuentes se deben ejecutar los siguiente comandos por consola:

```
gradlew build
gradlew bootRun
```

La ejecución de la consola para acceder a la base de datos es a través del siguiente link:

```
http://localhost:8080/h2-console
user: admin
pass: admin
```

La ejecución para acceder a swagger y api-docs es a traves de los siguientes links:

```
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```

La ejecución del servicio de registro es a través de la siguiente url:

```
POST http://localhost:8080/api/v1/user/register
body:
{
    { 
    "name": "Avaro Maldonado", 
    "email": "alvaro@mail.com", 
    "password": "Amrrpr88", 
    "phones": [ 
        { 
            "number": "1234567", 
            "citycode": "1", 
            "contrycode": "57" 
        } 
    ] 
}
```

Respuesta:

```
{
    "last_Login": "23-11-2023 09:44:58",
    "isactive": true,
    "id": "b750edfc-9826-403c-9653-67d79544b079",
    "created": "23-11-2023 09:44:58",
    "modified": "23-11-2023 09:44:58",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbHZhcm9AbWFpbC5jb20iLCJpYXQiOjE3MDA3NDM0OTgsImV4cCI6MTcwMDc0NTI5OH0.i46CsOL_bhnBfpX-YiHEDPysK3QeP1zXDSS7MqlYFP0"
}
```

## Pre-requisitos 

- JDK 17
- Gradle 8.4

## Desarrollo

Herramientas y lenguajes utilizados

* Java 17 - Lenguaje de programación.
* Spring Boot 3.1.5 - Framework de desarrollo
* JWT 0.11.5 - Generación de tokens 
* Swagger 2 - Documentación API
* Gradle 8.4 - Gestor de dependencias.
* IntelliJ IDEA 2023.2.5 (Ultimate Edition) - IDE de desarrollo.

## Autor

* **Álvaro Maldonado**  

