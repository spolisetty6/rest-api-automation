package booking.app.support;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RestHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestHelper.class);
    private static String BASE_URL = "https://restful-booker.herokuapp.com";
    Response response;

    /* Method to login to the portal using valid credentials and extract the token from the response received */
    public String getAuthToken(String requestBody) {
        LOGGER.info("log in to get auth token");
        response =  given().contentType("application/x-www-form-urlencoded")
                .header("Content-Type", "application/json")
                .when()
                .body(requestBody)
                .post(BASE_URL + "/auth")
                .then()
                .extract()
                .response();
        assertThat("login failed",response.statusCode(), is( 200));
        LOGGER.info("Login successful");
       return response.jsonPath().get("token");
    }

    /* Method to create the booking */
    public String createBooking(String requestBody) {
        response = given().contentType(ContentType.JSON)
                .when()
                .body(requestBody)
                .post(BASE_URL + "/booking")
                .then()
                .extract()
                .response();
        assertThat(response.getStatusCode(), is( 200));

        return response.jsonPath().get("bookingid").toString();
    }

    /* Method to update existing booking using the OAUTH token received when logged in to the portal*/
    public Response updateBooking(String requestBody, String bookingId, String oAuth_TOKEN) {
        response = given().contentType(ContentType.JSON)
                .header("Cookie",  "token=" + oAuth_TOKEN)
                .when()
                .pathParam("bookingId", bookingId)
                .body(requestBody)
                .put(BASE_URL + "/booking/{bookingId}");
        assertThat(response.getStatusCode(), is( 200));
        return response;

    }

    /* Method to partially update existing booking using the OAUTH token received when logged in to the portal*/
    public Response partialUpdateBooking(String requestBody, String bookingId, String oAuth_TOKEN) {

        response = given().contentType(ContentType.JSON)
                .header("Cookie", "token=" + oAuth_TOKEN)
                .when()
                .pathParam("bookingId", bookingId)
                .body(requestBody)
                .patch(BASE_URL + "/booking/{bookingId}");
        assertThat(response.getStatusCode(), is( 200));
        return response;
    }

    /* Method to delete existing booking using the OAUTH token received when logged in to the portal*/
    public Response deleteBooking(String bookingId, String oAuth_TOKEN) {
        response = given().contentType(ContentType.JSON)
                .header("Cookie", "token=" + oAuth_TOKEN)
                .when()
                .pathParam("bookingId", bookingId)
                .delete(BASE_URL + "/booking/{bookingId}");
        assertThat(response.getStatusCode(), is( 201));
        return response;

    }

}
