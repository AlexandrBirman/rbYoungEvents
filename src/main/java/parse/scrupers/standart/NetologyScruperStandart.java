package parse.scrupers.standart;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import parse.scrupers.BaseScruper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetologyScruperStandart extends BaseScruper {

    private static String marketingUrl = "https://netology.ru/marketing/programs";
    private static String managementUrl = "https://netology.ru/management/programs";
    private static String designUrl = "https://netology.ru/design/programs";
    private static String developmentUrl = "https://netology.ru/development/programs";
    private static String datascienceUrl = "https://netology.ru/data-science/programs";



    @Override
    protected List<String> getReferences(String content) throws IOException {
        return null;
    }

    private List<String> sum() throws IOException {
        List<String> data = new ArrayList<>();

        data.addAll(getFromHTML(managementUrl, "direction-module-content-2UwHl8"));


        return data;
    }

    @Override
    public List<String> getData() throws IOException {
        return sum();
    }

    @Override
    protected List<String> getFromHTML(String url, String tag) throws IOException {
        List<String> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.getElementsByClass("Anchor-module-root-Zn7Wln programCard-module-link-1kfOUv");
        // Elements elements = document.getElementsByClass("div." + tag);
        Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
            data.add((links.get(i).attr("abs:href")));
        }

        return data;
    }
}
