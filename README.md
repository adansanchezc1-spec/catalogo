# Catálogo de Productos

Proyecto Java para gestionar un catálogo de productos con operaciones CRUD, persistencia en JSON y tests unitarios con JUnit 5.

## Estructura del Proyecto

- `src/main/java/model/main/catalogo/Catalogo.java`: Clase principal con el flujo de ejecución.
- `src/main/java/model/main/controller/CatalogoController.java`: Controlador que maneja la lógica de negocio.
- `src/main/java/model/main/service/CatalogoService.java`: Servicio que interactúa con el repositorio.
- `src/main/java/model/main/repository/CatalogoRepositoryJSON.java`: Repositorio para persistencia en JSON.
- `src/main/java/model/main/menu/`: Clases de modelo (ProductoCatalogo, ProveedorComercial, Cliente, Gerente).

## Tests Unitarios

Los tests unitarios están en `src/test/java/` y cubren:

- **ProductoCatalogoTest**: Constructores, getters, setters, toString.
- **ProveedorComercialTest**: Constructores, getters, crearProductoEjemplo.
- **ClienteTest**: Constructores y getters.
- **GerenteTest**: Constructores y getters.
- **CatalogoRepositoryJSONTest**: Operaciones CRUD de persistencia.
- **CatalogoServiceTest**: Lógica de servicio.
- **CatalogoControllerTest**: Lógica de controlador.
- **CatalogoTest**: Test básico de la clase main.

## Ejecutar Tests

Para ejecutar los tests con Maven:

```bash
mvn test
```

Los tests usan JUnit 5 y verifican la funcionalidad de todas las clases.

## Dependencias

- JUnit 5 para tests.
- Java 24 para compilación.

## Flujo de Ejecución

1. Configurar cliente y proveedor (input del usuario).
2. Añadir productos al catálogo.
3. Revisar productos guardados.
4. Menú interactivo para CRUD y cambiar proveedor.