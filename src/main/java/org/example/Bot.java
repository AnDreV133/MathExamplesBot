package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Bot extends TelegramLongPollingBot {
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
            String message = update.getMessage().getText();
            long chatID = update.getMessage().getChatId();
            System.out.println(1);
            if (message.equals("/start") || message.equals("/mainMenu")) {
                System.out.println(2);
                try {
                    execute(new Keyboards().getInlineStartKeyboard(chatID));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (update.hasCallbackQuery()) {
            System.out.println(3);
            long chatID = update.getCallbackQuery().getMessage().getChatId();
            String callbackQuery = update.getCallbackQuery().getData();
            if (callbackQuery.equals("buttonStudent")) {
                System.out.println(4);
                try {
                    execute(new Keyboards().getStudentKeyboard(chatID));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (callbackQuery.equals("buttonTeacher")) {
                System.out.println(5);
                try {
                    execute(new Keyboards().getTeacherKeyboard(chatID));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        if (update.hasCallbackQuery()) {
            studentLogics(update);
        }
//        if (update.hasMessage()) {
//            teacherLogics(update);
//        }
    }

    private static void studentLogics(Update update) {
        String callbackQuery = update.getCallbackQuery().getData();
        BotLogics mathExercises = new BotLogics();
        if (callbackQuery.equals("lvl0")) {

        } else if (callbackQuery.equals("lvl1")) {

        } else if (callbackQuery.equals("lvl2")) {

        } else if (callbackQuery.equals("lvl3")) {

        } else if (callbackQuery.equals("Bag")) {

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
