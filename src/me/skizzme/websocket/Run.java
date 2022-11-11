package me.skizzme.websocket;

import com.google.gson.JsonObject;
import me.skizzme.websocket.exception.NotConnectedException;
import me.skizzme.websocket.handler.WebsocketHandler;
import me.skizzme.websocket.handler.builtins.JsonHandler;

import java.io.IOException;
import java.util.logging.Handler;

public class Run {

    public static void main(String[] args) {
        Websocket socket = new Websocket(new JsonHandler() {
            @Override
            public void handle(JsonObject obj, String original) {
                System.out.println(original);
            }
        });
        socket.connect("localhost", 10020);
        try {
            socket.send("test");
            socket.receiver();
        } catch (NotConnectedException | IOException e) {
            e.printStackTrace();
        }
    }

}
