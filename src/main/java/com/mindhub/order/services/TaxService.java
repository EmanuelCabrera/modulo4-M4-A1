package com.mindhub.order.services;

import com.mindhub.order.interfaces.ITaxProcessor;

public class TaxService implements ITaxProcessor {
    //Tasa de impuesto
    private double taxRate;

    public TaxService(double taxRate) {
        this.taxRate = taxRate;
    }

    //Calcula el impuesto de la orden
    @Override
    public double calculateTax() {
        System.out.println("Calculando impuestos...");
        return taxRate * 1.21;
    }
}