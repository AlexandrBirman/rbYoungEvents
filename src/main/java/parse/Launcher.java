package parse;

import parse.scrupers.BaseScruper;
import parse.scrupers.HhScraperFromAPI;
import parse.scrupers.TimePadScraperFromAPI;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Launcher extends TimerTask{
    private static long period = 30*1000;
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
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

        for (BaseScruper baseScruper : getScrupers()) {
            try {
                for (String url : baseScruper.getData()) {
                    // здесь выгрузка ссылок в базу, а не в консоль
                    System.out.println(url);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("------------");
        }


    }

    private List<BaseScruper> getScrupers(){
        List<BaseScruper> storage = new ArrayList<>();
        storage.add(new HhScraperFromAPI());
        storage.add(new TimePadScraperFromAPI());

        return storage;
    }
}

