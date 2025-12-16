package com.mindhub.order.services;

import com.mindhub.order.interfaces.*;

/**
 * OrderService - Orquesta el procesamiento de órdenes
 * Cumple SRP: Solo orquesta, delega responsabilidades a otros servicios
 * Cumple DIP: Depende de abstracciones (interfaces), no de implementaciones concretas
 */
public class OrderService implements IOrderProcessor {
    
    private final IOrderType orderType;
    private final ITaxProcessor taxProcessor;
    private final IPersistence persistence;
    private final ISendNotification notification;
    private final IGenerateReport reportGenerator;
    
    // Inyección de dependencias (DIP)
    public OrderService(IOrderType orderType,
                       ITaxProcessor taxProcessor,
                       IPersistence persistence,
                       ISendNotification notification,
                       IGenerateReport reportGenerator) {
        this.orderType = orderType;
        this.taxProcessor = taxProcessor;
        this.persistence = persistence;
        this.notification = notification;
        this.reportGenerator = reportGenerator;
    }
    
    @Override
    public void processOrder(String type) {
        // OCP: Usa Strategy Pattern a través de IOrderType, no if/else
        orderType.process();
        
        // SRP: Delega cada responsabilidad a su servicio especializado
        taxProcessor.calculateTax();
        persistence.save();
        notification.sendEmailConfirmation();
        reportGenerator.generatePDFReport();
    }
}

