package com.mindhub.order.classes;

import com.mindhub.order.interfaces.IOrderType;

public class StandarOrder implements IOrderType {
    @Override
    public void process() {
        System.out.println("Procesando orden estándar...");
    }
}
