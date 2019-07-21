package parse.scrupers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.interfaces.HasAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TimePadScraperFromAPI extends BaseScruper  {

    private String url = "https://api.timepad.ru/v1/events.json?limit=100";


    @Override
    public List<String> getData() throws IOException {
        return getReferences(this.getJsonString(url));
    }

    @Override
    public List<String> getReferences(String content) throws IOException {
        List<String> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("values");

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                response.add(objNode.get("url").toString());
            }
        }

        return  response;
    }

}


