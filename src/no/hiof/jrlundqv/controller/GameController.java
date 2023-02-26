package no.hiof.jrlundqv.controller;

import no.hiof.jrlundqv.model.player.ComputerOpponent;
import no.hiof.jrlundqv.model.dice.Dice;
import no.hiof.jrlundqv.model.dice.DiceManager;
import no.hiof.jrlundqv.model.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameController {
    private static final int WIN_CONDITION = 10;
    private final ArrayList<Player> playerList = new ArrayList<>();
    private final DiceManager diceManager;
    private Player currentPlayer;

    public GameController(DiceManager diceManager) {
        this.diceManager = diceManager;
    }

    /**
     * Randomly selects which player gets to go first
     */
    public void setStartingPlayer() {
        Random random = new Random();
        setCurrentPlayer(playerList.get(random.nextInt(playerList.size())));
        if (currentPlayer instanceof ComputerOpponent) setStartingPlayer();
        else System.out.println(currentPlayer.getPlayerName() + " you get to start!");
    }

    /**
     * Sets currentPlayer to the next player in the list
     * If currentPlayer is the last player in the list, it goes back to the first
     */
    private void playerTurnOver() {
        if (playerList.indexOf(currentPlayer) == playerList.size() - 1)
             setCurrentPlayer(playerList.get(0));
        else
            setCurrentPlayer(playerList.get(playerList.indexOf(currentPlayer) + 1));
    }

    public void printAvailableCommands() {
        System.out.println("""
                *** Available commands ***
                enter - roll dice
                s - save score
                l - show leaderboard
                """);
    }

    private void printPlayerTurn() {
        System.out.println("\n" + currentPlayer.getPlayerName() + ", it is your turn!");
    }

    /**
     * Sorts a copy of playerList by score, in sinking order (highest score on top).
     * Uses a copy because the original list is used to keep track of player turns.
     */
    public void printLeaderboard() {
        ArrayList<Player> playerListCopy = getPlayerList();

        Collections.sort(playerListCopy);

        System.out.println("\n*** LEADERBOARD ***");
        for (Player player : playerListCopy) {
            System.out.println(playerListCopy.indexOf(player) + 1 + ". " + player.getPlayerName() + " score: " + player.getPlayerScore());
        }
    }

    public void rollDice() {
        diceManager.rollDice();

        int rollScore = 0;
        boolean successfulRoll = true;

        try {Thread.sleep(500);}
        catch (InterruptedException e) {e.printStackTrace();}

        for (Dice dice : diceManager.getDiceList()) {
            if (dice.getDiceRoll() == 1) {
                lostRound(dice.getDiceRoll());
                successfulRoll = false;
                break;
            }
        }

        if (successfulRoll) {
            for (Dice dice : diceManager.getDiceList()) {
                currentPlayer.addRollScore(dice.getDiceRoll());
                rollScore += dice.getDiceRoll();
            }
            checkWinCondition();
            System.out.println(currentPlayer.getPlayerName() + ", you rolled " + rollScore + ", your round score is now " + currentPlayer.getTurnScore());
            System.out.println("Press enter to roll again, or type s and press enter to save your score");
        }
        computerCheckAction();
    }

    public void computerCheckAction() {
        if (currentPlayer instanceof ComputerOpponent) ((ComputerOpponent) currentPlayer).chooseAction();
    }

    public void lostRound(int diceRoll) {
        if (currentPlayer.getTurnScore() == 0)
            System.out.println(currentPlayer.getPlayerName() + " didn't stand a chance against the dice");
        else
            System.out.println(currentPlayer.getPlayerName() + " rolled a " + diceRoll + ", lost their " + currentPlayer.getTurnScore() + " points, and their turn is over!");

        currentPlayer.setTurnScore(0);
        playerTurnOver();
        printLeaderboard();
        printPlayerTurn();
        computerCheckAction();
    }

    public void saveScore() {
        currentPlayer.addTurnScore();
        System.out.println(currentPlayer.getPlayerName() + " saved their round score of " + currentPlayer.getTurnScore());
        currentPlayer.setTurnScore(0);
        playerTurnOver();
        printLeaderboard();
        printPlayerTurn();
        computerCheckAction();
    }

    public void checkWinCondition() {
        if (currentPlayer.getPlayerScore() + currentPlayer.getTurnScore() >= WIN_CONDITION) endGame();
    }

    public void endGame() {
        currentPlayer.setPlayerScore(getWinCondition());
        printLeaderboard();
        System.out.println("\n" + currentPlayer.getPlayerName() + " won the game!");
        System.exit(0);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public int getWinCondition() {
        return WIN_CONDITION;
    }

    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }

    public ArrayList<Player> getPlayerList() {
        return new ArrayList<>(playerList);
    }
}
