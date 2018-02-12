package Coins12Fake1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Coin> twelveCoins;
        Coin fake;

        for (int i = 0; i < 12; i++) {
            System.out.println();
            twelveCoins = TwelveCoinsGenerator.generate(i);
            fake = FakeSearcher.find(twelveCoins);
            System.out.format("{ %s }\n", fake);
            System.out.format("Fake coin have weight %f\n", fake.getWeight());
            System.out.format("Position of Fake coin: %d\n", twelveCoins.indexOf(fake));
        }
    }

}
