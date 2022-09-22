package client;

import javax.xml.bind.JAXBException;

public class ClientMain {
    public static void main(String[] args) throws JAXBException {

        System.out.println("client");
        Client client = new Client();
        ClientReceiver clientReceiver = new ClientReceiver();
        ClientInvoker clientInvoker = new ClientInvoker(clientReceiver);
        client.setClient(client);
        client.connect();
        Terminal terminal = new Terminal(clientInvoker, client);
        terminal.inputKeyboard();
        //разобпаться с парсеров и еже с ним классами

    }
}
