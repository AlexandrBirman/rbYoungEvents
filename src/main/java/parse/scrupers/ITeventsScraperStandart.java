package parse.scrupers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ITeventsScraperStandart extends BaseScruper{

    private  static String url = "https://it-events.com/";

    @Override
    List<String> getReferences(String content) throws IOException {

        List<String> data = new ArrayList<>();

        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.col-10");
        Elements links = elements.select("a[href]");
        for (int i = 1; i < elements.size(); i++) {
            data.add(links.get(i).attr("abs:href"));
        }

        return data;
    }

    @Override
    public List<String> getData() throws IOException {
        return getReferences(null);
    }


}
