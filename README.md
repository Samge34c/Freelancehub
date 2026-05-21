# FreelanceHub - Avance Semana 2

Este repositorio contiene el backend de FreelanceHub, una plataforma para conectar clientes con profesionales freelance.

En esta segunda semana se dejó funcionando la base principal del backend: autenticación, roles, conexión a base de datos, entidades principales y pruebas desde Swagger.

## Lo que ya funciona

- El proyecto corre con Java 21 y Spring Boot.
- La base de datos está en Supabase usando PostgreSQL.
- El backend se conecta a Supabase mediante variables de entorno.
- Swagger está habilitado para probar los endpoints.
- Hay autenticación con JWT.
- Las contraseñas se guardan cifradas con BCrypt.
- Existen tres roles:
  - ADMIN
  - CLIENTE
  - PROFESIONAL
- Se cargan datos iniciales al iniciar el proyecto.
- Se pueden consultar y crear categorías.
- Se pueden consultar y crear habilidades.
- Un cliente puede crear un proyecto.
- Un profesional puede enviar una cotización a un proyecto.
- El cliente puede ver las cotizaciones recibidas para su proyecto.

## Usuarios de prueba

ADMIN:

- admin@freelancehub.com
- Admin123!

CLIENTE:

- cliente@test.com
- Cliente123!

PROFESIONAL:

- profesional@test.com
- Profesional123!

## Tecnologías usadas

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Supabase
- Spring Data JPA
- Hibernate
- MapStruct
- Jakarta Validation
- Swagger
- Maven

## Cómo ejecutar el proyecto

El backend se ejecuta desde IntelliJ IDEA usando la configuración de Spring Boot.

La conexión a Supabase se maneja mediante variables de entorno:

- DB_URL
- DB_USERNAME
- DB_PASSWORD

Estas variables se configuran desde IntelliJ IDEA en:

Run → Edit Configurations → Environment variables

Después se ejecuta la aplicación con el botón verde de Run.

No se deben subir contraseñas reales al repositorio.

## Swagger

Con el backend corriendo, Swagger se abre en:

http://localhost:8080/swagger-ui/index.html

Desde ahí se pueden probar todos los endpoints.

## Flujo probado

El flujo que ya se probó fue:

1. Login como administrador.
2. Consulta de categorías.
3. Creación de una categoría.
4. Consulta de habilidades.
5. Creación de una habilidad.
6. Login como cliente.
7. Creación de un proyecto.
8. Login como profesional.
9. Creación de una cotización para el proyecto.
10. Consulta de cotizaciones del profesional.
11. Login como cliente.
12. Consulta de cotizaciones recibidas en el proyecto.

## Endpoints principales probados

Autenticación:

POST /api/v1/auth/login

Categorías:

GET  /api/v1/categories
POST /api/v1/categories

Habilidades:

GET  /api/v1/habilidades
POST /api/v1/habilidades

Proyectos:

POST /api/v1/projects
GET  /api/v1/projects/my

Cotizaciones:

POST /api/v1/projects/{id}/quotes
GET  /api/v1/quotes/my
GET  /api/v1/projects/{id}/quotes

## Ejemplo del flujo

Se creó un proyecto como cliente con el título:

Desarrollo de página web corporativa

Después el profesional envió una cotización con:

Precio: 2400000
Plazo: 25 días
Estado: PENDIENTE

Luego el cliente pudo ver esa cotización desde el endpoint del proyecto.

## Entidades actuales

El backend trabaja actualmente con estas entidades:

- Usuario
- ClientePerfil
- ProfesionalPerfil
- Categoria
- Habilidad
- Proyecto
- Cotizacion

Para esta entrega se priorizó el CRUD funcional de:

- Categoria
- Habilidad
- Proyecto

También se implementó el flujo básico:

Cliente crea proyecto → Profesional envía cotización → Cliente consulta cotizaciones recibidas

## Seguridad

El backend usa Spring Security con JWT.

Las rutas protegidas requieren token.

Si se intenta acceder sin token o con un token inválido, el sistema responde con un error 401.

Las operaciones también dependen del rol del usuario. Por ejemplo:

- ADMIN puede gestionar categorías y habilidades.
- CLIENTE puede crear proyectos y consultar cotizaciones de sus proyectos.
- PROFESIONAL puede enviar cotizaciones y consultar sus propias cotizaciones.

## Pendiente

Para las siguientes semanas queda pendiente completar:

- Contratos.
- Pago simulado.
- Evidencias.
- Calificaciones.
- Arbitraje.
- Expediente de confianza.
- Dashboard.
- Frontend.
- Relación completa entre profesionales y habilidades.