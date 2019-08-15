package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;
import parse.scrupers.ScruperEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventsYandexScruperStandart extends BaseScruper {
    private static String url = "https://events.yandex.ru/";


    @Override
    protected List<ScruperEvent> getReferences(String content) throws IOException {
        List<ScruperEvent> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.events-calendar__cell-inner");
        //Elements links = elements.select("a[href]"); достает на одну ссылку меньше

        for (int i = 0; i < elements.size() - 1; i++) { // тут вылазит исключение на выхож из массива, пока не понял где собака зарыта
            //System.out.println((links.get(i).attr("abs:href")));
            //BaseScruper.builder.setLength(0);
            event = new ScruperEvent();

            if (elements.get(i).text().contains("открыта")){

                //builder.append(links.get(i).attr("abs:href") + "\n");
                //builder.append(elements.get(i).select("a[href]").attr("abs:href") + "\n");
                event.setUrl(elements.get(i).select("a[href]").attr("abs:href"));

                //builder.append(elements.get(i).getElementsByClass("auto-ellipsis__inner").text() + "\n");
                event.setName(elements.get(i).getElementsByClass("auto-ellipsis__inner").text());

                //builder.append(elements.get(i).getElementsByClass("action-announce__date").text());
                event.setDate(elements.get(i).getElementsByClass("action-announce__date").text());

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
