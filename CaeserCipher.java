package cipher;

import java.io.PrintWriter;

/**
 * CaeserCipher encodes and decodes messages using the Caesar cipher that shifts letters based on
 * the value of key from 0 to 25 mod 26.
 */
public class CaeserCipher {
  public static int base = 97;// base is value of 'a' as an integer

  /**
   * The main function takes two arguments at run time. The first is either encode or decode, and
   * the second is a string that will be encrypted or decrypted. The program would display all 26
   * possible encryption or decryption of the given argument.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != 2) {
      // terminates the program if the wrong number of entries
      System.err.println("Incorrect number of parameters");
      System.exit(2);
    }
    if (!(args[0].equals("encode") || args[0].equals("decode"))) {
      // terminates the program if invalid input
      System.err.println("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }

    String text = args[1];// text is the string that will be encrypted or decrypted

    if (args[0].equals("encode")) {
      for (int n = 0; n < 26; n++) {
        pen.println("n = " + n + ": " + encode(text, n));
      }
    } // print 26 encryption of the text if encode is the input
    else {
      for (int n = 0; n < 26; n++) {
        pen.println("n = " + n + ": " + decode(text, n));
      }
    } // otherwise print 26 decryption of the text
  }

  /**
   * The encode function takes two parameters. The first is a string representing the plaintext, and
   * the second is the value of integer key. The function would return a string that is encoded
   * based the CaeserCipher using the integer n as the key.
   */
  public static String encode(String plaintext, int n) {
    int[] text = charToInt(plaintext.toCharArray());
    int[] code = new int[text.length];
    for (int i = 0; i < text.length; i++) {
      code[i] = (text[i] + n) % 26;
    }
    return new String(intToChar(code));
  }

  /**
   * The decode function takes two parameters. The first is a string representing the ciphertext,
   * and the second is the value of integer key. The function would return a string that is decoded
   * based the CaeserCipher using the integer n as the key.
   */
  public static String decode(String ciphertext, int n) {
    int[] code = charToInt(ciphertext.toCharArray());
    int[] text = new int[code.length];
    for (int i = 0; i < code.length; i++) {
      text[i] = ((code[i] - n) + 26) % 26;
    }
    return new String(intToChar(text));
  }

  /**
   * The charToInt function takes an array of characters as parameter, and it returns an array of
   * integer that represents the characters mod 26 with a being represented by 0.
   */
  public static int[] charToInt(char[] chars) {
    int[] ints = new int[chars.length];
    for (int i = 0; i < chars.length; i++) {
      ints[i] = ((int) chars[i]) - base;
    }
    return ints;
  }

  /**
   * The intToChar function takes an array of integers as parameter, and it returns an array of
   * characters that is represented by the integers.
   */
  public static char[] intToChar(int[] ints) {
    char[] chars = new char[ints.length];
    for (int i = 0; i < ints.length; i++) {
      chars[i] = (char) (ints[i] + base);
    }
    return chars;
  }
}
// https://www.systutorials.com/241729/how-to-print-a-line-to-stderr-and-stdout-in-java/
