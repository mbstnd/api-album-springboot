# 🎴 Sistema de Gestión de Álbumes y Láminas - API REST

### **Integrantes del equipo (Grupo 12, Sección 70):**
- **Mario Quevedo**  
- **Patricio Ibargaray**  
- **Franco Vasquez**

## Descripción
Sistema de gestión para coleccionistas de láminas de álbumes utilizando Spring Boot. Permite administrar colecciones, seguimiento de láminas faltantes y repetidas.

## 🛠️ Stack 

### 🌟 Core
- [**Java 21**](https://www.oracle.com/java/technologies/downloads/#java21) - Lenguaje base
- [**Spring Boot 3.4.1**](https://spring.io/projects/spring-boot) - Framework backend

### 📚 Base de Datos
- [**PostgreSQL**](https://www.postgresql.org/) - Base de datos relacional
- [**JPA**](https://jakarta.ee/specifications/persistence/) - Persistencia de datos

### 🧰 Herramientas
- [**Maven**](https://maven.apache.org/) - Gestión de dependencias
- [**Lombok**](https://projectlombok.org/) - Reducción de código
- [**Spring Boot DevTools**](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.devtools)

### 🧪 Testing
- [**Spring Boot Starter Test**](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-testing) - Suite de pruebas
- [**JUnit 5**](https://junit.org/junit5/) - Framework de testing

## Configuración e Instalación

### Prerrequisitos
- Java 21
- Maven
- PostgreSQL

### Base de Datos
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/coleccion
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

# Clonar repositorio
```
git clone https://github.com/tu-usuario/coleccion-album.git
cd coleccion-album
```

# Compilar proyecto
```
mvn clean install
```

# Ejecutar aplicación
```
mvn spring-boot:run
```

## 📚 Endpoints

### 📗 Álbumes

| Método | Ruta | Descripción | Código de Éxito |
|--------|------|-------------|-----------------|
| GET | `/v1/album/getAll` | Obtiene todos los álbumes | 200 |
| GET | `/v1/album/getById/{id}` | Obtiene un álbum específico | 200 |
| POST | `/v1/album/create` | Crea un nuevo álbum | 201 |
| PUT | `/v1/album/update/{id}` | Actualiza un álbum existente | 200 |
| DELETE | `/v1/album/delete/{id}` | Elimina un álbum | 200 |

#### Ejemplo Crear Álbum
```json
POST /v1/album/create
Request:
{
    "nombre": "Dragon Ball Super Collection",
    "imagen": "https://storage.googleapis.com/albums/dragonball.jpg",
    "fechaLanzamiento": "2024-01-01",
    "tipoLaminas": "METALICA"
}

Response:
{
    "status": 201,
    "message": "Álbum creado exitosamente",
    "data": {
        "id": 1,
        "nombre": "Dragon Ball Super Collection",
        "imagen": "https://storage.googleapis.com/albums/dragonball.jpg",
        "fechaLanzamiento": "2024-01-01",
        "tipoLaminas": "METALICA"
    }
}
```
### 🎴 Láminas

| Método | Ruta | Descripción | Código de Éxito |
|--------|------|-------------|-----------------|
| GET | `/v1/laminas/getAll` | Lista todas las láminas | 200 |
| GET | `/v1/laminas/getById/{id}` | Obtiene una lámina específica | 200 |
| POST | `/v1/laminas/create` | Crea una nueva lámina | 201 |
| PUT | `/v1/laminas/update/{id}` | Actualiza una lámina existente | 200 |
| DELETE | `/v1/laminas/delete/{id}` | Elimina una lámina | 200 |
| GET | `/v1/laminas/faltantes` | Obtiene láminas faltantes | 200 |
| GET | `/v1/laminas/repetidas` | Obtiene láminas repetidas | 200 |
| POST | `/v1/laminas/bulk/{albumId}` | Crea múltiples láminas | 201 |

#### Ejemplo Crear Lámina
```json
POST /v1/laminas/create
Request:
{
    "nombre": "Goku Ultra Instinto",
    "imagen": "goku-ui.jpg",
    "cantidadRepetidas": 0,
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
        "cantidadRepetidas": 0,
        "faltante": false,
        "albumId": 1,
        "albumNombre": "Dragon Ball Super Collection"
    }
}
```