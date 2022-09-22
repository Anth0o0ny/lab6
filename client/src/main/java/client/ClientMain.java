package client;

import javax.xml.bind.JAXBException;
import java.util.NoSuchElementException;

public class ClientMain {
    public static void main(String[] args){

        Client client = new Client();
        ClientReceiver clientReceiver = new ClientReceiver();
        ClientInvoker clientInvoker = new ClientInvoker(clientReceiver);
        client.setClient(client);
        client.connect();
        Terminal terminal = new Terminal(clientInvoker, client);
        try {
            terminal.inputKeyboard();
        } catch (JAXBException e) {
            e.printStackTrace();
        }catch (NoSuchElementException ignore){

        }finally {
            System.out.println("Клиент закончил работу.");
        }
        //разобпаться с парсеров и еже с ним классами

    }
}
