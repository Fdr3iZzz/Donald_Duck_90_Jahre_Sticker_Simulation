package de.mueller.franz;

import java.util.Random;
import java.util.Set;

public class CardOdds {
    private final Set<Integer> stickerSet;
    private final Set<Integer> tradingCardSet;
    private final Set<Integer> limitedEditionSet;

    public CardOdds(Set<Integer> stickerSet, Set<Integer> tradingCardSet,
                    Set<Integer> limitedEditionSet) {
        this.stickerSet = stickerSet;
        this.tradingCardSet = tradingCardSet;
        this.limitedEditionSet = limitedEditionSet;
    }
    public void getSticker() {
        Random rand = new Random();
            int randNumber = rand.nextInt(276);
            stickerSet.add(randNumber);
            if (stickerSet.size() == 277) {
                System.out.println("Hit sticker cap");
                return;
            }


    }
    public void getTradingCard() {
        Random rand = new Random();
        int randNumber = rand.nextInt(50);
        tradingCardSet.add(randNumber);
        if (tradingCardSet.size() == 51) {
            System.out.println("Hit tradingCard cap");
            return;
        }


    }
    public void getLimitedEdition() {
        Random rand = new Random();
        int randNumber = rand.nextInt(4);
        limitedEditionSet.add(randNumber);
        if (limitedEditionSet.size() == 5) {
            System.out.println("Hit limitedEdition cap");
            return;
        }


    }
}
