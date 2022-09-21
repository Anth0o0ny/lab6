package commands;

import baseclasses.Movie;
import baseclasses.MoviesCollection;
import other.ClientCommand;
import other.ClientInvoker;
import other.Receiver;
import sub.StringConstants;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.util.Stack;

public class Exit implements ClientCommand {
    private final Receiver receiver;

    public Exit(Receiver receiver) {
        this.receiver = receiver;
    }



    @Override
    public String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
        if (argument.isEmpty()) {
            return receiver.exit();
        } else {
            return StringConstants.Commands.CMD_WITHOUT_ARG;
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.EXIT.commandName + " : " + StringConstants.Commands.EXIT_HELP;
    }
}
