package de.mueller.franz;

import java.util.Set;

public class Tuete extends CardOdds{
    // 4 sticker
    // 1 tradingCard
    // 0 limitedEdition
    // 1.00€ price
    public Tuete(int amount, Bank bank, Set<Integer> stickerSet, Set<Integer> tradingCardSet, Set<Integer> limitedEditionSet) {
        super(stickerSet, tradingCardSet, limitedEditionSet);
        if (amount <= 0) return;
        for (int i = 0; i < amount; i++) {
            bank.addAmount(1.0);
            getSticker();
            getTradingCard();
        }
    }

    @Override
    public void getSticker() {
        for (int i = 0; i < 4; i++) {
            super.getSticker();
        }
    }

    @Override
    public void getTradingCard() {
        super.getTradingCard();
    }
}
