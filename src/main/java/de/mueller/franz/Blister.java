package de.mueller.franz;

import java.util.Set;

public class Blister extends CardOdds {
    // 28 sticker
    // 7 tradingCard
    // 1 limitedEdition
    // 6,50€ price
    public Blister(int amount, Bank bank, Set<Integer> stickerSet, Set<Integer> tradingCardSet, Set<Integer> limitedEditionSet) {
        super(stickerSet, tradingCardSet, limitedEditionSet);
        if (amount <= 0) return;
        for (int i = 0; i < amount; i++) {
            bank.addAmount(6.50);
            getSticker();
            getTradingCard();
            getLimitedEdition();
        }
    }

    @Override
    public void getSticker() {
        for (int i = 0; i < 28; i++) {
            super.getSticker();
        }
    }

    @Override
    public void getTradingCard() {
        for (int i = 0; i < 7; i++) {
            super.getTradingCard();
        }
    }

    @Override
    public void getLimitedEdition() {
        super.getLimitedEdition();
    }
}
