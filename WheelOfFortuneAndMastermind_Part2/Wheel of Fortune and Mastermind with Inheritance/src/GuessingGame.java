import java.util.Objects;
import java.util.Scanner;

/**
 * this class contains same parts for all games
 */

public class GuessingGame extends Game{

    protected StringBuilder hiddenPhrase;
    protected int chance;
    protected int miss;

    protected String userAnswer = "";
    protected String currentPlayer = "";

    protected int numbersPlayed = 0;
    protected String phrase;


    public GuessingGame(){
        this.hiddenPhrase=new StringBuilder("");
        this.chance = 10;
        this.miss = 0;
        this.phrase = "";

    }

    /**
     * getHiddenPhrase
     * Generate the initial hidden phrase chosen from the list.
     */
    public void getHiddenPhrase(){
        this.hiddenPhrase = new StringBuilder(phrase);
        //show the hidden phrases with *
        for(int i=0; i<phrase.length(); i++){
            if(Character.isLetter(phrase.charAt(i))) {
                hiddenPhrase.setCharAt(i, '*');
            }
        }
        //print the hidden phrase
        System.out.println(hiddenPhrase);
    }

    /**
     * ger the player ID
     * @return player ID
     */
    @Override
    public String playerId() {
        String plyerId = "";

        //if the player is a user, let the player input an id
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an id for play");
        plyerId = scanner.nextLine();

        return plyerId;
    }

    /**
     * play method
     * @return game record
     */
    @Override
    public GameRecord play() {
        return null;
    }

    /**
     * play next method
     * @return true or false
     */
    @Override
    public boolean playNext() {
        return false;
    }

    /**
     * get next guess
     * @param hiddenPhrase a hidden phrase
     * @param previousGuesses  previous guessed letters
     * @return
     */
    @Override
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses) {
        return 0;
    }


    /**
     * reset
     */
    @Override
    public void reset() {
        this.chance=10;
        this.miss=0;
        userAnswer = "";
    }

    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "GuessingGame{" +
                "hiddenPhrase=" + hiddenPhrase +
                ", chance=" + chance +
                ", miss=" + miss +
                ", userAnswer='" + userAnswer + '\'' +
                ", currentPlayer='" + currentPlayer + '\'' +
                ", numbersPlayed=" + numbersPlayed +
                ", phrase='" + phrase + '\'' +
                ", records=" + records +
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
        if (!super.equals(o)) return false;
        GuessingGame that = (GuessingGame) o;
        return chance == that.chance && miss == that.miss && numbersPlayed == that.numbersPlayed && Objects.equals(hiddenPhrase, that.hiddenPhrase) && Objects.equals(userAnswer, that.userAnswer) && Objects.equals(currentPlayer, that.currentPlayer) && Objects.equals(phrase, that.phrase);
    }

}
