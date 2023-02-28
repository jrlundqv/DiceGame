package no.hiof.jrlundqv.model.player;

import no.hiof.jrlundqv.controller.GameController;

import java.util.Random;

public class ComputerOpponent extends Player {
    private int targetScore;
    private final GameController gameController;

    public ComputerOpponent(String playerName, GameController gameController) {
        super(playerName);
        this.gameController = gameController;
        randomizeTargetScore();
    }

    public void randomizeTargetScore() {
        Random random = new Random();
        targetScore = 10 + random.nextInt(20);
    }

    public void chooseAction() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getTurnScore() < targetScore) chooseRoll();
        else chooseSave();
    }

    private void chooseRoll() {
        System.out.println();
        gameController.rollDice();
    }

    private void chooseSave() {
        System.out.println();
        gameController.saveScore();
        randomizeTargetScore();
    }
}