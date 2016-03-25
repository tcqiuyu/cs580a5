package a1.yqiu;

import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Qiu on 1/20/16.
 */
public class Roman {

    public static final Hashtable<String, Integer> romanDecimalTable = new Hashtable<>();
    public static final Hashtable<Integer, String> decimalRomanTable = new Hashtable<>();
    private String romanLetter = "IVXLCDM";

    public Roman() {
        // Roman to Decimal
        romanDecimalTable.put("I", 1);
        romanDecimalTable.put("V", 5);
        romanDecimalTable.put("X", 10);
        romanDecimalTable.put("L", 50);
        romanDecimalTable.put("C", 100);
        romanDecimalTable.put("D", 500);
        romanDecimalTable.put("M", 1000);
        // Decimal to Roman
        decimalRomanTable.put(1, "I");
        decimalRomanTable.put(5, "V");
        decimalRomanTable.put(10, "X");
        decimalRomanTable.put(50, "L");
        decimalRomanTable.put(100, "C");
        decimalRomanTable.put(500, "D");
        decimalRomanTable.put(1000, "M");

    }

    /**
     * Roman number to decimal number
     *
     * @param romanNumber
     * @return
     * @throws InvalidNumberException
     */
    public int toDecimal(String romanNumber) throws InvalidNumberException {
        romanNumber = romanNumber.toUpperCase();
        char[] chars = romanNumber.toCharArray();
        for (char ch : chars) {
            if (ch >= 'A' && ch <= 'Z' && romanDecimalTable.containsKey(String.valueOf(ch))) {

            } else {
                throw new InvalidNumberException("Unrecognized input character: " + ch);
            }
        }

        Pattern repetition = Pattern.compile(".*([" + romanLetter + "])\\1{3,}.*");
        Matcher matcher = repetition.matcher(romanNumber);
        if (matcher.matches()) {
            throw new InvalidNumberException("Invalid Roman number format: A letter can only be repeated three times - " + romanNumber);
        }

        int output = 0;
        int prev = Integer.MAX_VALUE;
        for (int i = 0; i < chars.length; i++) {

            char c = chars[i];
            int current = romanDecimalTable.get(String.valueOf(c));

            if (current < prev) {// if current number is bigger than the previous number, simply do summation
                if (i >= 2 && current >= romanDecimalTable.get(String.valueOf(chars[i - 2]))) {
                    throw new InvalidNumberException("Invalid Roman number format: " +
                            "A letter following a larger number must be smaller than the one preceding that number - " + romanNumber);
                }
                output += current;
            } else if (current == prev) {
                output += current;
            } else {// if current number is smaller than the previous number, need to do subtraction
//                System.out.println("current" + ":" + current + "|" + "prev" + ":" + prev);
                if (String.valueOf(prev).charAt(0) == '5') {
                    throw new InvalidNumberException("Invalid Roman number format: Subtraction only applies to powers of ten - " + romanNumber);
                }
                if (prev < current / 10) {
                    throw new InvalidNumberException("Invalid Roman number format: Subtract a number from one that is more than 10 times greater - " + romanNumber);
                }
                output += current - 2 * prev;// subtract the previous number twice since it has been added in the previous loop
            }
            prev = current;
        }
        return output;
    }

    /**
     * Decimal number to Roman number
     *
     * @param decimalNumber
     * @return
     * @throws InvalidNumberException
     */
    public String toRoman(int decimalNumber) throws InvalidNumberException {
        if (decimalNumber < 1 || decimalNumber >= 4000) {
            throw new InvalidNumberException("Input decimal number out of range.");
        }

        String inputStr = String.valueOf(decimalNumber);
        int strLen = inputStr.length();
        StringBuilder sb = new StringBuilder();


        for (char c : inputStr.toCharArray()) {
            int currentNum = Integer.parseInt(String.valueOf(c));
            int base = (int) Math.pow(10, strLen - 1);

            if (currentNum % 5 == 4) {// if current number is 4 or 9
                sb.append(decimalRomanTable.get(base));//small number goes first
                if (currentNum / 5 == 1) {// current number is 9
                    sb.append(decimalRomanTable.get(base * 10));
                } else {// current number is 4
                    sb.append(decimalRomanTable.get(base * 5));
                }
            } else {
                int loopNum = currentNum % 5;
                if (currentNum / 5 == 1) {// if current number is bigger than 5
                    sb.append(decimalRomanTable.get(base * 5));
                }
                for (int i = 0; i < loopNum; i++) {// add 1~3 base roman number at last
                    sb.append(decimalRomanTable.get(base));
                }
            }
            strLen--;
        }
        return sb.toString();
    }


}