package pojos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class HeroPojo {
    /*
    {
    "bookingid": 4979,
    "booking": {
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
    }

        Not: Pojo class olustururken amaç genel cevabi(response) kaydedebilecegimiz bir kalip olusturmaktir
     */

    private int bookingid;
    private HeroBookingPojo booking;


}
