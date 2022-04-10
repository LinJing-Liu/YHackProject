import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Hashtable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class main {
    static JFrame frame = new JFrame("Food Waste Tracker");
    static JLabel pic = new JLabel(new ImageIcon("image/zero.jpg"));
    static JLabel feedback = new JLabel("", SwingConstants.CENTER);
    static boolean showstats = false;

    private static void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImageIcon defaultImg = new ImageIcon("images/zero.jpg");
        defaultImg = new ImageIcon(defaultImg.getImage().getScaledInstance(120, 120, java.awt.Image.SCALE_SMOOTH));
        pic = new JLabel(defaultImg);
        pic.setBorder(new EmptyBorder(30, 20, 40, 20));
        JPanel imgPanel = new JPanel(new BorderLayout());
        imgPanel.add(pic, BorderLayout.CENTER);
        imgPanel.add(feedback, BorderLayout.PAGE_END);
        frame.add(imgPanel, BorderLayout.CENTER);

        Components components = new Components();
        components.askUser(frame);
        JSlider leftoverSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 0);
        addSliderLabels(leftoverSlider, "0", "5");
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.add(new JLabel("Waste", SwingConstants.CENTER), BorderLayout.NORTH);
        sliderPanel.add(leftoverSlider);
        frame.add(sliderPanel, BorderLayout.LINE_START);
        leftoverSlider.addChangeListener(e -> {
            components.setImage(frame, feedback, pic, leftoverSlider.getValue());
        });

        JButton statButton = new JButton("Show Stats");
        JLabel label = new JLabel("");
        label.setText(components.returnInfo());
        label.setBorder(new EmptyBorder(20, 20, 20, 20));
        statButton.addActionListener(e -> {
            showstats = (!showstats);
            if (showstats) {
                frame.add(label, BorderLayout.LINE_END);
                statButton.setText("Hide Stats");
                label.setVisible(true);
            }
            if (!showstats) {
                statButton.setText("Show Stats");
                label.setVisible(false);
            }
            frame.repaint();
            frame.pack();
        });

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            components.addDataPoint(leftoverSlider.getValue());
            label.setText(components.returnInfo());
            frame.pack();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(statButton);
        buttonPanel.add(submitButton);
        frame.add(buttonPanel, BorderLayout.PAGE_END);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void addSliderLabels(JSlider slider, String minLabel,
            String maxLabel) {
        Hashtable<Integer, JLabel> labels = new Hashtable<>();

        labels.put(slider.getMinimum(), new JLabel(minLabel));
        labels.put(slider.getMaximum(), new JLabel(maxLabel));
        slider.setLabelTable(labels);
        slider.setPaintLabels(true);

    }

    private static JComponent makeSliderPanel(JSlider slider, String title) {
        JPanel size = new JPanel(new BorderLayout());

        Border empty = BorderFactory.createEmptyBorder(4, 4, 4, 4);
        size.setBorder(empty);
        size.add(new JLabel(title), BorderLayout.NORTH);
        size.setAlignmentX(Component.CENTER_ALIGNMENT);
        size.add(slider);
        return size;

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}