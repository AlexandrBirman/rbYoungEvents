package bot.processing.processes;

import bot.ChatBot;
import bot.UserSession;
import bot.keyboards.CheckBoxKeyBoardProvider;
import org.telegram.telegrambots.meta.api.objects.Update;
import util.ListsUtil;

import java.util.List;

public class FormantsProcess extends Process {

    private CheckBoxKeyBoardProvider checkBoxStartMenu;

    public FormantsProcess(UserSession userSession) {
        this.userSession = userSession;
        List<String> types = ListsUtil.getList("formats.txt");
        // boolean[] selected = loadSelectedTypes(userSession.getChatId(), types);

        checkBoxStartMenu = new CheckBoxKeyBoardProvider(types, "formats");
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
            if(!data.startsWith(checkBoxStartMenu.getListName()))
                return;

            data = data.split(" ")[1];
            if(data.equals("save")) {
                curState = "answer";



                boolean[] selected = checkBoxStartMenu.getSelected();

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


                if (checkBoxStartMenu.hasSelected(i)) {

                    checkBoxStartMenu.removeSelected(i);
                    checkBoxStartMenu.createKeyboard();

                } else {

                    checkBoxStartMenu.addSelected(i);
                    checkBoxStartMenu.createKeyboard();
                }
            }
        }
    }

    @Override
    public void sendResponse(Update update) {
        switch (curState) {
            case "sendQuestion": {
                ChatBot.sendMessage(userSession.getChatId(),
                        "Осталось выбрать форматы!");
                ChatBot.editMessageReplyMarkup(userSession.getChatId(), startUpdate.getCallbackQuery().getMessage().getMessageId(),
                        startUpdate.getCallbackQuery().getInlineMessageId(), checkBoxStartMenu.getKeyboard());
                break;
            }
            case "answer": {
                ChatBot.sendMessage(userSession.getChatId(),
                        "Ваш выбор успешно сохранен!");
                curState = "finished";
                break;
            }
            default: {
                String data = update.getCallbackQuery().getData();
                if(!data.startsWith(checkBoxStartMenu.getListName()))
                    return;
                ChatBot.editMessageReplyMarkup(userSession.getChatId(), update.getCallbackQuery().getMessage().getMessageId(),
                        update.getCallbackQuery().getInlineMessageId(), checkBoxStartMenu.getKeyboard());
            }
        }
    }

    @Override
    public String getCurState() {
        return super.getCurState();
    }
}
