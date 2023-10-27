/**
 * This interface define methods needed by all WheelOfFortunePlayers
 */
public interface WheelOfFortunePlayer {


    /**
     * get the next guess
     * @param hiddenPhrase a hidden phrase
     * @param previousGuesses  previous guessed letters
     * @return a letter
     */
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses);

    /**
     * set an id for the player
     * @return an id for the player
     */
    public String playerId();

    /**
     *  reset the player to start a new game
     */
    public void reset();

}
