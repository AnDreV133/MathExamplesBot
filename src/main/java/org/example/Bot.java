package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Bot extends TelegramLongPollingBot {
    private static String mode = "";
    private static Properties property() {
        Properties property;
        try {
            property = new Properties();
            FileInputStream file = new FileInputStream("src/main/resources/SecretInfoOfTelegramBot.properties");
            property.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }
    @Override
    public String getBotUsername() {
        return property().getProperty("BotName");
    }

    @Override
    public String getBotToken() {
        return property().getProperty("Token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().getText().equals("/start") || update.getMessage().getText().equals("/mainMenu")) {
                mode = "";
                try {
                    execute(new Keyboards().getInlineStartKeyboard(update.getUpdateId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (update.hasCallbackQuery() && mode.equals("")) {
            if (update.getCallbackQuery().getMessage().getText().equals("buttonStudent")) {
                mode = "Student";
                try {
                    execute(new Keyboards().getInlineStudentKeyboard(update.getUpdateId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
//                studentLogics(update);
            } else if (update.getCallbackQuery().getMessage().getText().equals("buttonTeacher")) {
                mode = "Teacher";
                try {
                    execute(new Keyboards().getInlineStudentKeyboard(update.getUpdateId()));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
//                teacherLogics(update);
            }
        }
        if (update.hasCallbackQuery() && mode.equals("Student")) {
            studentLogics(update);
        }
        if (update.hasCallbackQuery() && mode.equals("Teacher")) {
            studentLogics(update);
        }
    }

    private static void studentLogics(Update update) {
        if () {

        }
    }

    private static void teacherLogics(Update update) {
    }

//    private static void startWork(Message message) {
//        if (message.hasEntities()) {
//            if (message.getEntities().)
//        }
//    }
//    private static void studentLogics () {
//        if
//    }
    @SneakyThrows
    public static void main(String[] args) {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }
}
