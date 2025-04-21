package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.model.EmailResponse;
import com.email.service.EmailService;
			/*												How to run this code : 						
			 				This is a REST API.     go to POSTMAN app, then
								POST,			 url: http://localhost:8080/sendemail, 			select 		body -> JSON
								write in box ->{ "to":"abc@gmail.com", "subject":"......", "message":"........"}
								its frontend is made in angular , see EmailGUI project on vscode
			*/
@RestController
@CrossOrigin					//ye tab lagate hain jab front end kisi aur ide par banaya gaya ho, aur local host conflict na kare, iss case me vscode par angular se frontend banaya gaya port 4200 par
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@RequestMapping("/welcome")
	public String welcome() {
		
		return "hello this is my email api";
	}
	
	//api to send email
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request) { //RequestBody se json mein jo data bharenge wo request me store ho jayega
	
		System.out.println(request);
		boolean result =	emailService.sendEmail(request.getSubject(), request.getMessage(),request.getTo());
		
		if (result) {
			return ResponseEntity.ok(new EmailResponse("Email sent successfully. . . ."));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email not sent . . . ."));
		}
		
	}
	
}
