package parse.scrupers;

import java.io.IOException;
import java.util.List;
// не работает
public class GrantistScruperStandart extends BaseScruper{
    private static String url = "http://grantist.com/page/126/";

    @Override
    List<String> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences("content-area");
    }
}
