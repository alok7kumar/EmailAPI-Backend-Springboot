package com.email.service;

import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	public boolean sendEmail(String subject, String message, String to) {  //this method is called in controller
		
		//this code is copied from EmailSender project( class GEmailSender)
			boolean flag = false;
		
			String from = "alokkumar9318@gmail.com";
		
			//SMTP properties  : we can also set these properties in application.properties and then retrieve from it
			Properties properties		=	new Properties();
			properties.put("mail.smtp.auth", true);					//Enables SMTP authentication— Gmail requires authentication when sending email, so this should be true.
			properties.put("mail.smtp.starttls.enable", true);		// Enables TLS security (see at last for full explanation)— It ensures the communication with Gmail’s SMTP server is encrypted.
			properties.put("mail.smtp.port", "587");					//The Gmail TLS port, Sets the port number— Port 587 is typically used for TLS/STARTTLS.
			properties.put("mail.smtp.host", "smtp.gmail.com");	//Gmail's SMTP server— For Gmail, this should always be smtp.gmail.com.
			
			String username = "alokkumar9318";
			String password = "xjkb hhfu bpbb cknj"; //use generated app password from gmail(not actual gmail password),   see main class for full info
			
			//Session
			//Step1 : to get the session object
			Session session	=	Session.getInstance(properties, 							//Session.getInstance: Creates a session with the properties and authentication
																		new Authenticator() {				//Provides your Gmail credentials for SMTP authentication
																					@Override
																					protected PasswordAuthentication getPasswordAuthentication() {
																					return new PasswordAuthentication(username, password);
																					}
																				});
			
			try {
				// Step 2 : compose the message [text, multimedia]
				Message message1	=	new MimeMessage(session);
				
				message1.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message1.setFrom(new InternetAddress(from));
				message1.setSubject(subject);
				message1.setText(message);
				
			//Step 3 : send the message using Transport class
				Transport.send(message1);		//This sends the message using Gmail's SMTP server, If successful, the flag becomes true
				System.out.println("Sent success. . . . . . . .");
				
				flag = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return flag;
		}
	
}


/*
 	 Email bhejne ke liye pehle jis account se bhejna hai usme 2-step authentication karna hoga. is project mein alokkumar9318 me aisa
 	 kiya gaya hai. 2way karne ke baad usi account se ek temporary "App Password " generate kiya gaya hai. 
 	 aisa karne ke liye is link par gaye kyuki gmail par visible nahi hai :    https://myaccount.google.com/apppasswords
 	 is link par jakar app name mein name ke andar EmailApp likho aur enter karne par 16 digits ka password generate hoga,
 	 usko copy karne ke baad GMailSender class ke password variable ke andar paste kar dein, 
 	 ab code run ho jayega aur mail sent ho jayega.
 	 
 	 Note: jarurat ho to password save kar lo, kyuki dobara ye visible nahi rahega, ya fir puranaa  delete kar ke naya app password bana lo
	
 	 code run karne ke baad us password ko bhi delete kar dein
*/
