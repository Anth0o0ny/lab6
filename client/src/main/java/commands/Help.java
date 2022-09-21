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

public class Help  implements ClientCommand {

    private final Receiver receiver;

    public Help(Receiver receiver) {
        this.receiver = receiver;
    }



    @Override
    public String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
        if (argument.isEmpty()) {
            return receiver.help(invoker);
        } else {
            return StringConstants.Commands.CMD_WITHOUT_ARG;
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.HELP.commandName + " : " + StringConstants.Commands.HELP_HELP;
    }
}
