package utilities;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Ignore;
import org.junit.Test;

public class HooksApi {

    public static RequestSpecification spec;
    public static String token;



    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("base_url").build();
    }


    @Before
    public void beforeGenerateToken(){
        token = Authentication.generateToken();
    }








}
