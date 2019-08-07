package parse.scrupers.standart;

import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.List;
// только ссылки
public class RunetIdScruperStandart extends BaseScruper {
    private static String url = "https://runet-id.com/events/";

    @Override
    protected List<String> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences("unit.span4.event ");
    }
}