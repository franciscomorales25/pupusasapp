# PupusApp – Proyecto de la Unidad 6 (Spring Boot)

Proyecto completo con todo el código de la clase: arquitectura en capas, JPA con base de datos H2, Lombok, Swagger y ejemplos para Postman.

## 1. Lo que necesitas instalar (una sola vez)

1. **JDK 17 o superior** (por ejemplo Eclipse Temurin: https://adoptium.net).
2. **Visual Studio Code**.
3. Al abrir la carpeta, VS Code te va a sugerir extensiones; acepta. Son:
   - *Extension Pack for Java* (Microsoft)
   - *Spring Boot Extension Pack* (VMware)
   - *REST Client* (para el archivo `pruebas.http`)

Lombok ya viene soportado por el Extension Pack for Java, no hay que instalar nada más.

## 2. Abrir y correr

1. Descomprime el zip y en VS Code usa **File > Open Folder** sobre la carpeta `pupusapp`.
2. Espera a que en la barra de abajo termine de cargar el proyecto Java (la primera vez descarga las librerías de internet, tarda unos minutos).
3. Abre `src/main/java/com/pupusapp/PupusAppApplication.java` y da clic en **Run** arriba del método `main` (o presiona F5).
4. Cuando en la consola salga `Started PupusAppApplication`, ya está corriendo en el puerto 8080.

Si tienes Maven instalado, también puedes correrlo con: `mvn spring-boot:run`

## 3. Qué probar en clase

| Qué | Dirección |
| --- | --- |
| Menú (base de datos) | http://localhost:8080/api/pupusas |
| Pupusa por ID | http://localhost:8080/api/pupusas/1 |
| Filtro con @RequestParam | http://localhost:8080/api/pupusas/baratas?maxPrecio=0.80 |
| Saludo (inyección de dependencias) | http://localhost:8080/saludo?nombre=Karla |
| Primer REST en memoria | http://localhost:8080/api/memoria/pupusas |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| Consola de la base de datos H2 | http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:pupusapp`, usuario `sa`, sin contraseña) |

Para los POST:
- **En VS Code:** abre `pruebas.http` y da clic en *Send Request* sobre cada bloque.
- **En Postman:** File > Import y elige `postman/PupusApp.postman_collection.json`.

## 4. Mapa del código (igual que en la clase)

```
src/main/java/com/pupusapp/
├── PupusAppApplication.java      Arranque (@SpringBootApplication)
├── controller/
│   ├── PupusaController.java     6.2 Controlador: el portero (+ anotaciones Swagger)
│   ├── SaludoController.java     6.3.4 Ejemplo de @Autowired
│   └── MenuMemoriaController.java 6.3.5 Primer servicio REST con lista en memoria
├── service/
│   ├── PupusaService.java        6.2 Servicio: reglas de negocio (@Transactional)
│   └── SaludoService.java        6.3.4 Servicio del saludo
├── repository/
│   └── PupusaRepository.java     6.2 Repositorio: findBySku, findByPrecioLessThan
├── model/Pupusa.java             Entidad con Lombok (@Data)
├── dto/PupusaDTO.java            La "papeleta" que manda el cliente
├── exception/ManejadorErrores.java Convierte errores en 400 y 409
└── config/
    ├── DatosIniciales.java       Carga 4 pupusas al arrancar
    └── OpenApiConfig.java        Título e información de Swagger
src/test/java/.../PupusaServiceTest.java  6.1 Probar la regla del precio SIN base de datos
```

## 5. Demostraciones rápidas para la clase

- **Regla de negocio:** manda un POST con `"precio": 0` y verás `400` con el mensaje "El precio debe ser mayor a cero."
- **SKU repetido:** manda un POST con `"sku": "PUP-REV-001"` y verás `409 Conflict`.
- **404:** pide `/api/pupusas/999`.
- **Memoria vs base de datos:** crea una pupusa, reinicia la app y vuelve a consultar: desaparece, porque H2 está en memoria. Es el puente para hablar de MySQL más adelante.
- **Prueba unitaria:** en VS Code abre `PupusaServiceTest.java` y da clic en el triángulo verde junto a la prueba. Corre en milisegundos y sin base de datos: eso es arquitectura limpia.
