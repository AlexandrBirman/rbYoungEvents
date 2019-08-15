package parse.scrupers.standart;

import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.List;

public class HackatonIoScruperStandart extends BaseScruper {
    private static String url = "https://www.hackathon.io/events";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("event-teaser");
    }
}
