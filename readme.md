# 📧 EmailAPI-Backend-Springboot

A simple Spring Boot backend project that sends emails via SMTP using the JavaMail API with `MimeMessage` and `Transport`. It exposes a REST API that can be tested using tools like Postman.

---

## 🔧 Tech Stack

- Java 17+
- Spring Boot
- JavaMailSender (`MimeMessage`)
- SMTP (e.g., Gmail)
- Postman (for testing)

Note: Use an app-specific password if using Gmail with 2FA.
---

## 🚀 How to Run the Project

### 1. Clone the Repository

git clone https://github.com/alok7kumar/EmailAPI-Backend-Springboot
cd EmailAPI-Backend-Springboot

##📨 Sending Email
##Endpoint

POST http://localhost:8080/sendemail
##Headers

Content-Type: application/json

##Request Body Example (JSON)

{
  "to": "recipient@example.com",
  "subject": "Test Email",
  "message": "This is a test email sent from Spring Boot REST API."
}

🧪 How to Test
Use Postman or any REST client:

1.Set the method to POST

2.URL: http://localhost:8080/sendemail

3.Body: raw JSON (see above)

4.Click Send — you should see a success message and receive an email



