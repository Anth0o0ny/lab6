package server;

import baseclasses.MoviesCollection;
import interaction.Request;
import interaction.Response;
import parse.Parser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class ServerMain {

    private static final ServerReceiver serverReceiver = new ServerReceiver();
    ////Exception in thread "main" java.lang.ExceptionInInitializerError
    //Caused by: java.lang.NullPointerException: Cannot invoke "java.util.Optional.ifPresent(java.util.function.Consumer)" because "optional" is null
    //	at server.ServerInvoker.<init>(ServerInvoker.java:25)
    //	at server.ServerMain.<clinit>(ServerMain.java:16)
    private static final ServerInvoker serverInvoker = new ServerInvoker(serverReceiver);

    public static void main(String[] args) throws IOException, JAXBException {

        MoviesCollection mc = new MoviesCollection();
        String pathToFile = System.getenv("pathToFile");
        Parser.parsingToObj(mc.getCollection(), pathToFile);
        Server server = new Server();

        while (true) {
            server.getSelector().select(3000);
            Set<SelectionKey> keys = server.getSelector().selectedKeys();
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = (SelectionKey) iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    server.register();
                    System.out.println(key);
                } else if (key.isReadable()) {
                    Request request = server.readRequest(key);
                    if (request != null) {
                        Optional<Response> optionalResponse = serverInvoker.execute(request);
                        System.out.println("Готов ли ответ: ");
                        System.out.println(optionalResponse.isPresent());
                        if (optionalResponse.isPresent()) {
                            Response response = optionalResponse.get();
                            server.sendResponse(response, key);
                            System.out.println(" теоретически выполнилась");
                        }
                    }
                }
            }
        }
    }
}
