package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.interfaces.HasAPI;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;
import util.StringsUtil;

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
    public List<ScruperEvent> getData() throws IOException {
        return getReferences(getJsonString(url));
    }

    @Override
    public List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("values");
        StringBuilder builder = new StringBuilder();

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                builder.setLength(0);
                event = new ScruperEvent();
                //builder.append(StringsUtil.deleteCommos(objNode.get("url").toString()) + "\n");
                //builder.append(objNode.get("categories").get("name").toString() + "\n");
                event.setUrl(StringsUtil.deleteCommos(objNode.get("url").toString()));
                //builder.append(StringsUtil.deleteCommos(objNode.get("name").toString()) + "\n");
                event.setName(StringsUtil.deleteCommos(objNode.get("name").toString()));
                //builder.append(objNode.get("area").get("name").toString());
                response.add(event);
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


