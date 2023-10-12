package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static org.hamcrest.CoreMatchers.*;


public class Test01 {

    static RequestSpecification spec;

    public static void main(String[] args) {
            /*

    https://restful-booker.herokuapp.com/booking url’ine asagidaki body’ye sahip bir POST
    request gonderdigimizde
    {
      “firstname” : “Ahmet”,
      “lastname” : “Bulut”,
      “totalprice” : 500,
      “depositpaid” : false,
      “bookingdates” : {
                        “checkin” : “2021-06-01”,
                        “checkout” : “2021-06-10”
                        },
       “additionalneeds” : “wi-fi”
    }
            donen Response’un,
            status code’unun 200,
            ve content type’inin application-json, ve response body’sindeki
            “firstname”in,“Ahmet”, ve “lastname”in, “Bulut”,
            ve “totalprice”in,500,
            ve “depositpaid”in,false,
            ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,“wi-fi” olduğunu test edin (edited)

     */

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com/booking").build(); // ana sayfa olusturuldu

        spec.pathParams("pp1", "booking"); // parametreler olusturuldu

        String fullPath = "/{pp1}"; // parametre girisi icin kolay bir string olusturuldu

        JSONObject innerBody = new JSONObject(); // kucuk objemiz
        innerBody.put("checkin","2021-06-01");
        innerBody.put("checkout","2021-06-10");

        JSONObject reqBody = new JSONObject(); // ana request objemiz
        reqBody.put("firstname","Ahmet");
        reqBody.put("lastname","Bulut");
        reqBody.put("totalprice",500);
        reqBody.put("depositpaid",false);
        reqBody.put("bookingdates",innerBody);
        reqBody.put("additionalneeds","wi-fi");

        Response response = given()
                .contentType(ContentType.JSON) // gonderilen veriler json formatinda
                .spec(spec) // olusturdugum ( spec isimli obje ) base url ve parametreleri kullanacagim
                .headers("Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON
                )
                .when()
                .body(reqBody.toString())
                .log().all()
                .post(fullPath);

        response.prettyPrint();
        /*
        donen Response’un,
            status code’unun 200,
            ve content type’inin application-json, ve response body’sindeki
            “firstname”in,“Ahmet”, ve
            “lastname”in, “Bulut”,
            ve “totalprice”in,500,
            ve “depositpaid”in,false,
            ve “checkin” tarihinin 2021-06-01 ve
            “checkout” tarihinin 2021-06-10 ve
            “additionalneeds”in,“wi-fi” olduğunu test edin (edited)

         */
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname", equalTo("Ahmet"))
                .body("booking.lastname", equalTo("Bulut"))
                .body("booking.totalprice", equalTo(500))
                .body("booking.depositpaid", equalTo(false))
                .body("booking.bookingdates.checkin", equalTo("2021-06-01"))
                .body("booking.bookingdates.checkout", equalTo("2021-06-10"))
                .body("booking.additionalneeds", equalTo("wi-fi"));


        response.prettyPrint();
    }
}
