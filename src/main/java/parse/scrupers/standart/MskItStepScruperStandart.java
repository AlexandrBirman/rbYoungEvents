package parse.scrupers.standart;

import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.List;
// выгружает меньше ссылок чме надо
public class MskItStepScruperStandart extends BaseScruper {
    private static String url = "https://msk.itstep.org/events";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        return getFromHTML(url, content);
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("events-preview__post.preview-post");
    }
}
