package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.List;

public class RvcScruperStandart extends BaseScruper {

    private static String url = "https://www.rvc.ru/calendar/";


    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect(url)
                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
                    .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("#catalog-list");
        System.out.println(elements);
        Elements links = elements.select("a[href]");
        for (int i = 1; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
        }
    }


    @Override
    protected List<String> getReferences(String content) {
        return null;
    }

    @Override
    public List<String> getData() {
        return null;
    }
}
