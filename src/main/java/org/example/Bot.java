package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Bot extends DefaultAbsSender {
    public Bot (DefaultBotOptions option) {
        super(option);
    }

    @Override
    public String getBotToken() {
        return "5402029894:AAEPajUqXlP-aAHoNORKHjB-x6VKc27m8l4";
    }
    @SneakyThrows
    public static void main (String[] args) {
        Bot bot = new Bot(new DefaultBotOptions());
        Message message = new Message();
//        bot.execute(SendMessage.builder().chatId(message.chat.id).text("hi").build());
    }

}
