package com.descorp.rpgdocs.modules;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author David
 */
public class EmailSenderModule {
    
    private static final String EMAIL = "descorprg@gmail.com";
    private static final String PASS ="!DogeCoin00987";
    private Session s;
    private static EmailSenderModule myself;
    
    
    private EmailSenderModule(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication()
             {
                   return new PasswordAuthentication(EMAIL, PASS);
             }
        });
        this.s = session;
    }
    
    public static EmailSenderModule getInstance(){
        if(myself == null) {
            return new EmailSenderModule();
        }
        
        return myself;
    }
    
    
    public void sendNotification(String to, String msg){
     
        try {

            Message message = new MimeMessage(this.s);
            message.setFrom(new InternetAddress(EMAIL));

            Address[] toUser = InternetAddress.parse(to);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Convite chegando! -  RPGDocs");//Assunto
            message.setContent(msg, "text/html");
           
            Transport.send(message);

           } catch (MessagingException e) {
              throw new RuntimeException(e);
          }        
    }   
}
