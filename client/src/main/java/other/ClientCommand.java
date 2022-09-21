package other;

import baseclasses.Movie;
import baseclasses.MoviesCollection;

import javax.xml.bind.JAXBException;
import java.util.Stack;

public interface ClientCommand {

        String execute(ClientInvoker invoker, Stack<Movie> collection, String argument, MoviesCollection moviesCollection) throws JAXBException;

        String getHelp();

}
