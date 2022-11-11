package me.skizzme.websocket.handler.builtins;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.skizzme.websocket.handler.WebsocketHandler;

public abstract class JsonHandler extends WebsocketHandler {

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @Override
    public void handleIncoming(String data) {
        this.handle(gson.fromJson(data, JsonElement.class).getAsJsonObject(), data);
    }

    public abstract void handle(JsonObject obj, String original);

}
