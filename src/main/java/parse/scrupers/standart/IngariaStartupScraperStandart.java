package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IngariaStartupScraperStandart extends BaseScruper {

    private static String url = "https://ingria-startup.ru/events/";


    @Override
    protected List<String> getReferences(String content) throws IOException {
        List<String> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div.container");
        Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            System.out.println((links.get(i).attr("abs:href")));
            //data.add((links.get(i).attr("abs:href")));
        }

        return data;
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences(null);
    }
}
