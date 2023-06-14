package org.example.Cripto;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.List;

public class CryptoBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            // создание объекта класса SendMessage
            SendMessage message = new SendMessage();
            // оператор вывода
            if (messageText.equals("/start")) {
                // проверка условий
                message.setChatId(chatId);
                message.setText("Нажмите на кнопоку");

                // Создаем клавиатуру
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                keyboardMarkup.setSelective(true);
                keyboardMarkup.setResizeKeyboard(true);
                keyboardMarkup.setOneTimeKeyboard(false);

                // Создаем кнопки
                KeyboardButton button1 = new KeyboardButton();
                button1.setText("Крипто топ-50");

                // Создаем ряды кнопок
                KeyboardRow row1 = new KeyboardRow();
                row1.add(button1);

                // Добавляем ряды кнопок в клавиатуру
                keyboardMarkup.setKeyboard(List.of(row1));

                // Устанавливаем клавиатуру в сообщение
                message.setReplyMarkup(keyboardMarkup);
                // обработка исключений
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            // проверка условий нажата ли кнопка
            String out;
            if (messageText.equals("Крипто топ-50")){
                Wither cripto = new Wither();
                out = cripto.witherNow();
                message.setChatId(chatId);
                message.setText(out);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();

                }

            }
            //вывод инфы в консоль
            System.out.println("id: " + update.getMessage().getChatId().toString()
                    + "\n"
                    + "massage: " + update.getMessage().getText());
        }
    }
    @Override
    public String getBotUsername() {
        return "MayCriptorBot";
    }

    @Override
    public String getBotToken() {
        return "6218823980:AAHo3gdFk5bnCMwoDtO91_2iNDhlhV7c9hE";
    }

}
