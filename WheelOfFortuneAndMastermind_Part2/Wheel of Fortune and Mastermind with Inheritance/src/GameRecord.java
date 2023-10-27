import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * track score and player id for a single player of a game
 */
public class GameRecord implements Comparable<GameRecord>{
    private int score;
    private String playerId;

    /**
     * get the score of a dingle game record
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * set the score for a single game record
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }


    /**
     * pet the ID of the current player
     * @return the player ID
     */
    public String getPlayerId(){
        return playerId;
    }

    /**
     * set the ID for a player
     * @param playerId
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * compare scores
     * @param other the object to be compared.
     * @return
     */
    @Override
    public int compareTo(GameRecord other){
        if(this.score > other.score){
            return 1;
        } else if(this.score < other.score){
            return -1;
        }else{
            return 0;
        }
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
        GameRecord that = (GameRecord) o;
        return score == that.score && Objects.equals(playerId, that.playerId);
    }

    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "GameRecord{" +
                "score=" + score +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}