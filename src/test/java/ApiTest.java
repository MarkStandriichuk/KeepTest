import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class ApiTest {

    @Test
    public void login_test() {
        given().accept(ContentType.XML)
                .auth().basic("automation@keepitqa.com", "E#*b2wGIbFHz")
                .when().get("https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm")
                .then().assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void request_body_test() {
        given().accept(ContentType.XML)
                .auth().basic("automation@keepitqa.com", "E#*b2wGIbFHz")
                .when()
                .get("https://ws-test.keepit.com/users/zhc4v6-5ev7di-9hhhlm")
                .then()
                .assertThat().body("user",
                        hasItems("true", "2019-02-28T13:07:49Z", "7dwqnq-5cvrcm-1z3ehj", "80ltks-yhfls5-24zyf2", "true"));
    }
}
