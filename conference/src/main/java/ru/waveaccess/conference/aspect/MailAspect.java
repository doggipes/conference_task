package ru.waveaccess.conference.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.waveaccess.conference.model.entity.VerificationToken;
import ru.waveaccess.conference.service.interfaces.MailService;

@Aspect
@Component
public class MailAspect {
    private final MailService mailService;

    public MailAspect(MailService mailService) {
        this.mailService = mailService;
    }

    @AfterReturning(pointcut = "execution(* ru.waveaccess.conference.service.impl.TokenServiceImpl.createToken(..))", returning = "verificationToken")
    public void sendEmail(VerificationToken verificationToken) {
        mailService.sendEmail(verificationToken.getUser().getEmail(), verificationToken.getToken());
    }
}
