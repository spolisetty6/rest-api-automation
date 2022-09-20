package booking.app.support;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AssertHelper {

    /* Compare the status code and raise an assertion error if the response code is not as expected*/

    public void assertStatusCode(int statusCode, Response response) {
        assertThat("Did not get successful response as expected", response.statusCode(), is(statusCode));
    }

    /* Compare the updated booking by looking at the updated JSON body and raise an assertion error if it is not match with expected change*/
    public void assertUpdateBooking(String updatedParameter, String updatedValue, Response response){
       assertThat("Booking not updated as expected", response.jsonPath().get(updatedParameter), is(updatedValue));

    }

}
