package com.mindhub.order.classes;

import com.mindhub.order.interfaces.IOrderProcessor;
import com.mindhub.order.interfaces.IPersistence;
import com.mindhub.order.database.NullPersistence;

public class ReadOnlyOrderManager implements IOrderProcessor {
    private IOrderProcessor orderProcessor;
    private IPersistence readOnlyPersistence;


    public ReadOnlyOrderManager(IOrderProcessor orderProcessor, IPersistence readOnlyPersistence) {
        this.orderProcessor = orderProcessor;
        this.readOnlyPersistence = new NullPersistence();
    }

    //Modo solo lectura
    @Override
    public void processOrder(String type) {
        System.out.println("Procesando orden en modo solo lectura...");
        orderProcessor.processOrder(type);
        readOnlyPersistence.save();
    }
}