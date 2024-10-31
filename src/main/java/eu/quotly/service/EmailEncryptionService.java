package eu.quotly.service;

import eu.quotly.config.QuotlyConfig;
import eu.quotly.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@ApplicationScoped
public class EmailEncryptionService {
  private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
  private static final int KEY_SIZE = 256;

  public String getEncryptedEmailAddress(UserEntity user) throws Exception {
    if (isEmailEncrypted(user)) {
      return user.getEmailAddress();
    }

    String emailAddress = user.getEmailAddress();
    String discordId = user.getDiscordId();
    SecretKey secretKey = getSecretKeyFromDiscordId(discordId);
    IvParameterSpec initializationVectorSpec = getFixedInitializationVectorFromDiscordId(discordId);

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, initializationVectorSpec);

    byte[] encryptedEmailAddress = cipher.doFinal(emailAddress.getBytes(StandardCharsets.UTF_8));
    return Base64.getEncoder().encodeToString(encryptedEmailAddress);
  }

  public String getDecryptedEmailAddress(UserEntity user) throws Exception {
    if (!isEmailEncrypted(user)) {
      return user.getEmailAddress();
    }

    String encryptedEmailAddress = user.getEmailAddress();
    String discordId = user.getDiscordId();
    SecretKey secretKey = getSecretKeyFromDiscordId(discordId);
    IvParameterSpec initializationVectorSpec = getFixedInitializationVectorFromDiscordId(discordId);

    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, initializationVectorSpec);

    byte[] encryptedEmail = Base64.getDecoder().decode(encryptedEmailAddress);
    byte[] decryptedEmail = cipher.doFinal(encryptedEmail);
    return new String(decryptedEmail, StandardCharsets.UTF_8);
  }

  private Boolean isEmailEncrypted(UserEntity user) {
    return user.getEmailAddress().matches("^[A-Za-z0-9+/]+={0,2}$");
  }

  private SecretKey getSecretKeyFromDiscordId(String discordId) throws NoSuchAlgorithmException {
    MessageDigest sha = MessageDigest.getInstance("SHA-256");
    byte[] key = sha.digest(discordId.getBytes(StandardCharsets.UTF_8));
    return new SecretKeySpec(key, 0, KEY_SIZE / 8, "AES");
  }

  private IvParameterSpec getFixedInitializationVectorFromDiscordId(String discordId) throws NoSuchAlgorithmException {
    MessageDigest sha = MessageDigest.getInstance("MD5");
    byte[] initializationVector = sha.digest(discordId.getBytes(StandardCharsets.UTF_8));
    return new IvParameterSpec(initializationVector);
  }
}
