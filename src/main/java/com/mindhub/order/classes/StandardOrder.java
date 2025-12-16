package com.mindhub.order.classes;

import com.mindhub.order.interfaces.IOrderType;

public class StandardOrder implements IOrderType {
    @Override
    public void process() {
        System.out.println("Procesando orden estándar...");
    }
}

