package parse.scrupers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseScruper {

    protected String getJsonString(String url) throws IOException {
        String urlString = url;
        URL urlObject = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();

        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    protected List<String> getFromHTML(String url, String tag) throws IOException {
        List<String> data = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent("Chrome/4.0.249.0 Safari/532.5")
                .referrer("http://www.google.com")
                .get();

        Elements elements = document.select("div." + tag);
       // Elements elements = document.getElementsByClass("div." + tag);
        Elements links = elements.select("a[href]");
        for (int i = 0; i < elements.size(); i++) {
            //System.out.println((links.get(i).attr("abs:href")));
            data.add((links.get(i).attr("abs:href")));
        }

        return data;
    }

    abstract List<String> getReferences(String content) throws IOException;

    public abstract List<String> getData() throws IOException;

}
