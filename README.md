```markdown
# Book Manager API

API REST para la gestión de una colección personal de libros, construida con **Spring Boot 3** y **Java 17**.

## Tecnologías

| Herramienta     | Versión / Detalle                |
|-----------------|----------------------------------|
| Java            | 17+                              |
| Spring Boot     | 3.2.5                            |
| Maven           | 3.8+                             |
| Base de datos   | H2 (en memoria)                  |
| Persistencia    | Spring Data JPA / Hibernate      |
| Validación      | Jakarta Validation               |

## Requisitos

- **Java 17** o superior
- **Maven 3.8** o superior

## Cómo ejecutar

```bash
mvn spring-boot:run
```

La API arranca en `http://localhost:8080`.

### Consola H2

Accesible en `http://localhost:8080/h2-console`:

- **JDBC URL:** `jdbc:h2:mem:bookdb`
- **Usuario:** `sa`
- **Contraseña:** *(vacío)*

## Endpoints

| Método | URL                              | Descripción                       |
|--------|----------------------------------|-----------------------------------|
| GET    | `/api/books`                     | Listar todos los libros           |
| GET    | `/api/books/{id}`                | Obtener un libro por ID           |
| POST   | `/api/books`                     | Crear un nuevo libro              |
| PUT    | `/api/books/{id}`                | Actualizar un libro existente     |
| DELETE | `/api/books/{id}`                | Eliminar un libro                 |
| GET    | `/api/books/search?author=`      | Buscar por autor                  |
| GET    | `/api/books/search?genre=`       | Buscar por género                 |
| GET    | `/api/books/search?read=`        | Buscar por estado de lectura      |

## Modelo de datos (`Book`)

| Campo    | Tipo      | Restricciones                          |
|----------|-----------|----------------------------------------|
| `id`     | `Long`    | Clave primaria, autogenerado           |
| `title`  | `String`  | `@NotBlank`, obligatorio               |
| `author` | `String`  | `@NotBlank`, obligatorio               |
| `year`   | `String`  | `@Pattern(regexp="^\d{4}$")`           |
| `genre`  | `String`  | Sin restricciones                      |
| `read`   | `boolean` | Por defecto `false`                    |

## Ejemplos de uso

```bash
# Crear un libro
curl -X POST http://localhost:8080/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"1984","author":"George Orwell","year":"1949","genre":"Dystopian","read":true}'

# Listar todos los libros
curl http://localhost:8080/api/books

# Obtener un libro por ID
curl http://localhost:8080/api/books/1

# Buscar por autor
curl "http://localhost:8080/api/books/search?author=George%20Orwell"

# Buscar por género
curl "http://localhost:8080/api/books/search?genre=Dystopian"

# Buscar por estado de lectura
curl "http://localhost:8080/api/books/search?read=true"

# Actualizar un libro
curl -X PUT http://localhost:8080/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"1984","author":"George Orwell","year":"1949","genre":"Political Fiction","read":true}'

# Eliminar un libro
curl -X DELETE http://localhost:8080/api/books/1
```

## Pruebas

```bash
mvn test
```

## Compilar

```bash
mvn package
```

## Estructura del proyecto

```
book-manager-api/
├── .gitignore
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   ├── java/com/bookmanager/
    │   │   ├── BookManagerApplication.java
    │   │   ├── controller/
    │   │   │   └── BookController.java
    │   │   ├── model/
    │   │   │   └── Book.java
    │   │   ├── repository/
    │   │   │   └── BookRepository.java
    │   │   └── service/
    │   │       └── BookService.java
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/bookmanager/
            └── BookManagerApplicationTests.java
```
