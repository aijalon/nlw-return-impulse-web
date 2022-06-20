package com.feedbackwidget.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import com.feedbackwidget.api.controller.FeedbackController;
import com.feedbackwidget.domain.model.Feedback;
import com.feedbackwidget.domain.repository.FeedbackRepository;
import com.feedbackwidget.domain.services.FeedbackService;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {
	
	@Autowired
	private FeedbackController feedbackController;
		
	@MockBean
	private FeedbackService feedbackService;
	
	@MockBean
	private FeedbackRepository feedbackRepository;
	
	Feedback feedback = new Feedback();

	
	@BeforeEach
	public void setup() {
		RestAssuredMockMvc.standaloneSetup(this.feedbackController);
		
		feedback.setId(1L);
		feedback.setComment("Tá bugado");
		feedback.setType("teste");
		feedback.setUuid("63c7d41f-07e1-4380-a85d-322236d6ed6c");
	}
	
	@Test
	public void shouldReturnSuccess_WhenPostFeedback(){
			
		RestAssuredMockMvc.
		given()
			.contentType("application/json")
			.body(this.feedback)
		.when()
			.post("/feedbacks")
		.then()
			.statusCode(HttpStatus.CREATED.value());	
		
	}
	
	@Test
	public void shouldReturnError_WhenMissType() {
		
		feedback.setType(null);
		
		RestAssuredMockMvc.
		given()
			.contentType("application/json")
			.body(feedback)
		.when()
			.post("/feedbacks")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void shouldReturnError_WhenMissComment() {
		
		feedback.setComment(null);
		
		RestAssuredMockMvc.
		given()
			.contentType("application/json")
			.body(feedback)
		.when()
			.post("/feedbacks")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	

}
