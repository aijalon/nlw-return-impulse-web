package com.feedbackwidget.domain.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feedbackwidget.domain.model.Feedback;
import com.feedbackwidget.domain.repository.FeedbackRepository;
import com.feedbackwidget.domain.services.SendMailService.Message;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private SendMailService sendMail;

	@Transactional
	public Feedback save(Feedback feedback) {
		Set<String> emailAdress = new HashSet<String>();
		emailAdress.add("juniorsjs6@hotmail.com");
		
		var message = Message.builder()
				.subject("Seu Feedback foi Registrado!")
				.email("<div style=\"font-family: sans-serif; font-size: 16px; color: #111;\">"+
				"<p>Tipo do feedback: "+feedback.getType()+"</p>"+
				"<p>Comentário: "+feedback.getComment()+"</p>"+
				"<img src=\""+feedback.getScreenshot()+"\" />"+
				"</div>")
				.destination(emailAdress)
				.build();
		
		sendMail.send(message);
		
		return feedbackRepository.save(feedback);
	}
}
