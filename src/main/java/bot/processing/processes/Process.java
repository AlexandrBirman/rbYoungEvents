package bot.processing.processes;

import bot.UserSession;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Process {
    protected UserSession userSession;
    protected String curState;
    protected static Update startUpdate;

    public abstract void handleRequest(Update update);

    public abstract void sendResponse(Update update);

    public String getCurState() {
        return curState;
    }
}
