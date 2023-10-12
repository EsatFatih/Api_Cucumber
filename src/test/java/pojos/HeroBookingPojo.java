package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HeroBookingPojo {
    /*
        "firstname": "Ahmet",
        "lastname": "Bulut",
        "totalprice": 500,
        "depositpaid": false,
        "bookingdates": {
                    "checkin": "2021-06-01",
                    "checkout": "2021-06-10"
                        },
        "additionalneeds": "wi-fi"
                }
     */

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private HeroBookingDatesPojo bookingdates;
    private String additionalneeds;
}
