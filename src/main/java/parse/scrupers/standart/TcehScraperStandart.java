package parse.scrupers.standart;

import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.List;
// пока выдает только ссылки, причем некоторые дублирует
public class TcehScraperStandart extends BaseScruper {
    private static String url = "https://tceh.com/events/";

    @Override
    protected List<String> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences("events-card.align-items-stretch");
    }
}
