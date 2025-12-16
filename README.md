# Order System

Sistema de gestión de órdenes aplicando principios SOLID.

## Descripción

Este proyecto implementa un sistema de gestión de órdenes que demuestra la aplicación práctica de los cinco principios SOLID:
- **SRP** (Single Responsibility Principle): Cada clase tiene una única responsabilidad
- **OCP** (Open/Closed Principle): Extensible sin modificar código existente
- **LSP** (Liskov Substitution Principle): Subtipos sustituibles sin romper el comportamiento
- **ISP** (Interface Segregation Principle): Interfaces pequeñas y específicas
- **DIP** (Dependency Inversion Principle): Dependencias de abstracciones, no de implementaciones

## Estructura del Proyecto

```
src/
├── main/
│   └── java/
│       └── com/
│           └── mindhub/
│               └── order/
│                   ├── interfaces/    # Interfaces del sistema
│                   │   ├── IOrderProcessor.java
│                   │   ├── IOrderType.java
│                   │   ├── ITaxProcessor.java
│                   │   ├── IPersistence.java
│                   │   ├── ISendNotification.java
│                   │   └── IGenerateReport.java
│                   ├── classes/       # Clases de dominio
│                   │   ├── StandardOrder.java
│                   │   ├── ExpressOrder.java
│                   │   └── ReadOnlyOrderManager.java
│                   ├── database/      # Implementaciones de persistencia
│                   │   ├── MySQLPersistence.java
│                   │   └── NullPersistence.java
│                   ├── services/      # Servicios de negocio
│                   │   ├── OrderService.java
│                   │   ├── OrderTypeFactory.java
│                   │   ├── TaxService.java
│                   │   ├── NotificationService.java
│                   │   └── ReportService.java
│                   └── main/         # Clase principal
│                       └── OrderSystem.java
└── test/
    └── java/
        └── com/
            └── mindhub/
                └── order/
                    └── (tests)
```

## Componentes Principales

### Interfaces (`interfaces/`)

- **IOrderProcessor**: Define el contrato para procesar órdenes
- **IOrderType**: Define el contrato para tipos de orden (Strategy Pattern)
- **ITaxProcessor**: Define el contrato para cálculo de impuestos
- **IPersistence**: Define el contrato para persistencia de datos
- **ISendNotification**: Define el contrato para envío de notificaciones
- **IGenerateReport**: Define el contrato para generación de reportes

### Clases de Dominio (`classes/`)

- **StandardOrder**: Implementación de orden estándar
- **ExpressOrder**: Implementación de orden express
- **ReadOnlyOrderManager**: Manager de solo lectura que demuestra LSP

### Persistencia (`database/`)

- **MySQLPersistence**: Implementación de persistencia en MySQL
- **NullPersistence**: Implementación nula (Null Object Pattern) para modo solo lectura

### Servicios (`services/`)

- **OrderService**: Orquesta el procesamiento completo de órdenes
- **OrderTypeFactory**: Factory para crear tipos de orden (OCP)
- **TaxService**: Servicio de cálculo de impuestos
- **NotificationService**: Servicio de notificaciones por email
- **ReportService**: Servicio de generación de reportes PDF

## Principios SOLID Implementados

### SRP (Single Responsibility Principle)
Cada clase tiene una única responsabilidad:
- `TaxService`: Solo calcula impuestos
- `NotificationService`: Solo envía notificaciones
- `ReportService`: Solo genera reportes
- `OrderService`: Solo orquesta el proceso

### OCP (Open/Closed Principle)
Se pueden agregar nuevos tipos de orden sin modificar código existente:
- Nuevos tipos se agregan implementando `IOrderType`
- `OrderTypeFactory` se extiende sin modificar la lógica existente

### LSP (Liskov Substitution Principle)
`ReadOnlyOrderManager` puede sustituir a `OrderService` sin romper el comportamiento:
- Implementa `IOrderProcessor` igual que `OrderService`
- Usa `NullPersistence` en lugar de lanzar excepciones

### ISP (Interface Segregation Principle)
Interfaces pequeñas y específicas:
- Cada interfaz define un contrato único y específico
- Las clases solo implementan lo que necesitan

### DIP (Dependency Inversion Principle)
Dependencias de abstracciones, no de implementaciones:
- `OrderService` depende de interfaces, no de clases concretas
- Inyección de dependencias a través del constructor

## Compilación

```bash
mvn clean compile
```

## Ejecución para correr la prueba

```bash
mvn exec:java
```

El programa demostrará:
1. Procesamiento de orden estándar
2. Procesamiento de orden express
3. Procesamiento de orden en modo solo lectura (LSP)

## Testing

```bash
mvn test
```

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior

