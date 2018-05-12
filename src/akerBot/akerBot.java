package akerBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.api.methods.groupadministration.KickChatMember;
import org.telegram.telegrambots.api.methods.groupadministration.LeaveChat;
import org.telegram.telegrambots.api.objects.*;
import org.telegram.telegrambots.api.methods.send.*;
import org.telegram.telegrambots.exceptions.*;
import com.vdurmont.emoji.EmojiParser;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;


public class akerBot extends TelegramLongPollingBot{
	
	private YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
        public void initialize(HttpRequest request) throws IOException {
        }
    }).setApplicationName("akerbot").build();
	
	public akerBot(){
		super();
	}
	
	@Override
    public void onUpdateReceived(Update update){
		if (update.hasMessage() && update.getMessage().hasText()){
        	
        	SendMessage message;
        	String command = update.getMessage().getText();
        	message = new SendMessage();
    		message.setChatId(update.getMessage().getChatId());
        
        	switch(command){      	
	        	case "If you're happy and you know it clap your hands": case "if you're happy and you know it clap your hands":
	        	case "if you're happy and you know it, clap your hands": case "If you're happy and you know it, clap your hands":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		message.setText(EmojiParser.parseToUnicode(":clap:") + EmojiParser.parseToUnicode(":clap:"));
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/about": case "/about@mazo_bot":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		message.setText("AkerBot v1.2    Author: TruckerHat\r\n" + 
	        				"\r\n" + 
	        				"--- Version timeline ---\r\n" + 
	        				"         Version 1.21: 12/05/2018\r\n" +
	        				"         --/map now fetches the playermap\r\n" +
	        				"         Version 1.2: 07/05/2018\r\n" +
	        				"         --AkerBot is no longer dead!\r\n" +
	        				"         Version 1.1: 17/04/2017\r\n" + 
	        				"         --/vods now lets you search for MAZO vods!\r\n" + 
	        				"         Version 1.0: 16/04/2017\r\n" + 
	        				"         --AkerBot is born!");
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/twitter": case "/twitter@mazo_bot":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		message.setText("https://twitter.com/mazosmash");
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/stream": case "/stream@mazo_bot":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		message.setText("https://www.smashcast.tv/mazosmash");
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/twitch": case "/twitch@mazo_bot":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		message.setText("https://www.twitch.tv/mazosmash");
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/rank": case "/rank@mazo_bot":
	        		SendPhoto sendPhotoRequest = new SendPhoto();
                    sendPhotoRequest.setChatId(update.getMessage().getChatId());
                    sendPhotoRequest.setNewPhoto(new File("rank.png"));
	        		
	        		try {
	                    sendPhoto(sendPhotoRequest); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/neutra": case "/neutra@mazo_bot":
	        		KickChatMember sendKick = new KickChatMember();
	        		sendKick.setChatId(update.getMessage().getChatId());
	        		sendKick.setUserId(update.getMessage().getFrom().getId());
	        		
	        		try {
	                    kickMember(sendKick); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/coinflip": case "/coinflip@mazo_bot":
	        		message = new SendMessage();
	        		message.setChatId(update.getMessage().getChatId());
	        		Random rand = new Random();
	        		if(rand.nextBoolean())
	        			message.setText("Heads");
	        		else
	        			message.setText("Tails");
	        		
	        		try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException e) {
	                    e.printStackTrace();
	                }
	        		return;
	        	case "/vods@mazo_bot":
        			message = new SendMessage();
        			message.setChatId(update.getMessage().getChatId());
        			message.setText("Enter '/vods <query body>' to search for VoDs");

					try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException d) {
	                    d.printStackTrace();
	                }
					return;
	        	case "/map": case "/map@mazo_bot":
        			message = new SendMessage();
        			message.setChatId(update.getMessage().getChatId());
        			message.setText("https://drive.google.com/open?id=1Liiv88Xzqdtt-647OyCiBH23T1apHd_Q&usp=sharing");

					try {
	                    sendMessage(message); // Call method to send the message
	                } catch (TelegramApiException d) {
	                    d.printStackTrace();
	                }
					return;
	        	default:
	        		if(command.length() > 6 && (command.substring(0, 6)).equals("/vods ")){

	        		        try {
	        		            // This object is used to make YouTube Data API requests. The last
	        		            // argument is required, but since we don't need anything
	        		            // initialized when the HttpRequest is initialized, we override
	        		            // the interface and provide a no-op function.
	        		            

	        		            // Prompt the user to enter a query term.
	        		            String queryTerm = command.substring(6);

	        		            // Define the API request for retrieving search results.
	        		            YouTube.Search.List search = youtube.search().list("id,snippet");

	        		            // Set your developer key from the {{ Google Cloud Console }} for
	        		            // non-authenticated requests. See:
	        		            // {{ https://cloud.google.com/console }}
	        		            String apiKey = "AIzaSyA_1jj95ceUWX1LlXFjxkQrbYfB0yB2j5E";
	        		            search.setKey(apiKey);
	        		            search.setQ(queryTerm);
	        		            search.setChannelId("UCFoJ2u0wCSmlQnxES9Ay61Q");

	        		            // Restrict the search results to only include videos. See:
	        		            // https://developers.google.com/youtube/v3/docs/search/list#type
	        		            search.setType("video");

	        		            // To increase efficiency, only retrieve the fields that the
	        		            // application uses.
	        		            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
	        		            search.setMaxResults((long) 25);

	        		            // Call the API and print results.
	        		            SearchListResponse searchResponse = search.execute();
	        		            List<SearchResult> searchResultList = searchResponse.getItems();
	        		            if (searchResultList == null) {
	        		            	message = new SendMessage();
	        	        			message.setChatId(update.getMessage().getChatId());
	        	        			message.setText("No VoDs found under the query \"" + queryTerm + "\"");

	        	        			
	        						try {
	        		                    sendMessage(message); // Call method to send the message
	        		                } catch (TelegramApiException d) {
	        		                    d.printStackTrace();
	        		                }
	        						return;
	        		            }
	        		            SearchResult singleVideo = searchResultList.get(0);
	        		            
	        	                message = new SendMessage();
	        	                message.setText("https://youtu.be/"+singleVideo.getId().getVideoId());
        	        			message.setChatId(update.getMessage().getChatId());

        						try {
        		                    sendMessage(message); // Call method to send the message
        		                } catch (TelegramApiException d) {
        		                    d.printStackTrace();
        		                }
        						
        						return;
        						
	        		        } catch (GoogleJsonResponseException e) {
	        		            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
	        		                    + e.getDetails().getMessage());
	        		        } catch (IOException e) {
	        		            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
	        		        } catch (Throwable t) {
	        		            t.printStackTrace();
	        		        }
						return;
	        		}
        	}
			return;
		}
    }
	
	public void leaveGroup(long chatId){
		
		LeaveChat l = new LeaveChat();
		l.setChatId(chatId);
		try{
			leaveChat(l);
		}catch (TelegramApiException e){
			return;
		}
		
		return;
	}
	
	@Override
    public String getBotUsername() {
        return "mazo_bot";
    }

    @Override
    public String getBotToken() {
        return "355408227:AAE2Z2I1QEN5wFawZTifP0RVGgxdMXLZwW0";
    }
}
