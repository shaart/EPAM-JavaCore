package Coins12Fake1;

import java.util.ArrayList;
import java.util.List;

public class FakeSearcher {
    /**
     * Finds coin with different weight (all other must have same weight) in pile of 12 coins
     *
     * @param coins12 list with 12 coins
     * @return Fake coin
     */
    public static Coin find(final List<Coin> coins12) throws NullPointerException {
        if (coins12 == null || coins12.size() < 12) return null;
        for (Coin c : coins12) {
            if (c == null) {
                throw new NullPointerException("One of list's element is null");
            }
        }

        Coin fake = null;

        // Make 3 piles of 4 coins
        final List<Coin> left4 = new ArrayList<>();
        final List<Coin> right4 = new ArrayList<>();
        final List<Coin> remainder4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            left4.add(coins12.get(i)); // 1, 2, 3, 4 coins
            right4.add(coins12.get(i + 4)); // 5, 6, 7, 8 coins
            remainder4.add(coins12.get(i + 8)); // 9, 10, 11, 12 coins
        }

        int firstLeft4ToRight4 = BalanceScales.compare(left4, right4);
        if (firstLeft4ToRight4 == 0) {
            // Take remainder4 pile
            // Make 2 piles of 2 coins
            List<Coin> left3 = new ArrayList<Coin>() {{
                add(coins12.get(8)); // 9
                add(coins12.get(9)); // 10
                add(coins12.get(10)); // 11
            }};
            List<Coin> right3 = new ArrayList<Coin>() {{
                add(coins12.get(0)); // 1
                add(coins12.get(1)); // 2
                add(coins12.get(2)); // 3
            }};

            int secondLeft3ToCommon3 = BalanceScales.compare(left3, right3);
            if (secondLeft3ToCommon3 == 0) {
                // 12 coin is fake
                int third12ToCommon = BalanceScales.compare(/*questionable*/ coins12.get(11), /*common*/ coins12.get(0));

                fake = coins12.get(11); // by value of compare we can say is coin heavy or light
            } else {
                // Compare 9 and 10
                int thirdLastTwo = BalanceScales.compare(left3.get(0), left3.get(1));

                if (thirdLastTwo == 0) {
                    fake = left3.get(2); // 11
                } else if (secondLeft3ToCommon3 > 0 && thirdLastTwo > 0 || secondLeft3ToCommon3 < 0 && thirdLastTwo < 0) {
                    fake = left3.get(0); // 9
                } else {
                    fake = left3.get(1); // 10
                }
            }
        } else {
            // Make 2 piles of 3 coins
            List<Coin> left3 = new ArrayList<Coin>() {{
                add(coins12.get(0)); // 1 from left
                add(coins12.get(1)); // 2 from left
                add(coins12.get(4)); // 5 from right
            }};
            List<Coin> right3 = new ArrayList<Coin>() {{

                add(coins12.get(2)); // 3 from left
                add(coins12.get(3)); // 4 from left
                add(coins12.get(5)); // 6 from right
            }};
            List<Coin> remaining2 = new ArrayList<Coin>() {{
                add(coins12.get(6)); // 7
                add(coins12.get(7)); // 8
            }};

            int secondLeft3ToRight3 = BalanceScales.compare(left3, right3);
            if (secondLeft3ToRight3 == 0) {
                // Need search in the remaining two coins: 7 or 8
                int third7To8 = BalanceScales.compare(remaining2.get(0), remaining2.get(1));
                if (firstLeft4ToRight4 > 0 && third7To8 > 0 || firstLeft4ToRight4 < 0 && third7To8 < 0) {
                    fake = remaining2.get(1); // 7
                } else {
                    fake = remaining2.get(0); // 8
                }
            } else {
                // Need search in one of these piles
                if (firstLeft4ToRight4 > 0 && secondLeft3ToRight3 > 0 || firstLeft4ToRight4 < 0 && secondLeft3ToRight3 < 0) {
                    // 1, 2 or 6
                    int thirdTwoOfThree = BalanceScales.compare(left3.get(0), left3.get(1));
                    if (thirdTwoOfThree == 0) {
                        fake = right3.get(2); // 6
                    } else {
                        if (firstLeft4ToRight4 > 0 && thirdTwoOfThree > 0 || firstLeft4ToRight4 < 0 && thirdTwoOfThree < 0) {
                            fake = left3.get(0); // 1
                        } else {
                            fake = left3.get(1); // 2
                        }
                    }
                } else {
                    // 3, 4 or 5
                    int thirdTwoOfThree = BalanceScales.compare(right3.get(0), right3.get(1));
                    if (thirdTwoOfThree == 0) {
                        fake = left3.get(2); // 5
                    } else {
                        if (firstLeft4ToRight4 > 0 && thirdTwoOfThree > 0 || firstLeft4ToRight4 < 0 && thirdTwoOfThree < 0) {
                            fake = right3.get(0); // 3
                        } else {
                            fake = right3.get(1); // 4
                        }
                    }
                }
            }
        }

        return fake;
    }
}
