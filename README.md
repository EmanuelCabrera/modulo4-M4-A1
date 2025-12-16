# Order System

Sistema de gestión de órdenes aplicando principios SOLID.

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── mindhub/
│               └── order/
│                   ├── interfaces/    # Interfaces del sistema
│                   ├── classes/       # Clases de dominio
│                   ├── database/      # Implementaciones de persistencia
│                   ├── services/      # Servicios de negocio
│                   └── main/         # Clase principal
└── test/
    └── java/
        └── com/
            └── mindhub/
                └── order/
                    └── (tests)
```

## Paquetes

- **interfaces**: Contiene todas las interfaces del sistema
- **classes**: Clases de dominio y entidades
- **database**: Implementaciones de acceso a datos
- **services**: Lógica de negocio y servicios
- **main**: Punto de entrada de la aplicación

## Compilación

```bash
mvn clean compile
```

## Ejecución

```bash
mvn exec:java -Dexec.mainClass="com.mindhub.order.main.OrderSystem"
```

## Testing

```bash
mvn test
```

