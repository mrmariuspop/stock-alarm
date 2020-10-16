package stockv1.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stockv1.demo.service.EmailService;

/**
 * @author Marius Pop
 */
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ApiOperation("send email api")
    @PostMapping("/send-email")
    public String sendEmail(@RequestParam(name = "to") String to,
                          @RequestParam(name = "subject") String subject,
                          @RequestParam(name = "emailBody") String content) {
        emailService.send(to, subject, content);
        return "Email sent";
    }
}
