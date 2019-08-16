package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
// пока выдает только ссылки, причем некоторые дублирует
public class TcehScraperStandart extends BaseScruper {
    private static String url = "https://tceh.com/events/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.row.all");
        Elements allElements = elements.select("div.events-card.align-items-stretch");
        //Elements links = elements.select("a[href]"); достает на одну ссылку меньше

        for (int i = 0; i < elements.size(); i++) { // тут вылазит исключение на выхож из массива, пока не понял где собака зарыта
            event = new ScruperEvent();

            event.setUrl(allElements.get(i).select("a[href]").attr("abs:href"));

            event.setName(allElements.get(i).select("h3").select("a").text());
            //event.setName(elements.get(i).getElementsByClass());
            event.setCategory((allElements.get(i).select("span.events-card__tag.p--further").text()));
            event.setDate(allElements.get(i).select("p.p--further.events-card__meta.mt-0").text());

            data.add(event);
        }

        return data;
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences("events-card.align-items-stretch");
    }
}
