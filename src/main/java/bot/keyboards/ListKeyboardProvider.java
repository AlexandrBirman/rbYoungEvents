package bot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ListKeyboardProvider extends InlineKeyboardProvider {
    private List<String> buttonTexts;
    private int curIndex;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private String listName;

    public ListKeyboardProvider(List<String> buttonTexts, String listName) {
        this.buttonTexts = buttonTexts;
        curIndex = 0;
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        this.listName = listName;
        createKeyboard();
    }

    private void createKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for(int i = curIndex; i < Math.min(buttonTexts.size(), curIndex + 4); i++) {
            keyboard.add(createRow(createButton(buttonTexts.get(i),
                                            listName + " " + i)));
        }
        keyboard.add(createRow(createButton("<<", listName + " prev"),
                createButton(">>", listName + " next")));
        inlineKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public InlineKeyboardMarkup getKeyboard() {
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getNextKeyboard() {
        if(curIndex + 4 < buttonTexts.size()) {
            curIndex += 4;
            createKeyboard();
        }
        return inlineKeyboardMarkup;
    }

    public InlineKeyboardMarkup getPrevKeyboard() {
        if(curIndex > 0) {
            curIndex = Math.max(0, curIndex - 4);
            createKeyboard();
        }
        return inlineKeyboardMarkup;
    }
}
