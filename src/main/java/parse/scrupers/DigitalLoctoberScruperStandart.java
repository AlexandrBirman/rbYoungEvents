package parse.scrupers;

import java.io.IOException;
import java.util.List;
//только ссылки
public class DigitalLoctoberScruperStandart extends BaseScruper {
    private String url = "https://digitaloctober.com/";

    @Override
    List<String> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences("events-list__item");
    }
}
