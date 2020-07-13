package u_nameGen.util.externalAccess;

import u_nameGen.main.Student;
import java.util.List;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail {
	String from = "ogosinkem@gmail.com";

	public SendEmail(List<Student> students, String[] forms, String[] emails) {
		writeEmail(students, forms, emails);
	}

	public SendEmail() {
		sendEmails(new String[] { "HELLO" },
				new String[] { "11ogosi_n@bexleygs.co.uk" });
	}

	public void writeEmail(List<Student> students, String[] forms,
			String[] emails) {
		sendEmails(createFiles(students, forms), emails);
	}

	private String[] createFiles(List<Student> students, String[] forms) {
		String[] messages = new String[forms.length];
		for (int a = 0; a < forms.length; a++) {
			messages[a] = "";
			for (int i = 0; i < students.size(); i++) {
				Student s = students.get(i);
				String stuInfo = s.getSName() + "," + s.getFName() + ","
						+ s.getUserName();
				if (forms[a].toLowerCase().equals(s.getForm().toLowerCase())) {
					messages[a] += stuInfo + "\n";
				}
			}
		}
		System.out.println(messages[0]);
		return messages;
	}

	public void sendEmails(String[] messages, String[] emails) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(from, "wasdzxcv12");
					}
				});
		for (int j = 0; j < 1; j++) {
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(from));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(emails[j]));

				// Set Subject: header field
				message.setSubject("This is the Subject Line!");

				// Now set the actual message
				message.setText(messages[j]);

				Transport transport = session.getTransport("smtp");

				transport.close();
				Transport.send(message);

				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}

	}
	// need to ask sir how he wants the files to be saved

}
