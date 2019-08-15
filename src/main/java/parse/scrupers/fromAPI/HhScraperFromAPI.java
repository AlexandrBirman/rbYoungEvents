package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.helper.StringUtil;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;
import util.StringsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HhScraperFromAPI extends BaseScruper {

    private static String url = "https://api.hh.ru/vacancies?employment=probation";

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences(getJsonString(url));
    }

    @Override
    public List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("items");
        StringBuilder builder = new StringBuilder();

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                //builder.setLength(0);
                event = new ScruperEvent();

                //builder.append(StringsUtil.deleteCommos(objNode.get("alternate_url").toString()) + "\n");
                event.setUrl(StringsUtil.deleteCommos(objNode.get("alternate_url").toString()));

                //builder.append(StringsUtil.deleteCommos(objNode.get("name").toString()) + "\n");
                event.setName(StringsUtil.deleteCommos(objNode.get("name").toString()));

                //builder.append(StringsUtil.deleteCommos(objNode.get("area").get("name").toString()));
                event.setLocation(StringsUtil.deleteCommos(objNode.get("area").get("name").toString()));

                response.add(event);
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