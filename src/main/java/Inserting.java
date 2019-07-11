/*import dbService.entity.Vacancy;
import parse.HeadHunterParser;

import java.util.List;

public class Inserting {
    public static void main(String[] args) {
        HeadHunterParser parser1 = new HeadHunterParser("https://hh.ru/search/vacancy?text=java&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=/");
        List<Vacancy> vacancies1 = parser1.parseVacancies();
        parser1.uploadVacanciesToDB(vacancies1, "Java", 10);

        HeadHunterParser parser2 = new HeadHunterParser("https://hh.ru/search/vacancy?text=C%2B%2B&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=/");
        List<Vacancy> vacancies2 = parser2.parseVacancies();
        parser2.uploadVacanciesToDB(vacancies2, "C++", 10);

        HeadHunterParser parser3 = new HeadHunterParser("https://hh.ru/search/vacancy?text=Python&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=/");
        List<Vacancy> vacancies3 = parser3.parseVacancies();
        parser3.uploadVacanciesToDB(vacancies3, "Python", 10);

        HeadHunterParser parser4 = new HeadHunterParser("https://hh.ru/search/vacancy?text=PHP&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=/");
        List<Vacancy> vacancies4 = parser4.parseVacancies();
        parser4.uploadVacanciesToDB(vacancies4, "PHP", 10);

        HeadHunterParser parser5 = new HeadHunterParser("https://hh.ru/search/vacancy?text=JavaScript&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=&from=suggest_post/");
        List<Vacancy> vacancies5 = parser5.parseVacancies();
        parser5.uploadVacanciesToDB(vacancies5, "JavaScript", 10);

        HeadHunterParser parser6 = new HeadHunterParser("https://hh.ru/search/vacancy?text=Analyst&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=&from=suggest_post");
        List<Vacancy> vacancies6 = parser6.parseVacancies();
        parser6.uploadVacanciesToDB(vacancies6, "Аналитика", 10);

        HeadHunterParser parser7 = new HeadHunterParser("https://hh.ru/search/vacancy?text=Business&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=/");
        List<Vacancy> vacancies7 = parser7.parseVacancies();
        parser7.uploadVacanciesToDB(vacancies7, "Бизнес", 10);

        HeadHunterParser parser8 = new HeadHunterParser("https://hh.ru/search/vacancy?text=Designer&only_with_salary=false&employment=probation&clusters=true&area=1&enable_snippets=true&salary=&from=suggest_post/");
        List<Vacancy> vacancies8 = parser8.parseVacancies();
        parser8.uploadVacanciesToDB(vacancies8, "Дизайн", 10);

        System.out.println(322);
    }
}
*/