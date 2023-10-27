import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * For each AI player specified, this class will play a game for each phrase read in from the file.
 * So if there are m players and n phrases, Game’s playAll() should play m*n games.
 */
public class WheelOfFortuneAIGame extends WheelOfFortune{

    protected WheelOfFortunePlayer defaultPlayer;
    protected WheelOfFortunePlayer player;
    protected ArrayList<WheelOfFortunePlayer> players;
    protected  WheelOfFortunePlayer currentPlayer;
    protected int indexOfPlayer;

    /**
     *  have three constructors.
     * One should set the WheelOfFortunePlayer to a default player,
     */
    public WheelOfFortuneAIGame(){
        this.defaultPlayer = new UnintelligentBotPlayer();
    }

    /**
     * one should allow the client to specify a single concrete WheelOfFortunePlayer,
     */
    public WheelOfFortuneAIGame(WheelOfFortune singlePlayer){
        this.player = singlePlayer;
    }

    /**
     * one should accept a list of WheelOfFortunePlayers.
     */
    public WheelOfFortuneAIGame(ArrayList<WheelOfFortunePlayer> players){
        this.players = players;
    }

    /**
     * this implements one play
     * @return game record
     */
    @Override
    public GameRecord play() {

        GameRecord record = new GameRecord();
        //get current player
        currentPlayer = players.get(indexOfPlayer);

        record.setPlayerId(currentPlayer.playerId());

        //get a random phrase
        readPhrases();

        instruction(chance);

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
            char letterInput = currentPlayer.nextGuess(hiddenPhrase, previousGuesses);

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
                System.out.println("Congratulations! You have successfully guessed all letters!");
                System.out.println("-----------------------------------------------------------------\n");
                continueGuess=false;
            }

            //if the player run out all the chances, finish the game
            if ((chance-miss) == 0) {
                System.out.println("I am sorry, you have ran out all of the chances. Try next time.");
                System.out.println("-----------------------------------------------------------------\n");
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
     * judge if to play next game
     * @return true or false
     */
    @Override
    public boolean playNext() {
        if(numbersPlayed!=0){
            currentPlayer.reset();
        }
        //if all games are played for all users, then finish
        if(indexOfPlayer>=(players.size()-1)&&numbersPlayed>=phraseListSize){
            System.out.println("All games are played for all players\n");
            return false;
        }
        if(numbersPlayed!=0 && numbersPlayed>=phraseListSize){
            System.out.println("All games are played for player" + currentPlayer.playerId() + ".\n");
            phraseListSize=0;
            numbersPlayed=0;
            indexOfPlayer++;
            reset();
        }
        numbersPlayed++;
        return true;
    }

    /**
     * get the guesses
     * @param previousGuesses a string contains previous guesses
     * @return a letter
     */
    @Override
    public char getGuess(String previousGuesses) {
        char letterInput;

        while(true) {
            System.out.println("Guess and enter a letter: ");
            char input = nextGuess(hiddenPhrase, previousGuesses);
            //show the letter:
            System.out.println(input);

            //if a letter is already guessed, do not count and let the player try again.
            if (previousGuesses.indexOf(input) != -1) {
                System.out.println("You have guessed this letter before. Try another letter.");
                System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
                continue;
            }

            //if the input is not a letter, let the player enter again
            if (!Character.isLetter(input)) {
                System.out.println("This is not a letter. Please input a letter.");
                System.out.println("The phrase with correctly guessed letters: " + hiddenPhrase);
            }else{
                letterInput = input;
                break;
            }
        }

        return letterInput;
    }


    /**
     * get the next guess
     * @param hiddenPhrase a hidden phrase
     * @param previousGuesses  previous guessed letters
     * @return a letter
     */
    @Override
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses) {
        return currentPlayer.nextGuess(hiddenPhrase, previousGuesses);
    }

    /**
     * get the player ID
     * @return player ID
     */
    @Override
    public String playerId() {
        return currentPlayer.playerId();
    }

    /**
     * reset data members
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
        return "WheelOfFortuneAIGame{" +
                "defaultPlayer=" + defaultPlayer +
                ", player=" + player +
                ", players=" + players +
                ", currentPlayer=" + currentPlayer +
                ", indexOfPlayer=" + indexOfPlayer +
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
        WheelOfFortuneAIGame that = (WheelOfFortuneAIGame) o;
        return indexOfPlayer == that.indexOfPlayer && Objects.equals(defaultPlayer, that.defaultPlayer) && Objects.equals(player, that.player) && Objects.equals(players, that.players) && Objects.equals(currentPlayer, that.currentPlayer);
    }


    /**
     * WheelOfFortunedAIGame should create at least three different players,
     * then call playAll() to run through all the phrases for each player.
     * Both main’s should demonstrate the methods of GamesRecord to display results.
     */
    public static void main(String [] args) {
        WheelOfFortunePlayer player1 = new UnintelligentBotPlayer();
        WheelOfFortunePlayer player2 = new NormalBotPlayer();
        WheelOfFortunePlayer player3 = new IntelligentBotPlayer();
        ArrayList<WheelOfFortunePlayer> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);


        WheelOfFortuneAIGame hangmanUserGame = new WheelOfFortuneAIGame(players);
        AllGamesRecord record = hangmanUserGame.playAll();

        System.out.println(record);  // or call specific functions of record

        //display highGameList(for two games?)
        System.out.println("highGameList: " + record.highGameList("UnintelligentBotPlayer", hangmanUserGame.phraseListSize));

        System.out.println("highGameList: " + record.highGameList("NormalBotPlayer", hangmanUserGame.phraseListSize));

        System.out.println("highGameList: " + record.highGameList("IntelligentBotPlayer", hangmanUserGame.phraseListSize));


        //display average of games
        float average = (float)record.average();
        System.out.println("Average score of all games is: "+ average);

        //display average of each player
        System.out.println("UnintelligentBotPlayer's average score is: "+ (float)record.average("UnintelligentBotPlayer"));

        System.out.println("NormalBotPlayer's average score is: "+ (float)record.average("NormalBotPlayer"));

        System.out.println("IntelligentBotPlayer's average score is: "+ (float)record.average("IntelligentBotPlayer"));


    }

}
