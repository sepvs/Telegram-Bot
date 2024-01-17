package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static es.codegym.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "TheAngryCatBot";
    public static final String TOKEN = "6719377951:AAH9VQQOicGpBrmd1dzA8yoMHeq74zOJoBo";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        // TODO: escribiremos la funcionalidad principal del bot aquí
        setUserGlory(0);
        if (getMessageText().equals("/start")) {
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hackear la nevera", "step_1_btn" , "Pegarle con la pata a la puerta", "step_1_wrng_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("¡Tomar una salchicha! +20 de fama", "step_2_btn" ,
                            "¡Tomar un pescado! +20 de fama", "step_2_btn",
                            "¡Tirar una lata de pepinillos! +20 de fama","step_2_btn"));
        } else if (getCallbackQueryButtonKey().equals("step_1_wrng_btn")){
            substractUserGlory(20);
            sendTextMessageAsync("La nevera ni se inmuta y sus puertas se mantienen cerradas. Tu pereza no te llevará a ningún lado felino. -20 de fama. Acumulado: " + getUserGlory(), Map.of("Seguir adelante", "wrng_ans_step_1"));
        }
        if (getCallbackQueryButtonKey().equals("step_2_btn") || getCallbackQueryButtonKey().equals("wrng_ans_step_1")){
            sendPhotoMessageAsync("step_3_pic");
            sendTextMessageAsync(STEP_3_TEXT,
                    Map.of("Hackear al robot aspiradora", ("step_3_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT + "Acumulado: " + getUserGlory() + " Elige tu recompensa:",
                    Map.of("Enviar al robot aspirador a por comida! +30 de fama", ("step_4_btn"),
                            "Dar un paseo en el robot aspirador! +30 de fama", ("step_4_btn"),
                            "¡Huir del robot aspirador! +30 de fama", ("step_4_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_5_pic");
            sendTextMessageAsync(STEP_5_TEXT,
                    Map.of("Encender y ponerse la GoPro!", ("step_5_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic");
            sendTextMessageAsync(STEP_6_TEXT +  "_Acumulado:_ " + getUserGlory() + "Elige tu recompensa:",
                    Map.of("¡Correr por los tejados, grabar con la GoPro! +40 de fama!", ("step_6_btn"),
                            "¡Atacar a otros gatos desde tu escondite con la GoPro! +40 de fama!", ("step_6_btn"),
                            "¡Atacar a los perros desde tu escondite con la GoPro! +40 de fama!", ("step_6_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_7_pic");
            sendTextMessageAsync(STEP_7_TEXT,
                    Map.of("Hackear la contraseña", ("step_7_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            sendPhotoMessageAsync("step_8_pic");
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("Salir al patio", ("step_8_btn")));
        }
        if (getCallbackQueryButtonKey().equals("step_8_btn")) {
            sendPhotoMessageAsync("final_pic");
            sendTextMessageAsync(FINAL_TEXT);
        }

    }


    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
