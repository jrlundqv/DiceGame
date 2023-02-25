package no.hiof.jrlundqv.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Dice {
    private final ArrayList<Integer> diceValues = new ArrayList<>();
    private final String[][] dieFaces;
    private static final String DICE_DOT = "⬤";
    private static final String EMPTY_DICE_DOT = "⚪";
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
        if (dieCast == 1) setDieFaceToOne();
        else if (dieCast == 2) setDieFaceToTwo();
        else if (dieCast == 3) setDieFaceToThree();
        else if (dieCast == 4) setDieFaceToFour();
        else if (dieCast == 5) setDieFaceToFive();
        else if (dieCast == 6) setDieFaceToSix();
        else if (dieCast == 7) setDieFaceToSeven();
        else if (dieCast == 8) setDieFaceToEight();
        else if (dieCast == 9) setDieFaceToNine();
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
