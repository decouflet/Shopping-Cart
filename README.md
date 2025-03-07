# Proyecto Shopping-Cart 2.0 Backend y Frontend

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


## Mejoras version 2.0
Los cambios que se hicieron fueron enfocados en el FrontEnd para optimizar su funcionalidad y mejorar la experiencia de usuario:

1. **Búsqueda de productos**: Se agregó un buscador en el header para filtrar productos en tiempo real.  
2. **Categorías de productos**: Se implementó un dropdown en el header con categorías para filtrar productos.  
3. **Listado de compras en el header**: Se añadió la funcionalidad del despliegue de un listado con los productos agregados, sus cantidades, precio y el total del costo del carrito en tiempo real (se movió la funcionalidad del boton 'cart cost' a esta seccion).  
4. **Paginación de productos**: Se implementó una navegación con flechas para mostrar solo cuatro productos a la vez y desplazarlos con animaciones.  
5. **Animaciones de desplazamiento**: Se agregó una transición suave al cambiar entre grupos de productos.  
6. **Resaltado de productos**: Ahora, los productos se resaltan con un borde rojo cuando el usuario pasa el mouse sobre ellos.  
7. **Refactorizacion en la estructura de la lista de productos**: Se modifico la manera de mostrar los propductos para evitar codigo repetido, haciendo uso de la directiva *ngFor  
8. **Lista de clientes**: Se agrego una lista de clientes que actualiza el gasto de cada uno en tiempo real, ademas de su estado vip (en caso de gastar mas de $10.000 pasa a ser usuario vip)  
9. **Detalle del producto**: Se agrego tanto el nombre como el precio del producto dentro del cuadro donde se visualiza  

---

