package commands;

import client.ClientReceiver;
import interaction.Request;
import sub.AbstractCommand;

public abstract class ClientCommand extends AbstractCommand<String, Request> {

        private final ClientReceiver clientReceiver;

        public ClientCommand(ClientReceiver clientReceiver) {
                this.clientReceiver = clientReceiver;
        }

        @Override
        public String getHelp() {
                return "";
        }
}
