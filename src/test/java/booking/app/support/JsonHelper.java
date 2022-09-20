package booking.app.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.Json;
import java.time.LocalDate;
import java.util.Random;

public class JsonHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonHelper.class);
        public String loginRequestBody(String username, String password){
            return Json.createObjectBuilder()
                    .add("username", username )
                    .add("password", password )
                    .build().toString();
        }

    public String getBookingRequestBody() {
            LOGGER.info("Generating Booking request body");
        return Json.createObjectBuilder()
                .add("firstname","firstname" + getRandomName())
                .add("lastname", "lastName" + getRandomName())
                .add("totalprice", 111)
                .add("depositpaid", true)
                .add("bookingdates", Json.createObjectBuilder()
                        .add("checkin",getCheckinDate())
                      .add("checkout", getCheckoutDate())
                )
                .add("additionalneeds", "Breakfast")
                .build().toString();

    }

    public String updateBookingRequestBody(String requestBody){
        return requestBody.replace("Breakfast", "Dinner");

    }

    public String partialUpdateRequestBody(String requestBody){
        return  requestBody.replace("Breakfast", "Lunch");
    }

    /* Generate a random text string. This string will then be prefixed with either "FirstName" or "Lastname" according to the method call */
    private String getRandomName(){
       Random random =  new Random();

        return random.ints(97, 122 + 1)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    /* Generate a date that is 3 days from now (i.e. Current date + 3 days) */
    private String getCheckinDate(){
        LocalDate date = LocalDate.now();
       return date.plusDays(3).toString();

    }
    /* Generate a date that is 8 days from now (i.e. Current date + 8 days). As a result the booking duration will be 5 days. */
    private String getCheckoutDate(){
        LocalDate date = LocalDate.now();
        return date.plusDays(8).toString();

    }

}
