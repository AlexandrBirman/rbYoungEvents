package bot.processing.processes;

import bot.ChatBot;
import bot.UserSession;
import bot.keyboards.CheckBoxKeyBoardProvider;
//import dbService.dao.DAOContext;
//import dbService.dao.SimpleDAO;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.ListsUtil;

import java.util.List;

public class BusinessMenuProcess extends Process {

    private CheckBoxKeyBoardProvider checkBoxBusinessMenu;

    public BusinessMenuProcess(UserSession userSession) {
        this.userSession = userSession;
        List<String> types = ListsUtil.getList("businessMenu.txt");
        // boolean[] selected = loadSelectedTypes(userSession.getChatId(), types);

        checkBoxBusinessMenu = new CheckBoxKeyBoardProvider(types, "businessMenu");
        curState = "sendQuestion";
    }

   /*private boolean[] loadSelectedTypes(String chatId, List<String> types) {
        boolean[] selected = new boolean[types.size()];
        SimpleDAO dao = DAOContext.getSimpleDAO();

        List<Integer> userTypes = dao.selectTypes(chatId);
        for(int ind : userTypes)
            selected[ind - 1] = true;

        dao.deleteTypesByChatId(chatId);

        return selected;
    }
    */

    @Override
    public void handleRequest(Update update) {
        if (update == null){
            return;
        }
        if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if(!data.startsWith(checkBoxBusinessMenu.getListName()))
                return;

            data = data.split(" ")[1];
            if(data.equals("save")) {
                curState = "finished";



                boolean[] selected = checkBoxBusinessMenu.getSelected();
                if (selected[0])
                    userSession.getProcessQueue().add(new MarketingMenuProcess(userSession));
                if (selected[1])
                    userSession.getProcessQueue().add(new ManagementMenuProcess(userSession));
                if (selected[2])
                    userSession.getProcessQueue().add(new ProductMenuProcess(userSession));
                if (userSession.getProcessQueue().size() == 1)
                    userSession.getProcessQueue().add(new FormantsProcess(userSession));

                    /*SimpleDAO dao = DAOContext.getSimpleDAO();
                    for(int i = 0; i < selected.length; i++) {
                        if(selected[i]) {
                            dao.insertType(userSession.getChatId(), i + 1);
                        }
                    }*/
            }
            else {
                curState = "checking";
                int i = Integer.parseInt(data);


                if (checkBoxBusinessMenu.hasSelected(i)) {

                    checkBoxBusinessMenu.removeSelected(i);
                    checkBoxBusinessMenu.createKeyboard();

                } else {

                    checkBoxBusinessMenu.addSelected(i);
                    checkBoxBusinessMenu.createKeyboard();
                }
            }
        }
    }

    @Override
    public void sendResponse(Update update) {
        switch (curState) {
            case "sendQuestion": {
                ChatBot.sendMessage(userSession.getChatId(), "Вы выбрали бизнес, хотите уточнить отрасли?");
                ChatBot.editMessageReplyMarkup(userSession.getChatId(),
                        startUpdate.getCallbackQuery().getMessage().getMessageId(),
                        startUpdate.getCallbackQuery().getInlineMessageId(),
                        checkBoxBusinessMenu.getKeyboard());
                break;
            }
            default: {
                String data = update.getCallbackQuery().getData();
                if(!data.startsWith(checkBoxBusinessMenu.getListName()))
                    return;
                ChatBot.editMessageReplyMarkup(userSession.getChatId(), update.getCallbackQuery().getMessage().getMessageId(),
                        update.getCallbackQuery().getInlineMessageId(), checkBoxBusinessMenu.getKeyboard());
            }
        }
    }

    @Override
    public String getCurState() {
        return super.getCurState();
    }
}
