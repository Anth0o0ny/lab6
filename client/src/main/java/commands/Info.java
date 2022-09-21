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

public class Info implements ClientCommand {

    private final Receiver receiver;

    public Info(Receiver receiver) {
        this.receiver = receiver;
    }


    @Override
    public String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
        if (argument.isEmpty()) {
            return receiver.info(collection);
        } else {
            return StringConstants.Commands.CMD_WITHOUT_ARG;
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.INFO.commandName + " : " + StringConstants.Commands.INFO_HELP;
    }
}
