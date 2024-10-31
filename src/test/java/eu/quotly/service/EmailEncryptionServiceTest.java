package eu.quotly.service;

import eu.quotly.entity.UserEntity;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class EmailEncryptionServiceTest {
  private static final String DISCORD_ID = "1234567890";
  private static final String DECRYPTED_EMAIL_ADDRESS = "dummy@quotly.eu";
  private static final String ENCRYPTED_EMAIL_ADDRESS = "+jBjTFiwLzb5OxXIkO1mMg==";

  @Inject
  EmailEncryptionService emailEncryptionService;

  @Test
  void shouldGetEncryptedEmailAddress() throws Exception {
    String encryptedEmailAddress = emailEncryptionService.getEncryptedEmailAddress(getDummyUser(DECRYPTED_EMAIL_ADDRESS));
    assertNotNull(encryptedEmailAddress, "Encrypted email address should not be null");
    assertEquals(ENCRYPTED_EMAIL_ADDRESS, encryptedEmailAddress, "Encrypted email address should match");
  }

  @Test
  void shouldGetEncryptedEmailAddressAlreadyEncrypted() throws Exception {
    String encryptedEmailAddress = emailEncryptionService.getEncryptedEmailAddress(getDummyUser(ENCRYPTED_EMAIL_ADDRESS));
    assertNotNull(encryptedEmailAddress, "Encrypted email address should not be null");
    assertEquals(ENCRYPTED_EMAIL_ADDRESS, encryptedEmailAddress, "Encrypted email address should match");
  }

  @Test
  void shouldGetDecryptedEmailAddress() throws Exception {
    String decryptedEmailAddress = emailEncryptionService.getDecryptedEmailAddress(getDummyUser(ENCRYPTED_EMAIL_ADDRESS));
    assertNotNull(decryptedEmailAddress, "Decrypted email address should not be null");
    assertEquals(DECRYPTED_EMAIL_ADDRESS, decryptedEmailAddress, "Decrypted email address should match");
  }

  @Test
  void shouldGetDecryptedEmailAddressAlreadyDecrypted() throws Exception {
    String decryptedEmailAddress = emailEncryptionService.getDecryptedEmailAddress(getDummyUser(DECRYPTED_EMAIL_ADDRESS));
    assertNotNull(decryptedEmailAddress, "Decrypted email address should not be null");
    assertEquals(DECRYPTED_EMAIL_ADDRESS, decryptedEmailAddress, "Decrypted email address should match");
  }

  private UserEntity getDummyUser(String emailAddress) {
    UserEntity user = new UserEntity();
    user.setDiscordId(DISCORD_ID);
    user.setEmailAddress(emailAddress);
    return user;
  }
}