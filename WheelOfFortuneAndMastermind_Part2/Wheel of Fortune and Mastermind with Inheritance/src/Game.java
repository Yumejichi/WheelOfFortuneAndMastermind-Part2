import java.util.Objects;

/**
 * Encapsulate the code for looping through a set of games and recording the results.
 */
public abstract class Game implements WheelOfFortunePlayer{
    protected AllGamesRecord records;
    /**
     * a method that plays a set of games and records
     * and returns an AllGamesRecord object for the set.
     * @return all players' records
     */
    public AllGamesRecord playAll() {
        AllGamesRecord allRecords = new AllGamesRecord();
        //plays a set of games
        //stop when the user requires or all the phrases are played

        int gameNumber = 1;
        while(playNext()){
            System.out.println("-----------------Game number: " + gameNumber + "--------------------");
            GameRecord record = play();
            allRecords.add(record);
            gameNumber++;

        }

        return allRecords;
    }

    /**
     * plays a game and returns a GameRecord
     */
    public abstract GameRecord play();

    /**
     * asks if the next game should be played
     * (this will be called even to check if the first game should be played).
     * @return true or false
     */
    public abstract boolean playNext();


    /**
     * returns a string that "textually represents" this object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Game{" +
                "records=" + records +
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
        Game game = (Game) o;
        return Objects.equals(records, game.records);
    }

}
