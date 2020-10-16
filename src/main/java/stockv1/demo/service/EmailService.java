package stockv1.demo.service;

import org.springframework.stereotype.Service;
import stockv1.demo.repository.EncryptedPasswordRepository;
import stockv1.demo.utils.AesUtils;
import stockv1.demo.utils.SendMailUtils;

/**
 * @author Marius Pop
 */
@Service
public class EmailService {

    private final EncryptedPasswordRepository encryptedPasswordRepository;

    public EmailService(EncryptedPasswordRepository encryptedPasswordRepository) {
        this.encryptedPasswordRepository = encryptedPasswordRepository;
    }

    public void send(String to, String subject, String content) {
        SendMailUtils.sendEmail(to, subject, content , AesUtils.decrypt(encryptedPasswordRepository.findAll().get(0).getPassword(), encryptedPasswordRepository.findAll().get(0).getSecret()));
    }
}
