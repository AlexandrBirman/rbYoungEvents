package parse.scrupers.standart;

import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.List;
// не работает
public class GrantistScruperStandart extends BaseScruper {
    private static String url = "http://grantist.com/page/126/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("content-area");
    }
}
