package eu.quotly;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.quarkus.runtime.configuration.ConfigUtils;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusMain
public class Quotly {
  private static final Logger LOGGER = LoggerFactory.getLogger(Quotly.class);

  public static void main(final String[] args) {
    String version = ConfigProvider.getConfig().getValue("quotly.version", String.class);
    LOGGER.info("Starting Quotly with { version={}, profiles={} }", version, ConfigUtils.getProfiles());
    Quarkus.run(args);
  }
}
