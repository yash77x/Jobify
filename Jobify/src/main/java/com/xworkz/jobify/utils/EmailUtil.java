package com.xworkz.jobify.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    public boolean mail(String recipientEmail, String name) {
        if (recipientEmail != null && !recipientEmail.isEmpty()) {
            final String fromEmail = "yashmeharwade77@outlook.com"; // requires valid gmail id
            final String password = "Yash7302mm?"; // correct password for gmail id

            System.out.println("TLSEmail Start");
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.office365.com"); // SMTP Host
            props.put("mail.smtp.port", "587"); // TLS Port
            props.put("mail.smtp.auth", "true"); // enable authentication
            props.put("mail.smtp.starttls.enable", "true"); // enable STARTTLS

            // create Authenticator object to pass in Session.getInstance argument
            Authenticator auth = new Authenticator() {
                // override the getPasswordAuthentication method
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            };
            
            Session session = Session.getInstance(props, auth);

            return sendEmail(session, recipientEmail, "Welcome to Jobify - Your Job Portal Registration is Complete", 
            	    "Dear " + name + ",\n\n" +
            	    "We are thrilled to inform you that your registration on Jobify, the premier job portal, has been successfully completed. Welcome aboard!\n\n" +
            	    "If you have any queries or require assistance navigating our platform, please feel free to reach out to our support team.\n\n" +
            	    "Thank you for choosing Jobify. Wishing you success in your job search!\n\n" +
            	    "Best regards,\n" +
            	    "The Jobify Team");        
            } else {
            System.out.println("Invalid recipient email address");
            return false;
        }
    }

    private static boolean sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            // set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("yashmeharwade77@outlook.com", "Jobify"));

            msg.setReplyTo(InternetAddress.parse("yashmeharwade77@outlook.com", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            System.out.println("Message is ready");
            Transport.send(msg);

            System.out.println("Email Sent Successfully!!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
