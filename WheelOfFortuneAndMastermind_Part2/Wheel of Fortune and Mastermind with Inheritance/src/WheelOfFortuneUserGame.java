import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * WheelOfFortuneUserGame should allow the user to play each game with a random phrase,
 * and if there are more phrases, ask after the game if the player wants to continue.
 * Once a particular phrase is used, it should be discarded from the phrase list so it isn’t chosen again.
 * The class should implement the play() and playNext() methods from Game in order to make these things happen.
 */
public class WheelOfFortuneUserGame extends WheelOfFortune implements WheelOfFortunePlayer{
    private int n;

    @Override
    public GameRecord play() {
        GameRecord record = new GameRecord();

        //if the previous player was not same, set the player id
        if(numbersPlayed==0 || !userAnswer.equals("y")){
            currentPlayer = playerId();
        }
        record.setPlayerId(currentPlayer);

        //get a random phrase
        readPhrases();

        //display the instructions
        if(!userAnswer.equals("y")){
            instruction(chance);
        }

        //replace the phrase with *(get the hidden phrase)
        getHiddenPhrase();

        // define a boolean to record if to continue to guess
        boolean continueGuess = true;

        //loop while the player has more than 0 chances
        while(continueGuess) {

            //declare a boolean variable to store the result and initialize with false
            boolean matchResult = false;

            //get the input letter and convert it to a lowercase letter to use in the following judgement
            //get user guess
            char letterInput = getGuess(previousGuesses);

            //confirm if a letter matches and modify the hidden phrase
            matchResult = processGuess(phrase, letterInput);

            //count misses / remain chances and record the score

            if (matchResult == false) {
                miss++;
            }


            //show the result

            //print out the hidden phrase with correctly guessed letters and the times of misses and left chances.
            System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
            System.out.println("All letters guessed before: " + previousGuesses);
            System.out.println("Status: Miss: " + miss + ", Left chance: " + (chance-miss));
            System.out.println();


            //if all letters are guessed, finish the game
            if (hiddenPhrase.indexOf("*") == -1) {
                System.out.println("Congratulations! You have successfully guessed all letters!\n");
                System.out.println("Your score is "+ (chance-miss)+"\n");
                System.out.println("-----------------------------------------------------------------");
                System.out.println("-----------------------------------------------------------------");
                continueGuess=false;
            }

            //if the player run out all the chances, finish the game
            if (chance-miss == 0) {
                System.out.println("\nI am sorry, you have ran out all of the chances. Try next time.\n");
                System.out.println("Your score is "+ (chance-miss)+"\n");
                continueGuess=false;
            }
            //before ending the play, set records
            //set score as chance - miss
            record.setScore(chance - miss);

        }
        reset();
        return record;
    }

    /**
     * get the next guess
     * @return a letter
     */
    @Override
    public boolean playNext() {
        //if the player wants to end the game then finish
        Scanner scanner = new Scanner(System.in);
        //if it's the first time to play, return true
        if(numbersPlayed==0){
            n=0;
            numbersPlayed++;
            return true;
        }

        //if all phrases are chosen, then finish
        if(numbersPlayed>=phraseListSize){
            System.out.println("All games are played.");
            return false;
        }

        if(numbersPlayed>0) {
            System.out.println("Do you want to continue playing? enter y to continue, enter n to end the game");
            userAnswer = scanner.nextLine();
            if(userAnswer.equals("n")){
                n = numbersPlayed;
                numbersPlayed=0;
                return false;
            }
        }
        numbersPlayed++;
        return true;
    }

    /**
     * pet the guess
     * @param previousGuesses previous guessed letters
     * @return a letter
     */
    @Override
    public char getGuess(String previousGuesses) {
        char letterInput;

        while(true) {
            System.out.println("Guess and enter a letter: ");
            Scanner scanner = new Scanner(System.in);
            String playerInput = scanner.nextLine();

            //If the player enters nothing, let the player enter again
            if(playerInput.length() == 0){
                System.out.println("You entered nothing.");
                System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
                continue;
            }

            //store the first letter entered as player input
            char input = playerInput.charAt(0);

            //if a letter is already guessed, do not count and let the player try again.
            if (previousGuesses.indexOf(input) != -1) {
                System.out.println("You have guessed this letter before. Try another one.");
                System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
                continue;
            }

            //if the input is not a letter, let the player enter again
            if (!Character.isLetter(input)) {
                System.out.println("This is not a letter. Please enter a letter.");
                System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
            }else{
                letterInput = input;
                break;
            }
        }
        return letterInput;
    }

    @Override
    /**
     * get the next guess (but not used in this class)
     * @param hiddenPhrase a hidden phrase
     * @param previousGuesses  previous guessed letters
     * @return a letter
     */
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses){
        return 0;
    }



    /**
     * reset the data members
     */
    @Override
    public void reset() {
        this.phrase = "";
        this.previousGuesses = "";
        this.indexOfPhrase=new ArrayList<>();
        this.chance = 10;
        this.miss = 0;
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "WheelOfFortuneUserGame{" +
                "userAnswer='" + userAnswer + '\'' +
                ", currentPlayer='" + currentPlayer + '\'' +
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
        WheelOfFortuneUserGame that = (WheelOfFortuneUserGame) o;
        return Objects.equals(userAnswer, that.userAnswer) && Objects.equals(currentPlayer, that.currentPlayer);
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
        System.out.println("Let's start!");
        System.out.println("-----------------------------------------------------------------------------");

    }





    /**
     * main method
     * WheelOfFortunedUserGame should allow the user to play until they quit or run out of phrases.
     * Both main’s should demonstrate the methods of GamesRecord to display results.
     */

    public static void main(String [] args) {
        WheelOfFortuneUserGame hangmanUserGame = new WheelOfFortuneUserGame();
        AllGamesRecord record = hangmanUserGame.playAll();
        System.out.println(record);  // or call specific functions of record

        //display highGameList
        int n = 2;
        if(record.allScores.size()>=2){
            System.out.println("highGameList: " + record.highGameList(n));
        }


        //display average of games
        float average = (float)record.average();
        System.out.println("Average score of all games is: "+ average);

    }

}
