package com.mindhub.order.database;

import com.mindhub.order.interfaces.IPersistence;

public class MySQLPersistence implements IPersistence {
    @Override
    public void save() {
        System.out.println("Guardando en MySQL...");
    }
}
