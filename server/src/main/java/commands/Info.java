package commands;

import interaction.Request;
import interaction.Response;
import server.ServerReceiver;

import java.util.Optional;

public class Info extends ServerCommand {
    public Info(ServerReceiver serverReceiver) {
        super(serverReceiver);
    }

    @Override
    public Optional<Response> execute(Request arg) {
        return Optional.of(serverReceiver.info());
    }

    @Override
    public String getHelp() {
        return null;
    }
}
