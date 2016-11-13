package CurrencyReport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CurrencyReportor implements Reportor{

	
	public static void sendCurrncyReport(String content, InternetAddress receiver) {
		try {
			InputStream is = new FileInputStream("G://xuex//Java file//CurrencyReport//src//Prop");
			Properties prop= new Properties();
			prop.load(is);
			Authenticator au = new Authenticator() {
				
				@Override
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					String userName = prop.getProperty("UserName");
					String password = prop.getProperty("Password");
					return new javax.mail.PasswordAuthentication(userName, password);
				}	
			};
			Session mailSession = Session.getInstance(prop, au);
			MimeMessage msg = new MimeMessage(mailSession);
			InternetAddress from = new InternetAddress(prop.getProperty("UserName"));
			InternetAddress to = receiver;
			msg.setFrom(from);
			msg.setRecipient(RecipientType.TO, to);
			msg.setSubject("today's price");
			msg.setText(content);
			Transport.send(msg);
			System.out.println("OK");
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}


	
	
	

}
