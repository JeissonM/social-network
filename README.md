# 🧑‍🤝‍🧑 Social Network API

Una red social simple construida con arquitectura hexagonal utilizando **Java + Spring Boot**, **JWT**, **PostgreSQL**, **React con TypeScript** como frontend y **Docker** para el entorno de despliegue.

---

## 🚀 Características principales

- Registro e inicio de sesión con JWT
- Cierre de sesión (token invalidation mediante frontend)
- Publicaciones con contenido y timestamp
- Likes por publicación (un usuario solo puede dar like una vez)
- Listado de publicaciones con conteo de likes
- Listado de publicaciones por autor
- Seeder inicial con usuarios y publicaciones de prueba
- Estructura limpia basada en arquitectura hexagonal
- Tests unitarios para autenticación y creación de publicaciones

---

## 🧱 Tecnologías utilizadas

| Capa        | Tecnología                         |
|-------------|-------------------------------------|
| Backend     | Java 17, Spring Boot, JPA (Hibernate) |
| Base de datos | PostgreSQL                        |
| Seguridad   | Spring Security, JWT               |
| Frontend    | React + TypeScript (proyecto aparte) |
| Contenedores| Docker, Docker Compose             |
| Arquitectura| Hexagonal (puertos y adaptadores)  |
| Pruebas     | JUnit 5, Mockito                   |

---

## 📁 Estructura del proyecto

```bash
com.example.social_network
│
├── application/         # Servicios de aplicación
├── domain/              # Entidades y puertos
├── infrastructure/
│   ├── controller/      # Controladores REST
│   ├── security/        # Filtros y JWT
│   |── persistence/
│   |   ├── jpa/         # Adaptadores JPA, puertos
|   ├── common/          # comunes, respuesta global
│   └── config/
|   ├── dto/             # Request y Response DTOs
│   └── exception/       # control de errores y manejo de excepciones
├── dto/                 # Request y Response DTOs
├── mapper/              # Transformadores Entity <-> DTO
├── SocialNetworkApplication.java
└── ...
```


## 🔐 Autenticación

Usamos JWT para proteger los endpoints. Después de hacer login, recibirás un token que deberás enviar en el header de tus peticiones protegidas:

## 📦 Seeder de datos

Al iniciar la aplicación, se ejecuta un `CommandLineRunner` que crea 5 usuarios de prueba (si no existen) con sus respectivas publicaciones:

**Ejemplo de usuario:**
Email: user1@example.com
Contraseña: password1
Alias: Alias1

cada usuario se crea de la misma forma incrementando el indice por usuario Alias1, Alias2, Alias3, etc.
Con ellos pueden iniciar sesión y probar, si lo prefieres puedes crear uno desde la documentación swagger.

## 🧪 Ejecución de pruebas

```bash
./mvnw test
```


# Incluye pruebas unitarias para:
Autenticación (login)
Creación de publicaciones
Servicios usando Mockito (@Mock, @InjectMocks, when().thenReturn())


🧾 Licencia
Este proyecto fue desarrollado con fines educativos como parte de una prueba técnica.

📌 Pendientes y mejoras futuras
Eliminación y edición de publicaciones
Comentarios en publicaciones
Soporte para imágenes
Logout con blacklist de tokens
Tests de integración
Paginación y ordenamiento de publicaciones




# 🚀 Guía de instalación y puesta en marcha con Docker

## 📋 Requisitos previos

Asegúrate de tener instalado en tu sistema:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Git](https://git-scm.com/) (para clonar el repositorio)
- [Java JDK]
- [Java JRE]
- [Maven]

---

## 📥 Clonar el repositorio

```bash
git https://github.com/JeissonM/social-network.git
```

Luego de clonado en tu maquina local procede a ejecutar Docker Desktop y lanzar tu aplicación, para ello:
1. Ve a la raíz del proyecto
2. Verifica las credenciales que quieres usar para la base de datos en el archivo docker-compose.yml y en el archivo de propiedades del proyecto.
3. Ejecuta para generar el JAR
```bash
./mvnw clean package -DskipTests
Si quieres ejecutar los test unitarios solo escribe 
./mvnw clean package
```
4. Crea la imagen de la api + la base de datos
```bash
docker build -t social-network-api .
```
5. Reinicia docker
```bash
docker-compose down
```
6. Levanta el contenedor de nuevo
```bash
docker-compose up -d --build
```
7. Ya puedes ingresar a Swagger y probar la api: http://localhost:8080/swagger-ui/index.html#/
Verifica que 8080 sea el puerto habilitado para tu API
