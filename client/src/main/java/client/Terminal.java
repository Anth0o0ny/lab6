package client;

import baseclasses.MoviesCollection;
import interaction.Request;
import interaction.Response;
import sub.StringConstants;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;


public class Terminal {

    Scanner scanner;
    private final ClientInvoker clientInvoker;
    private final Client client;

    private String output;

    public Terminal(ClientInvoker clientInvoker, Client client) {
        this.clientInvoker = clientInvoker;
        this.client = client;
    }

//    public String startFile(String argument) throws FileNotFoundException {
//
//        String pathToFile = new File(argument).getAbsolutePath();
//        File file = new File(pathToFile);
//        this.scanner = new Scanner(file);
//
//        while (scanner.hasNextLine()) {
//            String commandLine = scanner.nextLine();
//            try {
//                output = lineParseToCommand(commandLine);
//                if (output == null) {
//                    break;
//                }
//                System.out.println(output);
//            } catch (JAXBException e) {
//                return StringConstants.StartTreatment.EXECUTE_FAILED;
//            }
//        }
//        return StringConstants.StartTreatment.EXECUTE_ENDED;
//    }

    public void inputKeyboard() throws JAXBException, NoSuchElementException {
        this.scanner = new Scanner(System.in);

        System.out.println(StringConstants.StartTreatment.START_HELPER);

        while (true) {
            System.out.println(StringConstants.StartTreatment.ENTER_COMMAND);
            String commandLine = scanner.nextLine();
            Optional<Request> optionalRequest = lineParseToCommand(commandLine);

            if(optionalRequest.isPresent()){

                Request request = optionalRequest.get();

                if (request.getCommandName().equals("execute_script")){
                    System.out.println("execute");
                    continue;
                }
                client.sendRequest(request);

                Optional<Response> optionalResponse = client.getResponse();
                if (optionalResponse.isPresent()){
                    Response response = optionalResponse.get();
                    responseProcessing(response);
                }
            } else{
                System.out.println(StringConstants.StartTreatment.COMMAND_NOT_EXISTS);
            }

        }
    }

    protected Optional<Request> lineParseToCommand(String line) throws JAXBException {

        String[] cmdline = line.trim().split(" ");
        String command = cmdline[0].trim();
        if (cmdline.length == 1) {
            return clientInvoker.check(command, null);
        } else if (cmdline.length == 2) {
            return clientInvoker.check(command, cmdline[1]);
        } else {
            return Optional.empty();
        }
    }

    private void responseProcessing(Response response) {
        if (response.getAnswer() == null) {
            System.out.println(response.getMessage());
        } else {
            for (String ans : response.getAnswer()) {
                System.out.println(ans);
            }
        }
    }
}
