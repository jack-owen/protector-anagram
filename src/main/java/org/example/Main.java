package org.example;

import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String[] args) {

    System.out.println(anagram("hi", "ih"));
    System.out.println(anagram("h", "ih"));
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
