package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class Authentication {

    private static RequestSpecification spec;

    public static String generateToken() {
        spec = new RequestSpecBuilder().setBaseUri("base_url").build();

        spec.pathParams("pp1", "api", "pp2", "gettoken");

        JSONObject reqBody = new JSONObject();

        reqBody.put("email", ConfigReader.getProperty("adminEmail"));
        reqBody.put("password", ConfigReader.getProperty("adminPassword"));

        Response response = given()
                .contentType(ContentType.JSON) // gonderilen veriler json formatinda
                .spec(spec) // olusturdugum ( spec isimli obje ) base url ve parametreleri kullanacagim
               .headers("Content-Type", ContentType.JSON,
                       "Accept", ContentType.JSON
                )
                .when()
                .body(reqBody.toString())
                .log().all()
                .post("/{pp1}/{pp2}");

        JsonPath resJP = response.jsonPath();

        String token = resJP.get("token");

        return token;
    }
}
