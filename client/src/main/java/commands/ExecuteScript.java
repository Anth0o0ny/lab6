package commands;

import baseclasses.Movie;
import baseclasses.MoviesCollection;
import other.ClientCommand;
import other.ClientInvoker;
import other.Receiver;
import sub.StringConstants;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

public class ExecuteScript implements ClientCommand {

    private final Receiver receiver;

    ArrayList<String> files = new ArrayList<>();

    public ExecuteScript(Receiver receiver) {
        this.receiver = receiver;
    }



    @Override
    public String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
       if (argument.isEmpty()) {
            return StringConstants.Commands.EXECUTE_ENTER_FILENAME;
        } else if (new File(argument).isDirectory()) {
            return StringConstants.Commands.EXECUTE_IS_DIRECTORY;
        } else {
            String filePath = new File(argument).getAbsolutePath();
            if (files.contains(filePath)) {
                files.clear();
                return StringConstants.Commands.EXECUTE_RECURSION;
            } else {
                files.add(filePath);
                try {
                    return receiver.executeScript(invoker, moviesCollection, argument);
                } catch (FileNotFoundException ex) {
                    return StringConstants.Commands.EXECUTE_FILE_NOT_EXISTS;
                } finally {
                    files.clear();
                }

            }
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.EXECUTE_SCRIPT.commandName + " file_name : " + StringConstants.Commands.EXECUTE_SCRIPT_HELP;
    }
}
