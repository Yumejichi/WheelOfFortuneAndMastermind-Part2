import java.util.Objects;

/**
 * this class is for a IntelligentBotPlayer to play the game
 */
public class IntelligentBotPlayer implements WheelOfFortunePlayer{

    /**
     * get the next guess from the player
     * @param hiddenPhrase
     * @param previousGuesses
     * @return the guessed letter by the bot
     */
    @Override
    public char nextGuess(StringBuilder hiddenPhrase, String previousGuesses) {

        char botGuess = 'e';
        //bot guess strategy
        //some common used words:
        if((hiddenPhrase).indexOf(" * ")!=-1 && previousGuesses.indexOf('a')==-1){
            botGuess = 'a';
        } else if((hiddenPhrase).indexOf(" ** ")!=-1 && previousGuesses.indexOf('i')==-1){
            //is
            botGuess = 'i';
        } else if((hiddenPhrase).indexOf(" ** ")!=-1 && previousGuesses.indexOf('t')==-1){
            //to
            botGuess = 't';
        }else if((hiddenPhrase).indexOf(" ** ")!=-1 && previousGuesses.indexOf('a')==-1){
            //an
            botGuess = 'a';
        } else if((hiddenPhrase).indexOf(" *** ")!=-1 && previousGuesses.indexOf('f')==-1){
            //for
            botGuess = 'f';
        } else if((hiddenPhrase).indexOf(" *** ")!=-1 && previousGuesses.indexOf('t')==-1){
            //the
            botGuess = 't';
        }else if((hiddenPhrase).indexOf(" *** ")!=-1 && previousGuesses.indexOf('a')==-1){
            //and, are
            botGuess = 'a';
        }else if((hiddenPhrase).indexOf(" **** ")!=-1 && previousGuesses.indexOf('f')==-1){
            //from
            botGuess = 'f';
        } else{
            //if not the above patterns,
            //use a StringBuilder to store the most frequent used English words found in the internet as below:
            //https://stancarey.wordpress.com/2013/01/07/etaoin-srhldcu-or-what-are-the-most-common-words-and-letters-in-english/
            StringBuilder mostFrequentlyUsedLetters = new StringBuilder("etaoinsrhldcumfpgwybvkxjqz");
            int letterIndex = 0;
            //if the letter is already guessed, use the next letter.
            while(previousGuesses.indexOf(mostFrequentlyUsedLetters.charAt(letterIndex))!=-1){
                letterIndex++;
            }
            botGuess = mostFrequentlyUsedLetters.charAt(letterIndex);
        }

        return botGuess;
    }


    /**
     * get the player ID
     * @return the player ID
     */
    @Override
    public String playerId() {
        return "IntelligentBotPlayer";
    }

    /**
     * reset the values for the bot player after 1 play
     */
    @Override
    public void reset() {
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "IntelligentBotPlayer{}";
    }

    /**
     * I don't have data members I don't have the method equals fot his class
     */



}
