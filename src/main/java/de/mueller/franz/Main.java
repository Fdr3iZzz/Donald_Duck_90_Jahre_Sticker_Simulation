package de.mueller.franz;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        getTotalIterations(0, 17, 0, 10, 0, 10);

        findMinima(0, 17, 0, 10, 0, 10);

    }

    /**
     * get a simulated final price
     * @param tueteAmount number of tueten bought in that simulation
     * @param boxAmount number of boxes bought in that simulation
     * @param blisterAmount number of blisters bought in that simulation
     * @return price for this simulation
     */
    public static double getSimulatedCost(int tueteAmount, int boxAmount, int blisterAmount, boolean verbose) {
        // verify inputs
        if (tueteAmount < 0 || boxAmount < 0 || blisterAmount < 0) {
            throw new IllegalArgumentException("Values have to be grater or equal 0");
        }
        if (tueteAmount == 0 && boxAmount == 0 && blisterAmount == 0) {
            throw new IllegalArgumentException("One value has to be grater then 0");
        }

        final Set<Integer> stickerSet = new HashSet<>();
        final Set<Integer> tradingCardSet = new HashSet<>();
        final Set<Integer> limitedEditionSet = new HashSet<>();

        Bank bank = new Bank();
        // simulate
        new Tuete(tueteAmount, bank, stickerSet, tradingCardSet, limitedEditionSet);
        new Box(boxAmount, bank, stickerSet, tradingCardSet, limitedEditionSet);
        new Blister(blisterAmount, bank, stickerSet, tradingCardSet, limitedEditionSet);
        // print price to get all stickers
        int missingSticker = 276 - stickerSet.size();
        int missingTradingCardSet = 50 - tradingCardSet.size();
        int missingLimitedEditionSet = 4 - limitedEditionSet.size();
        double totalCost = bank.getAmount() + missingSticker + missingTradingCardSet + missingLimitedEditionSet;
        if (verbose) {
            printSimulationInfo(stickerSet, tradingCardSet, limitedEditionSet, totalCost);
        }
        return totalCost;
    }

    /**
     * find the average cost for specified packs with specified iterations
     * @param iterations number of times the simulation is run
     * @param tueteAmount number of tueten
     * @param boxAmount number of boxes
     * @param blisterAmount number of blisters
     * @return average cost
     */
    public static double getAverageCost(int iterations, int tueteAmount, int boxAmount, int blisterAmount) {
        double averageCost = 0;
        for (int i = 0; i < iterations; i++) {
            averageCost += getSimulatedCost(tueteAmount, boxAmount, blisterAmount, false);
        }
        averageCost /= iterations;
        System.out.println("Average cost for " + iterations + " simulations: " + String.format("%.2f", averageCost));
        return averageCost;
    }

    /**
     * function finds the minimal cost for all possibilities provided, bruteforce it
     * time complexity of O(N^3)
     * space complexity of O(1)
     * @param tueteMin min number of tuete packages tested
     * @param tueteMax max number of tuete packages tested
     * @param boxMin min number of boxes packages tested
     * @param boxMax max number of boxes packages tested
     * @param blisterMin min number of blisters packages tested
     * @param blisterMax max number of blisters packages tested
     */
    public static void findMinima(int tueteMin, int tueteMax, int boxMin, int boxMax, int blisterMin, int blisterMax) {
        // init top three
        Combination first = null, second = null, third = null;

        for (int tueteAmount = tueteMin; tueteAmount <= tueteMax; tueteAmount++) {
            for (int boxAmount = boxMin; boxAmount <= boxMax; boxAmount++) {
                for (int blisterAmount = blisterMin; blisterAmount <= blisterMax; blisterAmount++) {
                    if (tueteAmount == 0 && boxAmount == 0 && blisterAmount == 0) {
                        continue;
                    }
                    double averageCost = getAverageCost(300, tueteAmount, boxAmount, blisterAmount);

                    // Create a new Combination object
                    Combination combo = new Combination(tueteAmount, boxAmount, blisterAmount, averageCost);

                    // Update the top three combinations
                    if (first == null || combo.averageCost() < first.averageCost()) {
                        third = second;
                        second = first;
                        first = combo;
                    } else if (second == null || combo.averageCost() < second.averageCost()) {
                        third = second;
                        second = combo;
                    } else if (third == null || combo.averageCost() < third.averageCost()) {
                        third = combo;
                    }
                }
            }
        }
        System.out.println("First Lowest:");
        System.out.println(first);
        System.out.println("Second Lowest:");
        System.out.println(second);
        System.out.println("Third Lowest:");
        System.out.println(third);
    }

    /**
     * print the number of iterations needed (upper bound)
     * @param tueteMin min number of tuete packages tested
     * @param tueteMax max number of tuete packages tested
     * @param boxMin min number of boxes packages tested
     * @param boxMax max number of boxes packages tested
     * @param blisterMin min number of blisters packages tested
     * @param blisterMax max number of blisters packages tested
     */
    public static void getTotalIterations(int tueteMin, int tueteMax, int boxMin, int boxMax, int blisterMin, int blisterMax) {
        int min = Math.min(tueteMin, Math.min(boxMin, blisterMin));
        int max = Math.max(tueteMax, Math.max(boxMax, blisterMax));

        int range = max - min + 1;
        long totalIterations = (long) range * range * range;
        System.out.println("Total iterations: " + totalIterations);
    }

    /**
     * print additional information
     * @param stickerSet set that holds all stickers already found
     * @param tradingCardSet set that holds all trading cards already found
     * @param limitedEditionSet set that holds all limited editions already found
     * @param totalCost cost of the packs plus the missing stickers (valued at 1€ each)
     */
    public static void printSimulationInfo(Set<Integer> stickerSet, Set<Integer> tradingCardSet, Set<Integer> limitedEditionSet,
                                           double totalCost) {
        System.out.println("Sticker: " + stickerSet.size() + "/276");
        System.out.println("Trading Cards: " + tradingCardSet.size() + "/50");
        System.out.println("Limited Addition: " + limitedEditionSet.size() + "/4");
        System.out.println("Total Price to buy missing: " + totalCost);
        System.out.println();
    }
}