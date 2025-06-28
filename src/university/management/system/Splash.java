package university.management.system;
import java.awt.*;
import javax.swing.*;

public class Splash extends JFrame {
    JProgressBar progressBar;

    Splash() {
        setLayout(new BorderLayout());
        
        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/College.png"));
        Image i2 = i1.getImage().getScaledInstance(652, 330, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image, BorderLayout.CENTER);
        
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.CYAN);
        add(progressBar, BorderLayout.SOUTH);

        setResizable(false);
        setLocation(400, 200);
        setSize(652, 370);
        setVisible(true);
        setTitle("Shri Khandelwal Vaish P.G. Mahavidhyalaya");

        fillProgressBar();
    }

    public void fillProgressBar() {
        int i = 0;
        while (i <= 100) {
            try {
                Thread.sleep(30); // Adjusted for smoother progress (5 sec total)
                progressBar.setValue(i);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dispose();
        new Login(); // Open the next frame
    }

    public static void main(String[] args) {
        new Splash();
    }
}