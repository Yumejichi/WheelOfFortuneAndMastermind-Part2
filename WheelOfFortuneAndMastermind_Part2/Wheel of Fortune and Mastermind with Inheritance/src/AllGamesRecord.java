import java.util.*;

/**
 * record all the scores for each play for all games(all users)
 */
public class AllGamesRecord{
    protected List<GameRecord> allScores;
    public AllGamesRecord(){
        this.allScores = new ArrayList<>();
    }


    /**
     * adds a GameRecord to the AllGamesRecord
     * @param gameRecord
     */
    public void add(GameRecord gameRecord){
        this.allScores.add(gameRecord);
    }

    /**
     * returns the average score for all games added to the record
     * @return average score
     */
    public float average(){
        float average = 0.0f;
        int sum = 0;
        for(int i=0; i<this.allScores.size(); i++){
            sum += this.allScores.get(i).getScore();
        }
        average = (float)sum/(float)this.allScores.size();
        return average;
    }

    /**
     * get the player ID
     * @param playerId
     * @return average score
     */
    public float average(String playerId){
        float average = 0.0f;
        int sum = 0;
        int count = 0;
        for(int i=0; i<this.allScores.size(); i++){
            if(this.allScores.get(i).getPlayerId().equals(playerId)){
                sum = sum + this.allScores.get(i).getScore();
                count++;
            }
        }
        average = (float)sum/(float)count;
        return average;
    }

    /**
     * sort player's scores by high to low
     * @param n
     * @return a sorted list of the top n scores including player and score
     */
    public List<GameRecord> highGameList(int n){

        List<GameRecord> sortedListOfTopScores = new ArrayList<>();
        Collections.sort(allScores);

        //add highest n values to the new list
        for(int i=n-1; i>=0; i--){
            sortedListOfTopScores.add(allScores.get(i));
        }
        return sortedListOfTopScores;
    }

    /**
     * sort a specific player's scores by high to low
     * @param playerId
     * @param n
     * @return a sorted list of the top n scores for the specified player.
     */
    public List<GameRecord> highGameList(String playerId, int n){
        //create a list to store all scores for a specified player
        List<GameRecord> allScoresOfAPlayer = new ArrayList<>();
        //create an array list to store the top n scores for the specified player
        List<GameRecord> sortedListOfTopScoresOfAPlayer = new ArrayList<>();

        //get the list for all scores for a specific player
        for(int i=0; i<this.allScores.size(); i++){
            if(this.allScores.get(i).getPlayerId().equals(playerId)){
                allScoresOfAPlayer.add(allScores.get(i));
            }
        }

        Collections.sort(allScoresOfAPlayer);

        //add highest n values to the new list
        for(int i=n-1; i>=0; i--){
            sortedListOfTopScoresOfAPlayer.add(allScoresOfAPlayer.get(i));
        }

        return sortedListOfTopScoresOfAPlayer;
    }


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "AllGamesRecord{" +
                "allScores=" + allScores +
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
        AllGamesRecord that = (AllGamesRecord) o;
        return Objects.equals(allScores, that.allScores);
    }

}
