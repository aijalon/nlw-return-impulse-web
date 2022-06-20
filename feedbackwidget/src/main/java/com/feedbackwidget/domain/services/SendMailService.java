package com.feedbackwidget.domain.services;

import java.util.Set;

public interface SendMailService {

	
	void send(Message message);
	
	class Message {
		private Set<String> destination;
		private String subject;
		private String email;
		
		
		public Set<String> getDestination() {
			return destination;
		}
		public void setDestination(Set<String> destination) {
			this.destination = destination;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		public static MessageBuilder builder() {
			return new MessageBuilder();
		}
		
		public static final class MessageBuilder{
			private Set<String> destination;
			private String subject;
			private String email;
			
			private MessageBuilder() {	
			}
			
			public MessageBuilder destination(Set<String> destination) {
				this.destination = destination;
				return this;
			}
			
			public MessageBuilder subject(String subject) {
				this.subject = subject;
				return this;
			}
			
			public MessageBuilder email(String email) {
				this.email = email;
				return this;
			}
			
			public Message build() {
				Message message = new Message();
				message.setDestination(destination);
				message.setEmail(email);
				message.setSubject(subject);
				return message;
			}
			
		}
		
	}

}
