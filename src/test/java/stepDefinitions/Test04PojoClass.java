package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import pojos.HeroPojo;
import pojos.JsonPojo;

import static io.restassured.RestAssured.given;

public class Test04PojoClass {

    static RequestSpecification spec;

    @Test
    public void test01(){

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
            ve “checkin” tarihinin 2021-06-01 ve “checkout” tarihinin 2021-06-10 ve “additionalneeds”in,
            “wi-fi” olduğunu test edin (edited)

     */

        spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build(); // anasayfa olusturuldu

        spec.pathParams("pp1", "booking"); // parametreler olusturuldu

        String fullPath = "/{pp1}";// parametre girisi icin kolay bir Striing olusturuldu

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

        HeroPojo responseHeroPojo = response.as(HeroPojo.class);
        System.out.println(responseHeroPojo);

        response.prettyPrint();

        /*
        donen Response’un,
            status code’unun 200,
            ve content type’inin application-json, ve response body’sindeki
            “firstname”in,“Ahmet”, ve
            “lastname”in, “Bulut”,
            ve “totalprice”in,500,
            ve “depositpaid”in,false, ve
                            “checkin” tarihinin 2021-06-01 ve
                            “checkout” tarihinin 2021-06-10 ve
            “additionalneeds”in,
            “wi-fi” olduğunu test edin (edited)

         */

        Assert.assertEquals("Ahmet",responseHeroPojo.getBooking().getFirstname());
        Assert.assertEquals("Bulut",responseHeroPojo.getBooking().getLastname());
        Assert.assertEquals(500,responseHeroPojo.getBooking().getTotalprice());
        Assert.assertEquals(false,responseHeroPojo.getBooking().isDepositpaid());
        Assert.assertEquals("2021-06-01",responseHeroPojo.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals("2021-06-10",responseHeroPojo.getBooking().getBookingdates().getCheckout());
        Assert.assertEquals("wi-fi",responseHeroPojo.getBooking().getAdditionalneeds());


         // response ı https://www.jsonschema2pojo.org/ sitesinden olustırdugumuz pojo class'lar ile test etme

        JsonPojo responseJsonObject = response.as(JsonPojo.class);

        Assert.assertEquals("Ahmet",responseJsonObject.getBooking().getFirstname());
        Assert.assertEquals("Bulut",responseJsonObject.getBooking().getLastname());
        // Assert.assertEquals(500,responseJsonObject.getBooking().getTotalprice()); devamını yap

    }
}
