/**
 * Created by Codin Pangell on 2/22/17.
 */
import java.util.Map;
import static spark.Spark.*;
import com.google.gson.Gson;

public class Nike {

    public static void main(String[] args) {

        //Configuration value to allow complex shuffling to be enabled
        boolean COMPLEX_SHUFFLING_ENABLED = true;

        //initiate new deck instance
        Deck deck = new Deck();

        //obtain list of decks
        get("/", (request, response) -> {
            Map<String, String[]> deckMap =  deck.list();
            response.status(200);
            response.type("application/json");
            return toJson(deckMap);
        });

        //get a named deck
        get("/:name", (request, response) -> {
            String name = request.params(":name");
            String[] queriedDeck = deck.lookup(name);
            if (queriedDeck == null) {
               return Nike.notFound("Deck named "+ name + " was not found.", response);
            }
            response.status(200);
            response.type("application/json");
            return toJson(queriedDeck);
        });

        // create named deck
        put("/:name", (request, response) -> {
            try {
                String name = request.params(":name");
                deck.create(name);
            } catch (Exception e) {
                return Nike.handleGenericException(e, response);
            }
            response.status(201);
            response.type("application/json");
            return Nike.Success();
        });

        // delete named deck
        delete("/:name", (request, response) -> {
            try {
                String name = request.params(":name");
                if (deck.destroy((name))) {
                    response.status(204);
                } else {
                    return notFound("Deck named "+ name + " was not found and cannot be removed.", response);
                }
            } catch (Exception e) {
                return Nike.handleGenericException(e, response);
            }
            return Nike.Success();
        });

        //shuffle named deck
        post("/:name", (request, response) -> {
            String name = request.params(":name");
            if (deck.shuffle(name, COMPLEX_SHUFFLING_ENABLED)) {
                response.status(204);
            } else {
                return Nike.notFound("Deck named "+ name + " was not found and cannot be shuffled.", response);
            }
            return Nike.Success();
        });

    }

    public static String handleGenericException(Exception e, spark.Response response) {
        response.status(500);
        response.type("application/json");
        return toJson(e);
    }

    public static String notFound(String msg, spark.Response response) {
        response.status(422);
        //response.body(msg);
        return msg;
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static String Success() {
        return "{\"message\":\"success\"}";
    }



}
