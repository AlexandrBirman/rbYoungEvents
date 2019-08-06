package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ITeventsScraperStandart extends BaseScruper {

    private  static String url = "https://it-events.com/";

    @Override
    protected List<String> getReferences(String content) throws IOException {

        List<String> data = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.event-list-item");
        Elements links = elements.select("a[href]"); // в этом месте создается два элемента одной ссылки
        for (int i = 0; i < elements.size(); i += 1) {
            builder.setLength(0);
            builder.append(links.get(2 * i).attr("abs:href") + "\n");
            builder.append(elements.get(i).getElementsByClass("event-list-item__type").text() + "\n");
            builder.append(elements.get(i).getElementsByClass("event-list-item__title").text() + "\n");
            builder.append(elements.get(i).getElementsByClass("event-list-item__info").text());


            data.add(builder.toString());
        }

        return data;
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences(null);
    }


}
