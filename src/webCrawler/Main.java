package webCrawler;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws IOException {
        String tempCache = "";
        String dataString ="";

        String fileName = JOptionPane.showInputDialog("Url");
        String outFileName = JOptionPane.showInputDialog("Input file Name");
        URL fInput = new URL(fileName);
        InputStream stream = fInput.openStream();

        Scanner input = new Scanner(stream, "utf-16");

        while (input.hasNext()) {
            tempCache = input.nextLine();
            dataString += checker(tempCache);
        }
        input.close();
        System.out.println(dataString);
        fileWriter(dataString, outFileName);
        JOptionPane.showMessageDialog(null,"Complete");

    }

    static String checker(String inputString) {
        String save = "";
        String saveControl = "";
        String temp = "<td class='smallbodytext'><table border='0' width=100%><tr><td class='smallbodytext' width=15%>";
        String lenTemp = "T</td><td class='smallbodytext' width=25%>19:00</td><td class='smallbodytext' width=25%>180</td><td class='smallbodytext' width=35%>ACW 004  ";

        String filter1 = "&nbsp;</td><td class='smallbodytext' width=25%>0:00</td><td class='smallbodytext' width=25%>0</td><td class='smallbodytext' width=35%>&nbsp";
        String filter2 = "&nbsp;</td><td class='smallbodytext' width=25%>&nbsp;</td><td class='smallbodytext' width=25%>0</td><td class='smallbodytext' width=35%>&nb";

        String replace1 = "</td><td class='smallbodytext' width=25%>";
        String replace2 = "</td><td class='smallbodytext' width=35%>";

        Pattern queStart = Pattern.compile(temp);

        Matcher matcher = queStart.matcher(inputString);

        while (matcher.find()) {
            int ind1 = matcher.start() + temp.length();

            saveControl = inputString.substring(ind1, ind1 + lenTemp.length()) + "\n";

            if (!saveControl.contains(filter1) && !saveControl.contains(filter2)) {
                save += saveControl;
            }
            save = save.replace(replace1, " : ");
            save = save.replace(replace2, " : ");
        }
        return save;
    }

    static void fileWriter(String input, String fileName) {
        String destination = "/home/abstergo/" + fileName;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(destination))) {
            bw.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
