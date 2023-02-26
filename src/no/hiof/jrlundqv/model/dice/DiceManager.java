package no.hiof.jrlundqv.model.dice;

import java.util.ArrayList;
import java.util.Random;

public class DiceManager {
    private final ArrayList<Dice> diceList = new ArrayList<>();

    public DiceManager(int numberOfDice, int diceMinValue, int diceMaxValue) {
        for (int i = 1; i <= numberOfDice; i++)
            addDice(new Dice(diceMinValue,diceMaxValue));
    }

    public void addDice(Dice dice) {
        diceList.add(dice);
    }

    public void rollDice() {
        Random random = new Random();
        int numberOfRolls = 1 + random.nextInt(5);

        for (int i = 0; i <= numberOfRolls; i++) {
            for (Dice dice : diceList) {
                dice.rollDice(dice);
            }
            printDice();

            try {Thread.sleep(200 + random.nextInt(500));}
            catch (InterruptedException e) {e.printStackTrace();}
        }
    }

    public void printDice() {
        for (int i = 0; i <= 2; i++) {
            for (Dice dice : diceList) {
                dice.printLine(i);
            }
            System.out.println();
        }
        System.out.println();
    }

    public ArrayList<Dice> getDiceList() {
        return diceList;
    }
}