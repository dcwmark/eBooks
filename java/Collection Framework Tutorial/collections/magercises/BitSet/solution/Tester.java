// Copyright 1999 MageLang Institute
// <version>$Id: //depot/main/src/edu/modules/Collections/magercises/BitSet/Solution/Tester.java#1 $</version>
public class Tester {

  // Test program
  public static void main (String args[]) {
    SparseBitSet set1 = new SparseBitSet();
    System.out.println("Empty Set: " );
    System.out.println("  Length: " + set1.length());
    System.out.println("  Size: " + set1.size());
    set1.set(128);
    set1.set(99);
    set1.set(100);
    System.out.println("Filled Set: ");
    System.out.println("  Contents: " + set1);
    System.out.println("  Length: " + set1.length());
    System.out.println("  Size: " + set1.size());
    System.out.println("Is 99 present? : ");
    System.out.println("  " + set1 + " " + set1.get(99));
    set1.clear(99);
    System.out.println("  " + set1 + " " + set1.get(99));
    SparseBitSet set2 = new SparseBitSet();
    set2.set(98);
    set2.set(99);
    set2.set(100);
    System.out.println("Are two sets equal? :");
    System.out.println("  Set 1: " + set1);
    System.out.println("  Set 2: " + set2);
    System.out.println("  Equal? " + set1.equals(set2));
    System.out.println("Or: " );
    SparseBitSet copy = (SparseBitSet)set1.clone();
    copy.or(set2);
    System.out.println("  " + copy);
    System.out.println("Xor: " );
    copy = (SparseBitSet)set1.clone();
    copy.xor(set2);
    System.out.println("  " + copy);
    System.out.println("And: " );
    copy = (SparseBitSet)set1.clone();
    copy.and(set2);
    System.out.println("  " + copy);
    System.out.println("AndNot: " );
    copy = (SparseBitSet)set1.clone();
    copy.andNot(set2);
    System.out.println("  " + copy);
  }
}
