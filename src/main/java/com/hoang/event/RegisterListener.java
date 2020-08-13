package com.hoang.event;

import com.hoang.entities.VerificationToken;
import com.hoang.repository.verify_token.IVerifyTokenRepository;
import com.hoang.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
@Component
public class RegisterListener implements ApplicationListener<RegisterCompleteEvent> {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IVerifyTokenRepository repository;
    @Override
    public void onApplicationEvent(RegisterCompleteEvent registerCompleteEvent) {
        this.confirmRegistation(registerCompleteEvent);
    }
    public void confirmRegistation(RegisterCompleteEvent registerCompleteEvent){
        VerificationToken token = new VerificationToken();
        token.setCustomer(registerCompleteEvent.getCustomer());
        repository.save(token);
        String message = "<h3>Vui lòng bấm vào đây để xác thực email</h3><br>";
        message += "<a href='"+registerCompleteEvent.getAppUrl()+"/verify-email?token="+token.getToken()+"' style='background-color: #008CBA;\n" +
                "border: none;\n" +
                "  color: white;\n" +
                "  padding: 20px 100px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "  margin: 4px 2px;\n" +
                "  cursor: pointer;'>Verify your email</a>";
        try {
            emailService.sendEmail(registerCompleteEvent.getCustomer().getEmail(),"Xác thực email",message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
