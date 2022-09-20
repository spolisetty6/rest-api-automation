import booking.app.support.AssertHelper;
import booking.app.support.JsonHelper;
import booking.app.support.RestHelper;
import io.restassured.response.Response;
import org.junit.Test;

public class TestHotelBookingAPI {

    private static String USER_NAME = "admin";
    private static String PASSWORD = "password123";
    private RestHelper restHelper = new RestHelper();
    private JsonHelper jsonHelper = new JsonHelper();
    private AssertHelper assertHelper = new AssertHelper();
    private String requestBody;
    private String oAuthToken;
    private String bookingId;
    private Response response;

    /* Test #1: Covering user logging into the portal and creating a booking and updating the booking using the OAUTH token captured when logged in. */
    @Test
    public void createAndUpdateBookingTest() {
        requestBody = jsonHelper.loginRequestBody(USER_NAME, PASSWORD);
        oAuthToken = restHelper.getAuthToken(requestBody);
        requestBody = jsonHelper.getBookingRequestBody();
        bookingId = restHelper.createBooking(requestBody);
        requestBody = jsonHelper.updateBookingRequestBody(requestBody);
        response = restHelper.updateBooking(requestBody, bookingId, oAuthToken);
        assertHelper.assertStatusCode(200, response);

    }

    /* Test #2: Covering user logging into the portal and creating a booking and partially updating the booking using the OAUTH token captured when logged in. */
    @Test
    public void createAndPartialUpdateBookingTest() {
        requestBody = jsonHelper.loginRequestBody(USER_NAME, PASSWORD);
        oAuthToken = restHelper.getAuthToken(requestBody);
        requestBody = jsonHelper.getBookingRequestBody();
        bookingId = restHelper.createBooking(requestBody);
        requestBody = jsonHelper.partialUpdateRequestBody(requestBody);
        response = restHelper.partialUpdateBooking(requestBody, bookingId, oAuthToken);
        assertHelper.assertStatusCode(200, response);

    }
    /* Test #1: Covering user logging into the portal and creating a booking and deleting that booking using the OAUTH token captured when logged in. */
    @Test
    public void createAndDeleteBooking() {
        requestBody = jsonHelper.loginRequestBody(USER_NAME, PASSWORD);
        oAuthToken = restHelper.getAuthToken(requestBody);
        requestBody = jsonHelper.getBookingRequestBody();
        bookingId = restHelper.createBooking(requestBody);
        response = restHelper.deleteBooking(bookingId, oAuthToken);
        assertHelper.assertStatusCode(201, response);

    }
}
