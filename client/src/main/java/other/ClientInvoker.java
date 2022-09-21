package other;

import baseclasses.Movie;
import baseclasses.MoviesCollection;
import commands.*;
import sub.CommandsEnum;
import sub.StringConstants;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Stack;

import static sub.CommandsEnum.values;

public class ClientInvoker {
    private final HashMap<String, ClientCommand> commandsMap = new HashMap<>();

    public void registration(String commandName, ClientCommand command) {
        commandsMap.put(commandName, command);
    }

    public String execute(ClientInvoker invoker, String commandName, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException {
        if (!commandsMap.containsKey(commandName)) {
            return StringConstants.PatternCommands.INVOKER_WRONG_COMMAND;
        }
        ClientCommand command = commandsMap.get(commandName);
        return command.execute(invoker, collection, argument, moviesCollection);
    }

    public HashMap<String, ClientCommand> getCommandMap() {
        return this.commandsMap;
    }

    public ClientInvoker(Receiver receiver){
        for (CommandsEnum commandsEnum : values()){
            Optional<ClientCommand> optional = Optional.ofNullable(create(receiver, commandsEnum));
            optional.ifPresent(command -> registration(commandsEnum.commandName, command));
        }
    }

    private ClientCommand create(Receiver receiver, CommandsEnum commandsEnum) {
        switch (commandsEnum){
            case HELP:
                return (new Help(receiver));
            case INFO:
                return (new Info(receiver));
            case SHOW:
                return (new Show(receiver));
            case ADD:
//                return (new Add(receiver));
//            case UPDATE:
//                return (new UpdateById(receiver));
//            case REMOVE_BY_ID:
//                return (new RemoveById(receiver));
//            case CLEAR:
//                return (new Clear(receiver));
//            case SAVE:
//                return (new Save(receiver));
            case EXECUTE_SCRIPT:
                return (new ExecuteScript(receiver));
            case EXIT:
                return (new Exit(receiver));
            case INSERT_AT:
//                return (new InsertAt(receiver));
//            case ADD_IF_MIN:
//                return (new AddIfMin(receiver));
//            case SHUFFLE:
//                return (new Shuffle(receiver));
//            case REMOVE_ALL_BY_SCREENWRITER:
//                return (new RemoveAllByScreenwriter(receiver));
//            case GROUP_COUNTING_BY_TAGLINE:
//                return (new GroupCountingByTagline(receiver));
//            case PRINT_DESCENDING:
//                return (new PrintDescending(receiver));
        }
        return null;
    }
}
