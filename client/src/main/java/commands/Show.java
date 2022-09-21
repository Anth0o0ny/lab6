package commands;



import baseclasses.Movie;
import baseclasses.MoviesCollection;
import other.ClientCommand;
import other.ClientInvoker;
import other.Receiver;
import other.StringConstants;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.util.Stack;

public class Show implements ClientCommand {
    private final Receiver receiver;

    public Show(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
        if (argument.isEmpty()) {
            return receiver.show(collection);
        } else {
            return StringConstants.Commands.CMD_WITHOUT_ARG;
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.SHOW.commandName + " : " + StringConstants.Commands.SHOW_HELP;
    }
}
