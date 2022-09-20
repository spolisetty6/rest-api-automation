import booking.app.support.AssertHelper;
import booking.app.support.JsonHelper;
import booking.app.support.RestHelper;
import io.restassured.response.Response;
import org.junit.Test;

public class TestHotelBookingAPi {


    private static String USER_NAME = "admin";
   private static String PASSWORD = "password123";


    private RestHelper restHelper = new RestHelper();
    private JsonHelper jsonHelper = new JsonHelper();
    private AssertHelper assertHelper = new AssertHelper();
    private String requestBody;
    private String oAuthToken;
    private String bookingId;
    private Response response;

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
