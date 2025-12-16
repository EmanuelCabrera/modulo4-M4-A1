package com.mindhub.order.classes;

import com.mindhub.order.interfaces.IOrderType;

public class ExpressOrder implements IOrderType {
    @Override
    public void process() {
        System.out.println("Procesando orden express...");
    }
}
