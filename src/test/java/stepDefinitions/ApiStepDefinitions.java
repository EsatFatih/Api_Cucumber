package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.HooksApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static utilities.HooksApi.token;

public class ApiStepDefinitions {

    String fullpath;
    static RequestSpecification spec;
    Response response;

    @Given("Admin path'deki parametrleri kurar {string}.")
    public void admin_path_deki_parametrleri_kurar(String rawPaths) {
        String [] paths = rawPaths.split("/");

        StringBuilder tempPath = new StringBuilder("/{");

        for (int i = 0; i < paths.length; i++) {
            String key = "pp"+i+1;
            String value = paths[i].trim();
            HooksApi.spec.pathParam(key,value);

            tempPath.append(key+"}/{");
        }

        tempPath.deleteCharAt(tempPath.lastIndexOf("{"));
        tempPath.deleteCharAt(tempPath.lastIndexOf("/"));

        fullpath = tempPath.toString();

    }
    @Then("Admin pickup listesi icin GET request gonderir")
    public void admin_pickup_listesi_icin_get_request_gonderir() {
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
                .get(fullpath);

    }
    @Then("Donen status code'un {int} oldugu ve response message bilgisinin Success oldugu dogrulanmali")
    public void donen_status_code_un_oldugu_ve_response_message_bilgisinin_Success_oldugu_dogrulanmali(Integer int1) {
        System.out.println(response.prettyPrint());
        /*response
                .then()
                .assertThat()
                .statusCode(int1)
                .body("message", equalTo("Success"),
                        "id",equalTo("1"))
        ;

         */

    }

    @Then("Lists icerigi \\(id'si = {int}, olan veri icerigindeki title: Cargo Security and Risk Management Crucial Aspects in Transportation, image_id: {int}) oldugu dogrulanmali.")
    public void lists_icerigi_id_si_olan_veri_icerigindeki_title_cargo_security_and_risk_management_crucial_aspects_in_transportation_image_id_oldugu_dogrulanmali(Integer int1, Integer imageID) {
        response
                .then()
                .assertThat()
                .statusCode(int1)
                .body("[0].title", equalTo("Cargo Security and Risk Management Crucial Aspects in Transportation"),
                        "[0].image_id",equalTo(imageID))
        ;

    }

}
