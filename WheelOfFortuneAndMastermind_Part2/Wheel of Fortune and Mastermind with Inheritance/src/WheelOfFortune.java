import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * class Game will encapsulate the code for looping through
 * a set of games and recording the results.
 */
public abstract class WheelOfFortune extends GuessingGame {
    protected String previousGuesses;

    protected int phraseListSize;

    protected ArrayList<Integer> indexOfPhrase;



    public WheelOfFortune() {
        this.phrase = "";
        this.previousGuesses = "";
        this.indexOfPhrase=new ArrayList<>();
    }

    /**
     * read phrase from a given list.
     */
    public void readPhrases() {
        List<String> phraseList = null;
        // Get the phrase from a file of phrases
        try {
            phraseList = Files.readAllLines(Paths.get("Wheel of Fortune and Mastermind with Inheritance/phrases.txt"));
        } catch (IOException e) {
            System.out.println(e);
        }
        // Get a random phrase from the list
        randomPhrase(phraseList);
    }


    /**
     * get random phrase
     * @param phraseList a list of a string
     */
    public void randomPhrase(List<String> phraseList) {
        // Get a random phrase from the list
        Random rand = new Random();
        phraseListSize=phraseList.size();
        int r = rand.nextInt(phraseListSize); // gets 0, 1, or 2//link to file or something
        this.phrase = phraseList.get(r);
        //phraseList.remove();
    }


    /**
     * Judge whether a letter matches any letter inside the chosen phrase,
     * and modifies the partially hidden phrase if there is a match.
     * @param  phrase original chosen phrase
     * @param  letterInput letter which was entered by player
     * @return matching result (true or false)
     */
    public boolean processGuess(String phrase, char letterInput) {
        StringBuilder originalPhrase = new StringBuilder(phrase);
        //change all letters to small characters.
        String lowercasePhrase = phrase.toLowerCase();

        char lowercaseLetterInput = Character.toLowerCase(letterInput);
        //declare a boolean variable to store the result and initialize with false
        boolean matchResult = false;

        //if the guessed letter is not included in the phrase, set the match result as false.
        if (lowercasePhrase.indexOf(lowercaseLetterInput) == -1) {
            System.out.println("This letter is not in the phrase. You lost 1 chance.");
            matchResult = false;
        } else {
            //when the input letter is new and correct,
            // check the phrase and replace * where matches and set the match result as true
            char ch;
            for (int i = 0; i < phrase.length(); i++) {

                ch = lowercasePhrase.charAt(i);
                //check if the element looking at is a letter
                if (Character.isLetter(ch)) {
                    //check if the guessed letter is right, if so, replace * with the original letter
                    if (ch == lowercaseLetterInput) {
                        (this.hiddenPhrase).setCharAt(i, originalPhrase.charAt(i));
                    }
                }
            }
            matchResult = true;
        }

        //add the guessed letter to previously guesses
        previousGuesses += lowercaseLetterInput;
        return matchResult;
    }

    /**
     * get the guess by the player
     */
    public abstract char getGuess(String previousGuesses);

    public void reset(){
        super.reset();
        this.phrase = "";
        this.previousGuesses = "";
        this.indexOfPhrase=new ArrayList<>();
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "WheelOfFortune{" +
                "phrase='" + phrase + '\'' +
                ", hiddenPhrase=" + hiddenPhrase +
                ", previousGuesses='" + previousGuesses + '\'' +
                ", phraseListSize=" + phraseListSize +
                ", indexOfPhrase=" + indexOfPhrase +
                ", chance=" + chance +
                ", miss=" + miss +
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
        WheelOfFortune that = (WheelOfFortune) o;
        return phraseListSize == that.phraseListSize && chance == that.chance && miss == that.miss  && Objects.equals(phrase, that.phrase) && Objects.equals(hiddenPhrase, that.hiddenPhrase) && Objects.equals(previousGuesses, that.previousGuesses) && Objects.equals(indexOfPhrase, that.indexOfPhrase);
    }

    /**
     * set the instruction for players
     * @param chance chances
     */
    public void instruction(int chance){
        //show instructions
        System.out.println("---------------------------------How to play---------------------------------");
        System.out.println("Please guess letters in the below hidden phrase.");


        //show instructions of how to play
        System.out.println("You will have " + chance + " chances to guess what letters are in the phrase.");
        System.out.println("You will see the phrase with new guessed letter once you enter the correct letter.");
        System.out.println("You will not see the change in the phrase when you enter a wrong letter.");
        System.out.println("When you guess a wrong letter, your will lose one chance.");
        System.out.println("When you guess the same letter you missed before, you will not lose a chance.");
        System.out.println("If you enter more than one letter, only the first letter will be considered.");
        System.out.println("Highest score is " + chance + ".");
        System.out.println("Let's start!");
        System.out.println("-----------------------------------------------------------------------------");

    }

}
