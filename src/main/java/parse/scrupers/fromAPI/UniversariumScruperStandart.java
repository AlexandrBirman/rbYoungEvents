package parse.scrupers.fromAPI;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// не робит
public class UniversariumScruperStandart extends BaseScruper {

    private static String url = "https://universarium.org/mapi/fcourses";
    private static String buildURL = "https://universarium.org/course/";


    @Override
    protected List<String> getReferences(String content) throws IOException {
        List<String> response = new ArrayList<>();

        JsonNode arrNode = new ObjectMapper().readTree(content).get("response");
        StringBuilder builder = new StringBuilder();
        if (arrNode.isArray()) {
            for (final JsonNode objNode : arrNode) {
                builder.setLength(0);
                builder.append(buildURL + objNode.get("id").toString() + "\n");
                builder.append((objNode.get("category").toString() + "\n"));
                builder.append("Курс: " + (objNode.get("title").toString() + "\n"));

                //builder.append((objNode.get("purpose").toString() + "\n"));
                response.add(builder.toString());
            }
        }

        return  response;
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences(getJsonString(url));
    }
}
