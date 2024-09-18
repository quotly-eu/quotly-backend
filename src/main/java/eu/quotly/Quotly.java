package eu.quotly;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusMain
public class Quotly {
  private static final Logger logger = LoggerFactory.getLogger(Quotly.class);

  public static void main(String[] args) {
    logger.info("Starting Quotly...");
    Quarkus.run(args);
  }
}
