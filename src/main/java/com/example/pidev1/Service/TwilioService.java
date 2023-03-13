package com.example.pidev1.Service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
  
//besh naamel compte w nhot num tel khater compte teii exprira

@Service
public class TwilioService {
    public void sendSMS(String Code) {
        Twilio.init("AC85c0d6d1fda13e86b586d8aee5d92da1", "e788580b1038f2df15acfb96f5f424a3");
        Message.creator(new PhoneNumber("+216....."), new PhoneNumber("+15075644130"), ("You're verfication Code IS :"+Code)).create();
    }
}