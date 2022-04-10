import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class data extends Components {
    public final static int[] DAYINMONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    public static int daysUsed = 0;
    public static int totalScore = 0;
    public static boolean initialize;
    public static int currDayWeek;
    public static int currMon;
    public static int currDayNum;
    public static ArrayList<Day> scores = new ArrayList<Day>();

    //update a single entry of data
    public static void updateData() {
        File file = new File("score.txt");
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file, true)))) {
            // add score here
            int s = scores.get(scores.size() - 1).getScore();
            out.println(s);
        } catch (Exception e) {

        }
    }

    public static void readData() {
        try (// the file to be opened for reading
                FileInputStream fis = new FileInputStream("score.txt")) {
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                int currS = Integer.parseInt(sc.nextLine());
                totalScore += currS;
                // System.out.println(currS);
                scores.add(new Day(currDayWeek, currDayNum, currMon, currS)); // returns the line that was skipped
            }
            // System.out.println(scores.size());
            daysUsed = scores.size();
            sc.close();
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
