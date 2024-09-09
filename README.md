# prueba-tecnica
Proyecto basado en la prueba tecnica del grupo INDITEX

Este proyecto consiste en una API de gestión de precios diseñada siguiendo principios de arquitectura hexagonal. El objetivo es manejar solicitudes de precios de productos para diferentes marcas y fechas, proporcionando la mejor oferta disponible según la prioridad de precios definida en la base de datos.

#Características Principales

Arquitectura Hexagonal: El proyecto está dividido en capas bien definidas (Dominio, Aplicación e Infraestructura), permitiendo un alto grado de desacoplamiento y flexibilidad para futuros cambios.

Seguridad con Spring Security: Implementación de seguridad básica con autenticación HTTP básica para proteger las rutas de la API.

Documentación de API con Swagger: La API está documentada y disponible para su exploración mediante Swagger, facilitando las pruebas y el acceso a los recursos de la API.

Base de Datos H2: Uso de H2 como base de datos en memoria, proporcionando un entorno de prueba ligero y de fácil configuración.

DTOs y Mappers: Utilización de MapStruct para transformar las entidades a DTOs de forma eficiente, asegurando la separación de las capas y la claridad en los datos expuestos.

Control de Logs: Integración de MDC (Mapped Diagnostic Context) para el seguimiento detallado de las solicitudes mediante requestId y registro de logs específicos de cada operación.

Manejo de Excepciones Personalizadas: Implementación de excepciones propias como PriceNotFoundException y UnexpectedPriceProcessingException para un manejo adecuado de errores en diferentes escenarios.

Pruebas Unitarias e Integradas: El desarrollo ha seguido un enfoque TDD (Desarrollo guiado por pruebas), cubriendo las principales funcionalidades con pruebas unitarias y de integración en los servicios y controladores.

Implementación de CI/CD: El proyecto está integrado con GitHub Actions, lo que permite la ejecución automatizada de pruebas en cada push o pull request para garantizar la calidad continua del código.

Dockerización: Se ha incluido un Dockerfile para facilitar el despliegue en diferentes entornos, incluyendo servicios en la nube como Render.

/api/prices/find: Busca y devuelve el precio aplicable para un producto, marca y fecha dada.



URL de la API alojada en un servidor gratuito Render. 
#https://price-api-vd2n.onrender.com/swagger-ui/index.html.

Aunque se esta utilizando un job para hacer ping y mantener el servidor activo, si este se encuentra inactivo por favor contacte con antoniomorenoarribas@gmail.com


#Casos de prueba
Los casos de pruebas mencionados en el documento, son los siguientes:

Realizar una petición a las 10:00 del día 14 para el producto 35455 y la marca 1 (ZARA) { "productId": 35455, "brandId": 1, "applicationDate": "2020-06-14T10:00:00" }

Realizar una petición a las 16:00 del día 14 para el producto 35455 y la marca 1 (ZARA).  { "productId": 35455, "brandId": 1, "applicationDate": "2020-06-14T16:00:00" } 

Realizar una petición a las 21:00 del día 14 para el producto 35455 y la marca 1 (ZARA).  { "productId": 35455, "brandId": 1, "applicationDate": "2020-06-14T21:00:00" } 

Realizar una petición a las 10:00 del día 15 para el producto 35455 y la marca 1 (ZARA).  { "productId": 35455, "brandId": 1, "applicationDate": "2020-06-15T10:00:00" } 

Realizar una petición a las 21:00 del día 16 para el producto 35455 y la marca 1 (ZARA).  { "productId": 35455, "brandId": 1, "applicationDate": "2020-06-16T21:00:00" }