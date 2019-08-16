package parse;

import parse.scrupers.*;
import parse.scrupers.fromAPI.HhScraperFromAPI;
import parse.scrupers.fromAPI.TimePadScraperFromAPI;
import parse.scrupers.fromAPI.UniversariumScruperStandart;
import parse.scrupers.standart.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
// пока нет сортировки по России
public class Launcher extends TimerTask{

    private static long period = 60*60*24*1000;
    //private static long stopPoint =

    public static void main(String[] args) throws IOException {
        TimerTask starter = new Launcher();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(starter, 0, period);
        System.out.println("TimerTask начал выполнение");

        // вызываем cancel() через какое-то время
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
        System.out.println("TimerTask прекращена");
        try {
            Thread.sleep(30*60*000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        for (BaseScruper baseScruper : getScrupers()) {
            try {
                for (ScruperEvent data : baseScruper.getData()) {
                    // здесь выгрузка ссылок в базу, а не в консоль
                    System.out.println(data.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("------------");
        }


    }

    private List<BaseScruper> getScrupers(){
        List<BaseScruper> storage = new ArrayList<>();
        //storage.add(new HhScraperFromAPI()); // стажировки | достает ссылку + название + город
        //storage.add(new TimePadScraperFromAPI()); // мероприятия по теме | Бизнес | IT и интернет |  название мероприятия
        //storage.add(new ITeventsScraperStandart()); //  ссылка + область + название мероприятия | (дата + город вместе)
        //storage.add(new TaglineEventsScruperStandart()); // ссылка + название + дата + город + цена(если есть)
        //storage.add(new EventsYandexScruperStandart()); // ссылка + название + дата (достает только активные мероприятия на которые открыта регистрация)
        //storage.add(new SkScruperStandart()); //работает неправильно, пока не знаю как исправить
        //storage.add(new LeaderIdScruperStandart()); // работает неправильно, пока не знаю как исправить
        //storage.add(new IngariaStartupScraperStandart()); // ссылка + название + дата
        //storage.add(new TcehScraperStandart()); // ссылка + название + дата + категория
        //storage.add(new DigitalLoctoberScruperStandart()); //ссылка + название + дата + категория + формат
        storage.add(new RunetIdScruperStandart());
        //storage.add(new HackatonIoScruperStandart());
        //storage.add(new NetologyScruperStandart()); //не доделан
        //storage.add(new MskItStepScruperStandart());
        //storage.add(new UniversariumScruperStandart()); // курсы | достает ссылку + область + название | все раздельно без лишних кавычек
        //storage.add(new BritishDesignScruperStandart()); // не работает
        return storage;
    }
}


