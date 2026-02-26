package org.example;

import java.io.IOException;
import java.util.List;

import static java.lang.IO.println;


public class Solutions {

    public static int countZerosAfterTurn(List<String> commands){
        var dial = new Dial();
        dial.turn(commands);
        return dial.zeroCount();
    }

    
    public static void solution1(String fileName) throws IOException {
        var lines = Utils.fileToLines(fileName);
        println(countZerosAfterTurn(lines));
    }

    public  static int countPointingToZeros(List<String> commands){
        var splittedCommands = Utils.splitCommands(commands);
        return countZerosAfterTurn(splittedCommands);
    }

    public static void solution2(String fileName) throws IOException {
        var lines = Utils.fileToLines(fileName);
        println(countPointingToZeros(lines));
    }

    public static void solution2_x(String fileName) throws IOException {
        var lines = Utils.readCommeSeparated(fileName);
        println(Utils.addInvalidIDinLines(lines));
    }

    public static void solution3_1(String fileName) throws IOException {
        var lines = Utils.fileToLines(fileName);
        println(Utils.addMaxJoltages(lines));
    }







}
