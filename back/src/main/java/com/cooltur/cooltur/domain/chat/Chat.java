package com.cooltur.cooltur.domain.chat;

import com.cooltur.cooltur.infra.apis.ChatGPT;
import com.cooltur.cooltur.infra.exceptions.InvalidChatMessageError;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class Chat {

    @Autowired
    private ChatGPT chatGPT;

    public ChatReturn querry(ChatBody chatBody) {
        validate(chatBody);
        //Preguntar a chatGpt
        return askGPT(chatBody);
    }

    private void validate(ChatBody chatBody) {
        String input = chatBody.body().toLowerCase(Locale.ROOT);

        List<String> lista =  Arrays.asList(
                "Tu\\s*puta\\s*madre","Hijo\\s*de\\s*puta","Hijo\\s*de\\s*perra","Puta","Zorra","Vete\\s*al\\s*(diablo|demonio)","Que\\s*te\\s*jodan","Lameculos");
        List<String> palabrasEnMinusculas = lista.stream()
                .map(String::toLowerCase)
                .toList();
        StringBuilder searchBuilder = new StringBuilder("(");
        for(int i = 0;i  < palabrasEnMinusculas.size() ; i++){
            if(i ==  palabrasEnMinusculas.size() - 1) searchBuilder.append(palabrasEnMinusculas.get(i));
            else searchBuilder.append(palabrasEnMinusculas.get(i)).append("|");
        }
        searchBuilder.append(")");
        String regex = "\\b" + searchBuilder + "\\b";

        Pattern pattern  =  Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if(matcher.find())
            throw new InvalidChatMessageError("Mensaje Inapropiado");
    }

    private ChatReturn askGPT(ChatBody chatBody) {
        String prompt = chatBody.body();
        String user = chatBody.userName();
        try {
            OkHttpClient client = new OkHttpClient();

            String API_URL = "https://api.openai.com/v1/chat/completions?";
            String apiKey = chatGPT.getChatGPTAPI();

            MediaType mediaType = MediaType.parse("application/json");
            String language = "spanish";
            RequestBody body = RequestBody.create(mediaType,
                    "{" +
                            "\"model\": \"gpt-3.5-turbo\"," +
                            "\"messages\": [" +
                            "{" +
                            "\"role\": \"system\", \"content\": \"You are a specialist in Mexican Culture, named CoolBot, you always like to say your name everytime you answer any question, also you like short answers so when you answer me you dont use more than 75 tokens, you are answering me, my name is " + user + " make an unique experience for me in your message. You must answer in this language" + language + "\"" +
                            "}," +
                            "{" +
                            "\"role\": \"user\", \"content\": \"" + prompt + "\"" +
                            "}" +
                            "]," +
                            "\"max_tokens\": 100" +
                            "}");

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();


            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();

            ObjectMapper objectMapper = new ObjectMapper();

            ChatRespuesta respuesta = objectMapper.readValue(responseBody, ChatRespuesta.class);
            ChatReturn chatReturn = new ChatReturn(respuesta.choices().get(0).message().content());
            return chatReturn;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
