/**
 * Created by Codin Pangell on 2/23/17.
 */

import java.util.Map;

public class Deck {


    //initiate new services instance
    public Services services = new Services();

    public void create(String name) {
        String[] newDeck = services.generateNewDeck();
        services.saveNamedDeck(name, newDeck);
    }

    public boolean destroy(String name) {
        return services.removeNamedDeck(name);
    }

    public Map<String, String[]> list() {
        return services.listDecks();
    }

    public boolean shuffle(String name, boolean complexShuffle) {
        String[] deck = services.getNamedDeck(name);
        if (deck != null) {
            if (complexShuffle) {
                return services.complexShuffleNamedDeck(name);
            } else {
                return services.shuffleNamedDeck(name);
            }
        } else {
            return false;
        }
    }

    public String[] lookup(String name) {
        String[] deck = services.getNamedDeck(name);
        if (deck != null) {
            return deck;
        }
        return null;
    }

}
