package model;

import configuration.ConfigsProperties;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class RestClient {

    private static final ConfigsProperties configuration = ConfigFactory.create(ConfigsProperties.class);
    //private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    public RequestSpecBuilder REQUEST_SPECIFICATION_BUILDER =
            new RequestSpecBuilder()
                    .addHeader("Authorization", "Bearer " + configuration.token())
                    .setBaseUri(configuration.baseUri())
                    .setAccept(ContentType.JSON)
                    .setContentType(ContentType.JSON)
                    .addFilter(new AllureRestAssured())
                    .setRelaxedHTTPSValidation();
                    //.addFilters(asList(new ResponseLoggingFilter(), new RequestLoggingFilter()));

    public RestClient withUri(String path) {
        REQUEST_SPECIFICATION_BUILDER = REQUEST_SPECIFICATION_BUILDER
                .setBasePath(path);
        return this;
    }

    public RequestSpecification build() {
        return REQUEST_SPECIFICATION_BUILDER.build();

    }
}
