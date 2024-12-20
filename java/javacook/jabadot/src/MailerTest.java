package jabadot;

import com.darwinsys.util.*;

import javax.mail.*;
import javax.mail.internet.*; 

public class MailerTest {

	public static void main(String[] args) throws MessagingException {
		Mailer m = new Mailer();
		m.setFrom("jabadot@daroad.darwinsys.com");
		m.setSubject("Have a nice day");
		m.addTo("ian");
		m.setBody("I have been wondering if you would permit me " +
			"to introduce myself\n");
		m.setVerbose(true);					// debugging
		m.setServer("127.0.0.1");
		m.doSend();
	}
} 
