# Prueba Técnica - BCI

Autor: Rodolfo Crisanto Rosas

## Descripción

Este proyecto corresponde a una API REST desarrollada con Spring Boot que permite el registro de usuarios, cumpliendo con una serie de validaciones y requisitos definidos en la prueba técnica.

La aplicación implementa buenas prácticas de desarrollo como separación por capas, validación de datos, manejo global de excepciones y generación de tokens de autenticación.

<hr>

## Tecnologías usadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- MapStruct
- JWT (JSON Web Token)
- Swagger (OpenAPI)
- Mockito (pruebas unitarias)

## Arquitectura

El proyecto está estructurado siguiendo una arquitectura en capas:

- Controller: expone los endpoints de la API
- Service: contiene la lógica de negocio
- Repository: acceso a datos mediante JPA
- DTO: objetos de transferencia de datos
- Mapper: conversión entre entidades y DTOs
- Exception: manejo global de errores
- Util: utilidades como generación de JWT

## Configuración y ejecución
### Requisitos
- Java 17 o superior
- Gradle
### Ejecución del proyecto
`./gradlew bootRun`

La aplicación se ejecutará por defecto en:

`http://localhost:8080`

## Documentación de de la Api
Swagger UI disponible en:
``http://localhost:8080/swagger-ui/index.html``. Desde alli es posible revizar los endpoints de manera más interactiva.

## Endpoint principal
### Crear usuario
POST `/api/users`

Request
```
Request
{
  "name": "Juan Rodriguez",
  "email": "juan@dominio.cl",
  "password": "Password1",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```

Response
```
{ 
    "id": "uuid", 
    "created": "2026-03-30 21:45:10", 
    "modified": "2026-03-30 21:45:10", 
    "lastLogin": "2026-03-30 21:45:10", 
    "token": "jwt-token", 
    "isActive": true 
}
```

Excepcion de error
```
{ "mensaje": "Descripción del error" }
```

## Pruebas unitarias
Se implementaron pruebas unitarias usando JUnit y Mockito para las validaciones requeridas del reto.
Para su ejecucion

``./gradlew test``

## Colección de Postman
Se incluye una colección de Postman para facilitar las pruebas del proyecto.

Ubicación: ``postman/Prueba Tecnica BCI.postman_collection.json``


## Consideraciones
- El token JWT es generado y almacenado junto al usuario, según lo solicitado en la prueba.
- La base de datos es en memoria, por lo que la información se pierde al reiniciar la aplicación.
- La validación de contraseña es configurable desde el archivo de propiedades.
- Se adjunta coleccion de POSTMAN para las pruebas