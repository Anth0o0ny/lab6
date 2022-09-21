package other;

import baseclasses.MoviesCollection;
import sub.StringConstants;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Terminal {

    Scanner scanner;
    private final ClientInvoker invoker;
    private final MoviesCollection moviesCollection;
    private String output;

    public Terminal(ClientInvoker invoker, MoviesCollection moviesCollection) {
        this.invoker = invoker;
        this.moviesCollection = moviesCollection;
    }

    public String startFile(String argument) throws FileNotFoundException {

        String pathToFile = new File(argument).getAbsolutePath();
        File file = new File(pathToFile);
        this.scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine();
            try {
                output = lineParseToCommand(commandLine);
                if (output == null) {
                    break;
                }
                System.out.println(output);
            } catch (JAXBException e) {
                return StringConstants.StartTreatment.EXECUTE_FAILED;
            }
        }
        return StringConstants.StartTreatment.EXECUTE_ENDED;
    }

    public void inputKeyboard() {
        this.scanner = new Scanner(System.in);

        System.out.println(StringConstants.StartTreatment.START_HELPER);

        while (true) {
            System.out.println(StringConstants.StartTreatment.ENTER_COMMAND);
            String commandLine = scanner.nextLine();
            try {
                output = lineParseToCommand(commandLine);
                if (output == null) {
                    break;
                }
                System.out.println(output);
            } catch (NullPointerException ex) {
                System.out.println(StringConstants.StartTreatment.COMMAND_NOT_EXISTS);
            } catch (JAXBException e) {
                System.out.println(StringConstants.StartTreatment.PARSE_FAILED);
                ;
            }
        }
    }

    protected String lineParseToCommand(String line) throws JAXBException {

        String[] cmdline = line.trim().split(" ");
        String command = cmdline[0].trim();
        if (cmdline.length == 1) {
            output = invoker.execute(invoker, command, moviesCollection.getCollection(), "", moviesCollection);
            return output;
        } else if (cmdline.length == 2) {
            String argument = cmdline[1];
            output = invoker.execute(invoker, command, moviesCollection.getCollection(), argument, moviesCollection);
            return output;
        } else {
            throw new NullPointerException();
        }
    }

}
