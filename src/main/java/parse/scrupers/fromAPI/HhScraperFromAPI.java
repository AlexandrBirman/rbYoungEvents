package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.helper.StringUtil;
import parse.scrupers.BaseScruper;
import util.StringsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HhScraperFromAPI extends BaseScruper {

    private static String url = "https://api.hh.ru/vacancies?employment=probation";

    @Override
    public List<String> getData() throws IOException {
        return getReferences(getJsonString(url));
    }

    @Override
    public List<String> getReferences(String content) throws IOException {
        List<String> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("items");
        StringBuilder builder = new StringBuilder();

        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                builder.setLength(0);
                builder.append(StringsUtil.deleteCommos(objNode.get("alternate_url").toString()) + "\n");
                builder.append(StringsUtil.deleteCommos(objNode.get("name").toString()) + "\n");
                builder.append(StringsUtil.deleteCommos(objNode.get("area").get("name").toString()));
                response.add(builder.toString());
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