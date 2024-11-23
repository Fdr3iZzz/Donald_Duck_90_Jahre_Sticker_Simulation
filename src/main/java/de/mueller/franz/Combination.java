package de.mueller.franz;

public record Combination(int tueteAmount, int boxAmount, int blisterAmount, double averageCost) {

    @Override
    public String toString() {
        return "Average Cost: " + String.format("%.2f", averageCost) + " Tuete: " + tueteAmount + ", Box: " + boxAmount +
                ", Blister: " + blisterAmount;
    }
}
