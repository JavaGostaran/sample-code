/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chap_1;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;


/**
 *
 * @author ghaseminya
 */
public class sendmail {
    public boolean send(String email){
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("javagostaran@gmail.com","XXXXXXX");
				}
			});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@javahosting.ir"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
//                        message.setHeader("Content-Type","text/plain; charset=UTF-8");

                        message.setSubject(MimeUtility.encodeText("موضوع!", "UTF-8", "Q"),"UTF-8");
			message.setContent("متن ایمیل نیز اینجا می آید که البته به شکل اسکریپت html می تواند باشد<br/>البته کامل تر از این!", "text/html;charset=UTF-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
        return false;
    }
    
    public static void main(String a[])throws Exception{
        sendmail sm=new sendmail();
        sm.send("ghaseminya@gmail.com");
    }
}
