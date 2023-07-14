package timofey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import timofey.handler.CallBackQueryHandler;
import timofey.handler.MessageHandler;

@Component
public class BotConfig extends TelegramLongPollingBot {

    private MessageHandler messageHandler;
    private CallBackQueryHandler callBackQueryHandler;
    private TelegramConfig telegramConfig;


    @Autowired
    public BotConfig(
            TelegramConfig telegramConfig,
            MessageHandler messageHandler,
            CallBackQueryHandler callBackQueryHandler
    ) {
        this.telegramConfig = telegramConfig;
        this.messageHandler = messageHandler;
        this.callBackQueryHandler = callBackQueryHandler;
    }
    @Override
    public String getBotUsername() {
        return telegramConfig.getBotName();
    }
    public String getBotToken(){
        return telegramConfig.getBotToken();
    }

    @Override
    public void onUpdateReceived( Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            messageHandler.setChatId(update.getMessage().getChatId());
            messageHandler.setMessageText(update.getMessage().getText());
            try {
                execute(messageHandler.getSendMessage());

            } catch (TelegramApiException e) {

            }

        }
        else if(update.hasCallbackQuery()){
            CallbackQuery callback = update.getCallbackQuery();
            callBackQueryHandler.setCallbackQuery(callback);


            try {
                execute(callBackQueryHandler.getReplyMessage());

            } catch (TelegramApiException e) {

            }

        }


    }
}