package eu.quotly.entity.listener;

import eu.quotly.config.QuotlyConfig;
import eu.quotly.entity.UserEntity;
import eu.quotly.service.EmailEncryptionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class UserEntityListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityListener.class);

  @Inject
  EmailEncryptionService emailEncryptionService;

  @Inject
  QuotlyConfig quotlyConfig;

  @PrePersist
  @PreUpdate
  public void encryptSensitiveData(UserEntity user) {
    if (quotlyConfig.isEmailEncryptionEnabled()) {
      String emailAddress = user.getEmailAddress();
      try {
        user.setEmailAddress(emailEncryptionService.getEncryptedEmailAddress(user));
      } catch (Exception exception) {
        LOGGER.error("Unable to encrypt email address from user {}", user.getDisplayName(), exception);
        user.setEmailAddress(emailAddress);
      }
    }
  }

  @PostLoad
  public void decryptSensitiveData(UserEntity user) {
    String emailAddress = user.getEmailAddress();
    try {
      user.setEmailAddress(emailEncryptionService.getDecryptedEmailAddress(user));
    } catch (Exception exception) {
      LOGGER.error("Unable to decrypt email address from user {}", user.getDisplayName(), exception);
      user.setEmailAddress(emailAddress);
    }
  }
}
