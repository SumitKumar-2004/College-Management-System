package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class NewNotice extends JFrame {

    JTextArea noticeArea;
    JTextField deleteField;
    JButton deleteButton, cancelButton;

    NewNotice() {
        // Set JFrame size and title
        Image i = new ImageIcon(this.getClass().getResource("/icons/MainLogo.png")).getImage();
        this.setIconImage(i);
        setSize(800, 500);
        setTitle("New Notices");
        setLayout(new BorderLayout());

        // Create JTextArea for displaying notices
        noticeArea = new JTextArea();
        noticeArea.setEditable(false);
        noticeArea.setFont(new Font("Arial", Font.PLAIN, 14));
        noticeArea.setLineWrap(true);
        noticeArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(noticeArea);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch notices from the database
        fetchNoticesFromDatabase();

        // Panel for buttons and input field
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        // Text field for entering the title of the notice to delete
        deleteField = new JTextField(20);
        bottomPanel.add(new JLabel("Enter Title to Delete: "));
        bottomPanel.add(deleteField);

        // Delete button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteNotice();
            }
        });
        bottomPanel.add(deleteButton);

        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the window
            }
        });
        bottomPanel.add(cancelButton);

        add(bottomPanel, BorderLayout.SOUTH);

        // Set frame visible
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Method to fetch notices from the database and display them in the JTextArea
    private void fetchNoticesFromDatabase() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "root");
            String query = "SELECT title, date, category, description FROM notices";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            noticeArea.setText("");

            while (rs.next()) {
                String noticeTitle = rs.getString("title");
                String noticeDate = rs.getString("date");
                String noticeCategory = rs.getString("category");
                String noticeDescription = rs.getString("description");

                noticeArea.append("Title: " + noticeTitle + "\n");
                noticeArea.append("Date: " + noticeDate + "\n");
                noticeArea.append("Category: " + noticeCategory + "\n");
                noticeArea.append("Description: " + noticeDescription + "\n\n");
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching notices from the database.");
        }
    }

    // Method to delete a notice from the database
    private void deleteNotice() {
        String titleToDelete = deleteField.getText().trim();
        if (titleToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a title to delete.");
            return;
        }

        try {
            Conn con = new Conn();
            String query = "DELETE FROM notices WHERE title = ?";
            PreparedStatement pstmt = con.c.prepareStatement(query);
            pstmt.setString(1, titleToDelete);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Notice deleted successfully.");
                fetchNoticesFromDatabase(); // Refresh the notices display
            } else {
                JOptionPane.showMessageDialog(this, "No notice found with the given title.");
            }

            pstmt.close();
            con.c.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting notice from the database.");
        }
    }

    public static void main(String[] args) {
        new NewNotice();
    }
}
