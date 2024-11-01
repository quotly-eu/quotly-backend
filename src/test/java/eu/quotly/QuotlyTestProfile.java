package eu.quotly;

import io.quarkus.test.junit.QuarkusTestProfile;

public class QuotlyTestProfile implements QuarkusTestProfile {
  @Override
  public String getConfigProfile() {
    return "test";
  }
}
