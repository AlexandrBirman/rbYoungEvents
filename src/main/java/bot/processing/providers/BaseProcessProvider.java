package bot.processing.providers;

import bot.UserSession;
import bot.processing.processes.*;
import bot.processing.processes.Process;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Queue;

public class BaseProcessProvider {
    protected static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("\"dd\" MMMM yyyy г.", new Locale("ru"));

    protected UserSession userSession;
    protected Queue<Process> processQueue;

    public BaseProcessProvider(UserSession userSession) {
        this.userSession = userSession;
        processQueue = userSession.getProcessQueue();
    }

//    public void addDocumentChoosingProcess() {
//        processQueue.add(new DocumentChoosingProcess(userSession));
//    }
//
//    public void addFillingFIOProcess() {
//        processQueue.add(new FillingFIOProcess(userSession));
//    }
//
//    public void addFillingLearningGroupProcess() {
//        processQueue.add(new FillingLearningGroupProcess(userSession));
//    }
//
//    public void addFillingPhoneNumberProcess() {
//        processQueue.add(new FillingPhoneNumberProcess(userSession));
//    }
//
//    public void addFillingStudentCertificateProcess() {
//        processQueue.add(new FillingStudentCertificateProcess(userSession));
//    }
//
//    public void addFillingMaterialAidReasonProcess() {
//        processQueue.add(new FillingMaterialAidReasonProcess(userSession));
//    }
//
//    public void addSendingPreviewProcess() {
//        processQueue.add(new SendingPreviewProcess(userSession));
//    }
//
//    public void addSendingDocumentProcess() {
//        processQueue.add(new SendingDocumentProcess(userSession));
//    }
//
//    public void addEditingProcess(String fieldsFileName) {
//        processQueue.add(new EditingProcess(userSession, fieldsFileName));
//    }
}
