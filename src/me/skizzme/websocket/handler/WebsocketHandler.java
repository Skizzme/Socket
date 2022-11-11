package me.skizzme.websocket.handler;

import me.skizzme.websocket.Websocket;

public abstract class WebsocketHandler {

    protected Websocket socket;

    public abstract void handleIncoming(String data);

    public void setSocket(Websocket socket) {
        this.socket = socket;
    }

}
