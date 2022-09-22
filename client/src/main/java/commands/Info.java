package commands;


import interaction.Request;
import client.ClientReceiver;
import sub.StringConstants;
import sub.CommandsEnum;

import javax.xml.bind.JAXBException;
import java.util.Optional;

public class Info extends ClientCommand {


    public Info(ClientReceiver clientReceiver) {
        super(clientReceiver);
    }


    @Override
    public Optional<Request> execute(String argument) {
        if (argument != null) {
//            return StringConstants.Commands.CMD_WITHOUT_ARG;
            return Optional.empty();
        } else {
            return Optional.of(new Request("info"));
        }
    }

    @Override
    public String getHelp() {
        return CommandsEnum.INFO.commandName + " : " + StringConstants.Commands.INFO_HELP;
    }
}
