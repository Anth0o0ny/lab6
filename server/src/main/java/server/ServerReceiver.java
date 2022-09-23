package server;

import baseclasses.Movie;
import baseclasses.MoviesCollection;
import commands.ServerCommand;
import interaction.Response;
import moviemaking.AddMovie;
import moviemaking.IdGenerator;
import parse.Parser;
import sub.StringConstants;

import java.util.*;

public class ServerReceiver {

    private IdGenerator idGenerator;
    private final Stack<Movie> collection;
    private final Date creationDate;
    private final MoviesCollection mc;
    private final String pathToFile;

    public ServerReceiver() {

        mc = new MoviesCollection();
        pathToFile = System.getenv("pathToFile");
        Parser.parsingToObj(mc.getCollection(), pathToFile);
        collection = mc.getCollection();
        idGenerator = new IdGenerator(collection);
        creationDate = new Date();
    }

//    public String exit() {
//        System.out.println(StringConstants.PatternCommands.RECEIVER_EXIT_RESULT);
//        return null;
//    }

//    public String help(ServerInvoker invoker) {
//
//
//        return "";
//    }

//    public String clear(Stack<Movie> collection) {
//        collection.clear();
//        return StringConstants.PatternCommands.RECEIVER_CLEAR_RESULT;
//    }
//
//    public String show(Stack<Movie> collection) {
//        if (collection.isEmpty()) {
//            return StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT;
//        } else {
//            StringBuilder stringBuilder = new StringBuilder();
//            for (Movie movie : collection) {
//                stringBuilder.append(movie).append("; ");
//            }
//            return stringBuilder.toString();
//        }
//    }

//    public String info(Stack<Movie> collection) {
//        return StringConstants.PatternCommands.RECEIVER_INFO_TYPE_COLLECTION + collection.getClass() +
//                StringConstants.PatternCommands.RECEIVER_INFO_AMOUNT + collection.size() +
//                StringConstants.PatternCommands.RECEIVER_INFO_INITIALIZATION_DATE + creationDate;
//    }

    public Response info(){
        String[] information = new String[3];
        information[0] = StringConstants.PatternCommands.RECEIVER_INFO_TYPE_COLLECTION  + collection.getClass();
        information[1] = StringConstants.PatternCommands.RECEIVER_INFO_AMOUNT + collection.size();
        information[2] = StringConstants.PatternCommands.RECEIVER_INFO_INITIALIZATION_DATE + creationDate;
        return new Response(information);
    }

    public Response help(Map<String, ServerCommand> commandMap) {
        return new Response(commandMap.values().stream().map(ServerCommand::getHelp).toArray(String[]::new));
    }

    public Response show(){
        return new Response(collection.stream().map(Movie::toString).toArray(String[]::new));
    }

    public Response clear(){
        collection.clear();
        return new Response(StringConstants.PatternCommands.RECEIVER_CLEAR_RESULT);
    }

    public Response shuffle(){
        if (collection.isEmpty()) {
            return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT)   ;
        } else {
            Collections.shuffle(collection);
            StringBuilder stringBuilder = new StringBuilder();
            for (Movie movie : collection) {
                stringBuilder.append(movie).append("; ");
            }
            return new Response(stringBuilder.toString());
        }
    }
    //    public String shuffle(Stack<Movie> collection) {
