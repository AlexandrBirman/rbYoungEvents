package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SkScruperStandart extends BaseScruper {
    private static String url = "http://sk.ru/events/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("ul.attribute-list");
        Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //builder.setLength(0);
            event = new ScruperEvent();

            //System.out.println((links.get(i).attr("abs:href")));
            builder.append(elements.get(i).select("a[href]").attr("abs:href") + "\n" + elements.get(i).text());
            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

            data.add(event);
        }
        return data;
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences(null);
    }
}
