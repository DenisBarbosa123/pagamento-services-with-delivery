package br.inatel.dm112.adapter;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class MailAdapter {

	public void sendMail(final String from, final String password, String to, byte[] content) {

		System.out.println("Enviando email para: " + to);

		try {
			Message message = new MimeMessage(getSession(from, password, getProperties()));
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPartText = new MimeBodyPart(); // texto
			
			if(content != null)
			{
				System.out.println("Enviando e-mail com anexo");
				message.setSubject("Boleto");
				messageBodyPartText.setText("Boleto gerado pelo sistema de Vendas");

				BodyPart messageBodyPartAtt = new MimeBodyPart(); // anexo
				ByteArrayDataSource source = new ByteArrayDataSource(content, "application/pdf");
				source.setName("Boleto.pdf");

				messageBodyPartAtt.setDataHandler(new DataHandler(source));
				messageBodyPartAtt.setFileName("Boleto_Venda.pdf");
				multipart.addBodyPart(messageBodyPartAtt);
			}
			else {
				System.out.println("Enviando e-mail informando o cliente sobre a entrega");
				message.setSubject("Entrega efetuada");
				messageBodyPartText.setText("Entrega efetuada com sucesso");
			}
			
			multipart.addBodyPart(messageBodyPartText);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private Session getSession(String from, String password, Properties props)
	{
		return Session.getInstance(props, new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(from, password);
			}
		});
	}
	
	private Properties getProperties()
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587"); 
		
		return props;
	}
}
