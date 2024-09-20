package com.cooltur.cooltur.domain.chat;

import com.cooltur.cooltur.infra.apis.ChatGPT;
import com.cooltur.cooltur.infra.exceptions.InvalidChatMessageError;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
public class Chat {

    @Autowired
    private ChatGPT chatGPT;

    public ChatRespuesta querry(ChatBody chatBody) {
        validate(chatBody);
        //Preguntar a chatGpt
        return askGPT(chatBody.body());
    }

    private void validate(ChatBody chatBody) {
        String input = chatBody.body().toLowerCase(Locale.ROOT);

        List<String> lista =  Arrays.asList(
                "Tu\\s*puta\\s*madre","Hijo\\s*de\\s*puta","Hijo\\s*de\\s*perra","Puta","Zorra","Vete\\s*al\\s*(diablo|demonio)","Que\\s*te\\s*jodan","Lameculos");
        List<String> palabrasEnMinusculas = lista.stream()
                .map(String::toLowerCase)
                .toList();
        StringBuilder searchBuilder = new StringBuilder("(");
        for(int i = 0;i  < lista.size() ; i++){
            if(i ==  lista.size()-1) searchBuilder.append(lista.get(i));
            else searchBuilder.append(lista.get(i)).append("|");
        }
        searchBuilder.append(")");
        String regex = "\\b" + searchBuilder + "\\b";
        System.out.printf("Hello and welcome!");

        //if operaciones de validacion
        // return else
        if(!true)
            throw new InvalidChatMessageError("Mensaje Inapropiado");
    }

    private ChatRespuesta askGPT(String prompt) {
        try {
            OkHttpClient client = new OkHttpClient();

            String API_URL = "https://api.openai.com/v1/completions";
            String apiKey = chatGPT.getChatGPTAPI();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType,
                            "{" +
                                    "\"prompt\": \"" + prompt + "\"," +
                                    "\"model\": \"gpt-3.5-turbo\", " +
                                    "\"max_tokens\": 100" +
                                    "}");

            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("Authorization", "Bearer " + apiKey)
                    .addHeader("Content-Type", "application/json")
                    .post(body)
                    .build();

            System.out.println(request);

            Response response = client.newCall(request).execute();

            String responseBody = response.body().string();
            System.out.println(responseBody);

            ChatRespuesta respuesta = new ChatRespuesta(responseBody);
            System.out.println(respuesta);

            return respuesta;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
