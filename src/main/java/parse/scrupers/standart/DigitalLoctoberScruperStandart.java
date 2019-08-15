package parse.scrupers.standart;

import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.List;
//только ссылки
public class DigitalLoctoberScruperStandart extends BaseScruper {
    private String url = "https://digitaloctober.com/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("events-list__item");
    }
}
