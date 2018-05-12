package akerBot;

import org.telegram.telegrambots.*;
import org.telegram.telegrambots.exceptions.*;

public class Main {
	    public static void main(String[] args) {
	    	ApiContextInitializer.init();

	    	TelegramBotsApi botsApi = new TelegramBotsApi();

	    	try {
	            botsApi.registerBot(new akerBot());
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
}