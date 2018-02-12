package Coins12Fake1;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BalanceScalesTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    final Coin NULL_COIN = null;
    final List<Coin> NULL_LIST = null;
    final Coin lightCoin = new Coin(1, 1);
    final Coin heavyCoin = new Coin(1, 2);
    final Coin heavyCoin2 = new Coin(1, 2);
    final List<Coin> lightPile = new ArrayList<Coin>() {{
        add(lightCoin);
        add(lightCoin);
        add(lightCoin);
        add(lightCoin);
    }};
    final List<Coin> heavyPile = new ArrayList<Coin>() {{
        add(lightCoin);
        add(lightCoin);
        add(lightCoin);
        add(heavyCoin);
    }};
    final List<Coin> heavyPile2 = new ArrayList<Coin>() {{
        add(lightCoin);
        add(lightCoin);
        add(lightCoin);
        add(heavyCoin2);
    }};

    @Test
    public void compare() {
        assertTrue(BalanceScales.compare(lightCoin, heavyCoin) < 0);
        assertTrue(BalanceScales.compare(heavyCoin, lightCoin) > 0);
        assertTrue(BalanceScales.compare(heavyCoin, heavyCoin2) == 0);
        assertTrue(BalanceScales.compare(heavyCoin, NULL_COIN) > 0);
        assertTrue(BalanceScales.compare(lightCoin, NULL_COIN) > 0);
        assertTrue(BalanceScales.compare(NULL_COIN, lightCoin) < 0);
        assertTrue(BalanceScales.compare(NULL_COIN, lightCoin) < 0);
        assertTrue(BalanceScales.compare(NULL_COIN, NULL_COIN) == 0);
    }

    @Test
    public void compareLists() {
        assertTrue(BalanceScales.compare(lightPile, heavyPile) < 0);
        assertTrue(BalanceScales.compare(heavyPile, lightPile) > 0);
        assertTrue(BalanceScales.compare(heavyPile, heavyPile2) == 0);
    }

    @Test
    public void compareListsThrowsNullPointerExceptionIfFirstArgumentIsNull() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null");
        BalanceScales.compare(null, lightPile);

    }

    @Test
    public void compareListsThrowsNullPointerExceptionIfSecondArgumentIsNull() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null");
        BalanceScales.compare(lightPile, null);

    }

    @Test
    public void compareListsThrowsNullPointerExceptionIfBothArgumentsAreNull() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null");
        BalanceScales.compare(NULL_LIST, NULL_LIST);

    }
}