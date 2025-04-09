package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

      for (int i = 0; i < words.size() - 1; i++) {
        String word1 = words.get(i);
        String word2 = words.get(i + 1);
        System.out.printf("%s and %s are anagrams: %b%n",
            word1, word2, anagram(word1, word2));
      }
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

  public static boolean anagram(String a, String b) {
    if (a.length() != b.length()) {
      return false;
    }

    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < a.length(); i++) {
      map.put(a.charAt(i), map.getOrDefault(a.charAt(i), 0) + 1);
    }

    for (int i = 0; i < b.length(); i++) {
      if (!map.containsKey(b.charAt(i))) {
        return false;
      }

      int count = map.get(b.charAt(i)) - 1;
      if (count == 0) {
        map.remove(b.charAt(i));
      } else {
        map.put(b.charAt(i), count);
      }
    }

    return map.isEmpty();
  }
}
