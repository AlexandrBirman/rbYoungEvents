import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parse.HeadHunterParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        try {
            Document document = Jsoup.connect("https://hh.ru/search/vacancy?text=C%2B%2B&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=&from=suggest_post/")
                    .get();
            Element body = document.body();
            Elements allElements = new Elements();
            getAllElements(body.getAllElements(), allElements);

            Elements vacancyNames = allElements.select(".vacancy-short__name");
            Elements vacancyURLs = allElements.select(".vacancy-short-footer");
            vacancyURLs = vacancyURLs.select("a[href]");

            List<String> urls = new ArrayList<>();
            for(Element elem : vacancyURLs) {
                String temp = elem.attr("abs:href");
                temp = temp.replaceAll("\\D", "");
                urls.add("hh.ru/vacancy/" + temp);
            }


            for(int i = 0; i < vacancyNames.size(); i++) {

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }

//    public static void getAllDiv(Elements elements, Elements result) {
//        for(Element elem : elements) {
//            Elements temp = elem.select("div");
//            if(temp.size() <= 1)
//                continue;
//            temp.remove(0);
//            result.addAll(temp);
//            getAllDiv(temp, result);
//        }
//    }

    public static void getAllElements(Elements elements, Elements result) {
        for(int i = 1; i < elements.size(); i++) {
            Elements temp = elements.get(i).getAllElements();
            if(temp.size() == 0)
                continue;
            result.addAll(temp);
            getAllElements(temp, result);
        }
    }
}
