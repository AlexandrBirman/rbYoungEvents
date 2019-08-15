package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaglineEventsScruperStandart extends BaseScruper {
    private static String url = "https://tagline.ru/events/";

    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.event__wrapper");
       // Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //builder.setLength(0);
            event = new ScruperEvent();

            if (!elements.get(i).text().contains("Реклама")){
                //builder.append(elements.get(i).select("a[href]").attr("abs:href") + "\n");
                event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

                //builder.append(elements.get(i).getElementsByClass("event__title").text() + "\n");
                event.setName(elements.get(i).getElementsByClass("event__title").text());

                //builder.append(elements.get(i).select("div.event__info.event__date span").text() + "\n");
                event.setDate(elements.get(i).select("div.event__info.event__date span").text());

                //builder.append(elements.get(i).select("span.event__info-text.event__info-text_city").text() + "\n");
                event.setLocation(elements.get(i).select("span.event__info-text.event__info-text_city").text());

                //builder.append(elements.get(i).select("div.event__info-content.event__info-content_medium").text() + "\n");
                event.setPrice(elements.get(i).select("div.event__info-content.event__info-content_medium").text());

                data.add(event);
            }


        }
        return data;
    }

    @Override
    public List<ScruperEvent> getData() throws IOException {
        return getReferences(null);
    }
}
