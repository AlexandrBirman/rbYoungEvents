package parse.scrupers;

import java.io.IOException;
import java.util.List;

public class HackatonIoScruperStandart extends BaseScruper {
    private static String url = "https://www.hackathon.io/events";

    @Override
    List<String> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences("event-teaser");
    }
}
