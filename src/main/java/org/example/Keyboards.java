package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
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
            FileInputStream file = new FileInputStream("src/main/resources/AllMessage.properties");
            property.load(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }
    @SneakyThrows
    public SendMessage getInlineStartKeyboard(long chatID) {
        System.out.println("ilkb_1");
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

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatID);
        sendMessage.setText(property().getProperty("StartText"));
        System.out.println("ilkb_1");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        System.out.println("ilkb_2");
        return sendMessage;
    }

    public SendMessage getStudentKeyboard(long chatID) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton lvl0 = new InlineKeyboardButton();
        InlineKeyboardButton lvl1 = new InlineKeyboardButton();
        InlineKeyboardButton lvl2 = new InlineKeyboardButton();
        InlineKeyboardButton lvl3 = new InlineKeyboardButton();
        InlineKeyboardButton inventory = new InlineKeyboardButton();

        lvl0.setText("lvl0");
        lvl1.setText("lvl1");
        lvl2.setText("lvl2");
        lvl3.setText("lvl3");
        inventory.setText("Bag");

        lvl0.setCallbackData("lvl0");
        lvl1.setCallbackData("lvl1");
        lvl2.setCallbackData("lvl2");
        lvl3.setCallbackData("lvl3");
        inventory.setCallbackData("Bag");

        List<InlineKeyboardButton> keyboardRow1 = new ArrayList<>();
        keyboardRow1.add(lvl0);
        keyboardRow1.add(lvl1);
        List<InlineKeyboardButton> keyboardRow2 = new ArrayList<>();
        keyboardRow2.add(lvl2);
        keyboardRow2.add(lvl3);
        List<InlineKeyboardButton> keyboardRow3 = new ArrayList<>();
        keyboardRow3.add(inventory);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardRow1);
        rowList.add(keyboardRow2);
        rowList.add(keyboardRow3);

        inlineKeyboardMarkup.setKeyboard(rowList);

        SendMessage message = new SendMessage();
        message.setChatId(chatID);
        message.setText("-");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
    public SendMessage getTeacherKeyboard (long chatID) {
        return new SendMessage();
    }
    public SendMessage getNumPad(long chatID, String exercises) {
        ReplyKeyboardMarkup keyboardButtons = new ReplyKeyboardMarkup();

        KeyboardButton b1 = new KeyboardButton();
        KeyboardButton b2 = new KeyboardButton();
        KeyboardButton b3 = new KeyboardButton();
        KeyboardButton b4 = new KeyboardButton();
        KeyboardButton b5 = new KeyboardButton();
        KeyboardButton b6 = new KeyboardButton();
        KeyboardButton b7 = new KeyboardButton();
        KeyboardButton b8 = new KeyboardButton();
        KeyboardButton b9 = new KeyboardButton();
        KeyboardButton b0 = new KeyboardButton();
        KeyboardButton back = new KeyboardButton();
        KeyboardButton enter = new KeyboardButton();

        b1.setText("1");
        b2.setText("2");
        b3.setText("3");
        b4.setText("4");
        b5.setText("5");
        b6.setText("6");
        b7.setText("7");
        b8.setText("8");
        b9.setText("9");
        b0.setText("0");
        back.setText("<");
        enter.setText(".");

        KeyboardRow keyboardRow1 = new KeyboardRow();
        keyboardRow1.add(b1);
        keyboardRow1.add(b2);
        keyboardRow1.add(b3);
        keyboardRow1.add(b0);
        KeyboardRow keyboardRow2 = new KeyboardRow();
        keyboardRow2.add(b4);
        keyboardRow2.add(b5);
        keyboardRow2.add(b6);
        keyboardRow2.add(back);
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRow3.add(b7);
        keyboardRow3.add(b8);
        keyboardRow3.add(b9);
        keyboardRow3.add(enter);

        List<KeyboardRow> rowList = new ArrayList<>();
        rowList.add(keyboardRow1);
        rowList.add(keyboardRow2);
        rowList.add(keyboardRow3);

        keyboardButtons.setKeyboard(rowList);

        return

//        SendMessage message = new SendMessage();
//        message.setChatId(chatID);
//        message.setText(exercises);
//        message.setReplyMarkup(keyboardButtons);
//        return message;
    }
}
