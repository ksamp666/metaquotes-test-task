package RestServices;

import Configuration.ConfigurationProperties;
import Models.LstTo.DataModel;
import Models.LstTo.LinkModel;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.Map;

import static Utils.JsonUtils.getJsonStringFromObject;
import static Utils.Log.getLogger;
import static io.restassured.RestAssured.given;

public class LstToApiService {
    private final static String BASE_URL = ConfigurationProperties.getProperties().getLstToBaseUrl();
    private final static String USER_AGENT = ConfigurationProperties.getProperties().getUserAgent();
    private final static String AUTH_TOKEN = "c6ef9a1b26a937e374667e08";

    private Map<String, String> headers;

    public LstToApiService() {
        headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("User-Agent", USER_AGENT);
        headers.put("X-AUTH-TOKEN", AUTH_TOKEN);
    }

    private DataModel generateRequestObjectForLink(String link) {
        LinkModel linkObject = new LinkModel();
        linkObject.setType("link");
        linkObject.setUrl(link);
        DataModel data = new DataModel();
        data.setData(linkObject);
        return data;
    }

    @Step("Get shorten link from LST TO API")
    public DataModel getShortenLink(String link) {
        getLogger().info("Getting shorten link for link: " + link);
        return given()
                .headers(headers)
                .body(getJsonStringFromObject(generateRequestObjectForLink(link)))
                .when().post(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .as(DataModel.class);
    }

    private String getLinkIdFromLink(String link) {
        return link.substring(link.lastIndexOf('/') + 1);
    }

    @Step("Delete shorten link from LST TO API")
    public void deleteLink(String link) {
        getLogger().info("Deleting shorten link from API: " + link);
        given()
                .headers(headers)
                .when().delete(BASE_URL + "/" + getLinkIdFromLink(link))
                .then()
                .statusCode(200);
    }
}
