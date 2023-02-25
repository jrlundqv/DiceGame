package no.hiof.jrlundqv.model;

import no.hiof.jrlundqv.controller.GameController;
import java.util.Random;

public class ComputerOpponent extends Player {
    private static int targetScore;
    private final GameController gameController;

    public ComputerOpponent(String player_name, GameController gameController) {
        super(player_name);
        this.gameController = gameController;
    }

    public void randomizeTargetScore() {
        Random random = new Random();
        setTargetScore(15 + random.nextInt(25));
    }

    public void chooseAction() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getTurnScore() < targetScore) {
            randomizeTargetScore();
            chooseRoll();
        }
        else chooseSave();

    }

    private void chooseRoll() {
        System.out.println();
        gameController.rollDice();
    }

    private void chooseSave() {
        System.out.println();
        gameController.saveScore();
    }

    public static void setTargetScore(int targetScore) {
        ComputerOpponent.targetScore = targetScore;
    }
}

 /*   private void calculateTargetScore() {
        double target = 5.0/6.0 * (4 +  );
}*/
