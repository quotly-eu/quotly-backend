package eu.quotly.config;

import org.eclipse.microprofile.config.inject.ConfigProperties;

@ConfigProperties(prefix = "quotly")
public class QuotlyProperties {
  public String basePath;
}
