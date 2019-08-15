package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;
import util.StringsUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// не робит
public class UniversariumScruperStandart extends BaseScruper {

    private static String url = "https://universarium.org/mapi/fcourses";
    private static String buildURL = "https://universarium.org/course/";


    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("response");
        StringBuilder builder = new StringBuilder();
        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                //builder.setLength(0);
                event = new ScruperEvent();

                //builder.append(StringsUtil.deleteCommos(buildURL + objNode.get("id").toString()) + "\n");
                event.setUrl(StringsUtil.deleteCommos(buildURL + objNode.get("id").toString()));

                //builder.append(StringsUtil.deleteCommos(objNode.get("category").toString()) + "\n");
                event.setCategory(StringsUtil.deleteCommos(objNode.get("category").toString()));

                //builder.append("Курс: " + StringsUtil.deleteCommos(objNode.get("title").toString()) + "\n");
                event.setName("Курс: " + StringsUtil.deleteCommos(objNode.get("title").toString()));

                //builder.append((objNode.get("purpose").toString() + "\n"));
                response.add(event);
            }
        }

        return  response;
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences(getJsonString(url));
    }
}
