import java.util.Objects;

/**
 * this class is for a NormalBotPlayer to play the game
 */
public class NormalBotPlayer implements WheelOfFortunePlayer{
    private int letterIndex = 0;
    @Override
    /**
     * get the next guess from the player
     * @param hiddenPhrase
     * @param previousGuesses
     * @return the guessed letter by the bot
     */
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses) {

        char botGuess;
        //use a StringBuilder to store the most frequent used English words found in the internet as below:
        //https://stancarey.wordpress.com/2013/01/07/etaoin-srhldcu-or-what-are-the-most-common-words-and-letters-in-english/
        StringBuilder mostFrequentlyUsedLetters = new StringBuilder("etaoinsrhldcumfpgwybvkxjqz");

        botGuess = mostFrequentlyUsedLetters.charAt(letterIndex);
        letterIndex++;
        return botGuess;
    }

    /**
     * get the player ID
     * @return the player ID
     */
    @Override
    public String playerId() {
        return "NormalBotPlayer";
    }

    /**
     * reset the data member for this player
     */
    @Override
    public void reset() {
        letterIndex=0;
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "NormalBotPlayer{" +
                "letterIndex=" + letterIndex +
                '}';
    }


    /**
     * The equals method implements an equivalence relation on non-null object references.
     * @param o obj to compare
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NormalBotPlayer that = (NormalBotPlayer) o;
        return letterIndex == that.letterIndex;
    }



}
