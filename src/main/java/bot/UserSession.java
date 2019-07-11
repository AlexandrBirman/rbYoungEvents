package bot;

import bot.keyboards.ReplyKeyboardProvider;
import bot.processing.processes.FormantsProcess;
import bot.processing.processes.StartMenuProcess;
import bot.processing.processes.Process;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class UserSession {
    private String chatId;
    private Queue<Process> processQueue;
    private ReplyKeyboardMarkup changingKeyboard;


    public UserSession(String chatId) {
        this.chatId = chatId;
        processQueue = new ArrayDeque<>();

        ReplyKeyboardProvider keyboardProvider = new ReplyKeyboardProvider() {
            @Override
            public ReplyKeyboardMarkup getKeyboard() {
                List<KeyboardRow> keyboardRows = new ArrayList<>();
                keyboardRows.add(createRow(createButton("Выбрать другие интересы")));
                ReplyKeyboardMarkup replyKeyboard = new ReplyKeyboardMarkup();
                replyKeyboard.setSelective(true)
                        .setResizeKeyboard(true)
                        .setOneTimeKeyboard(false)
                        .setKeyboard(keyboardRows);
                return replyKeyboard;
            }
        };
        changingKeyboard = keyboardProvider.getKeyboard();

        processQueue.add(new StartMenuProcess(this));
    }

    public void executeCurrentProcess(Update update) {
        if(update != null && update.hasMessage()) {
            Message message = update.getMessage();
            if(message.getText().equals("Выбрать другие интересы")) {
                processQueue.clear();
                processQueue.add(new StartMenuProcess(this));
                update = null;
            }
        }

        Process curProcess = processQueue.peek();
        curProcess.handleRequest(update);
        if(curProcess.getCurState().equals("finished")) {
            processQueue.poll();
            if(processQueue.isEmpty())
                return;
            curProcess = processQueue.peek();
            curProcess.sendResponse(null);
        }
        else
            curProcess.sendResponse(update);
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public Queue<Process> getProcessQueue() {
        return processQueue;
    }

    public void setProcessQueue(Queue<Process> processQueue) {
        this.processQueue = processQueue;
    }

    public ReplyKeyboardMarkup getChangingKeyboard() {
        return changingKeyboard;
    }
}
