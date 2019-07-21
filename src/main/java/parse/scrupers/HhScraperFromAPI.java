package parse.scrupers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.interfaces.HasAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HhScraperFromAPI extends BaseScruper {

    private static String url = "https://api.hh.ru/vacancies?employment=probation";

    @Override
    public List<String> getData() throws IOException {
        return getReferences(this.getJsonString(url));
    }

    @Override
    public List<String> getReferences(String content) throws IOException {
        List<String> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("items");

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                response.add(objNode.get("alternate_url").toString());
            }
        }

        return  response;
    }



    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        HhScraperFromAPI.url = url;
    }
}