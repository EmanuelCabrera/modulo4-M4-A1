package com.mindhub.order.main;

import com.mindhub.order.services.*;
import com.mindhub.order.database.MySQLPersistence;
import com.mindhub.order.classes.ReadOnlyOrderManager;
import com.mindhub.order.interfaces.IOrderType;
import com.mindhub.order.interfaces.ITaxProcessor;
import com.mindhub.order.interfaces.IPersistence;
import com.mindhub.order.interfaces.ISendNotification;
import com.mindhub.order.interfaces.IGenerateReport;

/**
 * OrderSystem - Clase principal que demuestra la solución SOLID
 * 
 * Demuestra:
 * - SRP: Cada servicio tiene una única responsabilidad
 * - OCP: Se pueden agregar nuevos tipos de orden sin modificar código existente
 * - LSP: ReadOnlyOrderManager puede sustituir a OrderService sin romper el comportamiento
 * - ISP: Interfaces pequeñas y específicas
 * - DIP: Dependencias de abstracciones, no de implementaciones concretas
 */
public class OrderSystem {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Órdenes - Solución SOLID ===\n");
        
        // --- Orden Normal ---
        System.out.println("--- Orden Normal ---");
        processStandardOrder();
        
        System.out.println("\n--- Orden Express ---");
        processExpressOrder();
        
        System.out.println("\n--- Orden de Solo Lectura (LSP cumplido) ---");
        processReadOnlyOrder();
    }
    
    private static void processStandardOrder() {
        // Crear servicios especializados (SRP: cada uno tiene una responsabilidad)
        IOrderType standardOrder = OrderTypeFactory.createOrderType("Standard");
        ITaxProcessor taxService = new TaxService(100.0);
        IPersistence mysqlPersistence = new MySQLPersistence();
        ISendNotification notificationService = new NotificationService();
        IGenerateReport reportService = new ReportService();
        
        // Inyectar dependencias (DIP: depende de interfaces, no de clases concretas)
        OrderService orderService = new OrderService(
            standardOrder, taxService, mysqlPersistence, 
            notificationService, reportService
        );
        
        orderService.processOrder("Standard");
    }
    
    private static void processExpressOrder() {
        // OCP: Agregar nuevo tipo sin modificar código existente
        IOrderType expressOrder = OrderTypeFactory.createOrderType("Express");
        ITaxProcessor taxService = new TaxService(150.0);
        IPersistence mysqlPersistence = new MySQLPersistence();
        ISendNotification notificationService = new NotificationService();
        IGenerateReport reportService = new ReportService();
        
        OrderService orderService = new OrderService(
            expressOrder, taxService, mysqlPersistence, 
            notificationService, reportService
        );
        
        orderService.processOrder("Express");
    }
    
    private static void processReadOnlyOrder() {
        // Crear servicios (sin persistencia, se inyectará NullPersistence en ReadOnlyOrderManager)
        IOrderType standardOrder = OrderTypeFactory.createOrderType("Standard");
        ITaxProcessor taxService = new TaxService(100.0);
        ISendNotification notificationService = new NotificationService();
        IGenerateReport reportService = new ReportService();
        
        // LSP: ReadOnlyOrderManager puede sustituir a OrderService sin romper el comportamiento
        // No lanza excepción, simplemente usa NullPersistence internamente
        ReadOnlyOrderManager readOnlyManager = new ReadOnlyOrderManager(
            standardOrder, taxService, notificationService, reportService
        );
        
        // Esto debería funcionar sin lanzar excepción (LSP cumplido)
        readOnlyManager.processOrder("Standard");
    }
}

