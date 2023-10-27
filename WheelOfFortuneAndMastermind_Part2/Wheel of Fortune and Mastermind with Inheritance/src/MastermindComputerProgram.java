/**
 * The computer program you write will generate the secret code, but not show it,
 * and then provide feedback for each of the user's guesses.
 * The secret code should be randomly generated for each new game.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * The game should include six possible colors, Red, Green, Blue, Yellow, Orange, and Purple,
 * each specified with a single letter (R, G, B, Y, O, P).
 * The player should enter a guess as a single string, e.g., "RGRY" for Red, Green, Red, Yellow
 */

public class MastermindComputerProgram{
    public final char[] colors = {'R', 'G', 'B', 'Y', 'O', 'P'};

    /**
     * generate the secret string
     */
    public String generateSecretColors(){
        String secret = "";
        Random random = new Random();
        int indexOfColor = 0;

        //get a random string from the array of the color
        for(int i=0; i<Mastermind.CODESIZE; i++){
            indexOfColor= random.nextInt(colors.length);
            secret+=(colors[indexOfColor]);
        }
        return secret;
    }

    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "MastermindComputerProgram{" +
                "colors=" + Arrays.toString(colors) +
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
        MastermindComputerProgram that = (MastermindComputerProgram) o;
        return Arrays.equals(colors, that.colors);
    }

}
