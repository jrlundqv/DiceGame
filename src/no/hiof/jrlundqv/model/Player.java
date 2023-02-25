package no.hiof.jrlundqv.model;

public class Player implements Comparable<Player>{
    private final String playerName;
    private int playerScore, turnScore;

    public Player(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int compareTo(Player o) {
        return o.playerScore - playerScore;
    }

    public void addRollScore(int rollScore) {
        this.turnScore += rollScore;
    }

    public void addTurnScore()  {
        this.playerScore += this.turnScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getTurnScore() {
        return turnScore;
    }

    public void setTurnScore(int turnScore) {
        this.turnScore = turnScore;
    }

    public void setPlayerScore(int playerScore) {
        this.playerScore = playerScore;
    }
}
