package client;

import input.InputArgumentTester;
import moviemaking.AddMovie;

public class ClientReceiver {

      private final AddMovie makeMovie;

    public ClientReceiver() {
        this.makeMovie = new AddMovie(new InputArgumentTester());
    }
}
