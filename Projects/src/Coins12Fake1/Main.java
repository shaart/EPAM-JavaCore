package Coins12Fake1;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Coin> twelveCoins = TwelveCoinsGenerator.generate();

        Coin fake = FakeSearcher.find(twelveCoins);
        System.out.format("Fake coin have weight %f\n", fake.getWeight());

//        for (Coin coin : twelveCoins) {
//            if (coin == fake) continue;
//
//            System.out.format("Weight of common coin: %f\n", coin.getWeight());
//            break;
//
//        }

        System.out.println("All coins weights: ");
        for (Coin coin : twelveCoins) {
            System.out.format("Coin weight %f\n", coin.getWeight());
        }
    }

}
