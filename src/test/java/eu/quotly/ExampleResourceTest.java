package eu.quotly;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {
  private static final int STATUS_CODE = 200;

  @Test
  void testHelloEndpoint() {
    given()
      .when().get("/hello")
      .then()
      .statusCode(STATUS_CODE)
      .body(is("Hello from Quarkus REST"));
  }
}
