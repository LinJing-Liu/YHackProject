import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Components extends JPanel implements MouseListener {
    public final static int[] DAYINMONTH = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private int daysUsed = 0;
    private int totalScore = 0;
    private int currDayWeek;
    private int currMon;
    private int currDayNum;
    private ArrayList<Day> scores = new ArrayList<Day>();
    Scanner in = new Scanner(System.in);

    public Components() {
        int[] tmp = getDate();
        currDayWeek = tmp[0];
        currMon = tmp[1];
        currDayNum = tmp[2];
    }

    public static int[] getDate() {
        Date d = new Date();
        return new int[] { d.getDay(), d.getMonth(), d.getDate() };
    }

    public void askUser(JFrame frame) {
        JLabel question = new JLabel("How much leftovers did you have on a scale of 0 to 5? ", SwingConstants.CENTER);
        question.setFont(new Font("Serif", Font.BOLD, 24));
        question.setBorder(new EmptyBorder(10, 20, 10, 20));
        frame.add(question, BorderLayout.NORTH);
        String tempuserin = in.next();
        int userin;
        while (!(Double.parseDouble(tempuserin) <= 5.0 && Double.parseDouble(tempuserin) >= 0.0)
                || Double.parseDouble(tempuserin) % 1.0 != 0.0) {
            System.out.print(
                    "How much leftovers did you have on a scale of 0 to 5 (please enter a valid whole number between 0 and 5)? ");
            tempuserin = in.next();
        }
        userin = (int) Double.parseDouble(tempuserin);
        addDataPoint(userin);
        printInfo();
        frame.pack();
    }

    public void setImage(JFrame frame, JLabel feedback, JLabel pic, int in) {
        if (in == 0) {
            feedback.setText("YAYYYY!!!"); // eventually add image using GUI
            pic.setIcon(resize("images/zero.jpg"));
        } else if (in == 1) {
            feedback.setText("Good job!");
            pic.setIcon(resize("images/one.jpg"));
        } else if (in == 2) {
            feedback.setText("Not bad! Let's try to do even better next time!");
            pic.setIcon(resize("images/two.jpg"));
        } else if (in == 3) {
            feedback.setText("Average.");
            pic.setIcon(resize("images/three.jpg"));
        } else if (in == 4) {
            feedback.setText(":(");
            pic.setIcon(resize("images/four.jpg"));
        } else if (in == 5) {
            feedback.setText(":'''''(");
            pic.setIcon(resize("images/five.jpg"));
        }
        repaint();
    }

    // resize images to appropiate size
    // change value in image.getScaledInstance() to aler properties
    // returns an imageicon
    public static ImageIcon resize(String i) {
        ImageIcon temp = new ImageIcon(i); // load the image to a imageIcon
        Image image = temp.getImage(); // transform it
        Image newimg = image.getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        temp = new ImageIcon(newimg); // transform it back
        return temp;
    }

    public ArrayList<Day> getScores() {
        return scores;
    }

    public void addDataPoint(int in) {
        daysUsed++;
        totalScore += in;
        Day d = new Day(currDayWeek, currDayNum, currMon, in);
        scores.add(d);
    }

    public void printInfo() {
        Day in = scores.get(scores.size() - 1);
        System.out.println("Day " + daysUsed);
        System.out.println("Your waste scale for today is: " + in.score);
        System.out.println("Weekly Average Score: " + String.format("%.2f", getSubAverage('w')));
        System.out.println("Monthly Average Score: " + String.format("%.2f", getSubAverage('m')));
        System.out.println("Overall Average Score: " + String.format("%.2f", totalAverage()));
    }

    public String returnInfo() {
        Day in = scores.get(scores.size() - 1);
        StringBuilder changingstr = new StringBuilder();
        changingstr.append("<html>");
        changingstr.append("Day " + daysUsed);
        changingstr.append("<br />");
        changingstr.append("Your waste scale for today is: " + in.score);
        changingstr.append("<br />");
        changingstr.append("Weekly Average Score: " + String.format("%.2f", getSubAverage('w')));
        changingstr.append("<br />");
        changingstr.append("Monthly Average Score: " + String.format("%.2f", getSubAverage('m')));
        changingstr.append("<br />");
        changingstr.append("Overall Average Score: " + String.format("%.2f", totalAverage()));
        changingstr.append("</html>");

        return changingstr.toString();
    }

    public double totalAverage() {
        // int sum = 0;
        // if (scores != null) {
        //     for (Day s : scores) {
        //         if (s != null) {
        //             sum += s.score;
        //         }
        //     }
        // }
        return (double) totalScore / scores.size();
    }

    public double getSubAverage(char type) {
        int iteration = 7;
        if (type == 'm')
            iteration = DAYINMONTH[currMon];
        int size = scores.size();
        if (size < iteration)
            iteration = size;
        return getScoreSum(scores, iteration) / (double) iteration;
    }

    // get the sum of the ArrayList for iterations from the last element for the
    // iteration specified
    public static int getScoreSum(ArrayList<Day> arr, int iteration) {
        int sum = 0;
        int size = arr.size();
        for (int i = 1; i <= iteration; i++) {
            sum += arr.get(size - i).score;
        }
        return sum;
    }

    private class Day {
        private int month;
        private int dayNum;
        private int dayWeek;
        private int score;

        private Day(int dw, int dn, int m, int s) {
            month = m;
            dayNum = dn;
            dayWeek = dw;
            score = s;
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }
}