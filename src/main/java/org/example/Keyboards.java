package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

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
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton lvl0 = new InlineKeyboardButton();
        InlineKeyboardButton lvl1 = new InlineKeyboardButton();
        InlineKeyboardButton lvl2 = new InlineKeyboardButton();
        InlineKeyboardButton lvl3 = new InlineKeyboardButton();
        InlineKeyboardButton inventory = new InlineKeyboardButton();

        lvl0.setText("Уровень: 0");
        lvl1.setText("Уровень: 1");
        lvl2.setText("Уровень: 2");
        lvl3.setText("Уровень: 3");
        inventory.setText("Сумка");

        lvl0.setCallbackData("lvl0");
        lvl1.setCallbackData("lvl1");
        lvl2.setCallbackData("lvl2");
        lvl3.setCallbackData("lvl3");
        inventory.setCallbackData("inventory");

        List<InlineKeyboardButton> keyboardButtonRow1 = new ArrayList<>();
        keyboardButtonRow1.add(lvl0);
        keyboardButtonRow1.add(lvl1);

        List<InlineKeyboardButton> keyboardButtonRow2 = new ArrayList<>();
        keyboardButtonRow2.add(lvl2);
        keyboardButtonRow2.add(lvl3);

        List<InlineKeyboardButton> keyboardButtonRow3 = new ArrayList<>();
        keyboardButtonRow3.add(inventory);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
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
