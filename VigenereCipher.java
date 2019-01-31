package cipher;

import java.io.PrintWriter;

/**
 * VigenereCipher encodes and decodes messages using the Vigenere cipher that shifts letters based
 * on the value of a keyword.
 */
public class VigenereCipher {
  public static int base = 97;// base is value of 'a' as an integer

  /**
   * The main function takes three arguments at run time. The first is either encode or decode, and
   * the second is a string that will be encrypted or decrypted. The third is the keyword that is
   * used in Vigenere Cipher.The program would display encryption or decryption of the given
   * argument.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    if (args.length != 3) {
      // terminates the program if the wrong number of entries
      System.err.println("Incorrect number of parameters");
      System.exit(2);
    }
    if (!(args[0].equals("encode") || args[0].equals("decode"))) {
      // terminates the program if invalid input
      System.err.println("Valid options are \"encode\" or \"decode\"");
      System.exit(1);
    }

    String text = args[1];
    String key = args[2];

    if (key.equals("")) {
      pen.println(text);
    } // if the keyword is empty string, the program prints the original string
    else if (args[0].equals("encode")) {
      pen.println(encode(text, key));
    } // prints the encryption based on the inputs if encode is called
    else {
      pen.println(decode(text, key));
    } // otherwise prints the decryption based on the inputs
  }

  /**
   * The encode function takes two parameters. The first is a string representing the plaintext, and
   * the second is the string keyword. The function would return a string that is encoded based the
   * Vigenere Cipher using the keyword key.
   */
  public static String encode(String plaintext, String key) {
    int[] text = charToInt(plaintext.toCharArray());
    int[] code = new int[text.length];
    int[] keyword = charToInt(key.toCharArray());

    for (int i = 0; i < text.length; i++) {
      code[i] = (text[i] + keyword[i % keyword.length]) % 26;
    }
    return new String(intToChar(code));
  }

  /**
   * The decode function takes two parameters. The first is a string representing the ciphertext,
   * and the second is the string keyword. The function would return a string that is decoded based
   * the Vigenere Cipher using the keyword key.
   */
  public static String decode(String ciphertext, String key) {
    int[] code = charToInt(ciphertext.toCharArray());
    int[] text = new int[code.length];
    int[] keyword = charToInt(key.toCharArray());

    for (int i = 0; i < code.length; i++) {
      text[i] = ((code[i] - keyword[i % keyword.length]) + 26) % 26;
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
