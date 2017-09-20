package webCrawler;

import javax.swing.*;
import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class ReaderSorter {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/home/abstergo/" + JOptionPane.showInputDialog("insert file name"));
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] part = line.split(" : ");
            String[] partRoom = part[3].split(" ");

        }
    }
}
