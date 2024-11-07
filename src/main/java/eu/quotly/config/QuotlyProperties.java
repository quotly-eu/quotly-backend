package eu.quotly.config;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "quotly")
public interface QuotlyProperties {
  String version();
  Boolean emailEncryption();
}
