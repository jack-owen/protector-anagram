package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

  public static void main(String[] args) {
    try {
      List<String> words = readWordsFromFile()
          .stream()
          .map(String::trim)
          .filter(s -> !s.isEmpty())
          .toList();

      anagram(words);
    } catch (IOException e) {
      System.err.println("Error reading words file: " + e.getMessage());
    }
  }

  private static List<String> readWordsFromFile() throws IOException {
    List<String> words = new ArrayList<>();
    try (InputStream is = Main.class.getResourceAsStream("/words-utf8.txt")) {
      if (is == null) {
        throw new IllegalStateException("file not found words-utf8.txt");
      }
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        String line;
        while ((line = reader.readLine()) != null) {
          words.add(line);
        }
      }
    }
    return words;
  }

  private static void anagram(List<String> words) {
    Map<String, List<String>> map = new HashMap<>();

    for (String original : words) {
      char[] chars = original.toLowerCase().toCharArray();
      Arrays.sort(chars);
      String sorted = Arrays.toString(chars);
      map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(original);
    }

    for (Map.Entry<String, List<String>> entry : map.entrySet()) {
      if (entry.getValue().size() > 1) {
        System.out.println(String.join(" ", entry.getValue()));
      }
    }

  }

}
