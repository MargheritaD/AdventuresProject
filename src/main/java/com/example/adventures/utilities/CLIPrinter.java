package com.example.adventures.utilities;

public class CLIPrinter {

    private CLIPrinter() { throw new IllegalStateException("Utility class");}
    public static void printMessage(String s) { System.out.print(s);}
    public static void printListOfTrips(int i, String name) {System.out.printf("%s) Trip name: %s\n",i , name);}
    public static void printNumbers(int i) {System.out.printf("%s) ", i);}
}
