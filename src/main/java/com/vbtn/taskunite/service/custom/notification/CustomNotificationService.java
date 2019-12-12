package com.vbtn.taskunite.service.custom.notification;

import com.vbtn.taskunite.domain.Notification;
import com.vbtn.taskunite.repository.NotificationRepository;
import com.vbtn.taskunite.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomNotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    MailService mailService;

    public Notification save(Notification n) {
        mailService.sendEmailFromTemplate(n.getUser().getUser(), "mail/notifyEmail", "email.task.notify");
        notificationRepository.save(n);
        return n;
    }
}
