package pl.coderslab.zhpsystem.service;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Properties;

public class EmailService {

    private String addressee;
    private String title;
    private String msg;



    public EmailService(String addressee, String title, String msgType) {
        this.addressee = addressee;
        this.title = title;
        this.msg = takeMessage(msgType, "");
        sendMail();
    }

    //constructor for register mail
    public EmailService(String addressee, String title, String msgType, String token) {
        this.addressee = addressee;
        this.title = title;
        String activationToken = addressee+"/"+token;
        this.msg = takeMessage(msgType, activationToken);
        sendMail();
    }

    private String takeMessage(String msgType, String addedValue){

        String getMessage = null;

        if(msgType.equals("register")){

            getMessage = "<div style='font-family: Trebuchet MS, Calibri, Tahoma, Verdana, Arial, sans-serif; width: 100%; max-width: 600px; margin: 0 auto;'>" +
                    "<div style='margin-top: 20px; width: 98%; background-color: rgba(134,162,12,1); color: white; padding: 1%; padding-top: 10px; padding-bottom: 10px;'>" +
                    "<div style='font-size: 20px; padding-bottom: 15px; border-bottom: 1px solid white; width: 50%'>Dzień dobry!</div>" +
                    "<div style='font-size: 15px; margin-top: 15px; width: 80%; color: white; line-height: 25px;'>Serdecznie witamy w gronie użytkowników systemu e-Harcerz.pl<br></div>" +
                    "</div>" +

                    "<div style='margin: 0 auto; margin-top: 10px; width: 92%; color: #404040; padding-top: 10px; padding-bottom: 10px; line-height: 25px; text-align: justify;'>" +
                    "<div style='font-size: 14px; padding-bottom: 15px; border-bottom: 1px solid white;'>Twoje konto zostało utworzone, ale nie jest jeszcze aktywne. Aby dokonać aktywacji wejdź na stronę:<br><br><a href='https://e-harcerz.pl/activation/"+addedValue+"' style='color: #57847d; text-decoration: none;'>https://e-harcerz.pl/activation/"+addedValue+"</a></div>" +
                    "<div style='font-size: 13px; margin-top: 15px; color: brown;'>Zalecamy zmianę hasła podczas pierwszego logowania.</div>" +
                    "</div>" +

                    "<div style='margin-top: 20px; width: 98%; background-color: rgba(134,162,12,1); color: white; padding: 1%; padding-top: 10px; padding-bottom: 10px;'>" +
                    "<div style='font-size: 20px; padding-bottom: 15px; border-bottom: 1px solid white; width: 50%'>Dziękujemy za rejestrację,</div>" +
                    "<div style='font-size: 15px; margin-top: 15px;'>e-Harcerz.pl</div>" +
                    "</div>" +
                    "</div>";

        }

        return getMessage;
    }

    private void sendMail() {

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 25);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("eharcerz", "**********");
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("eharcerz@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addressee));
            message.setSubject(title);



            MimeBodyPart mimeBodyPart = new MimeBodyPart();

            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            //dotyczy dołączanego pliku
            //MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            //attachmentBodyPart.attachFile(new File("pom.xml"));

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            //dotyczy dołączanego pliku
            //multipart.addBodyPart(attachmentBodyPart);

            message.setContent(multipart);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String ... args) {

    }

}