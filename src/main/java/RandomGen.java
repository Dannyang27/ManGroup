public class RandomGen {
    private final int[] randomNums;
    private final int[] discreteProbabilities;

    public RandomGen(int[] randomNums, int[] discreteProbabilities) {
        this.randomNums = randomNums;
        this.discreteProbabilities = discreteProbabilities;
    }

    public int nextNum(int n) {
        int[] prefix = new int[n];
        prefix[0] = discreteProbabilities[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + discreteProbabilities[i];
        }

        int randomNum = ((int) (Math.random() * 323567) % prefix[n - 1]) + 1;
        int index = findCeil(prefix, randomNum, 0, n - 1);

        return randomNums[index];
    }

    private int findCeil(int[] arr, int randomNum, int lowerIndex, int upperIndex) {
        int mid;

        while (lowerIndex < upperIndex) {
            mid = (lowerIndex + upperIndex) / 2;
            if (randomNum > arr[mid]) {
                lowerIndex = mid + 1;
            } else {
                upperIndex = mid;
            }
        }

        return lowerIndex;
    }
}