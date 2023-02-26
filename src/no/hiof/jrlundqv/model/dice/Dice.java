package no.hiof.jrlundqv.model.dice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dice {
    private final ArrayList<Integer> diceValues = new ArrayList<>();
    private final String[][] dieFaces;
    private static final String DICE_DOT = "⬤";
    private static final String EMPTY_DICE_DOT = "○";
    private int diceRoll;

    private static final int dieSize = 3;

    public Dice(int minDiceValue, int maxDiceValue) {
        this.dieFaces = new String[dieSize][dieSize];
        for (int i = minDiceValue; i <= maxDiceValue; i++) addDiceValue(i);
    }

    public void addDiceValue(int diceValue) {
        diceValues.add(diceValue);
    }

    public void rollDice(Dice dice) {
        dice.setDiceRoll(generateDieFace(pickRandomDiceValue()));
    }

    public void printLine(int line) {
        System.out.print(Arrays.toString(dieFaces[line]) + " ");
    }

    private int pickRandomDiceValue() {
        Collections.shuffle(diceValues);
        return diceValues.get(0);
    }

    private void emptyDieFace() {
        for (int i = 0; i < dieSize; i++) {
            for (int j = 0; j < dieSize; j++) {
                dieFaces[i][j] = EMPTY_DICE_DOT;
            }
        }
    }

    private int generateDieFace(int dieCast) {
        emptyDieFace();
        switch(dieCast) {
            case 1 -> setDieFaceToOne();
            case 2 -> setDieFaceToTwo();
            case 3 -> setDieFaceToThree();
            case 4 -> setDieFaceToFour();
            case 5 -> setDieFaceToFive();
            case 6 -> setDieFaceToSix();
            case 7 -> setDieFaceToSeven();
            case 8 -> setDieFaceToEight();
            case 9 -> setDieFaceToNine();
        }
        return dieCast;
    }

    private void setDieFaceToOne() {
        setDieFaces(1, 1);
    }

    private void setDieFaceToTwo() {
        setDieFaces(0, 0);
        setDieFaces(2, 2);
    }

    private void setDieFaceToThree() {
        setDieFaceToTwo();
        setDieFaceToOne();
    }

    private void setDieFaceToFour() {
        setDieFaces(0, 0);
        setDieFaces(2, 0);
        setDieFaces(0, 2);
        setDieFaces(2, 2);
    }

    private void setDieFaceToFive() {
        setDieFaceToFour();
        setDieFaceToOne();
    }

    private void setDieFaceToSix() {
        setDieFaceToFour();
        setDieFaces(1, 0);
        setDieFaces(1, 2);
    }

    private void setDieFaceToSeven() {
        setDieFaceToSix();
        setDieFaceToOne();
    }

    private void setDieFaceToEight() {
        setDieFaceToSix();
        setDieFaces(0, 1);
        setDieFaces(2, 1);
    }

    private void setDieFaceToNine() {
        setDieFaceToEight();
        setDieFaceToOne();
    }

    private void setDieFaces(int x, int y) {
        dieFaces[x][y] = DICE_DOT;
    }

    public int getDiceRoll() {
        return diceRoll;
    }

    public void setDiceRoll(int diceRoll) {
        this.diceRoll = diceRoll;
    }
}
