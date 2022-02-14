package no.hiof.jrlundqv;

import no.hiof.jrlundqv.controller.GameController;
import no.hiof.jrlundqv.model.ComputerOpponent;
import no.hiof.jrlundqv.model.DiceManager;
import no.hiof.jrlundqv.model.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        String userInput = "";

        DiceManager diceManager = new DiceManager(1, 1, 6);
        GameController gameController = new GameController(diceManager);

        gameController.addPlayer(new Player("Player (⌐■_■)"));
        gameController.addPlayer(new ComputerOpponent("Computer d[o_0]b", gameController));
        //gameController.addPlayer(new ComputerOpponent("Computer d[-_-]b", gameController));

        System.out.printf("""
                ******** Welcome to the game of pig! ********
                                
                You compete against the computer or other players to be the first to %s points.
                When it is your turn, you roll the dice by pressing enter.
                You now have two choices. To roll again, or save your points.
                You can continue rolling for as many times as you want, until you roll a 1.
                When you roll a 1, you lose the points you accumulated in that turn.
                                
                Choose wisely and good luck!
                                
                """, gameController.getWinCondition());

        gameController.printAvailableCommands();
        gameController.setStartingPlayer();

        BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));

        while (!userInput.equals("exit")) {

            userInput = systemIn.readLine();

            switch (userInput) {
                case "" -> gameController.rollDice();
                case "s" -> gameController.saveScore();
                case "l" -> gameController.printLeaderboard();
            }
        }
    }
}
