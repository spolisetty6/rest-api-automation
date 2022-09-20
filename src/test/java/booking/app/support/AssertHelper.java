package booking.app.support;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AssertHelper {


    public void assertStatusCode(int statusCode, Response response) {
        assertThat("Did not get successful response as expected", response.statusCode(), is(statusCode));
    }

    public void assertUpdateBooking(String updatedParameter, String updatedValue, Response response){
       assertThat("Booking not updated as expected", response.jsonPath().get(updatedParameter), is(updatedValue));

    }

}
