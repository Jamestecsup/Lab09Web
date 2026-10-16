# Lab09 - Backend JWT (demo_Security_JWT)

Implementacion fiel al docx `GLAB-S09-RCOELLO-2026-01-1.docx` + imagenes.

## Que se implemento
- Imagen 4 (Spring Initializr): Maven, Java 17, Jar, Properties, deps Web / Security / MySQL / Lombok / DevTools / Validation / JPA. Spring Boot parent `3.2.5` (estable con Java 17; la imagen dice 4.0.6 que no es estable/LTS para Java 17).
- Imagen 5 (estructura): `com.tecsup.config / controller / dto / entity / jwt / repository / service` con los 18 archivos de la guia (puntos 4-18).
- Paquete base `com.tecsup` (como esta en todo el codigo del docx). La imagen del Initializr dice `com.tecsup.demo_Security_JWT`, pero todo el codigo usa `com.tecsup.*`, asi que se usa `com.tecsup` para que compile.
- `TestController` incluye ademas `GET /api/test/mod` -> `"Mod Content"` porque `SecurityConfig` lo protege con `hasRole("MODERATOR")` (el docx solo lista `/all /user /admin`).
- `DataInitializer` crea `USER, ADMIN, MODERATOR` al arrancar. La guia no dice como crearlos y sin esto el signup falla con `Rol USER no encontrado`.

## Requisitos
Java 17, Maven 3.9+, MySQL 8 (XAMPP/Wamp o instalador), puerto 8080 libre.

## 1. Base de datos (paso I del docx)
```sql
create database security_db;
```
O ejecuta `demo_Security_JWT/database.sql`. La URL lleva `createDatabaseIfNotExist=true`, asi que arranca aunque no la crees.

Si tu MySQL tiene password, editalo en `src/main/resources/application.properties`:
`spring.datasource.password=TU_PASSWORD`

## 2. application.properties (punto 3 del docx, corregido con saltos de linea)
El docx lo trae todo pegado en una linea. Aqui esta separado:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/security_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
jwt.secret=clave_super_segura_clave_super_segura_para_hs256_minimo_32_chars
jwt.expiration=3600000
```
Nota: el secret del docx (`clave_super_segura_clave_super_segura`) ya mide 38 chars y vale para HS256 (>=32). Aqui se usa uno mas largo pero compatible.

## 3. Ejecutar
```bash
cd demo_Security_JWT
mvn spring-boot:run
```
Verificado: `mvn compile` OK con Java 17.

## 4. Probar en Postman (lo haces tu)
Guia con huecos para capturas: `PRUEBAS_POSTMAN_GUIA.md`
Textos copiar/pegar: `postman/textos_para_postman.txt`
Coleccion importable: `postman/Lab09_JWT.postman_collection.json`

Orden: signup admin -> signup user -> signin admin (copiar token) -> GET /api/test/admin con Bearer -> signin user (copiar token) -> GET /api/test/user con Bearer.
