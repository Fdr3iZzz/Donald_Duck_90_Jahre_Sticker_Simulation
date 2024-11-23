package de.mueller.franz;

public class Bank {
    private double amount;

    public Bank() {
        this(0.0);
    }
    public Bank(double amount) {
        this.amount = amount;
    }
    public double getAmount() {
        return amount;
    }
    public void addAmount(double amount) {
        this.amount = this.amount + amount;
    }
}
