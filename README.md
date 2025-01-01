## 🎴 Sistema de Gestión de Álbumes y Láminas - API REST

### **Integrantes del equipo (Grupo 12, Sección 70):**
- **Mario Quevedo**  
- **Patricio Ibargaray**  
- **Franco Vasquez**

## 📝 Descripción
Sistema de gestión para coleccionistas de láminas de álbumes utilizando Spring Boot. Permite administrar colecciones, seguimiento de láminas faltantes y repetidas.

## 🛠️ Stack 

### 🌟 Core
- [**Java 21**](https://www.oracle.com/java/technologies/downloads/#java21) - Lenguaje base
- [**Spring Boot 3.4.1**](https://spring.io/projects/spring-boot) - Framework backend
- [**Spring Data JPA**](https://spring.io/projects/spring-data-jpa) - Persistencia de datos
- [**Spring Web**](https://spring.io/guides/gs/spring-boot/) - Desarrollo de API REST

### 📚 Base de Datos
- [**MySQL**](https://www.mysql.com/) - Base de datos relacional
- [**JPA**](https://jakarta.ee/specifications/persistence/) - Soporte para persistencia de datos con JPA

### 🧰 Herramientas
- [**Maven**](https://maven.apache.org/) - Gestión de dependencias
- [**Lombok**](https://projectlombok.org/) - Reducción de código boilerplate
- [**Spring Boot DevTools**](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools) - Permite reinicios automáticos, soporte para plantillas y acelera el desarrollo
   
### 🔍 Monitoreo
- [**Spring Boot Actuator**](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html) - Monitoreo y gestión de la aplicación

### 🧪 Testing
- [**Spring Boot Starter Test**](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing) - Framework de pruebas integrado
- [**JUnit 5**](https://junit.org/junit5/) - Framework de testing

## 🚀 Configuración e Instalación

### Prerrequisitos
- Java 21
- Maven
- MySQL

## Clonar repositorio
```
git clone https://github.com/mbstnd/api-album-springboot.git
cd coleccion-album
```

## Compilar proyecto
```
mvn clean install
```

## Ejecutar aplicación
```
mvn spring-boot:run
```

## 📚 Endpoints

### 👤 Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/v1/usuarios/getAll` | Obtener todos los usuarios |
| GET | `/v1/usuarios/getById/{id}` | Obtener usuario por ID |
| GET | `/v1/usuarios/{userId}/albums` | Obtener álbumes de un usuario |
| POST | `/v1/usuarios/create` | Crear nuevo usuario |
| PUT | `/v1/usuarios/update/{id}` | Actualizar usuario |
| DELETE | `/v1/usuarios/delete/{id}` | Eliminar usuario |

## 📗 Álbumes

| Método | Ruta | Descripción | Código de Éxito |
|--------|------|-------------|-----------------|
| GET | `/v1/album/getAll` | Obtiene todos los álbumes | 200 |
| GET | `/v1/album/getById/{id}` | Obtiene un álbum específico | 200 |
| POST | `/v1/album/create` | Crea un nuevo álbum | 201 |
| PUT | `/v1/album/update/{id}` | Actualiza un álbum existente | 200 |
| DELETE | `/v1/album/delete/{id}` | Elimina un álbum | 200 |

## Ejemplo Crear Álbum
**Endpoint:** `POST /v1/album/create`
```json

Request:
{
    "nombre": "Liga Chilena 2024",
    "imagen": "https://storage.googleapis.com/albums/liga-chilena-2024.jpg",
    "fechaLanzamiento": "2024-03-20",
    "tipoLaminas": "REGULAR",
    "usuario": {
        "id": 1
    },
    "laminas": [
        {
            "nombre": "Arturo Vidal - Colo Colo",
            "imagen": "vidal-colo-colo.jpg",
            "cantidadRepetidas": 1,
            "faltante": false
        }
    ]
}

Response:
{
    "status": 201,
    "message": "Álbum creado exitosamente con 1 láminas",
    "data": {
        "id": 1,
        "nombre": "Liga Chilena 2024",
        "imagen": "https://storage.googleapis.com/albums/liga-chilena-2024.jpg",
        "fechaLanzamiento": "2024-03-20",
        "tipoLaminas": "REGULAR",
        "usuario": {
            "id": 1,
            "firstName": "Mario",
            "lastName": "Quevedo",
            "email": "mario.dev@gmail.com",
            "fechaCreacion": "2025-01-01T08:13:56.856405"
        },
        "laminas": [
            {
                "id": 1,
                "nombre": "Arturo Vidal - Colo Colo",
                "imagen": "vidal-colo-colo.jpg",
                "cantidadRepetidas": 1,
                "faltante": false
            }
        ]
    }
}
```
## 🎴 Láminas

| Método | Ruta | Descripción | Código de Éxito |
|--------|------|-------------|-----------------|
| GET | `/v1/laminas/getAll` | Lista todas las láminas | 200 |
| GET | `/v1/laminas/getById/{id}` | Obtiene una lámina específica | 200 |
| POST | `/v1/laminas/create` | Crea una nueva lámina | 201 |
| PUT | `/v1/laminas/update/{id}` | Actualiza una lámina existente | 200 |
| DELETE | `/v1/laminas/delete/{id}` | Elimina una lámina | 200 |
| GET | `/v1/laminas/faltantes` | Obtiene láminas faltantes | 200 |
| GET | `/v1/laminas/repetidas` | Obtiene láminas repetidas | 200 |


## Ejemplo Crear Lámina
**Endpoint:** `POST /v1/laminas/create`
```json
Request:
{
    "nombre": "Goku Ultra Instinto",
    "imagen": "goku-ui.jpg",
    "cantidadRepetidas": 2,
    "faltante": false,
    "album": {
        "id": 1
    }
}

Response:
{
    "status": 201,
    "message": "Lámina creada exitosamente",
    "data": {
        "id": 1,
        "nombre": "Goku Ultra Instinto",
        "imagen": "goku-ui.jpg",
        "cantidadRepetidas": 2,
        "faltante": false,
        "albumId": 1,
        "albumNombre": "Dragon Ball Super Collection"
    }
}
```
## 🔑 License

- [MIT](https://github.com/mbstnd/api-album-springboot/blob/main/LICENSE)
