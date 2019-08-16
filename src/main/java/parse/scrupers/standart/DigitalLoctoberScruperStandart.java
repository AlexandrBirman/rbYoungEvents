package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//только ссылки
public class DigitalLoctoberScruperStandart extends BaseScruper {
    private String url = "https://digitaloctober.com/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.events-list__item");

        String buffer;

        for (int i = 0; i < elements.size(); i++) {
            event = new ScruperEvent();

            event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

            event.setName(elements.get(i).select("div.event__title").text());
            event.setDate(elements.get(i).select("div.event__start").text());

            buffer = elements.get(i).select("div.event__meta-item").text();

            event.setFormat(buffer.split(" ")[0]);
            event.setCategory(buffer.split(" ")[1]);


            data.add(event);
        }

        return data;
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("events-list__item");
    }
}
