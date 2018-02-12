package Coins12Fake1;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FakeSearcherTest {

    @Test
    public void find() {
        List<Coin> twelveCoins;
        Coin fake;

        for (int i = 0; i < 12; i++) {
            System.out.println();
            twelveCoins = TwelveCoinsGenerator.generate(i);
            fake = FakeSearcher.find(twelveCoins);
            assertTrue(twelveCoins.indexOf(fake) == i);
        }
    }
}