package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.interfaces.HasAPI;
import parse.scrupers.BaseScruper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TimePadScraperFromAPI extends BaseScruper {

    private int limit = 100;

    private String url = "https://api.timepad.ru/v1/events.json?limit=" + limit + "&category_ids=217" + "&category_ids=452";


    @Override
    public List<String> getData() throws IOException {
        return getReferences(getJsonString(url));
    }

    @Override
    public List<String> getReferences(String content) throws IOException {
        List<String> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("values");
        StringBuilder builder = new StringBuilder();

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                builder.setLength(0);
                builder.append(objNode.get("url").toString() + "\n");
               // builder.append(objNode.get("categories").get("name").toString() + "\n");
                builder.append(objNode.get("name").toString() + "\n");
                //builder.append(objNode.get("area").get("name").toString());
                response.add(builder.toString());
            }
        }

        return  response;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}


