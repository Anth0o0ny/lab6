package server;

import interaction.Request;
import interaction.Response;
import sub.StringConstants;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class Server {
//    private final Integer PORT = 8013;
    private static final int BUFFER_SIZE = 1024 * 1024;
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private InputStream inputStream;
    private ByteBuffer buffer;


    public Server() {
        try{
            int PORT = Integer.parseInt(System.getenv("PORT"));
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (ClosedChannelException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Selector getSelector() {
        return selector;
    }


    public void register() {
        try {
            SocketChannel client = serverSocketChannel.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
            System.out.print(StringConstants.Server.CHANNEL_REGISTERED);
        }
        catch (IOException e) {
            System.out.println(StringConstants.Server.CHANNEL_REGISTER_CANCELED);
        }
    }

    public void sendResponse(Response response, SelectionKey key){
        SocketChannel socketChannel = (SocketChannel) key.channel();
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(response);

            socketChannel.write(ByteBuffer.wrap((byteArrayOutputStream.toByteArray())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Request readRequest(SelectionKey key) {
        Request request = null;
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try {
            socketChannel.read(buffer);
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
            ObjectInputStream input = new ObjectInputStream(bais);
            request = (Request) input.readObject();
        }
        catch (IOException e) {
            key.cancel();
        }
        catch (ClassNotFoundException e) {
            System.out.println(StringConstants.Server.READ_REQUEST_FAILED);
        }
        return request;
    }

}
