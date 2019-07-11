package bot.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxKeyBoardProvider extends InlineKeyboardProvider{

    private List<String> buttonTexts;
    private InlineKeyboardMarkup inlineKeyboardMarkup;
    private String listName;
    private boolean[] selected;


    public CheckBoxKeyBoardProvider(List<String> buttonTexts, String listName) {
        this.buttonTexts = buttonTexts;
        this.selected = new boolean[buttonTexts.size()];
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        this.listName = listName;
        createKeyboard();
    }

    public CheckBoxKeyBoardProvider(List<String> buttonTexts, String listName, boolean[] selected) {
        this.buttonTexts = buttonTexts;
        this.selected = selected;
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        this.listName = listName;
        createKeyboard();
    }


    public void createKeyboard(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (int i = 0; i < buttonTexts.size(); i++){
            if (!selected[i]) {
                keyboard.add(createRow(createButton(buttonTexts.get(i), listName + " " + i)));
            } else
                keyboard.add(createRow(createButton(buttonTexts.get(i) + "✔", listName + " " + i)));
        }

        keyboard.add(createRow(createButton("\uD83D\uDCBE Сохранить",listName + " save")));

        inlineKeyboardMarkup.setKeyboard(keyboard);
    }

    public void addSelected(int i){
        selected[i] = true;
    }

    public void removeSelected (int i){
        selected[i] = false;
    }

    public boolean hasSelected (int i){
        return selected[i];
    }

    @Override
    public InlineKeyboardMarkup getKeyboard() {
        return inlineKeyboardMarkup;
    }

    public String getListName() {
        return listName;
    }

    public boolean[] getSelected() {
        return selected;
    }

    public List<String> getButtonTexts() {
        return buttonTexts;
    }
}
