package de.mueller.franz;

import java.util.Set;

public class Box extends CardOdds {
    // 144 sticker
    // 36 tradingCard
    // 0 limitedEdition
    // 32,40€ price
    public Box(int amount, Bank bank, Set<Integer> stickerSet, Set<Integer> tradingCardSet, Set<Integer> limitedEditionSet) {
        super(stickerSet, tradingCardSet, limitedEditionSet);
        if (amount <= 0) return;
        for (int i = 0; i < amount; i++) {
            bank.addAmount(32.40);
            getSticker();
            getTradingCard();
        }
    }

    @Override
    public void getSticker() {
        for (int i = 0; i < 144; i++) {
            super.getSticker();
        }
    }

    @Override
    public void getTradingCard() {
        for (int i = 0; i < 36; i++) {
            super.getTradingCard();
        }
    }
}
