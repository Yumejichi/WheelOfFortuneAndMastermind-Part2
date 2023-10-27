import java.util.Objects;

/**
 * this class is for a NormalBotPlayer to play the game
 */
public class UnintelligentBotPlayer implements WheelOfFortunePlayer{

    private int nextLetterNumber = 0;
    @Override
    /**
     * get the next guess from the player
     * @param hiddenPhrase
     * @param previousGuesses
     * @return the guessed letter by the bot
     */
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses) {
        //loop through a to z

        char letter = (char) ('a'+ nextLetterNumber);
        nextLetterNumber++;
        return letter;
    }

    /**
     * get the player ID
     * @return the player ID
     */
    @Override
    public String playerId() {
        return "UnintelligentBotPlayer";
    }

    /**
     * reset the data member for this player
     */
    @Override
    public void reset() {
        nextLetterNumber=0;
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "UnintelligentBotPlayer{" +
                "nextLetterNumber=" + nextLetterNumber +
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
        UnintelligentBotPlayer that = (UnintelligentBotPlayer) o;
        return nextLetterNumber == that.nextLetterNumber;
    }

}
