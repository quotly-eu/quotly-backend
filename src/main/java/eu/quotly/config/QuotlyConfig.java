package eu.quotly.config;

import io.quarkus.runtime.annotations.StaticInitSafe;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@StaticInitSafe
@ApplicationScoped
public class QuotlyConfig {
  @Inject
  QuotlyProperties quotlyProperties;

  public String getVersion() {
    return quotlyProperties.version();
  }

  public Boolean isEmailEncryptionEnabled() {
    return quotlyProperties.emailEncryption();
  }
}
