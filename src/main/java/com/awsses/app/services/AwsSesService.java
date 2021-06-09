package com.awsses.app.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.awsses.app.config.AwsSesConfg;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.Body;
import software.amazon.awssdk.services.ses.model.Content;
import software.amazon.awssdk.services.ses.model.Destination;
import software.amazon.awssdk.services.ses.model.Message;
import software.amazon.awssdk.services.ses.model.SendEmailRequest;
import software.amazon.awssdk.services.ses.model.SendEmailResponse;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailRequest;
import software.amazon.awssdk.services.ses.model.SendTemplatedEmailResponse;
import software.amazon.awssdk.services.ses.model.SesException;


@Service
public class AwsSesService {

	@Autowired
	private AwsSesConfg sesClientConfig;

	//Configure email FROM and TO
	private final String FROM = "from@gmail.com";
	private final String TO = "to@gmail.com";
	private final String SUBJECT = "Amazon SES test (AWS SDK for Java)";


	private static final String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
			+ "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
			+ "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>" 
			+ "AWS SDK for Java</a>";


	/**
	 * Envia un email en formato HTML
	 * @return
	 */
	public String sendMailRaw() {
		try {
			Destination destination = Destination.builder()
					.toAddresses(TO)
					.build();

			Content content = Content.builder()
					.data(HTMLBODY)
					.build();

			Content sub = Content.builder()
					.data(SUBJECT)
					.build();


			Body body = Body.builder()
					.html(content)
					.build();


			Message msg = Message.builder()
					.subject(sub)
					.body(body)
					.build();


			SendEmailRequest emailRequest = SendEmailRequest.builder()
					.destination(destination)
					.message(msg)
					.source(FROM)
					.build();


			SesClient client = sesClientConfig.amazonSES();
			SendEmailResponse emailResult = client.sendEmail(emailRequest);

			return emailResult.messageId();
		} catch (Exception ex) {
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
			return ex.getMessage();
		}
	}


	/**
	 * Método que envía un email en base a un template
	 * @return string
	 */
	public String sendEmailFromTemplate() {

		try {

			String templateName = "TEMPLATE";
			String templateData = "{\"subject\":\"subject\", \"patient_name\":\"Arbey Jimenez\",\"service\":\"service\", \"type\":\"type\", \"attendedAt\":\"1564444800\", \"url\":\"https://localhost\"}}";

			Destination destination = Destination.builder()
					.toAddresses(TO)
					.build();

			SendTemplatedEmailRequest templatedEmailRequest = SendTemplatedEmailRequest.builder()
					.destination(destination)
					.source(FROM)
					.template(templateName)
					.templateData(templateData)
					.build();

			SesClient client = sesClientConfig.amazonSES();
			SendTemplatedEmailResponse templatedEmailResult = client.sendTemplatedEmail(templatedEmailRequest);
			return templatedEmailResult.messageId();

		} catch (SesException e) {
			return e.getMessage();
		}

	}
	
	
	public String saludo() {
		return "hola mundo cruel";
	}
}
