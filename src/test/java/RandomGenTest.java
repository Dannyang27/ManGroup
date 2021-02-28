import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomGenTest {
    private static final int ARRAY_SIZE = 10000;
    private static final double ERROR_MARGIN = 1;

    private static final int[] SAMPLE_DATA = {-1, 0, 1, 2, 3};
    private static final int[] DISCRETE_PROBABILITIES = {1, 30, 58, 10, 1};

    private final RandomGen randomGen = new RandomGen(SAMPLE_DATA, DISCRETE_PROBABILITIES);
    private Integer[] randomNums;

    @BeforeEach
    public void setup() {
        randomNums = IntStream.range(0, ARRAY_SIZE)
                .map(e -> randomGen.nextNum(SAMPLE_DATA.length))
                .boxed()
                .toArray(Integer[]::new);
    }

    @Test
    void nextEnumShouldReturnMinusOneWithOnePercentProbability() {
        int frequency = DISCRETE_PROBABILITIES[0];
        long numberOfMinusOnes = getNumberCount(-1);
        double probability = getActualProbability(numberOfMinusOnes);

        assertTrue(frequency - ERROR_MARGIN < probability
                && probability < frequency + ERROR_MARGIN);
    }

    @Test
    void nextEnumShouldReturnMinusOneWithThirtyPercentProbability() {
        int frequency = DISCRETE_PROBABILITIES[1];
        long numberOfMinusZero = getNumberCount(0);
        double probability = getActualProbability(numberOfMinusZero);

        assertTrue(frequency - ERROR_MARGIN < probability
                && probability < frequency + ERROR_MARGIN);
    }

    @Test
    void nextEnumShouldReturnOneWithFiftyEightPercentProbability() {
        int frequency = DISCRETE_PROBABILITIES[2];
        long numberOfOne = getNumberCount(1);
        double probability = getActualProbability(numberOfOne);

        assertTrue(frequency - ERROR_MARGIN < probability
                && probability < frequency + ERROR_MARGIN);
    }

    @Test
    void nextEnumShouldReturnTwoWithTenPercentProbability() {
        int frequency = DISCRETE_PROBABILITIES[3];
        long numberOfTwo = getNumberCount(2);
        double probability = getActualProbability(numberOfTwo);

        assertTrue(frequency - ERROR_MARGIN < probability
                && probability < frequency + ERROR_MARGIN);
    }

    @Test
    void nextEnumShouldReturnThreeWithOnePercentProbability() {
        int frequency = DISCRETE_PROBABILITIES[4];
        long numberOfThree = getNumberCount(3);
        double probability = getActualProbability(numberOfThree);

        assertTrue(frequency - ERROR_MARGIN < probability
                && probability < frequency + ERROR_MARGIN);
    }

    @Test
    void randomNumSetShouldOnlyContainValidRandomNumbers() {
        Integer[] filteredArray = Arrays.stream(randomNums)
                .filter(e -> -1 <= e && e <= 3)
                .toArray(Integer[]::new);

        assertArrayEquals(filteredArray, randomNums);
    }

    private long getNumberCount(int number) {
        return Arrays.stream(randomNums)
                .filter(e -> e.equals(number))
                .count();
    }

    private double getActualProbability(long count) {
        return (count / (double) ARRAY_SIZE) * 100;
    }
}