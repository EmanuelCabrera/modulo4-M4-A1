package com.mindhub.order.services;

import com.mindhub.order.interfaces.ISendNotification; 
public class NotificationService implements ISendNotification {

    //Solo envía correo de confirmación
    @Override
    public void sendEmailConfirmation() {
        System.out.println("Enviando correo de confirmación...");
    }
}
