package stepDefinitions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class Test03 {
    static RequestSpecification spec;
    @Test
    public void test01(){
        String token = "NdlcHRRr6Mph52nC1tqDuX6VPaNObA";

        // http://www.heallifehospital.com/api/ipdList

        spec = new RequestSpecBuilder().setBaseUri("http://www.heallifehospital.com").build(); // anasayfa olusturuldu

        spec.pathParams("pp1","api","pp2","ipdList"); // parametreler olusturuldu

        String fullPath = "/{pp1}/{pp2}";// parametre girisi icin kolay bir Striing olusturuldu

        Response response = given()
                .contentType(ContentType.JSON) // verimi JSON formatta gonderiyorum
                .spec(spec) // (olusturdugum spec isimli obje) base url ve parametreleri kullanacagiz
                .headers(
                        "Authorization","Bearer "+token,
                        "Content-Type", ContentType.JSON, // cevabi JSON olarak almak istiyorum dedik
                        "Accept", ContentType.JSON
                )
                .when()
                //.body(reqbody.toString()) //request icinde body varsa
                .log().all()  // olusturdugumuz request i toplu halde gormek icin
                .get(fullPath); // parametrelerle beraber request type girisi

        System.out.println("********************************Response**************************************");
        // response.prettyPrint();

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("message", equalTo("Success"));

    }

}
