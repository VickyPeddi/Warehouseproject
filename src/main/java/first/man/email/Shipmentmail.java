package first.man.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Component
@Service
public class Shipmentmail {
    @Autowired
    private JavaMailSender mailSender;

    //
    public boolean send(String to, String subject, String text) {
        boolean sent = false;

        try {
            //create Mime Message object

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            //use  helper class object
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            //set details to object
            helper.setTo(to);


            helper.setSubject(subject);
            helper.setText(text);
            //send email
            mailSender.send(mimeMessage);


            sent = true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sent;

    }

}
