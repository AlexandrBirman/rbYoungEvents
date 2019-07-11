/*package bot.processing.processes;

import bot.ChatBot;
import bot.UserSession;
import dbService.dao.DAOContext;
import dbService.dao.SimpleDAO;
import dbService.entity.Vacancy;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.*;

public class SendingEventsProcess extends Process{
    private boolean send;

    public SendingEventsProcess(UserSession userSession) {
        this.userSession = userSession;
        curState = "sendingEvents";
        send = false;
    }

    @Override
    public void handleRequest(Update update) {
        //do nothing
    }

    @Override
    public void sendResponse(Update update) {
        if(send)
            return;
        send = true;

        SimpleDAO dao = DAOContext.getSimpleDAO();

//        List<String> keyWords = dao.selectKeyWords(userSession.getChatId());
//        List<Integer> types = dao.selectTypes(userSession.getChatId());
//
//        List<Integer> eventIds = new ArrayList<>();
//        for(String keyWord : keyWords) {
//            eventIds.addAll(dao.selectEventIds(keyWord));
//        }
        List<Vacancy> vacancies = dao.selectVacanciesForPerson(userSession.getChatId());
        Random random = new Random(System.currentTimeMillis());
        Set<Integer> send = new HashSet<>();

        for(int i = 0; i < 3; i++) {
            if(send.size() == vacancies.size())
                break;

            int newIndex = random.nextInt(vacancies.size());
            while(send.contains(newIndex))
                newIndex = random.nextInt(vacancies.size());
            send.add(newIndex);

            Vacancy vacancy = vacancies.get(newIndex);
            ChatBot.sendMessage(userSession.getChatId(), "Новая стажировка!!!\n" +
                    vacancy.getName() + "\n" + vacancy.getUrl());
        }
    }
}
*/