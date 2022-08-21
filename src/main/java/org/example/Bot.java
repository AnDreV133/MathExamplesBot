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
        try {
            execute(new Keyboards().getInlineStartKeyboard(update.getUpdateId()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getMessage().getText().equals("buttonStudent")) {
                studentLogics(update);
            } else if (update.getCallbackQuery().getMessage().getText().equals("buttonTeacher")) {

            }
        }
    }
    private static void studentLogics(Update update) {
        if () {

        }
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
