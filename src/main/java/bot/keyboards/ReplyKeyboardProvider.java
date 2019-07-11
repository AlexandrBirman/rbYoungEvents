package bot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.Collections;

public abstract class ReplyKeyboardProvider {
    protected KeyboardButton createButton(String text) {
        return new KeyboardButton(text);
    }

    protected KeyboardRow createRow(KeyboardButton... buttons) {
        KeyboardRow row = new KeyboardRow();
        Collections.addAll(row, buttons);
        return row;
    }

    public abstract ReplyKeyboardMarkup getKeyboard();
}
