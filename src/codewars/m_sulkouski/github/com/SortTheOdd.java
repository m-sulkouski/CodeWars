package codewars.m_sulkouski.github.com;

public class SortTheOdd {
    public static void main(String[] args) {
        int[] nums = {5, 3, 2, 8, 1, 4, 17, 23};
        int[] oddIndeces = getOddIndeces(nums);
        int[] sortedNums = sortNumbers(nums, oddIndeces);
        for (int i : sortedNums) {
            System.out.println(i);
        }

    }


    private static int[] getOddIndeces(int[] nums) {
        int counter = 0;
        int[] oddIndeces;

        for (int i : nums) {
            if (i != 0 && i % 2 != 0) {
                counter++;
            }
        }
        oddIndeces = new int[counter];
        counter = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0 && nums[i] % 2 != 0) {
                oddIndeces[counter] = i;
                counter++;
            }
        }
        return oddIndeces;
    }

    private static int[] sortTheOdds(int[] odds) {
        int smallest = 0;
        int temp = 0;

        for (int i = 0; i < odds.length; i++) {
            smallest = odds[i];
            for (int k = i + 1; k < odds.length; k++) {
                if (odds[k] < smallest) {
                    temp = k;
                    smallest = odds[k];
                    odds[temp] = odds[i];
                    odds[i] = smallest;
                }
            }
        }
        return odds;
    }

    private static int[] sortNumbers(int[] nums, int[] indeces) {
        int[] odds = new int[indeces.length];
        int counter = 0;
        for (int i : indeces) {
            odds[counter] = nums[i];
            counter++;
        }

        odds = sortTheOdds(odds);

        for (int i = 0; i < indeces.length; i++) {
            nums[indeces[i]] = odds[i];
        }
        return nums;
    }
}
