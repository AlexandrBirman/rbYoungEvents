package bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ChatBot extends TelegramLongPollingBot {
    private final String BOT_NAME;
    private final String BOT_TOKEN;
    private final String PROPERTIES = "src/bot.properties";

    private static ChatBot bot;

    private Map<String, UserSession> userSessions;

    private ChatBot() {
        Properties properties = new Properties();
        try (FileInputStream fos = new FileInputStream(PROPERTIES)) {
            properties.load(fos);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        BOT_NAME = properties.getProperty("bot_name");
        BOT_TOKEN = properties.getProperty("bot_token");

        userSessions = new HashMap<>();
    }

    static {
        bot = new ChatBot();
    }

    @Override
    public void onUpdateReceived(Update update) {
        String chatId = null;
        if(update.hasCallbackQuery())
            chatId = String.valueOf(update.getCallbackQuery().getMessage().getChatId());
        else if(update.hasMessage())
            chatId = String.valueOf(update.getMessage().getChatId());
        else
            return;

        if(userSessions.containsKey(chatId))
            userSessions.get(chatId).executeCurrentProcess(update);
        else
            userSessions.put(chatId, new UserSession(chatId));
    }

    public static void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        try {
            bot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String chatId, String message, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        sendMessage.setReplyMarkup(keyboard);
        try {
            bot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessage(String chatId, ReplyKeyboard keyboard){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(keyboard);
    }

    public static void editMessageReplyMarkup(String chatId, Integer messageId, String inlineMessageId, InlineKeyboardMarkup keyboard) {
        EditMessageReplyMarkup editMessage = new EditMessageReplyMarkup();
        editMessage.setChatId(chatId)
                .setMessageId(messageId)
                .setInlineMessageId(inlineMessageId)
                .setReplyMarkup(keyboard);
        try {
            bot.execute(editMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendChatAction(String chatId, ActionType actionType) {
        SendChatAction action = new SendChatAction();
        action.setChatId(chatId)
                .setAction(actionType);
        try {
            bot.execute(action);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendDocument(String chatId, String message, File documentFile) {
        SendDocument document = new SendDocument();
        document.setChatId(chatId)
                .setCaption(message)
                .setDocument(documentFile);
        try {
            bot.execute(document);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendDocument(String chatId, String message, File documentFile, ReplyKeyboard keyboard) {
        SendDocument document = new SendDocument();
        document.setChatId(chatId)
                .setCaption(message)
                .setDocument(documentFile)
                .setReplyMarkup(keyboard);
        try {
            bot.execute(document);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static void sendPhoto(String chatId, String message, File imageFile, ReplyKeyboard keyboard) {
        SendPhoto photo = new SendPhoto();
        photo.setChatId(chatId)
                .setCaption(message)
                .setPhoto(imageFile)
                .setReplyMarkup(keyboard);
        try {
            bot.execute(photo);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public static ChatBot getInstance() {
        return bot;
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}