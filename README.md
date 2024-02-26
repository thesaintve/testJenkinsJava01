# Servicio de Creación de Usuarios

Este servicio permite la creación de usuarios. Desarrollado como parte de una prueba de habilidades, el proyecto está en desarrollo y no se recomienda su uso en un entorno de producción.

## Documentos
En la carpeta docs se encuentra el diagrama de componente y secuencia, además del enunciado de la prueba que generó este proyecto.

## Estado del Proyecto
En desarrollo

## Instalación del Proyecto
Siga estos pasos para instalar y ejecutar el proyecto en su máquina local.

### Requisitos Previos
Asegúrese de tener instalados los siguientes elementos en su entorno de desarrollo:
- Java Development Kit (JDK) - versión 8 o superior.
- Gradle - herramienta de construcción y gestión de dependencias.

### Pasos de Instalación
1. **Clonar el Repositorio:**
    ```bash
    git clone git@github.com:thesaintve/nttDataJavaTest.git
    cd nttDataJavaTest
    ```

2. **Configurar la Base de Datos H2 (Opcional):**
    El proyecto puede utilizar H2 como base de datos embebida. Modifique la configuración en `src/main/resources/application.yml` según sus necesidades.


3. **Compilar la Aplicación con Gradle:**
    ```bash
    ./gradlew clean build --refresh-dependencies
    ```
4. **Ejecutar la Aplicación con Gradle:**
    ```bash
    ./gradlew bootRun
    ```

## Uso de la Aplicación
1. **Acceder a la Aplicación:**
    La aplicación estará disponible en http://localhost:8091.

2. **Probar la API:**
    Utilice herramientas como Postman o curl para interactuar con la API.

3. **Crear un Usuario:**
    - Endpoint: `http://localhost:8091/user`
    - Método: `POST`
    - Cuerpo de la Solicitud:
        ```json
        {
          "name": "Nombre Apellido",
          "email": "correo@dominio.com",
          "password": "contrasena",
          "phones": [
            {
             "number": 123456789,
             "citycode": 1,
             "countrycode": "US"
            } 
          ]
        }
        ```

    - Ejemplo de Solicitud en CURL:    
        ```
        curl --location --request POST 'http://localhost:8091/user' \
        --header 'Content-Type: application/json' \
        --data-raw '{
            "name": "Pedro Perez",
            "email": "pedro01@dominio.com",
            "password": "Pwd12abc",
            "phones": [
                {
                    "number": 3332843,
                    "citycode": 4,
                    "countrycode": "5"
                }
            ]
        }'
        ```
    - Respuesta Exitosa, con el Token que se va a usar en el sigueinte end-pont:
        ```json
        {
            "id": "8b77b0d6-06c6-41b1-a545-f84228bda822",
            "name": "Pedro Perez",
            "email": "pedro01@dominio.com",
            "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJQZWRybyBQZXJleiIsImlhdCI6MTcwMjQxNjQ1OSwiZXhwIjoxNzAyNDIwMDU5fQ.s1RWAmZzgVNcHyquHGwGcj6DWFTslWh5NQVbR9cDahiWexv2S4B_GD-hn_EPk0d93sHsyShTRP7CSf-b-3dl4w",
            "created": "2023-12-12T21:27:39.884+00:00",
            "lastLogin": null,
            "active": true
        }
        ```


    - Respuesta Fallida, validación de usuario único:
        ```json        
        {
            "error": [
                {
                    "mensaje": "El correo ya registrado"
                }
            ]
        }        
        ```

    - Respuesta Fallida, password no cumple con el formato:
        ```json
        {
            "error": [
                {
                    "mensaje": "Fallo en validación [ password: El password debe tener Sólo una letra mayuscula, dos numeros y un rango de 8 a 12 caracteres ] "
                }
            ]
        }
        ```


## Documentación de las APIs
1. **Acceder al Swagger:**
    Con un navegador web como Chrome o Firefox se puede acceder a la información de APIs en http://localhost:8091/swagger-ui



## Estructura del Proyecto

```
tu-proyecto/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── microservice/
│   │   │           └── nttdata/
│   │   │               ├── NttDataUserApplication.java
│   │   │               ├── config/
│   │   │               │   └── SwaggerConfig.java
│   │   │               ├── controller/
│   │   │               │   └── UserController.java
│   │   │               ├── dto/
│   │   │               │   ├── CreatedUserDto.java
│   │   │               │   ├── SignUpUserDto.java
│   │   │               │   └── SignUpUserPhoneDto.java
│   │   │               ├── entity/
│   │   │               │   ├── User.java
│   │   │               │   └── UserPhone.java
│   │   │               ├── exceptios/
│   │   │               │   ├── CustomException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── repository/
│   │   │               │   └── UserRepository.java
│   │   │               ├── service/
│   │   │               │   ├── JwtUtil.java
│   │   │               │   ├── UserService.java
│   │   │               │   └── UserServiceTest.java
│   │   │               └── validators/
│   │   │                   ├── ConfigurableRegex.java
│   │   │                   └── ConfigurableRegexValidator.java
│   │   │               
│   │   ├── resources/
│   │   │   ├── application.yml
│   │   │   ├── static/
│   │   │   └── templates/
│   └── test/
│       └── java/
│           └── com/
│               └── microservice/
│                   └── nttdata/
│                       ├── NttDataUserApplicationTests.java
│                       ├── controller/
│                       │   └── UserControllerTest.java
│                       └── service/
│                           ├── JwtUtilTest.java
│                           └── UserServiceImplTest.java
├── build.gradle
└── README.md
```


## Contribuyendo
Las contribuciones son bienvenidas. Para cambios importantes, primero abra un problema para discutir lo que le gustaría cambiar.

Asegúrese de actualizar las pruebas según sea necesario.

## Licencia
MIT
