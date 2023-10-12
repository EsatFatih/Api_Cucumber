package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class HealLifeHospitalStepDefinitions {

    String token;
    static RequestSpecification spec;
    public static String fullPath;
    Response response;


    @Given("Api kullanicisi sisteme admin olarak giris yapar")
    public void api_kullanicisi_sisteme_admin_olarak_giris_yapar() {
        token = "bud5ZIH3syBnugqXvgGOTCg5h0pwnA";
    }
    @Given("Api kullanicisi url ile birlikte iki parametreli giris yapar parametreler {string} ve {string}")
    public void api_kullanicisi_url_ile_birlikte_iki_parametreli_giris_yapar_parametreler_ve(String pp1, String pp2) {

        spec = new RequestSpecBuilder().setBaseUri("http://www.heallifehospital.com").build(); // ana sayfa olusturuldu

        spec.pathParams("pp1", pp1,"pp2",pp2); // parametreler olusturuldu

        fullPath = "/{pp1}/{pp2}"; // parametre girisi icin kolay bir string olusturuldu
    }
    @When("Api kullanicisi body siz getRequest yapar")
    public void api_kullanicisi_body_siz_get_request_yapar() {
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
    }
    @Then("Api kullanicisi istenen bilgileri test eder")
    public void api_kullanicisi_istenen_bilgileri_test_eder() {
        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Success"));
    }

}
