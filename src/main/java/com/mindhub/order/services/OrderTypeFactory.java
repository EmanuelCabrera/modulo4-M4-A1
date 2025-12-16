package com.mindhub.order.services;

import com.mindhub.order.interfaces.IOrderType;
import com.mindhub.order.classes.StandardOrder;
import com.mindhub.order.classes.ExpressOrder;

/**
 * OrderTypeFactory - Factory para crear tipos de orden
 * Cumple OCP: Permite agregar nuevos tipos sin modificar código existente
 */
public class OrderTypeFactory {
    
    public static IOrderType createOrderType(String type) {
        switch(type) {
            case "Standard":
                return new StandardOrder();
            case "Express":
                return new ExpressOrder();
            default:
                throw new IllegalArgumentException("Tipo de orden desconocido: " + type);
        }
    }
}

