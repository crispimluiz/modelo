package com.crispimluiz.modelagem.services;

import org.springframework.mail.SimpleMailMessage;

import com.crispimluiz.modelagem.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
