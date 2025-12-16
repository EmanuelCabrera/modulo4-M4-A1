package com.mindhub.order.services;

import com.mindhub.order.interfaces.IGenerateReport;

public class ReportService implements IGenerateReport {

    //Genera un reporte PDF de la orden
    @Override
    public void generatePDFReport() {
        System.out.println("Generando reporte PDF de la orden...");
    }
}
