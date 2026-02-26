package org.example;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.IO.println;
import static java.lang.Math.abs;

public class Utils {

    static int computeChange(String command) {
        return Integer.parseInt(command.replace('L', '-').replace("R", ""));
    }

    static List<String> fileToLines(String fileName) throws IOException {
        var path = Path.of(fileName);
        return Files.readAllLines(path);
    }

    static List<String> readCommeSeparated(String fileName) throws IOException {
        var path = Path.of(fileName);
        String content = Files.readString(path);
        String[] splittedFile = content.replace("\n", "").split(",");
        return Arrays.asList(splittedFile);
    }

    private static List<String> splitCommand(String command) {
        var acc = new ArrayList<String>();
        for (var i = 0; i <abs(computeChange(command)); i++){
            acc.add(command.charAt(0) + "1");
        }
        return acc;
    }

    static List<String> splitCommands(List<String> commands) {
        var acc = new ArrayList<String>();
        for (var command: commands){
            acc.addAll(splitCommand(command));
        }
        return acc;
    }

    public static boolean isInvalidID(long i) {
        var idAsString = String.valueOf(i);
        var splitIndex = idAsString.length() / 2;
        return idAsString.substring(0,  splitIndex).equals(idAsString.substring(splitIndex));
    }



    private static long addInvalidIDsInRange(long start, long end) {
        long acc = 0;

        for(var i = start; i <= end; i++ ) {
            if (isInvalidID(i)) {
                acc = acc + i;
            }
        }
        return acc;
    }

    static long addInvalidIDinLines(List<String> lines) {
        long acc = 0;
        for (var line: lines){
            var parts = line.split("-");
            var start = Long.parseLong(parts[0]);
            var end = Long.parseLong(parts[1]);
            acc = acc + (addInvalidIDsInRange(start,end));
        }

        return (acc);
    }



    public static boolean isInvalidID2(long id) {
        var idAsString = String.valueOf(id);
        var splitIndex = idAsString.length() / 2;
        for (int i = 1; i <= splitIndex; i++) {
            if (idAsString.substring(0,  i).repeat((idAsString.length()) / i).equals(idAsString)) {
                return true;
            }
        }
        return false;
    }


    public static BigInteger addMaxJoltages(List<String> lines) {
        BigInteger acc = BigInteger.valueOf(0);
        for (var line: lines){
            acc = acc.add(  computeMaxVoltage2(line));
            println(acc);
        }
        return acc;
    }

    private static int computeMaxVoltage(String bank) {
        var bankAsList = Arrays.asList(bank.split(""));
        var firstDigit =    Collections.max(bankAsList.subList(0, bankAsList.size()-1));
        var indexOfFirstDigit = bankAsList.indexOf(firstDigit);
        var restOfBank = bankAsList.subList(indexOfFirstDigit + 1, bankAsList.size());
        var secondDigit = Collections.max(restOfBank);
        return Integer.parseInt(firstDigit + secondDigit);
    }


    private static BigInteger computeMaxVoltage2(String bank) {
        var bankAsList = Arrays.asList(bank.split(""));
        String max = bank.substring(3);
        for (var i = 0; i < bankAsList.size()-2; i++){
            for (var j = i+1; j < bankAsList.size() - 1; j++){
                for (var k = j+1; k < bankAsList.size(); k++){

                    var copy = new ArrayList<>(bankAsList);
                    copy.remove(i);
                    copy.remove(j - 1);
                    copy.remove(k -2);
                    var x = String.join("", copy);
                    if (x.compareTo(max) > 0){
                        max = x;
                        println("i=" + i + " j=" +j + " k=" + k);
                    }
                }
            }
        }

        println(bank);
       println("-----------------------------");
        return new BigInteger(max);

    }

}
