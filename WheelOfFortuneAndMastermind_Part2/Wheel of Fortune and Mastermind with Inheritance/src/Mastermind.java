import java.util.ArrayList;
import java.util.Scanner;

public class Mastermind extends GuessingGame{

    public static final int CODESIZE = 4;
    private static final String colors = "RGBYOP";


    /**
     * reset the data members
     */
    @Override
    public void reset() {
        this.phrase = "";
        this.chance = 10;
        this.miss = 0;
    }

    /**
     * play method
     * @return game record
     */
    @Override
    public GameRecord play() {
        GameRecord record = new GameRecord();
        MastermindComputerProgram generator = new MastermindComputerProgram();

        Scanner scanner = new Scanner(System.in);

        //if the previous player was not same, set the player id
        if(numbersPlayed==0 || !(userAnswer.equals("y") || userAnswer.equals("Y"))){
            currentPlayer = playerId();
        }
        record.setPlayerId(currentPlayer);

        //get the secret color string
        phrase = generator.generateSecretColors();

        //display the instructions
        if(!(userAnswer.equals("y") || userAnswer.equals("Y"))){
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
            System.out.print("Enter your guess with four letters (choose from R, G, B, Y, O, P): ");
            String playerInput = "";
            boolean isRightLetters = false;
            while(!isRightLetters){
                int flag=0;
                playerInput = scanner.nextLine();
                if(playerInput.length()!=CODESIZE){
                    System.out.println("The input length is wrong, input again please");
                    continue;
                }
                for(int i =0; i<CODESIZE; i++){
                    if(colors.indexOf(playerInput.charAt(i))==-1){
                        System.out.println("There are some letters not in the colors, please input again");
                        flag =1;
                        break;
                    }
                }
                isRightLetters = true;
                if(flag==1){
                    isRightLetters = false;
                }
            }
            //store the player input as uppercase letters
            String playerGuess = playerInput.toUpperCase();

            //confirm if a letter matches and modify the hidden phrase
            matchResult = processGuess(playerGuess);

            //count misses / remain chances and record the score
            if (matchResult == false) {
                miss++;
            }


            //print out the hidden phrase with correctly guessed letters and the times of misses and left chances.

            System.out.println("Status: Miss: " + miss + ", Left chance: " + (chance-miss));
            System.out.println();


            //if all letters are guessed, finish the game
            if (matchResult) {
                System.out.println("Congratulations! You have successfully guessed all letters!\n");
                continueGuess=false;
            }

            //if the player run out all the chances, finish the game
            if (chance-miss == 0) {
                System.out.println("\nI am sorry, you have ran out all of the chances. Try next time.");
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
     * judge if to play next
     * @return true or false
     */
    @Override
    public boolean playNext() {
        //if the player wants to end the game then finish
        Scanner scanner = new Scanner(System.in);
        //if it's the first time to play, return true
        if(numbersPlayed==0){
            numbersPlayed++;
            return true;
        }

        if(numbersPlayed>0) {
            System.out.println("Do you want to continue playing? enter y to continue, enter n to end the game");
            userAnswer = scanner.nextLine();
            if(userAnswer.equals("n")){
                return false;
            }
        }

        numbersPlayed++;
        return true;
    }


    /**
     * show instruction
     * @param chance for players
     */
    public void instruction(int chance){
        //show instructions
        System.out.println("---------------------------------How to play---------------------------------");
        System.out.println("Please guess letters in the below hidden phrase." +
                "(choose from 6 colors: R(Red), G(Green), B(Blue), Y(Yellow), O(Orange), P(Pink)");



        //show instructions of how to play
        System.out.println("You will have " + chance + " chances to guess what colors are hidden.");
        System.out.println("After input your guess, you will be shown how many exact(right place and color) " +
                " and partial(right color in different place) correct answers each time");
        System.out.println("Highest score is " + chance + ".");
        System.out.println("Let's start!");
        System.out.println("-----------------------------------------------------------------------------");

    }



    /**
     * processGuess
     * Judge whether a letter matches any letter inside the chosen phrase,
     * and modifies the partially hidden phrase if there is a match.
     *
     * @param  playerGuess letters which were entered by player
     * @return matching result (true or false)
     */
    public boolean processGuess(String playerGuess) {

        //declare a boolean variable to store the result and initialize with false
        boolean matchResult = false;

        //if the guessed letter is not included in the phrase, set the match result as false.

        //when the input letter is new and correct, check the phrase and replace * where matches and set the match result as true
        char ch;
        StringBuilder secretSB = new StringBuilder(phrase);

        StringBuilder guessSB = new StringBuilder(playerGuess);

        int exacts = checkExacts(secretSB, guessSB);
        int partials = checkPartials(secretSB, guessSB);

        if(exacts==CODESIZE){
            matchResult = true;
        }
        System.out.println("Current result: partials: " + partials + " exacts: " + exacts);
        return matchResult;
    }


    /**
     * check how many partials are in
     * @param secretSB original phrase
     * @param guessSB guessed bu user
     * @return
     */
    public int checkPartials(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;
        int partials = 0;

        while (i<CODESIZE) {
            int j=0;
            while (j<CODESIZE) {
                if (secretSB.charAt(i) == guessSB.charAt(j)) {
                    partials = partials + 1;
                    secretSB.setCharAt(i,'-');
                    guessSB.setCharAt(j,'*');
                }
                j++;
            }
            i++;
        }
        return partials;
    }


    /**
     * check how many exacts are in
     * @param secretSB original phrase
     * @param guessSB guessed bu user
     * @return
     */
    public int checkExacts(StringBuilder secretSB, StringBuilder guessSB) {
        // compare secret to guess
        int i=0;
        int exacts = 0;
        while (i<CODESIZE) {
            if (secretSB.charAt(i) == guessSB.charAt(i)) {
                exacts = exacts + 1;
                secretSB.setCharAt(i, '-');
                guessSB.setCharAt(i,'*');
            }
            i++;
        }
        return exacts;
    }

    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Mastermind{}";
    }

    /**
     * since there is no data member to compare, ther is no equals method here
     */


    public static void main(String[] args) {

        Mastermind game = new Mastermind();
        AllGamesRecord record = game.playAll();

        //display highGameList
        int n =2;
        if(record.allScores.size()>=2){
            System.out.println("highGameList: "+record.highGameList(n));
        }

        //display average of games
        float average = (float)record.average();
        System.out.println("Average score of all games is: "+ average);
    }

}
