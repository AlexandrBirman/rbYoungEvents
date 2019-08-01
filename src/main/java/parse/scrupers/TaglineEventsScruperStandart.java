package parse.scrupers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaglineEventsScruperStandart extends BaseScruper {
    private static String url = "https://tagline.ru/events/";

    @Override
    List<String> getReferences(String content) throws IOException {
        List<String> data = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.event__body");
        Elements links = elements.select("a[href]");
        for (int i = 1; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
            data.add((links.get(i).attr("abs:href")));
        }
        return data;
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences(null);
    }
}
