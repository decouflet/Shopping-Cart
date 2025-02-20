# Proyecto Shopping-Cart Backend y Frontend

## Versiones Utilizadas

Este proyecto utiliza las siguientes versiones de tecnologías:

- **Frontend:**
  - Angular 15
  - Angular CLI 15.1.5
  - Node.js 18.14.0
  - npm 9.3.1

- **Backend:**
  - Java 17
  - Spring Boot 3.4.3
  - MySQL 8.0.41 (junto con MySQL Workbench)

## Manual de Instalación y Ejecución

Para levantar la aplicación correctamente, siga los siguientes pasos:

### 1. Clonar los repositorios
Asegúrese de clonar tanto el backend como el frontend desde los repositorios correspondientes.

### 2. Instalar dependencias
Para el frontend, instale las dependencias ejecutando:
```sh
npm install
```

### 3. Crear bases de datos
Debe crear tres bases de datos en MySQL con los nombres especificados en el archivo `application.properties` de cada microservicio:

- **cartDb** (para el microservicio Cart)
- **productDb** (para el microservicio Product)
- **userDb** (para el microservicio User)

### 4. Levantar los microservicios
Ejecute los microservicios en el siguiente orden:

1. **MSVC-USER**
2. **MSVC-PRODUCT**
3. **MSVC-CART**

El usuario y contraseña de la base de datos están especificados en el `application.properties` de cada microservicio.

### 5. Levantar el frontend
Ejecute el siguiente comando en la carpeta del frontend:
```sh
ng serve --open
```
Esto abrirá la aplicación en el navegador en `http://localhost:4200/`.

## Posibles Mejoras

Para mejorar la arquitectura del proyecto, se sugieren las siguientes mejoras:

1. **Microservicio Gateway**: Implementar un API Gateway para centralizar y filtrar todas las solicitudes.
2. **Microservicio Eureka**: Agregar un servicio de descubrimiento para la comunicación entre microservicios.
3. **Autenticación JWT**: Implementar autenticación basada en JSON Web Token (JWT) para proteger los endpoints.
4. **Microservicio Common**: Crear un microservicio para manejar excepciones y respuestas personalizadas de manera centralizada.

## Recursos Adicionales

- **Collection de Postman**: Se adjunta la colección de Postman.

---

