package parse;

import parse.scrupers.HhScraperFromAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Launcher extends TimerTask{
    public static void main(String[] args) throws IOException {
        TimerTask starter = new Launcher();

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(starter, 0, 10*1000);
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
        /* здесь надо вставить реализацию куда заливать данные
        HhScraperFromAPI hhScraperFromAPI = new HhScraperFromAPI();

        try {
            for (String url : hhScraperFromAPI.getReferences(hhScraperFromAPI.getJsonString())) {
                System.out.println(url);
            }
            System.out.println("-------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
}


