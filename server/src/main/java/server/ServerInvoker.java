package server;

import commands.Info;
import commands.ServerCommand;
import interaction.Request;
import interaction.Response;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Optional;

import static sub.CommandsEnum.values;

public class ServerInvoker {
    private final HashMap<String, ServerCommand> commandsMap = new HashMap<>();

    public void registration(String commandName, ServerCommand command) {
        commandsMap.put(commandName, command);
    }

    public ServerInvoker(ServerReceiver serverReceiver){
        for (CommandsEnum commandsEnum : values()){
            Optional<ServerCommand> optional = create(serverReceiver, commandsEnum);
            optional.ifPresent(command -> registration(commandsEnum.commandName, command));
        }
    }

    public Optional<Response> execute(Request request) throws JAXBException {
        String commandName = request.getCommandName();
//      УСЛОВИЕ О ТОМ, ЧТО КОННЕКТ НЕ ПРОШЕЛ
//        if (this.commandsMap.containsKey(commandName))
//            return this.commandsMap.get(commandName).execute(argument);
        //System.out.println(RB.getString("badCommand"));
        // return StringConstants.PatternCommands.INVOKER_WRONG_COMMAND;
//        return Optional.empty();
        System.out.println(this.commandsMap.get(commandName));
        return this.commandsMap.get(commandName).execute(request);
    }

    public HashMap<String, ServerCommand> getCommandMap() {
        return this.commandsMap;
    }



    private Optional<ServerCommand> create(ServerReceiver serverReceiver, CommandsEnum commandsEnum) {
        switch (commandsEnum){
//            case HELP:
//                return (new Help(receiver));
            case INFO:
                return Optional.of(new Info(serverReceiver));
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
