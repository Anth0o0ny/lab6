package client;

import commands.*;
import interaction.Request;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Optional;

import static sub.CommandsEnum.values;

public class ClientInvoker {
    private final HashMap<String, ClientCommand> commandsMap = new HashMap<>();

    public void registration(String commandName, ClientCommand command) {
        commandsMap.put(commandName, command);
    }

    public Optional<Request> check(String commandName, String argument) throws JAXBException {
        if (this.commandsMap.containsKey(commandName))
            return this.commandsMap.get(commandName).execute(argument);
        //System.out.println(RB.getString("badCommand"));
        // return StringConstants.PatternCommands.INVOKER_WRONG_COMMAND;
//        return Optional.empty();

        return null;
    }

    public HashMap<String, ClientCommand> getCommandMap() {
        return this.commandsMap;
    }

    public ClientInvoker(ClientReceiver clientReceiver){

        for (CommandsEnum commandsEnum : values()){
            Optional<ClientCommand> optional = create(clientReceiver, commandsEnum);
            optional.ifPresent(command -> registration(commandsEnum.commandName, command));
        }
    }

    private Optional<ClientCommand> create(ClientReceiver clientReceiver, CommandsEnum commandsEnum) {
        switch (commandsEnum){
            case HELP:
                return Optional.of(new Help(clientReceiver));
            case INFO:
                return Optional.of(new Info(clientReceiver));
//            case SHOW:
//                return (new Show(receiver));
//            case ADD:
//                return (new Add(receiver));
//            case UPDATE:
//                return (new UpdateById(receiver));
//            case REMOVE_BY_ID:
//                return (new RemoveById(receiver));
//            case CLEAR:
//                return (new Clear(receiver));
//            case SAVE:
//                return (new Save(receiver));
//            case EXECUTE_SCRIPT:
//                return (new ExecuteScript(receiver));
//            case EXIT:
//                return (new Exit(receiver));
//            case INSERT_AT:
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
