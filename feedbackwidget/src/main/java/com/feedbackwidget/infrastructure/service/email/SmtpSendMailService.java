package com.feedbackwidget.infrastructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.feedbackwidget.core.email.EmailProperties;
import com.feedbackwidget.domain.services.SendMailService;

@Service
public class SmtpSendMailService implements SendMailService{

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void send(Message message) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper =new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setTo(message.getDestination().toArray(new String[0]));
			helper.setSubject(message.getSubject());
			helper.setText(message.getEmail(), true);
			
			mailSender.send(mimeMessage);
			
		}catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
		
		
		
	}

}