//        if (collection.isEmpty()) {
//            return StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT;
//        } else {
//            Collections.shuffle(collection);
//            StringBuilder stringBuilder = new StringBuilder();
//            for (Movie movie : collection) {
//                stringBuilder.append(movie).append("; ");
//            }
//            return stringBuilder.toString();
//        }
//    }
//
    public Response printDescending() {
        if (collection.isEmpty()) {
            return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT);
        } else {
            Stack<Movie> cl = new Stack<>();
            cl.addAll(collection);
            Collections.reverse(cl);
            StringBuilder stringBuilder = new StringBuilder();
            for (Movie movie : cl) {
                stringBuilder.append(movie).append("; ");
            }
            return new Response(stringBuilder.toString());
        }
    }
    //
    public Response groupCountingByTagline() {
        if (collection.isEmpty()) {
            return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT);
        } else {
            ArrayList<String> list = new ArrayList<>();
            for (Movie movie : collection) {
                list.add(movie.getTagline());
            }
            StringBuilder stringBuilder = new StringBuilder();
            Set<String> st = new HashSet<>(list);
            for (String s : st)
                stringBuilder.append("\"").append(s).append("\": ").append(Collections.frequency(list, s)).append("\n");

            return new Response(stringBuilder.toString());
        }
    }

    //
    public Response removeById(String argument) {
        String str = "";
        long id;
        try {
            id = Long.parseLong(argument);
        } catch (NumberFormatException e){
            return new Response("Клиент передал невалидный id.");
        }
        if (collection.isEmpty()) {
            return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT);
        } else {
            for (Movie movie : collection) {
                if (String.valueOf(movie.getId()).equals(argument)) {
                    collection.remove(movie);
                    str = StringConstants.PatternCommands.RECEIVER_REMOVE_BY_ID_ACTION + argument + ".";
                    break;

                } else {
                    str = StringConstants.PatternCommands.RECEIVER_REMOVE_BY_ID_WRONG_ACTION;
                }
            }
            return new Response(str);
        }
    }
    //
    public Response removeAllByScreenwriter(String arg) {
        boolean flag = false;
        if (collection.isEmpty()) {
            return new Response(StringConstants.PatternCommands.RECEIVER_EMPTY_COLLECTION_RESULT);
        } else {
            List<Movie> found = new ArrayList<>();
            for (Movie movie : collection) {
                if (String.valueOf(movie.getScreenwriter()).equals(arg)) {
                    System.out.println(movie);
                    found.add(movie);
                    flag = true;
                }
            }
            if (flag) {
                collection.removeAll(found);
                return new Response(StringConstants.PatternCommands.RECEIVER_REMOVE_ALL_BY_SCREENWRITER_RESULT + arg);
            } else {
                return new Response(StringConstants.PatternCommands.RECEIVER_REMOVE_ALL_BY_SCREENWRITER_WROMG_RESULT + arg) ;
            }
        }
    }
    //
    public Response add(Movie movie) {

        long id = idGenerator.generateId();
        movie.setId(id);
        collection.push(movie);
        return new Response("movie was add");
    }

    public Response addIfMin(Movie movie){
        if (movie.compareTo(Collections.min(collection)) < 0) {
            long id = idGenerator.generateId();
            movie.setId(id);
            collection.push(movie);
            return  new Response(StringConstants.MovieMaking.ADD_SUCCESS);
        } else {
            return new Response(StringConstants.MovieMaking.ADD_FAIL);
        }
    }

    public Response update(String arg, Movie movie){
        String str = "";

        for (Movie movie1 : collection) {

            if (String.valueOf(movie1.getId()).equals(arg)) {

                  long id = movie1.getId();

                  movie.setId(id);
                  collection.setElementAt(movie, (collection.size() - collection.search(movie1)));

                  str = StringConstants.PatternCommands.RECEIVER_UPDATE_RESULT + id;

                break;

            } else {

                str = StringConstants.PatternCommands.RECEIVER_UPDATE_WRONG_RESULT;

            }
        } return new Response(str);
    }

    //
//    public String addIfMin(Stack<Movie> collection) {
//        return AddMovie.AddMovieIfMin(collection);
//    }
//
//    public String update(Stack<Movie> collection, String argument){
//        String str = "";
//
//        for (Movie movie : collection) {
//
//            if (String.valueOf(movie.getId()).equals(argument)) {
//
//                  long id = movie.getId();
//                  Movie updateMovie = AddMovie.makeMovie();
//                  updateMovie.setId(id);
//                  collection.setElementAt(updateMovie, (collection.size() - collection.search(movie)));
//
//                  str = StringConstants.PatternCommands.RECEIVER_UPDATE_RESULT + id;
//
//                break;
//
//            } else {
//
//                str = StringConstants.PatternCommands.RECEIVER_UPDATE_WRONG_RESULT;
//
//            }
//        } return str;
//    }
    public Response insertAt(String argument, Movie movie){

        String str;
        int index = Integer.parseInt(argument);
        if (index < 0 ){
            str = StringConstants.PatternCommands.RECEIVER_INSERT_AT_WRONG_RESULT;
        }else{
            if ((collection.size() - index > 0)){
                long id = idGenerator.generateId();
                movie.setId(id);
                collection.insertElementAt(movie,index);

                str = StringConstants.PatternCommands.RECEIVER_INSERT_AT_RESULT;
            } else{
                str = StringConstants.PatternCommands.RECEIVER_INSERT_AT_WRONG_RESULT;
            }

        }
        return new Response(str);
    }
//    public String save(MoviesCollection moviesCollection){
//        Parser.parsingToXml(moviesCollection);
//        return StringConstants.PatternCommands.RECEIVER_SAVE_RESULT;
//    }
//
//    public String executeScript(ClientInvoker invoker, MoviesCollection moviesCollection, String argument) throws FileNotFoundException, FileNotFoundException {
//        Terminal inp = new Terminal(invoker, moviesCollection);
//        return inp.startFile(argument);
//    }
}





