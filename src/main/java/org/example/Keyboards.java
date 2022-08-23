package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Keyboards {
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
    @SneakyThrows
    public SendMessage getInlineStartKeyboard(long chatID) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton buttonTeacher = new InlineKeyboardButton();
        InlineKeyboardButton buttonStudent = new InlineKeyboardButton();

        buttonTeacher.setText("Учитель");
        buttonStudent.setText("Ученик");

        buttonTeacher.setCallbackData("buttonTeacher");
        buttonStudent.setCallbackData("buttonStudent");

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add(buttonTeacher);

        List<InlineKeyboardButton> keyboardButtonRow2 = new ArrayList<>();
        keyboardButtonRow2.add(buttonStudent);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonRow1);
        rowList.add(keyboardButtonRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText(property().getProperty("StartText"));
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }

    public SendMessage getInlineStudentKeyboard(long chatID) {
        ReplyKeyboardMarkup inlineKeyboardMarkup = new ReplyKeyboardMarkup();

        KeyboardButton lvl0 = new KeyboardButton();
        KeyboardButton lvl1 = new KeyboardButton();
        KeyboardButton lvl2 = new KeyboardButton();
        KeyboardButton lvl3 = new KeyboardButton();
        KeyboardButton inventory = new KeyboardButton();

        lvl0.setText("lvl0");
        lvl1.setText("lvl1");
        lvl2.setText("lvl2");
        lvl3.setText("lvl3");
        inventory.setText("Bag");

        KeyboardRow keyboardButtonRow1 = new KeyboardRow();
        keyboardButtonRow1.add(lvl0);
        keyboardButtonRow1.add(lvl1);
        KeyboardRow keyboardButtonRow2 = new KeyboardRow();
        keyboardButtonRow2.add(lvl2);
        keyboardButtonRow2.add(lvl3);
        KeyboardRow keyboardButtonRow3 = new KeyboardRow();
        keyboardButtonRow3.add(inventory);

        List<KeyboardRow> rowList = new ArrayList<>();
        rowList.add(keyboardButtonRow1);
        rowList.add(keyboardButtonRow2);
        rowList.add(keyboardButtonRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText("-");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
