package parse;

//import dbService.dao.DAOContext;
//import dbService.dao.SimpleDAO;
import dbService.entity.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeadHunterParser {
    private Document document;

    public HeadHunterParser(String url) {
        try {
            document = Jsoup.connect(url)
//                    .userAgent("Chrome/4.0.249.0 Safari/532.5")
//                    .referrer("http://www.google.com")
                    .get();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vacancy> parseVacancies() {
        Element body = document.body();
        Elements allElements = new Elements();
        getAllElements(body.getAllElements(), allElements);

        Elements vacancyNames = allElements.select(".vacancy-short__name");
        Elements vacancyURLs = allElements.select(".vacancy-short-footer");
        vacancyURLs = vacancyURLs.select("a[href]");

        List<Vacancy> vacancies = new ArrayList<>();
        for(int i = 0; i < vacancyNames.size(); i++) {
            String temp = vacancyURLs.get(i).attr("abs:href");
            temp = temp.replaceAll("\\D", "");

            Vacancy vacancy = new Vacancy(vacancyNames.get(i).text(), "hh.ru/vacancy/" + temp);
            vacancies.add(vacancy);
        }
        return vacancies;
    }

   /* public void uploadVacanciesToDB(List<Vacancy> vacancies, String keyword, int limit) {
        SimpleDAO dao = DAOContext.getSimpleDAO();
        for(int i = 0; i < Math.min(limit, vacancies.size()); i++) {
            dao.insertVacancy(vacancies.get(i), keyword);
        }
    }
    */
    private void getAllElements(Elements elements, Elements result) {
        for(int i = 1; i < elements.size(); i++) {
            Elements temp = elements.get(i).getAllElements();
            if(temp.size() == 0)
                continue;
            result.addAll(temp);
            getAllElements(temp, result);
        }
    }
}
