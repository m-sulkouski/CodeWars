package codewars.m_sulkouski.github.com;

public class Palindrome {
    public static boolean isPalindrome(String word) {

        char[] letters = word.toUpperCase().toCharArray();
        int leftIndex = 0, rightIndex = letters.length - 1;

        while(leftIndex < rightIndex) {
            if (letters[leftIndex] != (letters[rightIndex])) {
                return false;
            }
            leftIndex++;
            rightIndex--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(Palindrome.isPalindrome("Deleveled"));
    }
}
