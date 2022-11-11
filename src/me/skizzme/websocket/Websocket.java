package me.skizzme.websocket;

import me.skizzme.websocket.exception.NotConnectedException;
import me.skizzme.websocket.handler.WebsocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Websocket {

    private WebsocketHandler handler;
    private OutputStream outgoing;
    private BufferedReader incoming;
    private Socket socket;

    private byte[] body_buffer, header_buffer;
    private long expected_length;

    public Websocket(WebsocketHandler handler) {
        this.handler = handler;
    }

    public boolean connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.outgoing = this.socket.getOutputStream();
            this.incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void receiver() throws IOException {
        if (this.socket == null) {
            return;
        }
        while (this.socket.isConnected()) {
            try {
                byte[] header = new byte[8];
                for (int i = 0; i < 8; i++) {
                    header[i] = (byte) this.incoming.read();
                }
                char[] in = new char[(int) ByteBuffer.wrap(header).getLong()];
                this.incoming.read(in);
                this.handler.handleIncoming(String.valueOf(in));
            }
            catch (SocketException e) {
                this.socket.close();
                break;
            }
        }
    }

    public void send(String data) throws NotConnectedException, IOException {
        if (this.socket == null || !this.socket.isConnected()) {
            throw new NotConnectedException("Tried to send data without being connected.");
        }
        byte[] b_data = data.getBytes(StandardCharsets.UTF_8);
        byte[] header = ByteBuffer.allocate(8).putLong(
                b_data.length).array();
        byte[] out = new byte[8+b_data.length];
        System.arraycopy(header, 0, out, 0, header.length);
        System.arraycopy(b_data, 0, out, header.length, b_data.length);
        outgoing.write(out);
    }

}
