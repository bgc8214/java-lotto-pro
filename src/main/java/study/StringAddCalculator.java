package study;

import static study.StringUtil.convertStringArrayToIntArray;


public class StringAddCalculator {

    public static int splitAndSum(String text) {
        if (StringUtil.isNullOrEmpty(text)) {
            return 0;
        }
        if (CalculatorInputUtil.hasCustomDelimiter(text)) {
            return sumNumbers(convertStringArrayToIntArray(
                CalculatorInputUtil.splitTextWithCustomDelimiter(text)));
        }
        return sumNumbers(convertStringArrayToIntArray(StringUtil.splitText(text, CalculatorInputUtil.DELIMITER)));
    }

    public static void checkNegativeNumber(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

    private static int sumNumbers(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        return sum;
    }
}
