package com.appspot.lessor100.df;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appspot.lessor100.email.EmailSender;

@Service
public class ThresholdNotificationServiceImpl implements ThresholdNotificationService {

    @Autowired
    private EmailSender emailSender;

    @Override
    public void newMountsReceived(Server server) {
        MountGroup latestMountGroup = server.getLatestMountGroup();
        for (Mount mount : latestMountGroup.getMountListRef().getModelList()) {
            if (server.isEqualOrAboveThreshold(mount)) {
                String content = String.format("%s is running full on %s", mount.getMountedOn(), server.getName());
                emailSender.sendEmail("cmvagner@gmail.com", content, content);
            }
        }
    }
}
