package bot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class InlineKeyboardProvider {
    protected InlineKeyboardButton createButton(String message, String callbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(message);
        button.setCallbackData(callbackData);
        return button;
    }

    protected List<InlineKeyboardButton> createRow(InlineKeyboardButton... buttons) {
        List<InlineKeyboardButton> row = new ArrayList<>();
        Collections.addAll(row, buttons);
        return row;
    }

    public abstract InlineKeyboardMarkup getKeyboard();
}
