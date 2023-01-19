
import java.io.IOException;
import java.net.URI;
//import java.util.*;


class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String test= "test";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            //return String.format("Taylin's string: %d",test);
            String newTest= String.format("taylin's string: %8s",test);
            return newTest;
        } else if (url.getPath().equals("/addA")) {
            test=test+"A";
            return String.format("Letter A added! %8s",test);
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("string")) {
                    for (int i=0; i<parameters.length; i++){
                    test=test+parameters[i];
                    }
                    return String.format("Your new String list is: %8s", test);
                }
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}


