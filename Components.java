import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Components extends JPanel implements MouseListener {
    Scanner in = new Scanner(System.in);

    public Components() {
        int[] tmp = getDate();
        data.currDayWeek = tmp[0];
        data.currMon = tmp[1];
        data.currDayNum = tmp[2];
        data.readData();
    }

    public static int[] getDate() {
        Date d = new Date();
        return new int[] { d.getDay(), d.getMonth(), d.getDate() };
    }

    public void askUser(JFrame frame) {
        JLabel question = new JLabel("How much leftovers did you have on a scale of 0 to 5? ", SwingConstants.CENTER);
        frame.add(question, BorderLayout.NORTH);
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
        frame.repaint();
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
        return data.scores;
    }

    public void addDataPoint(int in) {
        data.daysUsed++;
        data.totalScore += in;
        Day d = new Day(data.currDayWeek, data.currDayNum, data.currMon, in);
        data.scores.add(d);
        data.updateData();
    }

    public void printInfo() {
        Day in = data.scores.get(data.scores.size() - 1);
        System.out.println("Day " + data.daysUsed);
        System.out.println("Your waste scale for today is: " + in.getScore());
        // System.out.println(FEEDBACKS[in.score]);

        System.out.println("Weekly Average Score: " + String.format("%.2f", getSubAverage('w')));
        System.out.println("Monthly Average Score: " + String.format("%.2f", getSubAverage('m')));
        System.out.println("Overall Average Score: " + String.format("%.2f", totalAverage()));
    }

    public String returnInfo() {
        Day in = data.scores.get(data.scores.size() - 1);
        StringBuilder changingstr = new StringBuilder();
        changingstr.append("<html>");
        changingstr.append("Day " + data.daysUsed);
        changingstr.append("<br />");
        changingstr.append("Your waste scale for today is: " + in.getScore());
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
        int sum = 0;
        if (data.scores != null) {
            for (Day s : data.scores) {
                if (s != null) {
                    sum += s.getScore();
                }
            }
        }
        return (double) sum / data.scores.size();
    }

    public double getSubAverage(char type) {
        int iteration = 7;
        if (type == 'm')
            iteration = data.DAYINMONTH[data.currMon];
        int size = data.scores.size();
        if (size < iteration)
            iteration = size;
        return getScoreSum(data.scores, iteration) / (double) iteration;
    }

    // get the sum of the ArrayList for iterations from the last element for the
    // iteration specified
    public static int getScoreSum(ArrayList<Day> scores, int iteration) {
        int sum = 0;
        int size = data.scores.size();
        for (int i = 1; i <= iteration; i++) {
            sum += data.scores.get(size - i).getScore();
        }
        return sum;
    }

    // public class Day {
    //     private int month;
    //     private int dayNum;
    //     private int dayWeek;
    //     private int score;

    //     public Day(int dw, int dn, int m, int s) {
    //         month = m;
    //         dayNum = dn;
    //         dayWeek = dw;
    //         score = s;
    //     }

    //     public int getScore() {
    //         return score;
    //     }
    // }

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