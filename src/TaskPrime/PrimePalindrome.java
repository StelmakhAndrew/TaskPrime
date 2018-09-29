package TaskPrime;

import java.math.BigInteger;
import java.util.ArrayList;

public class PrimePalindrome {
    private static int firstPrime = 0;
    private static int secondPrime = 0;
    private static long maxPalindrome = 0;
    private static ArrayList<Integer> listPrime = new ArrayList(10000);

    public static void main(String[] args) {
        searchPrime();
        searchMaxPalindrome();
        System.out.println("first prime " + firstPrime);
        System.out.println("second prime " + secondPrime);
        System.out.println("max palindrome " + maxPalindrome);
    }

    private static void searchPrime() {
        int prime1;
        int prime3;
        int prime7;
        int prime9;
        for (int i = 10001; i < 100000; i = i + 10) {
            prime1 = i;
            prime3 = i + 2;
            prime7 = i + 6;
            prime9 = i + 8;
            if (rabinMillerAlgorithm(prime1)) listPrime.add(prime1);
            if (rabinMillerAlgorithm(prime3)) listPrime.add(prime3);
            if (rabinMillerAlgorithm(prime7)) listPrime.add(prime7);
            if (rabinMillerAlgorithm(prime9)) listPrime.add(prime9);
        }

    }

    private static void searchMaxPalindrome() {
        int limitIndex = 0;
        long resultOfMultiplication;
        for (int i = listPrime.size() - 1; i > limitIndex; i--) {
            for (int j = listPrime.size() - 1; j > 0; j--) {
                resultOfMultiplication = (long) listPrime.get(i) * (long) listPrime.get(j);
                if (checkForPalindrome(String.valueOf(resultOfMultiplication))) {
                    if (resultOfMultiplication > maxPalindrome) {
                        maxPalindrome = resultOfMultiplication;
                        firstPrime = listPrime.get(i);
                        secondPrime = listPrime.get(j);
                        limitIndex = j;
                    }
                    break;
                }
            }
        }
    }

    private static boolean checkForPalindrome(String number) {
        char[] numberOfDigits = number.toCharArray();
        for (int i = 0; i < numberOfDigits.length / 2; i++) {
            if (numberOfDigits[i] != numberOfDigits[numberOfDigits.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static boolean rabinMillerAlgorithm(Integer elem) {
        BigInteger bigInteger = BigInteger.valueOf(elem);
        return bigInteger.isProbablePrime((int) Math.log(elem));
    }
}