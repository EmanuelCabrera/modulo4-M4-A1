package com.mindhub.order.classes;

import com.mindhub.order.interfaces.IOrderProcessor;
import com.mindhub.order.interfaces.IPersistence;
import com.mindhub.order.database.NullPersistence;
import com.mindhub.order.services.OrderService;
import com.mindhub.order.interfaces.IOrderType;
import com.mindhub.order.interfaces.ITaxProcessor;
import com.mindhub.order.interfaces.ISendNotification;
import com.mindhub.order.interfaces.IGenerateReport;

/**
 * ReadOnlyOrderManager - Decorator que envuelve OrderService con persistencia de solo lectura
 * Cumple LSP: Puede sustituir a OrderService sin romper el comportamiento
 * No lanza excepciones, simplemente usa NullPersistence que no guarda nada
 */
public class ReadOnlyOrderManager implements IOrderProcessor {
    
    private final OrderService orderService;
    
    /**
     * Constructor que crea un OrderService con NullPersistence
     * Esto asegura que no se guarde nada, pero sin lanzar excepciones (LSP)
     */
    public ReadOnlyOrderManager(IOrderType orderType,
                                ITaxProcessor taxProcessor,
                                ISendNotification notification,
                                IGenerateReport reportGenerator) {
        // Usa NullPersistence en lugar de MySQLPersistence
        IPersistence nullPersistence = new NullPersistence();
        
        this.orderService = new OrderService(
            orderType, taxProcessor, nullPersistence, 
            notification, reportGenerator
        );
    }
    
    @Override
    public void processOrder(String type) {
        System.out.println("Procesando orden en modo solo lectura...");
        // LSP: Se comporta igual que OrderService, pero con persistencia nula
        orderService.processOrder(type);
    }
}