package com.mindhub.order.database;

import com.mindhub.order.interfaces.IPersistence;

public class NullPersistence implements IPersistence {
    @Override
    public void save() {
        System.out.println("Modo solo lectura...");
    }
}
