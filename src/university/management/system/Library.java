package university.management.system;

import java.awt.*;
import javax.swing.*;

public class Library extends JFrame {
    JProgressBar progressBar;

    Library() {
        setLayout(null);  // Set null layout for custom positioning
        
        // Set the icon for the window
        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);
        
        // Load and scale the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/library.png"));
        Image i2 = i1.getImage().getScaledInstance(303, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        // Create a label for the image and set its position in the top-right corner
        JLabel image = new JLabel(i3);
        image.setBounds(340, 10, 303, 300);  // Positioned at the top-right corner
        add(image);
        
        // Create a JTextArea for the text to avoid clipping
        JTextArea text = new JTextArea("Welcome to the Library.                 The College Library grants accessibility to 13000 quality books, journals, and magazines written by renowned Indian and international authors. It continuously gets enriched with the addition of new books in each discipline every session.");
        text.setFont(new Font("Arial", Font.BOLD, 18));  // Set the font for the text
        text.setWrapStyleWord(true);  // Enable word wrapping
        text.setLineWrap(true);  // Enable line wrapping
        text.setEditable(false);  // Make it non-editable
        text.setBackground(null);  // Set the background to transparent
        text.setForeground(Color.BLACK);  // Set text color to black
        
        // Add the text area inside a scroll pane (if needed for larger text)
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setBounds(10, 10, 330, 300);  // Adjust the bounds for the text area
        add(scrollPane);

        // Set properties for the window
        setResizable(false);
        setLocation(400, 200);
        setSize(652, 350);
        setVisible(true);
        setTitle("Shri Khandelwal Vaish P.G. Mahavidhyalaya");
    }

    public static void main(String[] args) {
        new Library();
    }
}
