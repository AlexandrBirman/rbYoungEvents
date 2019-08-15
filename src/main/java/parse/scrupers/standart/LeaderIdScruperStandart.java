package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// трижды дублирует ссылки
public class LeaderIdScruperStandart extends BaseScruper {
    private static String url = "https://leader-id.ru/events/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException { // пока только одна страница парситься
        List<ScruperEvent> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.col-lg-4.col-md-6.grid__item");
        //Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
            event = new ScruperEvent();
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
