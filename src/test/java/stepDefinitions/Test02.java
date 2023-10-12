package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Test02 {

    static RequestSpecification spec;

    @Test
    public void test01(){
        //api/opdList endpoint'ine gecerli authorization bilgileri ile bir GET request gönderildiginde
        // dönen status code'un 200 oldugu
        // ve response message bilgisinin "Success" oldugu dogrulanmali

        // gecerli authorizaiton icin manuel olarak token aldik
        String token = "d0PpCcJHc3AKTCwc2eRbt4KTxrUX1Y";

        // https://www.heallifehospital.com
        spec = new RequestSpecBuilder().setBaseUri("http://www.heallifehospital.com").build(); // ana sayfa olusturuldu

        spec.pathParams("pp1", "api","pp2","opdList"); // parametreler olusturuldu

        String fullPath = "/{pp1}/{pp2}"; // parametre girisi icin kolay bir string olusturuldu


        Response response = given()
                .contentType(ContentType.JSON) // gonderilen veriler json formatinda
                .spec(spec) // olusturdugum ( spec isimli obje ) base url ve parametreleri kullanacagim
                .headers(
                        "Authorization","Bearer "+token, // gerekli authorization bilgisi giris satiri
                        "Content-Type", ContentType.JSON,
                        "Accept", ContentType.JSON
                )
                .when()
                // .body(reqBody.toString())
                .log().all()
                .get(fullPath);

        response.prettyPrint();
    }
}
