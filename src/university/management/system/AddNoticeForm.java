package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;
import java.sql.*;
import java.text.SimpleDateFormat;

public class AddNoticeForm extends JFrame implements ActionListener {

    JTextField tfTitle;
    JTextArea taDescription;
    JDateChooser dcDate;
    JComboBox<String> cbCategory;
    JButton submit, cancel;

    AddNoticeForm() {
        
        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);
        
        setTitle("Add Notice");
        setSize(600, 600);
        setLocation(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel heading = new JLabel("Add Notice");
        heading.setBounds(250, 30, 200, 40);
        heading.setFont(new Font("Serif", Font.BOLD, 30));
        add(heading);

        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 100, 100, 30);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblTitle);

        tfTitle = new JTextField();
        tfTitle.setBounds(150, 100, 400, 30);
        add(tfTitle);

        JLabel lblDate = new JLabel("Date:");
        lblDate.setBounds(50, 150, 100, 30);
        lblDate.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblDate);

        dcDate = new JDateChooser();
        dcDate.setBounds(150, 150, 150, 30);
        add(dcDate);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(50, 200, 100, 30);
        lblCategory.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblCategory);

        String[] categories = {"Events", "Exams", "Holidays", "Others"};
        cbCategory = new JComboBox<>(categories);
        cbCategory.setBounds(150, 200, 150, 30);
        add(cbCategory);

        JLabel lblDescription = new JLabel("Description:");
        lblDescription.setBounds(50, 250, 100, 30);
        lblDescription.setFont(new Font("Serif", Font.BOLD, 20));
        add(lblDescription);

        taDescription = new JTextArea();
        taDescription.setBounds(150, 250, 400, 150);
        taDescription.setLineWrap(true);
        taDescription.setWrapStyleWord(true);
        taDescription.setFont(new Font("Serif", Font.PLAIN, 18));
        add(taDescription);

        submit = new JButton("Submit");
        submit.setBounds(150, 420, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(350, 420, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String title = tfTitle.getText();
            String date = null;
            if (dcDate.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                date = dateFormat.format(dcDate.getDate());
            }
            String category = (String) cbCategory.getSelectedItem();
            String description = taDescription.getText();

            try {
                Conn conn = new Conn();
                String query = "INSERT INTO notices (title, date, category, description) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.c.prepareStatement(query);
                stmt.setString(1, title);
                stmt.setString(2, date);
                stmt.setString(3, category);
                stmt.setString(4, description);
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Notice Added Successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add notice.");
                }

                tfTitle.setText("");
                dcDate.setDate(null);
                taDescription.setText("");
                cbCategory.setSelectedIndex(0);

                stmt.close();
                conn.c.close();

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        } else if (ae.getSource() == cancel) {
//            tfTitle.setText("");
//            dcDate.setDate(null);
//            taDescription.setText("");
//            cbCategory.setSelectedIndex(0);
            dispose();

        }
    }

    public static void main(String[] args) {
        new AddNoticeForm();
    }
}
