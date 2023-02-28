package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.example.GetterSecretInformation;

public class Bot extends TelegramLongPollingBot {
    GetterSecretInformation SI = new GetterSecretInformation();
    @SneakyThrows
    public static void main(String[] args) {
        Bot bot = new Bot();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }

    @Override
    public String getBotUsername() {
        return SI.get("BotName");
    }

    @Override
    public String getBotToken() {
        return SI.get("Token");
    }

    @Override
    public void onUpdateReceived(Update update) {
//        DBHandler db = new DBHandler(update.getUpdateId());
//        if (update.hasMessage()) {
//            String message = update.getMessage().getText();
//            long chatID = update.getMessage().getChatId();
//            if (message.equals("/start") || message.equals("/mainMenu")) {
//                db.state = ""; // функция для удаления
//                try {
//                    execute(new Keyboards().getInlineStartKeyboard(chatID));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
        }
//        if (update.hasCallbackQuery()) {
//            long chatID = update.getCallbackQuery().getMessage().getChatId();
//            String callbackQuery = update.getCallbackQuery().getData();
//            if (callbackQuery.equals("buttonStudent")) {
//                db.state = "Student"; // функция для присваивания
//                try {
//                    execute(new Keyboards().getStudentKeyboard(chatID));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            } else if (callbackQuery.equals("buttonTeacher")) {
//                db.state = "Teacher"; // функция для присваивания
//                try {
//                    execute(new Keyboards().getTeacherKeyboard(chatID));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        if (update.hasCallbackQuery() && db.state.equals("Student")) { // функция для взятия значения
//            long chatID = update.getCallbackQuery().getMessage().getChatId();
//            db.changedLvl = update.getCallbackQuery().getData();
//            String text = "";
//            try {
//                studentLogics(update, chatID, db.minNumber, db.maxNumber, db.maxSmallNumber, text);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        if (update.hasMessage() && db.state.equals("Student")) {
//            // continue this <--------
//            String message = update.getMessage().getText();
//            long chatID = update.getMessage().getChatId();
//            String text;
//            if (message.equals(db.answer)) {
//                text = "Correctly answer!\nGo to the next one!";
//                switch (db.changedLvl) {
//                    case "lvl0" -> db.totalScore += 1;
//                    case "lvl1" -> db.totalScore += 2;
//                    case "lvl2" -> db.totalScore += 4;
//                    case "lvl3" -> db.totalScore += 8;
//                }
//            } else if (message.equals("<--")) {
//                text = "none";
//                try {
//                    execute(new Keyboards().getStudentKeyboard(chatID));
//                } catch (TelegramApiException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                text = "Incorrectly answer...\nTrue answer is " + db.answer + "\nGo to the next one.";
//            }
//            try {
//                studentLogics(update, chatID, db.minNumber, db.maxNumber, db.maxSmallNumber, text);
//            } catch (TelegramApiException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
////        if (update.hasMessage()) {
////            teacherLogics(update);
////        }
//
//
//    private void studentLogics(Update update, long chatID, int minNumber, int maxNumber, int maxSmallNumber, String text) throws TelegramApiException {
//        BotLogics botLogics = new BotLogics();
//        DBHandler db = new DBHandler(update.getUpdateId());
//        if (!text.equals("none")) {
//            switch (db.changedLvl) {
//                case "lvl0" -> {
//                    String[] mathExercise = botLogics.lvl0(minNumber, maxNumber);
//                    db.answer = mathExercise[1];
//                    execute(new Keyboards().getAnswersKeyboard(chatID, mathExercise, maxNumber - minNumber, text));
//
//                }
//                case "lvl1" -> {
//                    String[] mathExercise = botLogics.lvl1(minNumber, maxNumber, maxSmallNumber);
//                    db.answer = mathExercise[1];
//                    execute(new Keyboards().getAnswersKeyboard(chatID, mathExercise, maxNumber - minNumber, text));
//
//                }
//                case "lvl2" -> {
//                    String[] mathExercise = botLogics.lvl2(minNumber, maxNumber, maxSmallNumber);
//                    db.answer = mathExercise[1];
//                    execute(new Keyboards().getAnswersKeyboard(chatID, mathExercise, maxNumber - minNumber, text));
//
//                }
//                case "lvl3" -> {
//                    String[] mathExercise = botLogics.lvl3(minNumber, maxNumber, maxSmallNumber);
//                    db.answer = mathExercise[1];
//                    execute(new Keyboards().getAnswersKeyboard(chatID, mathExercise, maxNumber - minNumber, text));
//
//                }
//                case "Bag" -> {
//                    SendMessage sendMessage = new SendMessage();
//                    sendMessage.setChatId(chatID);
//                    sendMessage.setText("Total score is " + db.totalScore);
//                    execute(sendMessage);
//                }
//            }
//        }
//    }
}
