/**
 * Created by Codin Pangell on 2/23/17.
 */
import java.util.*;
import org.apache.commons.lang3.ArrayUtils;

public class Services {

    //deck is a collection of Strings
    private String[] deck = new String[52];

    //deckMap is a collection of Decks
    private Map<String, String[]> deckMap = new HashMap<String, String[]>();

    public String[] generateNewDeck() {
        Card c = new Card();
        int currentCard = 0;
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                deck[currentCard] = r.toString() + "-" + s.toString();
                currentCard ++;
            }
        }
        return deck;
    }

    public void saveNamedDeck(String deckName, String[] cards) {
        deckMap.put(deckName, cards);
    }

    public Map<String, String[]> listDecks() {
        return deckMap;
    }

    public boolean removeNamedDeck(String deckName) {
        if (getNamedDeck(deckName) != null) {
            deckMap.remove(deckName);
            return true;
        } else {
            return false;
        }
    }

    public boolean shuffleNamedDeck(String deckName) {
        String[] requestedDeck = getNamedDeck(deckName);
        if (requestedDeck != null) {
            //shuffle deck
            List<String> cardAsListArr = Arrays.asList(requestedDeck);
            Collections.shuffle(cardAsListArr);
            //replace existing deck with newly shuffled
            String[] cardArr = cardAsListArr.toArray(new String[0]);
            deckMap.put(deckName, cardArr);
            return true;
        } else {
            return false;
        }
    }

    public boolean complexShuffleNamedDeck(String deckName) {
        String[] requestedDeck = getNamedDeck(deckName);
        if (requestedDeck != null) {
            //run a loop to execute a complex shuffle 5 times.
            String[] source = requestedDeck;
            for (int i = 0; i < 5; i++) {
                System.out.println(i);
                source = getComplexShuffle(source);
            }
            deckMap.put(deckName, source);
            return true;
        }

        return false;
    }

    public String[] getComplexShuffle(String[] source) {
        //make a random cut of the deck
        int randomNumber = (int) (Math.random() * 52);
        int leftDeck = randomNumber;
        int rightDeck = 52 - randomNumber;

        //cut the deck at randomly chosen segments
        String[] firsthalf = Arrays.copyOfRange(source, 0, leftDeck);
        String[] secondhalf = Arrays.copyOfRange(source, leftDeck, source.length);

        //shuffle first half
        List<String> firsthalfAsListArr = Arrays.asList(firsthalf);
        Collections.shuffle(firsthalfAsListArr);

        //shuffle second half
        List<String> secondhalfAsListArr = Arrays.asList(secondhalf);
        Collections.shuffle(secondhalfAsListArr);

        //combine both sides
        String[] combined = ArrayUtils.addAll(firsthalfAsListArr.toArray(new String[0]), secondhalfAsListArr.toArray(new String[0]));
        return combined;
    }

    public String[] getNamedDeck(String deckName) {
        return deckMap.get(deckName);
    }

}
