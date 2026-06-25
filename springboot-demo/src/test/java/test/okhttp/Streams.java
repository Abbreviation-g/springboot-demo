package test.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;

public class Streams {

  public static final int BUFFER_SIZE = 4096;

  public static void copy(InputStream in, OutputStream out) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    int bytesRead;
    while ((bytesRead = in.read(buffer)) != -1) {
      out.write(buffer, 0, bytesRead);
    }
    out.flush();
  }

  public static String toString(Reader reader) throws IOException {
    StringBuilder out = new StringBuilder(BUFFER_SIZE);
    char[] buffer = new char[BUFFER_SIZE];
    int charsRead;
    while ((charsRead = reader.read(buffer)) != -1) {
      out.append(buffer, 0, charsRead);
    }
    return out.toString();
  }

  public static void readFully(InputStream in, byte[] bytes) throws IOException {
    int total = 0;
    int len = bytes.length;
    while (total < len) {
      int result = in.read(bytes, total, len - total);
      if (result == -1) {
        break;
      }
      total += result;
    }
  }
}
