import java.util.Random;

public class GeneratePassword {

  static final char[] symbols;

  static {
    StringBuilder tmp = new StringBuilder();
    for (char ch = '0'; ch <= '9'; ch++)
      tmp.append(ch);
    for (char ch = 'a'; ch <= 'z'; ch++)
      tmp.append(ch);
    symbols = tmp.toString().toCharArray();
  }   

  Random random = new Random();

  char[] buf;

  public void RandomString(int length) {
    if (length < 1)
      throw new IllegalArgumentException("length < 1: " + length);
    buf = new char[length];
  }

  public String nextString() {
    for (int idx = 0; idx < buf.length; idx++) 
      buf[idx] = symbols[random.nextInt(symbols.length)];
    return new String(buf);
  }
}


